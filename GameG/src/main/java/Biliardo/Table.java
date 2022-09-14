package Biliardo;

import Biliardo.MenuAvvio.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    JDialog jd=new JDialog();
    JLabel jl=new JLabel();
    int whiteBall_state=1;


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
    public static String[][] pack = new String[4][5];
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

        //restart.setText("restart");
        //restart.setLocation(new Point(500,100));
        //restart.addActionListener(e -> {
        //    setVisible(false);
        //    new RunGame();
        //});
        //add(restart);

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
        pack[0][2]="GameG/src/main/resources/images/darkWood.jpg";
        pack[0][3]="GameG/src/main/resources/images/textureLegno.jpg";
        pack[0][4]="GameG/src/main/resources/images/metalTexture.jpg";
        pack[1][1]="GameG/src/main/resources/images/backgroundA.jpg";
        pack[1][2] = "GameG/src/main/resources/images/backgroundB.jpg";
        pack[1][3]="GameG/src/main/resources/images/tilesB.jpg";
        pack[1][4]="GameG/src/main/resources/images/outsideTiles.jpg";
        pack[2][1]="GameG/src/main/resources/images/campo2.jpg";
        pack[2][2]="GameG/src/main/resources/images/red.jpg";
        pack[2][3]="GameG/src/main/resources/images/blue.jpg";
        pack[2][4]="GameG/src/main/resources/images/ice.jpg";
        pack[3][1]="GameG/src/main/resources/images/stecca.png";
        pack[3][2]="GameG/src/main/resources/images/stecca2.png";

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
                if (b.getXposition() <= 25 + pit[i].x && b.getXposition() >= pit[i].x - 25) {
                    if (b.getYposition() <= 25 + pit[i].y && b.getYposition() >= pit[i].y - 25) {
                        try {
                            shootSound.holeSound();
                        } catch (UnsupportedAudioFileException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (LineUnavailableException e) {
                            e.printStackTrace();
                        }
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

            if(whiteBall.getXposition()<=22+pit[i].x && whiteBall.getXposition()>=pit[i].x-4){
                if(whiteBall.getYposition()<=22+pit[i].y && whiteBall.getYposition()>=pit[i].y-4){
                    JDialog jj=new JDialog();
                    jj.setLocationRelativeTo(null);
                    jj.setSize(400,80);
                    JLabel jjl=new JLabel("foul: white ball in pit click inside the field to dispose the ball");
                    jj.add(jjl);
                    jj.setVisible(true);
                    jj.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    whiteBall_state=0;
                    whiteBall.setComponenteVelocitaX(0);
                    whiteBall.setComponenteVelocitaY(0);
                    whiteBall.setMovimentoRimanente(0);
                    whiteBall.setYposition(-20);
                    whiteBall.setXposition(-20);
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
            background = ImageIO.read(new File(pack[1][backgroundChooser.set_background]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wood = ImageIO.read(new File(pack[0][fieldChooser.set_board]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            field = ImageIO.read(new File(pack[2][carpetChooser.set_carpet]));
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
        g2d.fillRect(BOARD_WIDTH - x_board - 8, y_board + 20, 10, 300);
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




        if(whiteBall.movimentoRimanente<=0){
            if (Ball.checkMove(palleInGioco)) {
                //System.out.println("entrat");
                AffineTransform old = g2d.getTransform();
                angle = getAngle(new Point(poolCue.getX(), poolCue.getY()), new Point(whiteBall.posizioneX, whiteBall.posizioneY));

                if(Board.game_mode==0 && num%2!=0){
                    //non disegna
                }
                else{
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

                    int accuracy=0;
                    if(cueChooser.set_cue==2)
                        accuracy=40;
                    g2d.drawLine(whiteBall.posizioneX, whiteBall.posizioneY, whiteBall.posizioneX + 80+accuracy, whiteBall.posizioneY);
                    g2d.setTransform(old2);
                }


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
        if(xr<100 && xr>-100)
            xr=100;
        if(yr<100 && yr>-100)
            yr=100;
        whiteBall.movimentoRimanente = 3000;

        whiteBall.setComponenteVelocitaX(xr);
        whiteBall.setComponenteVelocitaY(yr);

        whiteBall.dx = 0;
        whiteBall.dy = 0;

        whiteBall.MoveBall();

    }
    int pot=0;
    public void shoot() {
        int permit=1;

        /*for(Ball b: palleInGioco){
            if(b.isMoving())
                permit=0;
        }*/
        num++;
        if (whiteBall.movimentoRimanente <= 0 && permit==1) {

            if (num % 2 == 0 && Board.game_mode==0) {
                shootRandom();
            } else {
                if(cueChooser.set_cue==1){
                    pot=500;
                }
                whiteBall.movimentoRimanente = 4000+pot;

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
    int msg_index=0;
    public void helper(){
        jd.setTitle("helper");
        Random r=new Random();
        String [] msg=new String[10];
        msg[0]="ball initial velocity is calculated between the distance of the cue and the ball";
        msg[1]="click when the cue is far from the white ball to increase shoot strength";
        msg[2]="click when the cue is near the white ball for a weak shot";
        msg[3]="try to hit the side of a coloured ball to give it direction";
        msg[4]="hit the wall if the white ball is clutched";
        msg[5]="don't pool the white ball";
        msg[6]="before the shoot you can see the preview of the direction";
        msg[7]="sometimes you need to shoot stronger";
        msg[8]="take your time to take the perfect direction";
        msg[9]="now you know everything about this game";
        jl.setText(msg[msg_index]);

        msg_index++;

        if(msg_index>9)
            msg_index=0;

        jd.add(jl);
        jd.setVisible(true);
        jd.setLocation(170,40);

        jd.setSize(new Dimension(500,100));



        jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }



    private class Adapt implements MouseMotionListener,MouseListener,KeyListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            //poolCue.mouseDragged(e);
        }

        @Override
        public void mouseClicked(MouseEvent e){
            if(whiteBall_state==1){
            DELAY=1;
            cont=0;
            control=1;
            coin_trigger=1;
            turno++;


            c.stopBall(palleInGioco);

            if(Board.game_mode==3){
                jd.setVisible(false);
                helper();
                control=1;
            }

            try {
                shootSound.shSound();
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
            }else{

                whiteBall.setXposition(e.getX());
                whiteBall.setYposition(e.getY());

                whiteBall_state=1;

            }


            shoot();
            //
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