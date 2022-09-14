package Biliardo;

import Biliardo.MenuAvvio.Board;
import Biliardo.MenuAvvio.ColorChooser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Biliardo.Table.BOARD_HEIGHT;
import static Biliardo.Table.BOARD_WIDTH;


public class Ball {
    private static final int MAX_VEL =3000 ;
    int radius;
    int number;

    Collision c=new Collision();
    static Color ballColor;
    int x_wall_up=Table.x_board;
    int y_wall_up=Table.y_board;
    int DELAY=1;
    public Thread animator2;

    //prende distanza dal centro e moltiplica per 25
    int movimentoRimanente;
    double slowFactor;

    int componenteVelocitaY;
    int componenteVelocitaX;

    int posizioneX;
    int posizioneY;
    int num=0;

    //inclinazione movimento
    int dx; //quanto si muove sull'asse x rispetto a y
    int dy; //quanto si muove sull'asse y rispetto a x
    int v=4;
    int velx=1;
    int vely=1;

    int x; //posizione corrente su x
    int y;

    public static void setBallStop(List<Biliardo.Ball> palleInGioco) {
        for(Biliardo.Ball b : palleInGioco){
            b.movimentoRimanente=0;
            b.dy=0;
            b.dx=0;
        }
    }

    public boolean isMoving(){
        if(getComponenteVelocitaX()!=0 || getComponenteVelocitaY() !=0)
            return true;
        return false;
    }


    public int getRadius() {
        return radius;
    }

    public void setMovimentoRimanente(int movimentoRimanente) {
        this.movimentoRimanente = movimentoRimanente;
    }

    public int getMovimentoRimanente() {
        return movimentoRimanente;
    }
    public int getNum() {
        return num;
    }

    public Color getColor() {
        return ballColor;
    }

    public static void setColore(Color colore) {
        ballColor = colore;
    }
    public void setNum(int Num){
        this.num=Num;
    }

    public int getComponenteVelocitaY() {
        return componenteVelocitaY;
    }

    public void setComponenteVelocitaY(int componenteVelocitaY) {
        this.componenteVelocitaY = componenteVelocitaY;
    }

    public int getComponenteVelocitaX() {
        return componenteVelocitaX;
    }

    public void setComponenteVelocitaX(int componenteVelocitaX) {
        this.componenteVelocitaX = componenteVelocitaX;
    }

    public int getXposition() {
        return posizioneX;
    }

    public void setXposition(int posizioneX) {
        this.posizioneX = posizioneX;
    }

    public int getYposition() {
        return posizioneY;
    }

    public void setYposition(int posizioneY) {
        this.posizioneY = posizioneY;
    }

    public Ball(int posx, int posy,int number) {
        ballColor = new Color(0, 0, 0);

        this.radius = 10;
        this.number=number;
        this.posizioneX = posx;
        this.posizioneY = posy;
        this.componenteVelocitaX = 0;
        this.componenteVelocitaY = 0;
        this.slowFactor=0.8;
        dx =0;
        dy =0;
        movimentoRimanente=1000;
    }

