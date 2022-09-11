package Biliardo.MenuAvvio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class carpetChooser extends JFrame{
    private JPanel panelC;
    private JButton greenButton;
    private JButton redButton;
    private JButton button3;
    private JButton blueButton;
    public static int set_carpet =1;

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        //scelta 1 di default
        BufferedImage green = new BufferedImage(20,20,1);
        try {
            green = ImageIO.read(new File("GameG/src/main/resources/images/campo2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //scelta 2
        BufferedImage red = new BufferedImage(20,20,1);
        try {
            red = ImageIO.read(new File("GameG/src/main/resources/images/red.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage blue = new BufferedImage(20,20,1);
        try {
            blue = ImageIO.read(new File("GameG/src/main/resources/images/blue.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage ice = new BufferedImage(20,20,1);
        try {
            ice = ImageIO.read(new File("GameG/src/main/resources/images/ice.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        g2d.drawImage(green, 20, 40,100,100 ,this);
        g2d.drawImage(red, 300, 40,100,100 ,this);
        g2d.drawImage(blue, 20, 300,100,100 ,this);
        g2d.drawImage(ice, 300, 300,100,100 ,this);

    }

    public carpetChooser() {
        setSize(500,500);
        setContentPane(panelC);
        setResizable(false);
        setLocationRelativeTo(null);


        greenButton.addActionListener(e -> {
            set_carpet =1;
            setVisible(false);
        });
        redButton.addActionListener(e -> {
            set_carpet =2;
            setVisible(false);
        });
        blueButton.addActionListener(e -> {
            set_carpet =3;
            setVisible(false);
        });
        button3.addActionListener(e -> {
            set_carpet =4;
            setVisible(false);
        });


        setVisible(true);
    }
}
