package Biliardo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class PoolCue  {
    private int dx_cue;
    private int dy_cue;
    private int x_cue=100;
    private int y_cue=100;

    Image poolCueImg;

    public PoolCue(){
        loadImage();
    }

    public void loadImage(){
        try {
            poolCueImg = ImageIO.read(new File("GameG/src/main/resources/images/stecca.png"));
            poolCueImg=poolCueImg.getScaledInstance(300,40,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void move(){
        x_cue=dx_cue;
        y_cue=dy_cue;
    }
    public int getX(){
        return x_cue;
    }
    public int getY(){
        return y_cue;
    }
    public Image getImage(){return poolCueImg;}


    public void mouseDragged(MouseEvent e) {
        dx_cue=e.getX();
        dy_cue=e.getY();
    }

    public void mouseClicked(MouseEvent e){

    }



    public void mouseMoved(MouseEvent e) {
        dx_cue=e.getX();
        dy_cue=e.getY();


    }




}