    public void MYpaintComponents(Graphics g, Color col,int num) {
        g.setColor(col);

        if(ColorChooser.selected==1){

            if(this.number<=8){
                g.setColor(Board.colorBall1);
                g.fillOval(posizioneX - 12, posizioneY - 12, radius * 2, radius * 2);
                if(this.number==0){
                    g.setColor(Color.white);
                    g.fillOval(posizioneX - 12, posizioneY - 12, radius * 2, radius * 2);
                }
            }else{
                g.setColor(Board.colorBall2);
                g.fillOval(posizioneX - 12, posizioneY - 12, radius * 2, radius * 2);
            }



        }else{
        if(this.number==1 || this.number==9)
            g.setColor(new Color(247,217,23));
        if(this.number==2 || this.number==10)
            g.setColor(new Color(65,175,225));
        if(this.number==3 || this.number==11)
            g.setColor(new Color(204,0,0));
        if(this.number==4 || this.number==12)
            g.setColor(new Color(75,0,130));
        if(this.number==5 || this.number==13)
            g.setColor(new Color(255,69,0));
        if(this.number==6 || this.number==14)
            g.setColor(new Color(0,122,65));
        if(this.number==7|| this.number==15)
            g.setColor(new Color(153,0,0));
        if(this.number==8)
            g.setColor(Color.black);

        if(this.number<=8){
            g.fillOval(posizioneX - 12, posizioneY - 12, radius * 2, radius * 2);
            if(num!=0){
                g.setColor(Color.white);
                g.drawString(""+this.number,posizioneX-5,posizioneY+5);
            }
        }else{
            g.fillOval(posizioneX - 12, posizioneY - 12, radius * 2, radius * 2);

            g.setColor(Color.white);
            g.fillOval(posizioneX-7, posizioneY-7, 14, 14);
            g.setColor(Color.black);
            g.drawString(""+this.number,posizioneX-6,posizioneY+6);

        }
        }

    }

    public static boolean checkMove(List<Biliardo.Ball> b){
        for(Biliardo.Ball a : b){
            if(a.movimentoRimanente>1){
                return false;
            }
        }
        return true;
    }

    public void MoveBall() {
        if(dx==0 && dy==0) {
            movimentoRimanente = Math.abs(getComponenteVelocitaX()) + Math.abs(getComponenteVelocitaY())*2;
            if (movimentoRimanente > MAX_VEL) {
                movimentoRimanente = MAX_VEL;
            }
            if (componenteVelocitaX != 0)
                dx = Math.abs(getComponenteVelocitaX() / 100);
            if (componenteVelocitaY != 0)
                dy = Math.abs(getComponenteVelocitaY() / 100);

        }
        if (movimentoRimanente > MAX_VEL) {
            movimentoRimanente = MAX_VEL;
        }
        if (getMovimentoRimanente()>0) {
            if (componenteVelocitaX != 0)
                dx = Math.abs(getComponenteVelocitaX() / 100);
            else
                dx=0;
            if (componenteVelocitaY != 0)
                dy = Math.abs(getComponenteVelocitaY() / 100);
            else dy=0;

            if (getComponenteVelocitaX() > 0) {
                setXposition(getXposition() + dx);
                setMovimentoRimanente(movimentoRimanente- Math.abs(dx) - Math.abs(dy));
                if (getXposition() + getRadius() >= 877) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    c.hitWall(Ball.this,0);
                }
            }
            if (getComponenteVelocitaX() < 0) {
                setXposition(getXposition() - dx);
                setMovimentoRimanente(movimentoRimanente - Math.abs(dx) - Math.abs(dy));
                if (getXposition() - getRadius()*2 <= 322) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    c.hitWall(Ball.this,0);
                }
            }
            if (getComponenteVelocitaY() > 0) {
                setYposition(getYposition() + dy);
                setMovimentoRimanente(movimentoRimanente - Math.abs(dx) - Math.abs(dy));
                if (getYposition() + getRadius()*2 >= 580) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    c.hitWall(Ball.this,1);
                }
            }
            if (getComponenteVelocitaY() < 0) {
                setYposition(getYposition() - dy);
                setMovimentoRimanente(movimentoRimanente - Math.abs(dx) - Math.abs(dy));
                if (getYposition() - getRadius()*2 <= 240) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    c.hitWall(Ball.this,1);
                }
            }
            // CONTROLLA LE COLLISIONI CON L'ARRAY DI PALLE

        }
        if (componenteVelocitaX ==0 && componenteVelocitaY ==0) {
            dy =0;
            dx =0;
            movimentoRimanente=0;
        }
        // CONTROLLA LE COLLISIONI CON L'ARRAY DI PALLE

    }

    /*public void cycle(){
        System.out.println("in");
        //MoveBall();
    }*/


}