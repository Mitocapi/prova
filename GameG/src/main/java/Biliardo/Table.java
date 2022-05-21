package Biliardo;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    final Color table_color= new Color(10,100,8);
    final int standard_width=260;
    final int standard_height=160;
    Image table;

    Table(){
        //setPreferredSize(new Dimension(800,600));

        setVisible(true);
        loadImage();
        //new game();
    }

    public void loadImage(){
        ImageIcon ii=new ImageIcon("/Users/samuele/IdeaProjects/prova/GameG/src/main/resources/images/campoBil.png");
        table=ii.getImage().getScaledInstance(standard_width*3,standard_height*3,0);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        g.setColor(table_color);
        //g.fillRect(400-standard_width/2,300-standard_height/2,standard_width*3,standard_height*3);
        g2d.drawImage(table,200,150,this);
    }
}
