package immaginiMovimento.entities.inputs;


import immaginiMovimento.entities.GameBas.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static immaginiMovimento.entities.utilz.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel=gamePanel;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                gamePanel.setDirection(UP);
                gamePanel.setMoving(true);
            }
            case KeyEvent.VK_A -> {
                gamePanel.setDirection(LEFT);
                gamePanel.setMoving(true);
            }
            case KeyEvent.VK_S -> {
                gamePanel.setDirection(DOWN);
                gamePanel.setMoving(true);
            }
            case KeyEvent.VK_D -> {
                gamePanel.setDirection(RIGHT);
                gamePanel.setMoving(true);
            }
            case KeyEvent.VK_SPACE -> {
                gamePanel.setJumping(true);
            }
            case KeyEvent.VK_Q -> {
                gamePanel.setAttacking(true);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D -> {
                gamePanel.setMoving(false);
            }
            case KeyEvent.VK_SPACE -> {
                gamePanel.setJumping(false);
            }
            case KeyEvent.VK_Q -> {
                gamePanel.setAttacking(false);
            }
        }


    }
}
