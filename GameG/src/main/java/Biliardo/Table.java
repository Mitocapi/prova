package Biliardo;

import Biliardo.MenuAvvio.Board;
import Biliardo.MenuAvvio.ColorChooser;
import Biliardo.MenuAvvio.ThreadAnimation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

public class Table extends JPanel implements ActionListener {
    public int coloreSelezionato;
    private Timer timer;
    private PoolCue poolCue;
    private Coin coin;
    Collision c=new Collision();

    public static PallaBianca whiteBall; //palla bianca che il giocatore colpisce
    public List<Ball> palleInGioco;
    //public Menu menuGioco;
    public static int BOARD_WIDTH = 1200; //ottimale 1200x800 ma poi è troppo lento
    public static int BOARD_HEIGHT = 800;
    private static int DELAY = 1;
    static int size_const = 3;

    private int dispose1=15; //aumenta se aumenti raggio palline, serve per disporr le palline in buca
    private int dispose2=15;
    public static final int standard_width = 190; //standard dimension of billiard board
    public static final int standard_height = 110;
    public static int x_board = (BOARD_WIDTH / 2) - (standard_width * size_const) / 2; //285 per centrare
    public static int y_board = (BOARD_HEIGHT / 2) - (standard_height * size_const) / 2; //
    public final int pit_dim = 30;


    Point p_shoot=new Point();
    Point[] pit = new Point[6]; //array per le buche
    String[][] pack = new String[2][3];
    BufferedImage background;
    BufferedImage prov;
    Image BilFrame;

    int pit_cont=0;

    BufferedImage wood;
    BufferedImage field;
    BufferedImage whiteDot;
    double angle;
    boolean ballClick=false;
    JButton restart=new JButton();
    int ball_num;
    int ball_startX=700;
    int ball_startY=395;

    JLabel shoot_label=new JLabel();
    int num=0;
    Random random=new Random();
    int turno=0;

    Point initialPos=new Point(x_board+50,BOARD_HEIGHT/2);

    int control=0;
    int cont=0; //se è pari aumenta il delay
    int coin_trigger=0;
    double angle_shoot;

    public Table() {
        initBoard();
    }


    public void initBoard() {

        //menuGioco = new Menu();
        //this.addMouseListener(menuGioco);
        addMouseMotionListener(new Adapt());
        addMouseListener(new Adapt());
        addKeyListener(new Adapt());


        shoot_label.setText("total shoot "+num);
        add(shoot_label);

        restart.setText("restart");
        restart.setLocation(new Point(500,100));
        restart.addActionListener(e -> {
            setVisible(false);
            new RunGame();
        });
        add(restart);

        //provo a mettere le palline piu lontane tra loro
        //cambio 10 con 15

        palleInGioco = new ArrayList<>();
        palleInGioco.add(new Ball(ball_startX, ball_startY,1));                   //1
        palleInGioco.add(new Ball(ball_startX+25,ball_startY+15,14));
        palleInGioco.add(new Ball(ball_startX+25,ball_startY-15,3));



       palleInGioco.add(new Ball(ball_startX+50,ball_startY+30,5));      //4
       palleInGioco.add(new Ball(ball_startX+50,ball_startY,8));              //5
       palleInGioco.add(new Ball(ball_startX+50,ball_startY-30,12));      //6
       palleInGioco.add(new Ball(ball_startX+75,ball_startY+45,10));
       palleInGioco.add(new Ball(ball_startX+75,ball_startY+15,6));
       palleInGioco.add(new Ball(ball_startX+75,ball_startY-15,9));
       palleInGioco.add(new Ball(ball_startX+75,ball_startY-45,7));
       palleInGioco.add(new Ball(ball_startX+100,ball_startY+60,4));
       palleInGioco.add(new Ball(ball_startX+100,ball_startY+30,15));
       palleInGioco.add(new Ball(ball_startX+100,ball_startY,11));
        palleInGioco.add(new Ball(ball_startX+100,ball_startY-30,2));
       palleInGioco.add(new Ball(ball_startX+100,ball_startY-60,13));



        whiteBall = new PallaBianca(initialPos.x, initialPos.y,0);
        Ball.setBallStop(palleInGioco);
        whiteBall.movimentoRimanente=0;

        poolCue = new PoolCue();
        coin=new Coin();

        setPakage(pack);
        setPit(pit);
        loadImage();
        //moveCue();
        //checkCollision();

        timer = new Timer(DELAY, this);
        timer.start();
        setVisible(true);
    }

