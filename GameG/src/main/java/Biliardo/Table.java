package Biliardo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.Graphics;

public class Table extends JPanel implements ActionListener {
    public int coloreSelezionato;
    private Timer timer;
    private PoolCue poolCue;
    public static Ball palladiprova;
    public Menu menuGioco;
    public static int BOARD_WIDTH = 1200; //ottimale 1200x800 ma poi è troppo lento
    public static int BOARD_HEIGHT = 800;
    public static int DELAY = 1;
    int size_const = 3;
    //int f = 100;    //fattore per aumentare o diminuire grandezza bordi
    final int standard_width = 190; //standard dimension of billiard board
    final int standard_height = 110;
    int x_board = (BOARD_WIDTH / 2) - (standard_width * size_const) / 2; //per centrare
    int y_board = (BOARD_HEIGHT / 2) - (standard_height * size_const) / 2;
    final int pit_dim = 40;
    boolean coin = false;
    //int x_cue = x_board / 2;
    //int y_cue = y_board / 2;
    //int cue_width = 4;
    //int cue_length = 12;
    BufferedImage bImage;
    //boolean state = true; //true solo a inizio per primo ciclo di immagini
    Point[] pit = new Point[6]; //array per le buche
    String[][] pack = new String[2][3];
    BufferedImage background;
    BufferedImage prov;
    Image table;
    Random rnd;
    BufferedImage wood;
    BufferedImage field;
    BufferedImage whiteDot;
    double angle;

    public Table() {
        initBoard();
    }

    public void initBoard() {

       menuGioco = new Menu();
       this.addMouseListener(menuGioco);
       addMouseMotionListener(new Adapt());
       palladiprova = new Ball(400, 400);
       poolCue = new PoolCue();
       setVisible(true);
       addArea();
       setPakage(pack);
       setPit(pit);
       loadImage();
       moveCue();

       timer = new Timer(DELAY, this);
       timer.start();
}

    private void moveCue(){
        poolCue.move();
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
        pit[0] = (new Point(x_board-pit_dim/3, y_board-pit_dim/3));
        pit[1] = (new Point(BOARD_WIDTH/2 -pit_dim/3,y_board -pit_dim/3 ));
        pit[2]=(new Point(BOARD_WIDTH-x_board -pit_dim/2,y_board-pit_dim/3));
        pit[3]=(new Point(x_board-pit_dim/3,BOARD_HEIGHT-y_board-pit_dim/2-7));
        pit[4]=(new Point(BOARD_WIDTH/2 -pit_dim/3,BOARD_HEIGHT-y_board-pit_dim/2-7));
        pit[5]=(new Point(BOARD_WIDTH-x_board -pit_dim/2,BOARD_HEIGHT-y_board-pit_dim/2-7));
    }




    public void checkCollision() {

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
            //g2d.setStroke(new BasicStroke(130.0f));
            //g2d.drawRect(60,60,BOARD_WIDTH-100,BOARD_HEIGHT-140);
            //cornice
            TexturePaint tp = new TexturePaint(wood, new Rectangle(100, 100));
            g2d.setPaint(tp);
            g2d.setStroke(new BasicStroke(110.0f));
            g2d.drawRoundRect(x_board, y_board, standard_width * size_const, standard_height * size_const, 1, 1);
            //campo
            TexturePaint tp2 = new TexturePaint(field, new Rectangle(300, 200));
            g2d.setPaint(tp2);
            g2d.fillRoundRect(x_board, y_board, standard_width * size_const, standard_height * size_const, 50, 50);
            g2d.setColor(Color.darkGray);

            //check collision con i 4 rettagoli

            g2d.fillRect(x_board, y_board + 20, 10, 285);
            g2d.fillRect(BOARD_WIDTH - x_board - 10, y_board + 20, 10, 285);
            g2d.fillRect(x_board,y_board,560,10);
            g2d.fillRect(x_board,BOARD_HEIGHT-y_board,560,10);
            //TexturePaint tp3 = new TexturePaint(whiteDot, new Rectangle(300, 200));
            //g2d.setPaint(tp3);
            g2d.setColor(Color.white);
            g2d.fillOval(BOARD_WIDTH / 2 - 5, BOARD_HEIGHT / 2 - 5, 10, 10);
            //coin
            if (coin) {
                //disegna coin
            }
            coin = false;
            g2d.setColor(Color.darkGray);
            for (Point point : pit) {
                g2d.fillOval(point.x, point.y, pit_dim, pit_dim);
            }
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
           menuGioco.deleteMenu(g);
           removeMouseListener(menuGioco);
           // loadImage();

            /*se palline sono in movimento fai repaint solo di palle e campo verde
             * se no troppo pesante il repaint e rallenta movimento */

            setTable(g2d);
            if(coloreSelezionato==1) {
                Ball.setColore(Menu.colorePalle);
            }
            else {
            Ball.setColore(Color.black);
            }
            palladiprova.paintComponents(g2d);
            //da eliminare
            g.setColor(Color.black);

           //stecca
            angle=getAngle(new Point(poolCue.getX(), poolCue.getY()),new Point(600,400));
            System.out.println(angle);
            g2d.rotate(angle, poolCue.getX(), poolCue.getY());
            g2d.drawImage(poolCue.poolCueImg,poolCue.getX(),poolCue.getY(),this);


            //g2d.fill3DRect(poolCue.getX(), poolCue.getY(), 5, 100, false);
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

    private class Adapt implements MouseMotionListener,MouseListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            poolCue.mouseDragged(e);


        }
         @Override
         public void mouseClicked(MouseEvent e){


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
            //poolCue.mouseMoved(e);

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        moveCue();

    }
}
