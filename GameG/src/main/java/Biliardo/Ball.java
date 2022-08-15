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

        this.radius = 12;
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
        if(num==1 || num==9)
            g.setColor(new Color(247,217,23));
        if(num==2 || num==10)
            g.setColor(new Color(65,175,225));
        if(num==3 || num==11)
            g.setColor(new Color(204,0,0));
        if(num==4 || num==12)
            g.setColor(new Color(75,0,130));
        if(num==5 || num==13)
            g.setColor(new Color(255,69,0));
        if(num==6 || num==14)
            g.setColor(new Color(0,122,65));
        if(num==7|| num==15)
            g.setColor(new Color(153,0,0));
        if(num==8)
            g.setColor(Color.black);

        if(num<=8){
            g.fillOval(posizioneX - 12, posizioneY - 12, radius * 2, radius * 2);
            if(num!=0){
                g.setColor(Color.white);
                g.drawString(""+num,posizioneX-5,posizioneY+5);
            }
        }else{
            g.fillOval(posizioneX - 12, posizioneY - 12, radius * 2, radius * 2);

            g.setColor(Color.white);
            g.fillOval(posizioneX-7, posizioneY-7, 14, 14);
            g.setColor(Color.black);
            g.drawString(""+num,posizioneX-6,posizioneY+6);

        }

    }

    public void hitAWall(int direzione) {
        // 0=x 1=y
        if (direzione == 0) {
            setComponenteVelocitaX(- getComponenteVelocitaX()/2);
        } else {
            setComponenteVelocitaY(- getComponenteVelocitaY()/2);
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

        if(dx==0 && dy ==0) {
            movimentoRimanente=Math.abs(getComponenteVelocitaX())+Math.abs(getComponenteVelocitaY())*2;
            if(movimentoRimanente>MAX_VEL){
                movimentoRimanente=MAX_VEL;
            }
            dx=Math.abs(getComponenteVelocitaX()/180);
            dy=Math.abs(getComponenteVelocitaY()/180);
        }

        if(dx!=0)
            System.out.println("dx:"+dx);
        if(dy!=0)
            System.out.println("dy:"+dy);


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
        if(componenteVelocitaX!=0)
            setComponenteVelocitaX(componenteVelocitaX--);
        if(componenteVelocitaY!=0)
            setComponenteVelocitaY(componenteVelocitaY--);

        if (componenteVelocitaX ==0 && componenteVelocitaY ==0) {
            dy =0;
            dx =0;
            movimentoRimanente=0;
        }
    }

}