    private void moveCue(){
        poolCue.move();

        whiteBall.MoveBall();
        for(Ball bilie:palleInGioco){
            bilie.MoveBall();
        }

        repaint();

    }
    private void setPakage(String[][] pack) {

        pack[0][0] = "GameG/src/main/resources/images/backgroundA.jpg";
        pack[0][1] = "GameG/src/main/resources/images/woodA.jpg";
        pack[0][2] = "GameG/src/main/resources/images/campo2.jpg";
        pack[1][0]="GameG/src/main/resources/images/backgroundB.jpg";
        pack[1][1]="GameG/src/main/resources/images/darkWood.jpg";
    }


    private void setPit(Point[] pit) {
        pit[0]=(new Point(x_board-pit_dim/2 +10, y_board-pit_dim/2 +10));
        pit[1]=(new Point(BOARD_WIDTH/2 -pit_dim/2,y_board -pit_dim/2 +10 ));
        pit[2]=(new Point(BOARD_WIDTH-x_board -pit_dim/2 -10,y_board-pit_dim/2 +10));
        pit[3]=(new Point(x_board-pit_dim/2 +10,BOARD_HEIGHT-y_board-pit_dim/2 ));
        pit[4]=(new Point(BOARD_WIDTH/2 -pit_dim/2,BOARD_HEIGHT-y_board-pit_dim/2));
        pit[5]=(new Point(BOARD_WIDTH-x_board -pit_dim/2 -10,BOARD_HEIGHT-y_board-pit_dim/2));
    }





    public void checkPit() {

        for(int i=0;i<pit.length;i++){

            for (Ball b : palleInGioco) {
                if (b.getXposition() <= 20 + pit[i].x && b.getXposition() >= pit[i].x - 20) {
                    if (b.getYposition() <= 20 + pit[i].y && b.getYposition() >= pit[i].y - 20) {
                        System.out.println("buca ok");
                        if(turno%2==0){
                            b.setXposition(1050);
                            b.setYposition(485 - dispose1);
                            dispose1+=30;
                        }
                        else{
                            b.setXposition(1080);
                            b.setYposition(485 - dispose2);
                            dispose2+=30;
                        }
                        b.dx = 0;
                        b.dy = 0;
                        b.setComponenteVelocitaX(0);
                        b.setComponenteVelocitaY(0);
                        b.movimentoRimanente = 0;
                        pit_cont++;
                    }
                }
            }

            if(whiteBall.getXposition()<=10+pit[i].x && whiteBall.getXposition()>=pit[i].x-10){
                if(whiteBall.getYposition()<=10+pit[i].y && whiteBall.getYposition()>=pit[i].y-10){
                    new RunGame();
                }
            }

        }



    }


