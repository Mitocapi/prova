package Biliardo.MenuAvvio;

import Biliardo.RunGame;
import Biliardo.Table;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel implements Runnable {

    private final int BOARD_WIDTH=1000;
    private final int BOARD_HEIGHT=800;
    private final int INITIAL_X=0;
    private final int INITIAL_Y=100;
    private final int DELAY=10;

    private final int IMGW=161;
    private final int IMGH=185;
    private JButton start_button=new JButton();
    private JButton option;
    private JButton colour=new JButton();


    private int vely=1;
    private int velx=1;

    Random random;

    private Image star;
    private Image rareCandy;
    private Thread animator;
    private int x,y;

    public Board(){
        initBoard();
    }

    public static void run2() {
        Board ex = new Board();
        ex.setVisible(true);
    }

    private void initBoard(){
        setBackground(new Color(40,70,40));

        start_button.setText("start");
        start_button.setPreferredSize(new Dimension(80,20));
        start_button.setLocation(245,200);
        start_button.addActionListener(e -> {
            new RunGame();
            setVisible(false);
        });
        add(start_button);

        colour.setText("color");
        colour.setPreferredSize(new Dimension(80,20));
        colour.addActionListener(e -> {
            new ColorChooser();
        });
        add(colour);




        setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        loadImage();
        x=INITIAL_X;
        y=INITIAL_Y;
    }

    private void loadImage() {


        ImageIcon ii = new ImageIcon("GameG/src/main/resources/images/8ball2r.png");
        star = ii.getImage();

    }
    public void addNotify(){
        super.addNotify();
        animator=new Thread(this);
        animator.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawStar(g);
    }
    private void drawStar(Graphics g){
        g.drawImage(star,x,y,this);
        Toolkit.getDefaultToolkit().sync();
    }

    private void cycle(){
        x+=velx;
        y+=vely;

        if(y>BOARD_HEIGHT-IMGH){
            vely=-1;
        }
        if(y<0){
            vely=1;
        }
        if(x>BOARD_WIDTH-IMGW){
            velx=-1;
        }
        if(x<0){
            velx=1;
        }

    }




    @Override
    public void run() {
        long bTime,timeDiff,sleep;
        bTime=System.currentTimeMillis();

        while(true){
            cycle();
            repaint();
            timeDiff=System.currentTimeMillis()-bTime;
            sleep=DELAY-timeDiff;
            if(sleep<0){
                sleep=2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {

                String msg = String.format("Thread interrupted: %s", e.getMessage());

                JOptionPane.showMessageDialog(this, msg, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            bTime = System.currentTimeMillis();
        }

    }

}
