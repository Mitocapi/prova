package Biliardo;

import javax.swing.*;
import java.awt.*;

public class RunGame extends JFrame {


    public RunGame(){
        setTitle("billiard");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200,800);
        setResizable(false);
        setLocationRelativeTo(null); //per centrare finestra nello schermo
        setVisible(true);
        add(new Table());
    }


    public static void main(String[] args) {
        new RunGame();
    }

}
