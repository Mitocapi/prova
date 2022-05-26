package Biliardo;

import javax.swing.*;

public class RunGame extends JFrame {

    RunGame(){
        setTitle("biliardo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800,700);
        add(new Table());
        setLocationRelativeTo(null); //per centrare finestra nello schermo
        setVisible(true);
    }

    public static void main(String[] args) {
        new RunGame();
    }
}
