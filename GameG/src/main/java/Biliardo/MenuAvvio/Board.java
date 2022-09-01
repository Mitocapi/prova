package Biliardo.MenuAvvio;

import Biliardo.RunGame;
import Biliardo.Table;

import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Board extends JPanel implements Runnable,MouseListener {

    private final int BOARD_WIDTH=800;
    private final int BOARD_HEIGHT=600;
    private final int INITIAL_X=0;
    private final int INITIAL_Y=100;
    private int DELAY=1;

    public Rectangle bottonePlay = new Rectangle(Table.BOARD_WIDTH/4 - 175,125,250,35);
    public Rectangle bottoneChiudi = new Rectangle(Table.BOARD_WIDTH/4 - 175,265,250,35);
    public Rectangle bottoneAltro = new Rectangle(Table.BOARD_WIDTH/4 - 175,195,250,35);
    public static Color colorBall1;
    public static Color colorBall2;
    public static int game_mode;
    private final int IMGW=70;
    private final int IMGH=70;
    private JButton start_button=new JButton();
    private JButton option=new JButton();
    private JButton colour=new JButton();
    private JButton colour2=new JButton();

    private JButton pvp=new JButton();
    private  JButton pvcpu=new JButton();

    private int vely=10;
    private int velx=10;

    Random random;

    private Image wball;
    private Image bball;
    private Thread animator;
    private int x,y;

    public Board(){
        initBoard();
    }



    private void initBoard(){

        setBackground(new Color(40,70,40));
        //addMouseListener(this);

        setLayout(new FlowLayout());

        pvp.setText("player 1 vs player 2");
        pvp.setPreferredSize(new Dimension(200,20));
        pvp.setLocation(10,100);
        pvp.addActionListener(e -> {
            game_mode=1;
        });

        add(pvp);

        pvcpu.setText("player 1 vs computer");
        pvcpu.setPreferredSize(new Dimension(200,20));
        pvcpu.setLocation(new Point(500,500));
        pvcpu.addActionListener(e -> {
            game_mode=0;
        });
        add(pvcpu);

      start_button.setText("start");
        start_button.setLocation(100,100);
        start_button.setPreferredSize(new Dimension(80,20));

        start_button.addActionListener(e -> {
            new RunGame();
            setVisible(false);
        });
        add(start_button);


        colour.setText("color");
        colour.setBackground(Color.blue);
        colour.setPreferredSize(new Dimension(80,20));
        colour.addActionListener(e -> {
            new ColorChooser();
            colorBall1=ColorChooser.colorePalle;
        });
        add(colour);

        colour2.setText("color");
        colour2.setBackground(Color.blue);
        colour2.setPreferredSize(new Dimension(80,20));
        colour2.addActionListener(e -> {
            new ColorChooser();
            colorBall2=ColorChooser.colorePalle;
        });
        add(colour2);

        setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        loadImage();
        x=INITIAL_X;
        y=INITIAL_Y;
    }

    private void loadImage() {


        ImageIcon ii = new ImageIcon("GameG/src/main/resources/images/whiteBallr.png");
        wball = ii.getImage();

    }
    public void addNotify(){
        super.addNotify();
        animator=new Thread(this);
        animator.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMenu(g);
        drawStar(g);

    }
    private void drawStar(Graphics g){
        g.drawImage(wball,x,y,this);
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




    public void drawMenu(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Color coloreSfondo = new Color(10, 100, 1);
        g.setColor(coloreSfondo);
        g.fillRect(0, 0, Table.BOARD_WIDTH, Table.BOARD_HEIGHT);
        g.setColor(Color.black);
        g.fillOval(75, 40, 350, 350);
        Font fontTitoloMenu = new Font("titolo", Font.BOLD, 35);
        g.setFont(fontTitoloMenu);
        g.setColor(Color.RED);
        g.drawString("title", 125, 35);
        g.setColor(Color.darkGray);
        g2d.draw(bottonePlay);
        g2d.draw(bottoneAltro);
        g2d.draw(bottoneChiudi);
        g.fillRect(Table.BOARD_WIDTH / 4 -175 , 125, 250, 35);
        g.fillRect(Table.BOARD_WIDTH / 4 -175, 195, 250, 35);
        g.fillRect(Table.BOARD_WIDTH / 4 -175, 265, 250, 35);
        Font fontbottoni = new Font("arial", Font.BOLD, 30);
        g.setFont(fontbottoni);
        g.setColor(Color.white);
        g.drawString("Gioca", bottonePlay.x+80, bottonePlay.y+30);
        g.drawString("Chiudi", bottoneChiudi.x+80, bottoneChiudi.y+30);
        g.drawString("Seleziona Colore", bottoneAltro.x+5, bottoneAltro.y+30);
    }

    public void mousePressed (MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        if(mouseX>=Table.BOARD_WIDTH/4 -175 && mouseX <= Table.BOARD_WIDTH/4 -175+250 ){
                if (mouseY >= 125 && mouseY <= 160) {
                    removeMouseListener(this);
                    new RunGame();
                    setVisible(false);
                }
                else if (mouseY >= 265 && mouseY <= 265+35) {
                    System.exit(1);
                }
                else if (mouseY >= 195 && mouseY <= 195+35) {
                    new ColorChooser();
                }
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
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
    }
