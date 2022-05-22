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
    int f=100;    //fattore per aumentare o diminuire grandezza bordi
    final int standard_width=190; //standard dimension of billiard board
    final int standard_height=110;

    int x_board=(BOARD_WIDTH/2) - (standard_width*size_const)/2; //per centrare
    int y_board=(BOARD_HEIGHT/2) - (standard_height*size_const)/2;
    final int oval_dim=10;

    Point[] pit=new Point[2];



    BufferedImage background;
    Image table;
    Image whiteDot;
    BufferedImage wood;
    BufferedImage field;

    Table(){

        setVisible(true);
        loadImage();
        setPit(pit);
        setTable();
        initBoard();
        //new game();
    }

    private void setPit(Point[] pit) {
        pit[0]=(new Point(90,90));
        pit[1]=(new Point(BOARD_WIDTH/2 -30,90));
        //pit[2]=(new Point(40,40));
        //pit[3]=(new Point(50,50));
        //pit[4]=(new Point(80,80));
        //pit[5]=(new Point(90,90));
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

        try {
            //background=ImageIO.read(new File("GameG/src/main/resources/images/backgroundB.jpg"));
            //background=ImageIO.read(new File("GameG/src/main/resources/images/tilesB.jpg"));
            background=ImageIO.read(new File("GameG/src/main/resources/images/background4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //wood=ImageIO.read(new File("GameG/src/main/resources/images/darkWoodT.jpg"));
            //wood=ImageIO.read(new File("GameG/src/main/resources/images/metalTexture.jpg"));ù
            wood=ImageIO.read(new File("GameG/src/main/resources/images/wood2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //field=ImageIO.read(new File("GameG/src/main/resources/images/GreenTfinal.jpg"));
            field=ImageIO.read(new File("GameG/src/main/resources/images/campo2.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    public void paintComponent(Graphics g){

        Graphics2D g2d=(Graphics2D) g;

        TexturePaint tpb=new TexturePaint(background,new Rectangle(200,200));
        g2d.setPaint(tpb);
        g2d.fillRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);

        //cornice
        TexturePaint tp=new TexturePaint(wood,new Rectangle(100,100));
        g2d.setPaint(tp);
        g2d.fillRoundRect(x_board-f/2,y_board-f/2,standard_width*size_const+f,standard_height*size_const+f,50,50);

        //campo
        TexturePaint tp2=new TexturePaint(field,new Rectangle(300,200));
        g2d.setPaint(tp2);
        g2d.fillRoundRect(x_board,y_board,standard_width*size_const,standard_height*size_const,50,50);

        g.setColor(Color.white);
        g2d.drawImage(whiteDot,BOARD_WIDTH/2 -oval_dim/2,BOARD_HEIGHT/2 -oval_dim/2,this);

        g2d.setColor(Color.black);

        for (Point point : pit) {
            //g2d.fillOval(point.x, point.y, 60, 60);
        }







        //da eliminare
        //g.setColor(Color.black);
        //g.drawLine(BOARD_WIDTH/2,BOARD_HEIGHT,BOARD_WIDTH/2,0);
        //g.drawLine(0,BOARD_HEIGHT/2,BOARD_WIDTH,BOARD_HEIGHT/2);
    }
}
