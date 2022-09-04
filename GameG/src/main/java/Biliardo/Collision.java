package Biliardo;

import java.util.List;

public class Collision {

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
        System.out.println("INIZIO COLLISIONE");
        System.out.println("B1 collisione\n"+b.movimentoRimanente);
        System.out.println(b.getComponenteVelocitaX());
        System.out.println(b.getComponenteVelocitaY());
        System.out.println("B1 fine rev");

        System.out.println("B2 collisione\n"+b2.movimentoRimanente);
        System.out.println(b2.getComponenteVelocitaX());
        System.out.println(b2.getComponenteVelocitaY());
        System.out.println("B2 fine rev");

        b.setComponenteVelocitaY((b2.getComponenteVelocitaY()));
        b.setComponenteVelocitaX((b2.getComponenteVelocitaX()));
        b.setMovimentoRimanente(b2.getMovimentoRimanente());

        b2.setComponenteVelocitaY(-(b2.getComponenteVelocitaY()));
        b2.setComponenteVelocitaX(-(b2.getComponenteVelocitaX()));
        b2.setMovimentoRimanente(b2.getMovimentoRimanente()/2);

        System.out.println("FINE COLLISIONE");
        System.out.println("B1 collisione"+b.movimentoRimanente);
        System.out.println(b.getComponenteVelocitaX());
        System.out.println(b.getComponenteVelocitaY());
        System.out.println("B1 fine rev");

        System.out.println("B2 collisione"+b2.movimentoRimanente);
        System.out.println(b2.getComponenteVelocitaX());
        System.out.println(b2.getComponenteVelocitaY());
        System.out.println("B2 fine rev\n\n");




        b.MoveBall();
        b2.MoveBall();

    }

}
