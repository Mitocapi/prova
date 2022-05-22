package Biliardo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Table extends JPanel {
    final Color table_color= new Color(50,70,20); //colore campo
    //final Color edge_color=new Color(170,90,80);

    public final int BOARD_WIDTH=1200;
    public final int BOARD_HEIGHT=800;
    int size_const=4;
    int f=60;    //fattore per aumentare o diminuire grandezza bordi
    final int standard_width=240; //standard dimension of billiard board
    final int standard_height=140;

    int x_board=(BOARD_WIDTH/2) - (standard_width*size_const)/2; //per centrare
    int y_board=(BOARD_HEIGHT/2) - (standard_height*size_const)/2;
    final int oval_dim=10;

    Image table;
    Image whiteDot;
    BufferedImage wood;
    BufferedImage field;

    Table(){

        setVisible(true);
        loadImage();
        setTable();
        initBoard();
        //new game();
    }
    public void initBoard(){
        //stato iniziale del tavolo -> disposizione palline etc
    }
    public void setTable(){

    }
    public void paintTable(Graphics g){

    }
    public void checkCollision(){

    }
    public void loadImage() {
        ImageIcon ii=new ImageIcon("GameG/src/main/resources/images/TavoloFinale2.png");
        table=ii.getImage();
        ImageIcon ii2 = new ImageIcon("GameG/src/main/resources/images/Wdot.png");
        whiteDot=ii2.getImage().getScaledInstance(10,10,0);


        try {
            wood=ImageIO.read(new File("GameG/src/main/resources/images/textureLegnoS.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            field=ImageIO.read(new File("GameG/src/main/resources/images/biliardoTexture.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    public void paintComponent(Graphics g){

        Graphics2D g2d=(Graphics2D) g;

        TexturePaint tp=new TexturePaint(wood,new Rectangle(50,50));
        g2d.setPaint(tp);
        g2d.fillRoundRect(x_board-f/2,y_board-f/2,standard_width*size_const+f,standard_height*size_const+f,50,50);

        TexturePaint tp2=new TexturePaint(field,new Rectangle(300,200));
        g2d.setPaint(tp2);
        g2d.fillRoundRect(x_board,y_board,standard_width*size_const,standard_height*size_const,50,50);

        g.setColor(Color.white);
        g2d.drawImage(whiteDot,BOARD_WIDTH/2 -oval_dim/2,BOARD_HEIGHT/2 -oval_dim/2,this);







        //da eliminare
        g.setColor(Color.black);
        g.drawLine(BOARD_WIDTH/2,BOARD_HEIGHT,BOARD_WIDTH/2,0);
        g.drawLine(0,BOARD_HEIGHT/2,BOARD_WIDTH,BOARD_HEIGHT/2);
    }
}
