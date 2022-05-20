package immaginiMovimento.entities.GameBas;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel){


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        setSize(1280,800);
        setLocationRelativeTo(null); //centra la finestra
        setResizable(false);
        pack();
        setVisible(true);
    }

}
