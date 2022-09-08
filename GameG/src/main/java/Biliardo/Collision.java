package Biliardo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Collision {

    int velx;
    int vely;
    int mov;
    int sign1y;
    int sign1x;
    int sign2y;
    int sign2x;
    double vris;
    public void hitWall(Ball b,int direction){
        if (direction == 0) {
            b.setComponenteVelocitaX(- b.getComponenteVelocitaX()/2);
        } else {
            b.setComponenteVelocitaY(- b.getComponenteVelocitaY()/2);
        }
    }

    public void stopBall(List<Ball>ball){
        for(Ball b : ball){
            b.movimentoRimanente=0;
            b.setComponenteVelocitaY(0);
            b.setComponenteVelocitaX(0);
            b.setMovimentoRimanente(0);
        }
    }

    public void checkCollision(List<Ball> b,Ball whiteBall){

        for(int i=0;i<b.size();i++){
            checkCol2(b.get(i),whiteBall);
            for(int j=0;j<b.size();j++){
                if(i==j) {
                }
                else{
                    checkCol2(b.get(j),b.get(i));

                }
            }
        }


    }
    public void checkCol2(Ball b,Ball b2){
        double distX=b.getXposition()-b2.getXposition();
        double distY=b.getYposition()- b2.getYposition();
        double radSum=b.getRadius()*2;
        double dist=Math.sqrt((distX*distX)+(distY*distY));
        if(dist<=radSum){
            if(b.getComponenteVelocitaX()>0 || b.getComponenteVelocitaY()>0 || b2.getComponenteVelocitaX()>0 || b2.getComponenteVelocitaY()>0)
                collision(b,b2);
        }
    }

    public void collision(Ball b,Ball b2){
        //b.setComponenteVelocitaX(b.getComponenteVelocitaX()/2); //provo a dargli un movimento minimo per vedere di
        //b.setComponenteVelocitaY(b.getComponenteVelocitaY()/2); //non farle fondere..
        ////MoveBall();
        ////MoveBall();
        //b.setComponenteVelocitaX( b.getComponenteVelocitaX());
        //b.setComponenteVelocitaY( b.getComponenteVelocitaY());

        //b2.dx=b.dx;
        //b2.dy=b.dy;

        velx=Math.abs(b.getComponenteVelocitaX())+Math.abs(b2.getComponenteVelocitaX());
        velx= (int) (velx/1.6);
        vely=Math.abs(b.getComponenteVelocitaY())+ Math.abs(b2.getComponenteVelocitaY());
        vely= (int) (vely/1.6);
        mov=((b.getMovimentoRimanente()+b2.getMovimentoRimanente())/2);

        vris=Math.sqrt(Math.pow(velx,2)+Math.pow(vely,2));
        velx= (int) (vris*Math.sin(getAngle(new Point(b.getXposition(),b.getYposition()),new Point(b2.getXposition(),b2.getYposition()))));
        vely= (int) (vris*Math.cos(getAngle(new Point(b.getXposition(),b.getYposition()),new Point(b2.getXposition(),b2.getYposition()))));




        if(b.getComponenteVelocitaY()>=0)
            sign1y=1;
        else
            sign1y=-1;

        if(b.getComponenteVelocitaX()>=0)
            sign1x=1;
        else
            sign1x=-1;

        if(b2.getComponenteVelocitaY()>=0)
            sign2y=1;
        else
            sign2y=-1;

        if(b2.getComponenteVelocitaX()>=0)
            sign2x=1;
        else
            sign2y=-1;

        if(b.getComponenteVelocitaX()==0){
            sign1x=sign2x;
            sign2x=-sign2x;
        }
        else if(b2.getComponenteVelocitaX()==0){
            sign2x=sign1x;
            sign1x=-sign1x;
        }
        if(b.getComponenteVelocitaY()==0){
            sign1y=sign2y;
            sign2y=-sign2y;
        }
        else if(b2.getComponenteVelocitaY()==0){
            sign2y=sign1y;
            sign1y=-sign1y;
        }

        double an=getAngle(new Point(b.getXposition(),b.getYposition()),new Point(b2.getXposition(),b2.getYposition()));







        System.out.println("INIZIO COLLISIONE tra b1 e b2");
        System.out.println("la collisione ha un angolo di "+an);
        System.out.println("B1");
        System.out.println("movimento rimanente: "+b.movimentoRimanente);
        System.out.println("vel x: "+b.getComponenteVelocitaX());
        System.out.println("vel y: "+b.getComponenteVelocitaY());
        System.out.println("num pallina: "+b.num);
        System.out.println("---------------");
        System.out.println("B2");
        System.out.println("movimento rimanente: "+b2.movimentoRimanente);
        System.out.println("vel x: "+b2.getComponenteVelocitaX());
        System.out.println("vel y: "+b2.getComponenteVelocitaY());
        System.out.println("num pallina: "+b2.num);

        b.setComponenteVelocitaY(vely*sign1y);
        b.setComponenteVelocitaX(velx*sign1x);
        b.setMovimentoRimanente(mov);

        b2.setComponenteVelocitaY(vely*sign2y);
        b2.setComponenteVelocitaX(velx*sign2x);
        b2.setMovimentoRimanente(mov);

        System.out.println("FINE COLLISIONE");
        System.out.println("B1");
        System.out.println("movimento rimanente: "+b.movimentoRimanente);
        System.out.println("vel x: "+b.getComponenteVelocitaX());
        System.out.println("vel y: "+b.getComponenteVelocitaY());
        System.out.println("num pallina: "+b.num);
        System.out.println("---------------");
        System.out.println("B2");
        System.out.println("movimento rimanente: "+b2.movimentoRimanente);
        System.out.println("vel x: "+b2.getComponenteVelocitaX());
        System.out.println("vel y: "+b2.getComponenteVelocitaY());
        System.out.println("num pallina: "+b2.num);
        System.out.println("\n\n");
        System.out.println("++++++++++++++++++++++++++++++++++");




        b.MoveBall();
        b2.MoveBall();

    }
    public double getAngle(Point p1,Point p2){
        int angle = (int) Math.toDegrees(Math.atan2(p1.y - p2.x, p1.x - p2.x));

        if(angle < 0){
            angle += 360;
        }
        while (angle>360)
            angle-=360;

        return angle;

    }

}