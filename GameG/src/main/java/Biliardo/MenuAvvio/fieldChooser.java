package Biliardo.MenuAvvio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class fieldChooser extends JFrame {
    private JPanel chPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        BufferedImage i1 = new BufferedImage(20,20,1);
        try {
            i1 = ImageIO.read(new File("GameG/src/main/resources/images/woodA.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        g2d.drawImage(i1, 20, 40,100,100 ,this);


    }

    public fieldChooser() {
        setSize(500,500);
        setContentPane(chPanel);
        setResizable(false);


        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new fieldChooser();
    }

}
