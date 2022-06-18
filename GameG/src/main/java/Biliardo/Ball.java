package Biliardo;

import java.awt.*;

public class Ball {
    int radius;
    static Color ballColor;
    /*
    opera d'arte, considero la velocità come "movimenti rimanenti"
    in base alla direzione
    */
    int movimentoRimanente;
    int movimentoRimanenteY;
    int movimentoRimanenteX;
    int posizioneX;
    int posizioneY;
    int rapportoneX;
    int rapportoneY;
    // get e set di fiducia

    public int getRadius() {
        return radius;
    }
    public void setMovimentoRimanente(int movimentoRimanente){
        this.movimentoRimanente=movimentoRimanente;
    }
    public int getMovimentoRimanente(){
        return movimentoRimanente;
    }
    public Color getColor() {
        return ballColor;
    }
    public static void setColore(Color colore) {
        ballColor = colore;
    }
    public int getMovimentoRimanenteY() {
        return movimentoRimanenteY;
    }
    public void setMovimentoRimanenteY(int movimentoRimanenteY) {
        this.movimentoRimanenteY = movimentoRimanenteY;
    }
    public int getMovimentoRimanenteX() {
        return movimentoRimanenteX;
    }
    public void setMovimentoRimanenteX(int movimentoRimanenteX) {
        this.movimentoRimanenteX = movimentoRimanenteX;
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
        this.movimentoRimanenteX = 700;
        this.movimentoRimanenteY = 300;
        rapportoneX=0;
        rapportoneY=0;
        movimentoRimanente=0;
    }

    public void paintComponents(Graphics g) {
        g.setColor(getColor());
        g.fillOval(posizioneX - 10, posizioneY - 10, radius * 2, radius * 2);
    }

    public void hitAWall(int direzione) {
        // 0=x 1=y
        if (direzione == 0) {
            setMovimentoRimanenteX(- getMovimentoRimanenteX());
        } else {
            setMovimentoRimanenteY(- getMovimentoRimanenteY());
        }
    }

    public void hitABall(Ball pallaInMovimento, Ball pallaColpita) {
        if (pallaInMovimento.getXposition() > pallaColpita.getXposition()) {
        }
    }

    public void MoveBall() {

        if(rapportoneX==0&&rapportoneY==0) {
            movimentoRimanente=Math.abs(getMovimentoRimanenteX())+Math.abs(getMovimentoRimanenteY());
            if (getMovimentoRimanenteY() > getMovimentoRimanenteX()) {
                if (movimentoRimanenteX != 0) {
                    rapportoneY = Math.abs(getMovimentoRimanenteY() / getMovimentoRimanenteX());
                    rapportoneX = 1;
                } else
                    rapportoneY = 1;
            } else {
                if (movimentoRimanenteY != 0) {
                    rapportoneX = Math.abs(getMovimentoRimanenteX() / getMovimentoRimanenteY());
                    rapportoneY = 1;
                } else
                    rapportoneX = 1;
            }
        } //rapporto tra i movimenti e direzione;

        if (movimentoRimanente>200) {
            if (getMovimentoRimanenteX() > 0) {
                setXposition(getXposition() + rapportoneX * 2);
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
            // CONTROLLA LE COLLISIONI CON L'ARRAY DI PALLE
        }
        if (movimentoRimanente>0) {
                if (getMovimentoRimanenteX() > 0) {
                    setXposition(getXposition() + rapportoneX);
                    setMovimentoRimanente(movimentoRimanente- 1);
                    if (movimentoRimanente>0 && getXposition() + getRadius() >= 877) {
                        //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                        hitAWall(0);
                    }
                }
                if (getMovimentoRimanenteX() < 0) {
                    setXposition(getXposition() - rapportoneX);
                    setMovimentoRimanente(movimentoRimanente - 1);
                    if (movimentoRimanente > 0 && getXposition() - getRadius() <= 322) {
                        //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                        hitAWall(0);
                    }
                }
                if (getMovimentoRimanenteY() > 0) {
                    setYposition(getYposition() + rapportoneY);
                    setMovimentoRimanente(movimentoRimanente - 1);
                    if (movimentoRimanente > 0 && getYposition() + getRadius() >= 567) {
                        //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                        hitAWall(1);
                    }
                }
                if (getMovimentoRimanenteY() < 0) {
                    setYposition(getYposition() -rapportoneY);
                    setMovimentoRimanente(movimentoRimanente - 1);
                    if (movimentoRimanente > 0 && getYposition() - getRadius() <= 240) {
                        //MODIFICA QUELLO 0 CON LA POSIZIONE DEI BORDI DEL CAMPO
                        hitAWall(1);
                    }
                }
                // CONTROLLA LE COLLISIONI CON L'ARRAY DI PALLE
            }
        else if (movimentoRimanenteX==0 && movimentoRimanenteY==0) {
            rapportoneY=0;
            rapportoneX=0;
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
