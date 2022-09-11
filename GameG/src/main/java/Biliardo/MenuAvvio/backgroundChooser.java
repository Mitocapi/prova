package Biliardo.MenuAvvio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class backgroundChooser extends JFrame {
    private JPanel panelB;
    private JButton whiteTilesButton;
    private JButton externButton;
    private JButton parquetButton;
    private JButton tilesButton;

    public static int set_background=1;

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        //scelta 1 di default
        BufferedImage tiles = new BufferedImage(20,20,1);
        try {
            tiles = ImageIO.read(new File("GameG/src/main/resources/images/backgroundA.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //scelta 2
        BufferedImage parquet = new BufferedImage(20,20,1);
        try {
            parquet = ImageIO.read(new File("GameG/src/main/resources/images/backgroundB.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage white_tiles = new BufferedImage(20,20,1);
        try {
            white_tiles = ImageIO.read(new File("GameG/src/main/resources/images/tilesB.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage outside = new BufferedImage(20,20,1);
        try {
            outside = ImageIO.read(new File("GameG/src/main/resources/images/outsideTiles.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        g2d.drawImage(tiles, 20, 40,100,100 ,this);
        g2d.drawImage(parquet, 300, 40,100,100 ,this);
        g2d.drawImage(white_tiles, 20, 300,100,100 ,this);
        g2d.drawImage(outside, 300, 300,100,100 ,this);

    }

    public backgroundChooser() {
        setSize(500,500);
        setContentPane(panelB);
        setResizable(false);
        setLocationRelativeTo(null);


        tilesButton.addActionListener(e -> {
            set_background=1;
            setVisible(false);
        });
        parquetButton.addActionListener(e -> {
            set_background=2;
            setVisible(false);
        });
        whiteTilesButton.addActionListener(e -> {
            set_background=3;
            setVisible(false);
        });
        externButton.addActionListener(e -> {
            set_background=4;
            setVisible(false);
        });


        setVisible(true);
    }

}
