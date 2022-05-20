package immaginiMovimento.entities.GameBas;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs; //così perchè lo chiamo 2 volte
    private int xd=100;
    private int yd=100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick,aniIndex,aniSpeed=15;  //max speed is frame but is too messy
    private int playerDir=-1;
    private boolean moving=false;
    private boolean attacking=false;
    private boolean jumping=false;
    private int playerAction=IDLE;

    public GamePanel(){

        mouseInputs=new MouseInputs(this);
        importIMG();
        loadAnimation();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));


        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void loadAnimation() {
        animations=new BufferedImage[9][6];

        for(int i=0;i<animations.length;i++){
            for(int j=0;j<animations[i].length;j++){
                animations[i][j]=img.getSubimage(j*64,i*40,64,40);
            }
        }


    }

    private void importIMG() {

        try {
            img = ImageIO.read(new File("/Users/samuele/IdeaProjects/GiocoFinale/src/main/resources/image/player_sprites.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ImageIcon ii=new ImageIcon("/Users/samuele/IdeaProjects/GiocoFinale/src/main/resources/image/player_sprites.png");
        //pirate= (BufferedImage) ii.getImage();
    }

    private void setPanelSize() {
        Dimension size=new Dimension(1280,800);

        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }

    public void setDirection(int direction){
        this.playerDir=direction;
        moving=true;
    }

    public void setMoving(boolean moving){
        this.moving=moving;
    }
    public void setAttacking(boolean attacking){
        this.attacking=attacking;
    }
    public void setJumping(boolean jumping){
        this.jumping=jumping;
    }

    private void updateAnimationTick() {

        aniTick++;

        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;

            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex=0;
            }
        }
    }
    private void setAnimation() {
        if(moving)
            playerAction=RUNNING;
        else if(attacking)
            playerAction=ATTACK_1;
        else if(jumping){
            playerAction=JUMP;
            yd-=5;
        }
        else
            playerAction=IDLE;
    }
    private void updatePos(){
        if(moving){
            switch(playerDir){
                case LEFT->xd += -5;
                case RIGHT-> xd += 5;
                case UP ->yd += -5;
                case DOWN->  yd += 5;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); //chiama la superclass jpanel

        updateAnimationTick();
        setAnimation();
        updatePos();

        g.drawImage(animations[playerAction][aniIndex],xd,yd,256,160,this);

    }




}
