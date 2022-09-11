package Biliardo.MenuAvvio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class fieldChooser extends JFrame {
    private JPanel chPanel;
    private JButton lightWoodButton;
    private JButton darkWoodButton;
    private JButton fantasyWoodButton;
    private JButton metalButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    public static int set_board=1;



    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        //scelta 1 di default
        BufferedImage lightWood = new BufferedImage(20,20,1);
        try {
            lightWood = ImageIO.read(new File("GameG/src/main/resources/images/woodA.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //scelta 2
        BufferedImage darkWood = new BufferedImage(20,20,1);
        try {
            darkWood = ImageIO.read(new File("GameG/src/main/resources/images/darkWood.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage fantasyWood = new BufferedImage(20,20,1);
        try {
            fantasyWood = ImageIO.read(new File("GameG/src/main/resources/images/textureLegno.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage metal = new BufferedImage(20,20,1);
        try {
            metal = ImageIO.read(new File("GameG/src/main/resources/images/metalTexture.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        g2d.drawImage(lightWood, 20, 40,100,100 ,this);
        g2d.drawImage(darkWood, 300, 40,100,100 ,this);
        g2d.drawImage(fantasyWood, 20, 300,100,100 ,this);
        g2d.drawImage(metal, 300, 300,100,100 ,this);

    }

    public fieldChooser() {
        setSize(500,500);
        setContentPane(chPanel);
        setResizable(false);
        setLocationRelativeTo(null);


        lightWoodButton.addActionListener(e -> {
            set_board=1;
            setVisible(false);
        });
        darkWoodButton.addActionListener(e -> {
            set_board=2;
            setVisible(false);
        });
        fantasyWoodButton.addActionListener(e -> {
            set_board=3;
            setVisible(false);
        });
        metalButton.addActionListener(e -> {
            set_board=4;
            setVisible(false);
        });


        setVisible(true);
    }



}
