package Biliardo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Coin {
    private int x_coin=100;
    private int y_coin=100;
    Random rnd=new Random();

    Image coinImg;

    public Coin(){
        loadImage();
        spawnCoin();
        whereCoinX();
        whereCoinY();
    }

    public void loadImage(){
        try {
            coinImg = ImageIO.read(new File("GameG/src/main/resources/images/coin.png"));
            coinImg=coinImg.getScaledInstance(10,5,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean spawnCoin(){
        int r;
        r= rnd.nextInt(1,4);
        if(r==2){
            return true;
        }
        return false;
    }
    public int whereCoinX(){
        int rx;
        rx= rnd.nextInt(200,400);
        return rx;
    }
    public int whereCoinY(){
        int ry;
        ry= rnd.nextInt(200,500);
        return ry;
    }
    public int getX(){
        return x_coin;
    }
    public int getY(){return y_coin;}
    public Image getImage(){return coinImg;}
}
