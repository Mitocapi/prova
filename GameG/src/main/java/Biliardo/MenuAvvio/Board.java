package Biliardo.MenuAvvio;

import Biliardo.RunGame;
import Biliardo.Table;
import com.formdev.flatlaf.FlatDarkLaf;

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

    public Rectangle bottoneP1VSP2 = new Rectangle(255,152,290,40);
    public Rectangle bottoneP1VSCOM = new Rectangle(255,222,290,40);
    public Rectangle bottoneAllenamento = new Rectangle(255,292,290,40 );
    public Rectangle bottonePersonalizza = new Rectangle(255,362,290,40 );
    public Rectangle bottoneChiudi = new Rectangle(255,432,290,40);
    public Rectangle bottoneIndietro = new Rectangle(x=50,10,25,25);

    public static int chosenMenu = 0;
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
        addMouseListener(this);

        setLayout(new FlowLayout());
/*
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
        add(colour2); */

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
    public void run()  {
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
        g.fillOval(150, 60, 500, 500);
        Font fontTitoloMenu = new Font("titolo", Font.BOLD, 35);
        g.setFont(fontTitoloMenu);
        g.setColor(Color.RED);
        g.drawString("Biliardo", 325, 35);
        g.setColor(Color.darkGray);
        g2d.draw(bottoneP1VSP2);
        g2d.draw(bottoneP1VSCOM);
        g2d.draw(bottoneAllenamento);
        g2d.draw(bottonePersonalizza);
        g2d.draw(bottoneChiudi);
        g.fillRect(bottoneP1VSP2.x, bottoneP1VSP2.y, bottoneP1VSP2.width, bottoneP1VSP2.height);
        g.fillRect(bottoneP1VSCOM.x, bottoneP1VSCOM.y, bottoneP1VSCOM.width, bottoneP1VSCOM.height);
        g.fillRect(bottoneAllenamento.x, bottoneAllenamento.y, bottoneAllenamento.width, bottoneAllenamento.height);
        g.fillRect(bottonePersonalizza.x, bottonePersonalizza.y, bottonePersonalizza.width, bottonePersonalizza.height);
        g.fillRect(bottoneChiudi.x, bottoneChiudi.y, bottoneChiudi.width, bottoneChiudi.height);
        Font fontbottoni = new Font("arial", Font.BOLD, 30);
        g.setFont(fontbottoni);
        g.setColor(Color.white);
        if(chosenMenu==0) {
            g.drawString("P1 VS P2", bottoneP1VSP2.x + 75, bottoneP1VSP2.y + 30);
            g.drawString("P1 VS COM", bottoneP1VSCOM.x + 70, bottoneP1VSCOM.y + 30);
            g.drawString("ALLENAMENTO", bottoneAllenamento.x + 30, bottoneAllenamento.y + 30);
            g.drawString("PERSONALIZZA", bottonePersonalizza.x + 30, bottonePersonalizza.y + 30);
            g.drawString("CHIUDI", bottoneChiudi.x + 93, bottoneChiudi.y + 30);
        }
        else{
            g.setColor(Color.darkGray);
            g2d.draw(bottoneIndietro);
            g.fillRect(bottoneIndietro.x, bottoneIndietro.y, bottoneIndietro.width, bottoneIndietro.height);
            g.setColor(Color.white);
            g.drawString("< ", bottoneIndietro.x+3, bottoneIndietro.y+22);
            g.drawString("SELEZ. TAPPETO", bottoneP1VSP2.x + 20, bottoneP1VSP2.y + 30);
            g.drawString("SELEZ. SFONDO", bottoneP1VSCOM.x + 25, bottoneP1VSCOM.y + 30);
            g.drawString("SELEZ. TAVOLO", bottoneAllenamento.x + 25, bottoneAllenamento.y + 30);
            g.drawString("COLORE P1", bottonePersonalizza.x + 55, bottonePersonalizza.y + 30);
            g.drawString("COLORE P2/COM", bottoneChiudi.x + 20, bottoneChiudi.y + 30);
        }
    }

    public void mousePressed (MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        if(chosenMenu==0) {
            if (mouseX >= bottoneP1VSP2.x && mouseX <= bottoneP1VSP2.x + bottoneP1VSP2.width) {
                if (mouseY >= bottoneP1VSP2.y && mouseY <= bottoneP1VSP2.y + bottoneP1VSP2.height) {
                    removeMouseListener(this);
                    game_mode = 1;
                    setVisible(false);
                    new cueChooser();

                } else if (mouseY >= bottoneP1VSCOM.y && mouseY <= bottoneP1VSCOM.y + bottoneP1VSCOM.height) {
                    removeMouseListener(this);
                    game_mode = 0;
                    setVisible(false);
                    new cueChooser();

                } else if (mouseY >= bottoneAllenamento.y && mouseY <= bottoneAllenamento.y + bottoneAllenamento.height) {

                    removeMouseListener(this);
                    game_mode=3;
                    setVisible(false);
                    new cueChooser();


                } else if (mouseY >= bottonePersonalizza.y && mouseY <= bottonePersonalizza.y + bottonePersonalizza.height) {

                    chosenMenu = 1;
                    repaint();

                } else if (mouseY >= bottoneChiudi.y && mouseY <= bottoneChiudi.y + bottoneChiudi.height) {

                    System.exit(1);
                }
            }
        }
        else {
            if (mouseX >= bottoneP1VSP2.x && mouseX <= bottoneP1VSP2.x + bottoneP1VSP2.width) {
                if (mouseY >= bottoneP1VSP2.y && mouseY <= bottoneP1VSP2.y + bottoneP1VSP2.height) {

                    new carpetChooser();

                } else if (mouseY >= bottoneP1VSCOM.y && mouseY <= bottoneP1VSCOM.y + bottoneP1VSCOM.height) {
                    game_mode=0;

                    try {
                        UIManager.setLookAndFeel(new FlatDarkLaf());
                    } catch (Exception ex) {
                        System.err.println("Failed to initialize LaF");
                    }
                    SwingUtilities.invokeLater(backgroundChooser::new);

                } else if (mouseY >= bottoneAllenamento.y && mouseY <= bottoneAllenamento.y + bottoneAllenamento.height) {


                    new fieldChooser();

                } else if (mouseY >= bottonePersonalizza.y && mouseY <= bottonePersonalizza.y + bottonePersonalizza.height) {

                    new ColorChooser();
                    colorBall1=ColorChooser.colorePalle;

                } else if (mouseY >= bottoneChiudi.y && mouseY <= bottoneChiudi.y + bottoneChiudi.height) {

                    new ColorChooser();
                    colorBall1=ColorChooser.colorePalle;

                }
            }
            else if (mouseX >= bottoneIndietro.x && mouseX <= bottoneIndietro.x + bottoneIndietro.width){
                if (mouseY >= bottoneIndietro.y && mouseY <= bottoneIndietro.y+ bottoneIndietro.height){
                    chosenMenu = 0;
                    repaint();
                }
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
