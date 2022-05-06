package caricaIMG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int dx;
    private int dy;
    protected boolean shoot=false;

    private int x=40;
    private int y=50;
    Image image;
    Image background;
    Image missile;

    private int imgW;
    private int imgH;


    public Player(){
        loadImage();
    }

    public void loadImage(){
        ImageIcon ii= new ImageIcon("/Users/samuele/IdeaProjects/progettoGioco/src/main/resources/images/SpaceShipRed.png");
        image=ii.getImage().getScaledInstance(60,60,1);  //ridimensiona
        ImageIcon ii2=new ImageIcon("/Users/samuele/IdeaProjects/progettoGioco/src/main/resources/images/world.jpg");
        background= ii2.getImage().getScaledInstance(1920,1202,1);
        ImageIcon ii3=new ImageIcon("/Users/samuele/IdeaProjects/progettoGioco/src/main/resources/images/missile.png");
        missile= ii3.getImage().getScaledInstance(5,2,1);


        imgW= 20;
        imgH=50;
    }

    public void move(){
        x+=dx;
        y+=dy;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getW(){
        return imgW;
    }
    public int getH(){
        return imgH;
    }
    public Image getImage(){
        return image;
    }

    public void KeyPressed(KeyEvent e){
       int key= e.getKeyCode();
       if(key==KeyEvent.VK_DOWN || key==KeyEvent.VK_S){
           dy=2;
       }
       if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A){
           dx=-2;
       }
       if(key==KeyEvent.VK_RIGHT|| key==KeyEvent.VK_D){
            dx=2;
       }
       if(key==KeyEvent.VK_UP   || key==KeyEvent.VK_W){
            dy=-2;
       }
       if(key==KeyEvent.VK_SPACE){
           shoot=true;
       }

    }
    public void KeyReleased(KeyEvent e){
        int key= e.getKeyCode();
        if(key==KeyEvent.VK_DOWN  || key==KeyEvent.VK_S ){
            dy=0;
        }
        if(key==KeyEvent.VK_LEFT  || key==KeyEvent.VK_A ){
            dx=0;
        }
        if(key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D ){
            dx=0;
        }
        if(key==KeyEvent.VK_UP   || key==KeyEvent.VK_W){
            dy=0;
        }

    }

}
