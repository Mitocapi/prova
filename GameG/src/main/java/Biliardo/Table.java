package Biliardo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.util.List;

public class Table extends JPanel implements ActionListener {
    public int coloreSelezionato;
    private Timer timer;
    private PoolCue poolCue;
    public static PallaBianca whiteBall; //palla bianca che il giocatore colpisce
    public List<Ball> palleInGioco;
    public Menu menuGioco;
    public static int BOARD_WIDTH = 1200; //ottimale 1200x800 ma poi è troppo lento
    public static int BOARD_HEIGHT = 800;
    public static int DELAY = 1;
    int size_const = 3;

    final int standard_width = 190; //standard dimension of billiard board
    final int standard_height = 110;
    int x_board = (BOARD_WIDTH / 2) - (standard_width * size_const) / 2; //per centrare
    int y_board = (BOARD_HEIGHT / 2) - (standard_height * size_const) / 2;
    final int pit_dim = 40;
    boolean coin = false;

    Point[] pit = new Point[6]; //array per le buche
    String[][] pack = new String[2][3];
    BufferedImage background;
    BufferedImage prov;
    Random rnd;
    BufferedImage wood;
    BufferedImage field;
    BufferedImage whiteDot;
    double angle;
    boolean ballClick=false;

    Point initialPos=new Point(x_board+50,BOARD_HEIGHT/2);

    int control=0;

    public Table() {
        initBoard();
    }