    public void loadImage() {
        try {
            prov = ImageIO.read(new File("GameG/src/main/resources/images/campoInter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BilFrame = ImageIO.read(new File("GameG/src/main/resources/images/bordoCampoR2.png"));
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

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        shoot_label.setText("total shoot: "+num);

        cont++;
        //System.out.println(cont);

        if(cont%2==0)  //aumenta %1 per rallentare piu lentamente !!!!!!!!!!!!!!!!!!!!!!!!!!!!
            timer.setDelay(DELAY++);

        if(whiteBall.movimentoRimanente<=0) {
            if(Ball.checkMove(palleInGioco))
                setTable(g2d);
        }
        if(control==1){
            setTable(g2d);

            control=0;
        }

        TexturePaint tp2 = new TexturePaint(field, new Rectangle(300, 200));
        g2d.setPaint(tp2);
        g2d.fillRect(x_board+2,y_board+2,standard_width * size_const, standard_height * size_const);


        //check collision con i 4 rettagoli
        g2d.setPaint(Color.darkGray);
        g2d.fillRect(x_board, y_board + 20, 10, 300);
        g2d.fillRect(BOARD_WIDTH - x_board - 10, y_board + 20, 10, 300);
        g2d.fillRect(x_board,y_board,560,10);
        g2d.fillRect(x_board,BOARD_HEIGHT-y_board,560,10);
        //buche
        for (Point point : pit) {
            g2d.fillOval(point.x, point.y, pit_dim, pit_dim);
        }
        g2d.setColor(Color.white);
        g2d.fillOval(BOARD_WIDTH / 2 - 5, BOARD_HEIGHT / 2 - 5, 10, 10);


        Ball.setColore(Color.white);
        ball_num=0;
        whiteBall.MYpaintComponents(g2d,Color.white,ball_num++);
        if(ColorChooser.selected==1) {
            for ( int i=0;i<palleInGioco.size();i++){
                if(i%2==0){
                    palleInGioco.get(i).MYpaintComponents(g2d, Board.colorBall1,ball_num++);
                }else {
                    palleInGioco.get(i).MYpaintComponents(g2d,Board.colorBall2,ball_num++);
                }
            }
        }
        else {
            for ( Ball bilie : palleInGioco){
                bilie.MYpaintComponents(g2d, Color.black,ball_num++);
            }

        }


        Menu.score(g);

        if(whiteBall.movimentoRimanente<=0){
            if (Ball.checkMove(palleInGioco)) {
                //System.out.println("entrat");
                AffineTransform old = g2d.getTransform();
                angle = getAngle(new Point(poolCue.getX(), poolCue.getY()), new Point(whiteBall.posizioneX, whiteBall.posizioneY));
                g2d.rotate(angle, poolCue.getX(), poolCue.getY());
                g2d.drawImage(poolCue.poolCueImg, poolCue.getX() - 150, poolCue.getY() - 15, this);
                g2d.setTransform(old);

                AffineTransform old2 = g2d.getTransform();
                g2d.setColor(Color.white);

                angle = Math.toDegrees(angle) + 360;
                angle = Math.toRadians(angle);
                angle_shoot=angle;
                g2d.rotate(angle, whiteBall.posizioneX, whiteBall.posizioneY);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(whiteBall.posizioneX, whiteBall.posizioneY, whiteBall.posizioneX + 80, whiteBall.posizioneY);
                g2d.setTransform(old2);
            }
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

    public void shootRandom(){
        int xr=random.nextInt(-1500,1500);
        int yr= random.nextInt(-1500,1500);
        System.out.println(xr+" "+yr);
        whiteBall.movimentoRimanente = 3000;

        whiteBall.setComponenteVelocitaX(xr);
        whiteBall.setComponenteVelocitaY(yr);

        whiteBall.dx = 0;
        whiteBall.dy = 0;

        whiteBall.MoveBall();

    }
    public void shoot() {
        int permit=1;

        /*for(Ball b: palleInGioco){
            if(b.isMoving())
                permit=0;
        }*/
        if (whiteBall.movimentoRimanente <= 0 && permit==1) {

            for(Ball b : palleInGioco)
                if(b.isMoving())

            num++;
            if (num % 2 == 0 && Board.game_mode==0) {
                shootRandom();
            } else {
                whiteBall.movimentoRimanente = 3000;

                whiteBall.setComponenteVelocitaX((whiteBall.getXposition() - poolCue.getX()) * 5);
                whiteBall.setComponenteVelocitaY((whiteBall.getYposition() - poolCue.getY()) * 5);
                System.out.println("tiro x "+whiteBall.getComponenteVelocitaX());
                System.out.println("tiro y "+whiteBall.getComponenteVelocitaY());
                whiteBall.dx = 0;
                whiteBall.dy = 0;
                whiteBall.MoveBall();
            }
        }
    }




    private class Adapt implements MouseMotionListener,MouseListener,KeyListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            //poolCue.mouseDragged(e);
        }
        @Override
        public void mouseClicked(MouseEvent e){
            DELAY=1;
            cont=0;
            control=1;
            coin_trigger=1;
            turno++;


            c.stopBall(palleInGioco);
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

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_DOWN){
                DELAY=1;
                timer.setDelay(1);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveCue();
        repaint();
        checkPit();
        c.checkCollision(palleInGioco,whiteBall);
    }
}