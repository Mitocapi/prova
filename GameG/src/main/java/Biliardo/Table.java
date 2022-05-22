package Biliardo;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    final Color table_color= new Color(50,70,20); //colore campo
    final Color edge_color=new Color(170,90,80);

    public final int BOARD_WIDTH=1200;
    public final int BOARD_HEIGHT=800;

    final int standard_width=260; //standard dimension of billiard board
    final int standard_height=160;
    final int oval_dim=20;
    Image table;
    Image whiteDot;

    Table(){

        setVisible(true);
        loadImage();
        initBoard();
        //new game();
    }
    public void initBoard(){
        //stato iniziale del tavolo -> disposizione palline etc
    }
    public void checkCollision(){

    }
    public void loadImage(){
        ImageIcon ii=new ImageIcon("GameG/src/main/resources/images/TavoloFinale2.png");
        table=ii.getImage();
        ImageIcon ii2 = new ImageIcon("GameG/src/main/resources/images/Wdot.png");
        whiteDot=ii2.getImage().getScaledInstance(10,10,0);

    }
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
       // g.fillRect(400-standard_width/2,300-standard_height/2,standard_width*3,standard_height*3);
       // g2d.drawImage(table,200,150,this);
        g.setColor(edge_color);
        g.fillRoundRect(30,30,standard_width*4+60,standard_height*4+60,50,50);
        g.setColor(table_color);
        g.fillRoundRect(60,60,standard_width*4,standard_height*4,10,10);
        g.setColor(Color.white);
        //g2d.fillOval((BOARD_WIDTH/2)-oval_dim/2,(BOARD_HEIGHT/2)-oval_dim/2,oval_dim,oval_dim);
        g2d.drawImage(whiteDot,BOARD_WIDTH/2 -oval_dim/2,BOARD_HEIGHT/2 -oval_dim/2,this);
        g2d.drawImage(table,10,10,this);


        //da eliminare
        g.setColor(Color.black);
        g.drawLine(BOARD_WIDTH/2,BOARD_HEIGHT,BOARD_WIDTH/2,0);
        g.drawLine(0,BOARD_HEIGHT/2,BOARD_WIDTH,BOARD_HEIGHT/2);
    }
}
