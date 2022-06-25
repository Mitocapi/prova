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

    public void paintComponents(Graphics g,Color col) {
        g.setColor(col);
        g.fillOval(posizioneX - 10, posizioneY - 10, radius * 2, radius * 2);
    }

    public void hitAWall(int direzione) {
        // 0=x 1=y
        if (direzione == 0) {
            setComponenteVelocitaX(- getComponenteVelocitaX());
        } else {
            setComponenteVelocitaY(- getComponenteVelocitaY());
        }

    }

    public void checkHitBall (Ball altraPalla){

        // if(getXposition()!=al traPalla.getXposition()||getYposition()!= altraPalla.getYposition()){
        int distanzax=getXposition() - altraPalla.getXposition();
        int distanzay=getYposition() - altraPalla.getYposition();
        if(Math.sqrt(Math.pow(distanzax,2)+Math.pow(distanzay,2))<=(double) getRadius()*2){
            hitABall(altraPalla);
            //}
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

        if(dx ==0&& dy ==0) {
            movimentoRimanente=Math.abs(getComponenteVelocitaX())+Math.abs(getComponenteVelocitaY());
            if(movimentoRimanente>MAX_VEL){
                movimentoRimanente=MAX_VEL;
            }
            if (getComponenteVelocitaY() > getComponenteVelocitaX()) {
                if (componenteVelocitaX != 0) {
                    dy = v*Math.abs(getComponenteVelocitaY() / getComponenteVelocitaX());
                    dx = 1*v;
                } else
                    dy = 1*v;
            } else {
                if (componenteVelocitaY != 0) {
                    dx = v*Math.abs(getComponenteVelocitaX() / getComponenteVelocitaY());
                    dy = 1*v;
                } else
                    dx = 1*v;
            }
        } //rapporto tra i movimenti e direzione;

        /*if (movimentoRimanente>200) {
            if (getMovimentoRimanenteX() > 0) {
                setXposition( (getXposition() + rapportoneX * 2));
                setMovimentoRimanente(getMovimentoRimanente() - 2);
                if (movimentoRimanente > 0 && getXposition() + getRadius() >= 877) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(0);
                }
            }
            if (getMovimentoRimanenteX() < 0) {
                setXposition(getXposition() - rapportoneX * 2);
                setMovimentoRimanente(getMovimentoRimanente() - 2);
                if (movimentoRimanente >0 && getXposition() - getRadius() <= 322) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(0);
                }
            }
            if (getMovimentoRimanenteY() > 0) {
                setYposition(getYposition() + rapportoneY * 2);
                setMovimentoRimanente(getMovimentoRimanente() - 2);
                if (movimentoRimanente > 0 && getYposition() + getRadius() >= 567) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(1);
                }
            }
            if (getMovimentoRimanenteY() < 0) {
                setYposition(getYposition() - rapportoneY * 2);
                setMovimentoRimanente(movimentoRimanente - 2);
                if (movimentoRimanente > 0 && getYposition() - getRadius() <= 240) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(1);
                }
            }
            // HO COMMENTATO STA ROBA PERCHè è UGUALE A QUELLO SOTTO MA FA ANDARE LA PALLA PIù VELOCE
        }*/
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
                if (getXposition() - getRadius() <= 322) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(0);
                }
            }
            if (getComponenteVelocitaY() > 0) {
                setYposition(getYposition() + dy);
                setMovimentoRimanente(movimentoRimanente - dx - dy);
                if (getYposition() + getRadius() >= 567) {
                    //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                    hitAWall(1);
                }
            }
            if (getComponenteVelocitaY() < 0) {
                setYposition(getYposition() - dy);
                setMovimentoRimanente(movimentoRimanente - dx - dy);
                if (getYposition() - getRadius() <= 240) {
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



//arriva il copypaste del lavoro del prof

/*
    public Ball(double width, double height) {
        RandomGenerator rnd = RandomGeneratorFactory.getDefault().create();
        this.radius = Math.max(width / 50, (width / 20) * rnd.nextDouble());
        this.position = new Vector2D(
                width * rnd.nextDouble() * 0.7 + radius,
                height * rnd.nextDouble() * 0.7 + radius);
        this.velocity = new Vector2D(
                (width / 30) * rnd.nextDouble(),
                (height / 30) * rnd.nextDouble());
        this.color = new Color(
                (int) scale(radius, width/50, width/20, 0, 255),
                0,
                0);
    }
    public Ball(Vector2D position, Vector2D velocity, double radius, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.color = color;
    }
    public static double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax) {
        return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
    }
    public void move() {
        position.x += velocity.x;
        position.y += velocity.y;
    }
    public boolean collideHorizontalWall(Dimension d) {
        return position.y - radius < 0 || position.y + radius > d.height;
    }
    public boolean collideVerticalWall(Dimension d) {
        return position.x - radius < 0 || position.x + radius > d.width;
    }
    public boolean collideBall(Ball other) {
        Vector2D d = position.sub(other.position);
        return d.length() <= radius + other.radius;
    }
    public void revolve(Ball other) {
        // get the mtd
        Vector2D delta = position.sub(other.position);
        double d = delta.length();
        // minimum translation distance to push balls apart after intersecting
        Vector2D mtd = delta.multiply(((radius + other.radius) - d) / d);
        // resolve intersection --
        // inverse mass quantities
        double im1 = 1 / radius;
        double im2 = 1 / other.radius;
        // push-pull them apart based off their mass
        position = position.add(mtd.multiply(im1 / (im1 + im2)));
        other.position = other.position.sub(mtd.multiply(im2 / (im1 + im2)));
        //velocity = velocity.dot(delta);
        double projVel = velocity.dot(delta) / delta.length();
        Vector2D a = new Vector2D(delta);
        a.normalize();
        a.multiply(projVel);
        velocity = velocity.add(a).normalize();
    }
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillOval((int) (position.x - radius), (int) (position.y - radius), (int) (radius * 2), (int) (radius * 2));
    }
} */