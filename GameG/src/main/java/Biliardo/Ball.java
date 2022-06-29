package Biliardo;

import java.awt.*;
import java.util.List;


public class Ball {
    private static final int MAX_VEL =3000 ;
    int radius;
    static Color ballColor;
    int x_wall_up=Table.x_board;
    int y_wall_up=Table.y_board;

    //prende distanza dal centro e moltiplica per 25
    int movimentoRimanente;

    int componenteVelocitaY;
    int componenteVelocitaX;

    int posizioneX;
    int posizioneY;
    int num=0;

    //inclinazione movimento
    int dx; //quanto si muove sull'asse x rispetto a y
    int dy; //quanto si muove sull'asse y rispetto a x
    int v=4;

    public static void setBallStop(List<Ball> palleInGioco) {
        for(Ball b : palleInGioco){
            b.movimentoRimanente=0;
            b.dy=0;
            b.dx=0;
        }
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

    public Color getColor() {
        return ballColor;
    }

    public static void setColore(Color colore) {
        ballColor = colore;
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

    public Ball(int posx, int posy) {
        ballColor = new Color(0, 0, 0);

        this.radius = 10;
        this.posizioneX = posx;
        this.posizioneY = posy;
        this.componenteVelocitaX = 0;
        this.componenteVelocitaY = 0;
        dx =0;
        dy =0;
        movimentoRimanente=1000;
    }

    public void MYpaintComponents(Graphics g, Color col) {
        g.setColor(col);
        g.fillOval(posizioneX - 10, posizioneY - 10, radius * 2, radius * 2);
        //if(num!=0){
        //    g.setColor(Color.white);
        //    g.drawString(""+num,posizioneX-5,posizioneY+5);
        //}
        //num++;
        //if(num==16){
        //    num=0;
        //}
    }

    public void hitAWall(int direzione) {
        // 0=x 1=y
        if (direzione == 0) {
            setComponenteVelocitaX(- getComponenteVelocitaX());
        } else {
            setComponenteVelocitaY(- getComponenteVelocitaY());
        }

    }

    public void checkHitBall (Ball ball){

        // if(getXposition()!=al traPalla.getXposition()||getYposition()!= altraPalla.getYposition()){
        /*int distanzax=getXposition()+getRadius() - altraPalla.getXposition()-getRadius();
        int distanzay=getYposition()+getRadius() - altraPalla.getYposition()-getRadius();
        if(Math.sqrt(Math.pow(distanzax,2)+Math.pow(distanzay,2))<=(double) getRadius()*2){
            hitABall(altraPalla);
            //}
        }*/

        double distX=getXposition()-ball.getXposition();
        double distY=getYposition()- ball.getYposition();
        double radSum=Math.pow(getRadius()*2,2);
        double dist=(distX*distX)+(distY*distY);
        if(dist<=radSum){
            hitABall(ball);
        }

    }

    public void hitABall(Ball uno) {

        //setComponenteVelocitaX(- getComponenteVelocitaX()); //provo a dargli un movimento minimo per vedere di
        //setComponenteVelocitaY(- getComponenteVelocitaY()); //non farle fondere..
        //MoveBall();
        //MoveBall();
        //setComponenteVelocitaX( getComponenteVelocitaX());
        //setComponenteVelocitaY( getComponenteVelocitaY());


        int movimentodimezzo= uno.getMovimentoRimanente();
        uno.setMovimentoRimanente(getMovimentoRimanente());
        setMovimentoRimanente(movimentodimezzo);
        setComponenteVelocitaX(uno.getComponenteVelocitaX());
        setComponenteVelocitaY(uno.getComponenteVelocitaY());
        uno.setComponenteVelocitaY(-getComponenteVelocitaY());
        uno.setComponenteVelocitaX(-getComponenteVelocitaX());

        //dy =0;
        //dx =0;
        //uno.dy =0;
        //uno.dx =0;

        MoveBall();
        uno.MoveBall();
    }
    public static boolean checkMove(List<Ball> b){
        for(Ball a : b){
            if(a.movimentoRimanente>1){
                return false;
            }
        }
        return true;
    }

    public void MoveBall() {

        if(dx==0&& dy ==0) {
            movimentoRimanente=Math.abs(getComponenteVelocitaX())+Math.abs(getComponenteVelocitaY());
            if(movimentoRimanente>MAX_VEL){
                movimentoRimanente=MAX_VEL;
            }
            /*
            if (getComponenteVelocitaY() > getComponenteVelocitaX()) {
                if (componenteVelocitaX != 0) {
                    dy = v*Math.abs(getComponenteVelocitaY() / getComponenteVelocitaX());
                    if(dy>30)
                        dy=30;
                    dx = 1*v;
                } else {
                    dy = 1 * v;
                    dx=0;
                }
            } else {
                if (componenteVelocitaY != 0) {
                    dx = v*Math.abs(getComponenteVelocitaX() / getComponenteVelocitaY());
                    if(dx>30)
                        dx=30;
                    dy = 1*v;
                } else{
                    dx = 1*v;
                    dy=0;
                }
            }
            */
            dx=Math.abs(getComponenteVelocitaX()/180);
            dy=Math.abs(getComponenteVelocitaY()/180);
        } //rapporto tra i movimenti e direzione;

        if (movimentoRimanente>0) {
            if (getComponenteVelocitaX() > 0) {
                setXposition(getXposition() + dx);
                setMovimentoRimanente(movimentoRimanente- dx - dy);
                if (getXposition() + getRadius() >= 877) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(0);
                }
            }
            if (getComponenteVelocitaX() < 0) {
                setXposition(getXposition() - dx);
                setMovimentoRimanente(movimentoRimanente - dx - dy);
                if (getXposition() - getRadius()*2 <= 322) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(0);
                }
            }
            if (getComponenteVelocitaY() > 0) {
                setYposition(getYposition() + dy);
                setMovimentoRimanente(movimentoRimanente - dx - dy);
                if (getYposition() + getRadius()*2 >= 567) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(1);
                }
            }
            if (getComponenteVelocitaY() < 0) {
                setYposition(getYposition() - dy);
                setMovimentoRimanente(movimentoRimanente - dx - dy);
                if (getYposition() - getRadius()*2 <= 240) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(1);
                }
            }
            // CONTROLLA LE COLLISIONI CON L'ARRAY DI PALLE
        }
        else if (componenteVelocitaX ==0 && componenteVelocitaY ==0) {
            dy =0;
            dx =0;
            movimentoRimanente=0;
        }
    }

}
