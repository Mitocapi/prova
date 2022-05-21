package Biliardo;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    final Color table_color= new Color(50,70,20); //colore campo
    final Color edge_color=new Color(140,80,80);

    public final int BOARD_WIDTH=1200;
    public final int BOARD_HEIGHT=800;

    final int standard_width=260; //standard dimension of billiard board
    final int standard_height=160;
    Image table;
    Image whiteDot;

    Table(){
        //setPreferredSize(new Dimension(800,600));

        setVisible(true);
        loadImage();
        //new game();
    }
    public void collision(){

    }
    public void loadImage(){
        //ImageIcon ii=new ImageIcon("/Users/samuele/IdeaProjects/prova/GameG/src/main/resources/images/campoBil.png");
        //table=ii.getImage().getScaledInstance(standard_width*3,standard_height*3,0);
        ImageIcon ii = new ImageIcon("GameG/src/main/resources/images/Wdot.png");
        whiteDot=ii.getImage().getScaledInstance(100,100,0);

    }
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
       // g.fillRect(400-standard_width/2,300-standard_height/2,standard_width*3,standard_height*3);
       // g2d.drawImage(table,200,150,this);
        g.setColor(edge_color);
        g.fillRoundRect(40,40,standard_width*4+40,standard_height*4+40,16,16);
        g.setColor(table_color);
        g.fillRoundRect(60,60,standard_width*4,standard_height*4,10,10);
        g.setColor(Color.white);
        g.fillOval((BOARD_WIDTH/2),(BOARD_HEIGHT/2),10,10);

        //da eliminare
        g.setColor(Color.black);
        g.drawLine(BOARD_WIDTH/2,BOARD_HEIGHT,BOARD_WIDTH/2,0);
        g.drawLine(0,BOARD_HEIGHT/2,BOARD_WIDTH,BOARD_HEIGHT/2);


        //g.setColor(table_color);
        //g.fillRect(220,120,standard_width*3,standard_height*3);

    }
}