    public void initBoard() {

        menuGioco = new Menu();
        this.addMouseListener(menuGioco);
        addMouseMotionListener(new Adapt());
        addMouseListener(new Adapt());

        palleInGioco = new ArrayList<>();
        for (int i=0; i<5;i++){
            if(i==0){
                palleInGioco.add(new Ball(700, 395));
            }
            else if(i==1){
                palleInGioco.add(new Ball(700 + 20, 395 - 10));
                palleInGioco.add(new Ball(700 + 20, 395 + 10));
            }
            else if(i%2==0) {
                int linee = 0;
                while(2* linee < i+1) {
                    palleInGioco.add(new Ball(700 + i * 20, 395 + linee * 20));
                    palleInGioco.add(new Ball(700 + i * 20, 395 - linee * 20));
                    linee++;
                }
            }
            else {
                int linee = 0;
                while(2* linee < i+1) {
                    palleInGioco.add(new Ball(700 + i * 20, 395 +10+ linee * 20));
                    palleInGioco.add(new Ball(700 + i * 20, 395 -10- linee * 20));
                    linee++;
                }
            }
        } //EVOCA LE CABBO DI PALLINE
        whiteBall = new PallaBianca(600, 395);

        poolCue = new PoolCue();
        setVisible(true);
        addArea();
        setPakage(pack);
        setPit(pit);
        loadImage();
        moveCue();
        checkCollision();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void moveCue(){
        poolCue.move();
        whiteBall.MoveBall();
      /*  for(Ball bilie:palleInGioco){
            bilie.MoveBall();
        }*/  //questo coso servirebbe a far muovere tutte le altre palle ma al momento le uniche palle che si muovono
        // sono le mie e la sua funzione è quella di regalare del lag ai passanti... provo a risolvere...
        repaint();

    }

    private void addArea() {
    }

    private void setPakage(String[][] pack) {
        /*pacchetti grafici
        0->background
        1->cornice
        2->campo
        3-buche (da mettere!)
        4->spondine (da mettere forse)*/
        pack[0][0] = "GameG/src/main/resources/images/backgroundA.jpg";
        pack[0][1] = "GameG/src/main/resources/images/woodA.jpg";
        pack[0][2] = "GameG/src/main/resources/images/campo2.jpg";
        pack[1][0]="GameG/src/main/resources/images/backgroundB.jpg";
    }


    private void setPit(Point[] pit) {
        pit[0] = (new Point(x_board-pit_dim/2, y_board-pit_dim/2));
        pit[1] = (new Point(BOARD_WIDTH/2 -pit_dim/2,y_board -pit_dim/2 ));
        pit[2]=(new Point(BOARD_WIDTH-x_board -pit_dim/2,y_board-pit_dim/2));
        pit[3]=(new Point(x_board-pit_dim/2,BOARD_HEIGHT-y_board-pit_dim/2-7));
        pit[4]=(new Point(BOARD_WIDTH/2 -pit_dim/2,BOARD_HEIGHT-y_board-pit_dim/2-7));
        pit[5]=(new Point(BOARD_WIDTH-x_board -pit_dim/2,BOARD_HEIGHT-y_board-pit_dim/2-7));
    }




    public void checkCollision() {

        for(int i=0;i<pit.length;i++){
            if(whiteBall.getXposition()<=10+pit[i].x && whiteBall.getXposition()>=pit[i].x-10){
                if(whiteBall.getYposition()<=10+pit[i].y && whiteBall.getYposition()>=pit[i].y-10){
                    whiteBall.movimentoRimanente=0;
                    whiteBall.posizioneX=initialPos.x;
                    whiteBall.posizioneY=initialPos.y;
                }
            }

        }
        for(Ball uno : palleInGioco){
            for(Ball dos : palleInGioco){
                if(uno.getYposition()!=dos.getYposition()||uno.getXposition()!=dos.getXposition()){
                    int distanzax = uno.getXposition()-dos.getXposition();
                    int distanzay = uno.getYposition()-dos.getYposition();
                    if(Math.sqrt(Math.pow(distanzax,2)+Math.pow(distanzay,2))>=uno.getRadius()){
                        uno.hitABall(uno,dos);
                    }
                }
            }
        }

        for(Ball dos : palleInGioco){
                int distanzax = whiteBall.getXposition()-dos.getXposition();
                int distanzay = whiteBall.getYposition()-dos.getYposition();
                if(Math.sqrt(Math.pow(distanzax,2)+Math.pow(distanzay,2))<=whiteBall.getRadius()){
                    whiteBall.hitABall(whiteBall,dos);

            }
        }
    }


    public void generateCoin(){
        //da chiamare dopo ogni tiro, 50% possibilità spawn casuale moneta sul campo
        int r;
        r=rnd.nextInt(0,1);
        if(r==1){
            coin=true;
        }
        coin=false;
    }

    public void loadImage() {
        try {
            prov = ImageIO.read(new File("GameG/src/main/resources/images/campoInter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            background = ImageIO.read(new File(pack[0][0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wood = ImageIO.read(new File(pack[0][1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            field = ImageIO.read(new File(pack[0][2]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            whiteDot = ImageIO.read(new File("GameG/src/main/resources/images/white.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setTable(Graphics2D g2d) {
        //floor
        TexturePaint tpb = new TexturePaint(background, new Rectangle(300, 300));
        g2d.setPaint(tpb);

        g2d.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        TexturePaint tp = new TexturePaint(wood, new Rectangle(100, 100));
        g2d.setPaint(tp);
        g2d.setStroke(new BasicStroke(110.0f));
        g2d.drawRoundRect(x_board, y_board, standard_width * size_const, standard_height * size_const, 1, 1);

        g2d.setColor(Color.darkGray);

        //coin
        if (coin) {
            //disegna coin
        }
        coin = false;
        g2d.setColor(Color.darkGray);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(RunGame.statoAttuale== RunGame.STATO.MENU) {
            menuGioco.drawMenu(g);
        }
        else if(RunGame.statoAttuale== RunGame.STATO.COLORI){
            menuGioco.optionColor(g);
            coloreSelezionato=1;
        }
        else {
            //menuGioco.deleteMenu(g);
            removeMouseListener(menuGioco);
            // loadImage();

            /*se palline sono in movimento fai repaint solo di palle e campo verde
             * se no troppo pesante il repaint e rallenta movimento */


            if(whiteBall.movimentoRimanente<=0) {
                setTable(g2d);
            }
            if(control==1){
                setTable(g2d);
                control=0;
            }

            TexturePaint tp2 = new TexturePaint(field, new Rectangle(300, 200));
            g2d.setPaint(tp2);
            g2d.fillRoundRect(x_board, y_board, standard_width * size_const, standard_height * size_const, 50, 50);

            //check collision con i 4 rettagoli
            g2d.setPaint(Color.darkGray);
            g2d.fillRect(x_board, y_board + 20, 10, 285);
            g2d.fillRect(BOARD_WIDTH - x_board - 10, y_board + 20, 10, 285);
            g2d.fillRect(x_board,y_board,560,10);
            g2d.fillRect(x_board,BOARD_HEIGHT-y_board,560,10);
            //buche
            for (Point point : pit) {
                g2d.fillOval(point.x, point.y, pit_dim, pit_dim);
            }
            g2d.setColor(Color.white);
            g2d.fillOval(BOARD_WIDTH / 2 - 5, BOARD_HEIGHT / 2 - 5, 10, 10);

            if(coloreSelezionato==1) {
                Ball.setColore(Menu.colorePalle);
            }
            else {
                Ball.setColore(Color.black);
            }
            for ( Ball bilie : palleInGioco){
                bilie.paintComponents(g);
            }
            Ball.setColore(Color.white);
            whiteBall.paintComponents(g2d);

            if(whiteBall.movimentoRimanente<=0){
                angle=getAngle(new Point(poolCue.getX(), poolCue.getY()),new Point(whiteBall.posizioneX, whiteBall.posizioneY));
                g2d.rotate(angle, poolCue.getX(), poolCue.getY());
                g2d.drawImage(poolCue.poolCueImg, poolCue.getX()-150, poolCue.getY()-15,this);
            }

            checkCollision();



        }
    }

    public double getAngle(Point p1,Point p2){
        double dist=Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
        double dist2=Math.abs(p1.y-p2.y);
        double an=Math.asin(dist2/dist);

        if(p1.x<p2.x && p1.y<p2.y)
            return an;
        if(p1.x>p2.x && p1.y<p2.y)
            return Math.PI-an;
        if(p1.x<p2.x && p1.y>p2.y)
            return -an;
        else
            return -(Math.PI-an);


    }

    public void shoot(){

        if(whiteBall.movimentoRimanente<=0){

            whiteBall.setMovimentoRimanenteX((whiteBall.getXposition()- poolCue.getX())*500);
            whiteBall.setMovimentoRimanenteY((whiteBall.getYposition()- poolCue.getY())*500);
            System.out.println("entrato");
            whiteBall.movimentoRimanente=1000;
            whiteBall.MoveBall();
        }

    }


    private class Adapt implements MouseMotionListener,MouseListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            //poolCue.mouseDragged(e);
        }
        @Override
        public void mouseClicked(MouseEvent e){
            control=1;

            shoot();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            poolCue.mouseMoved(e);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveCue();

    }
}