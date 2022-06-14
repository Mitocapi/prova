package Biliardo;

import java.awt.*;

public class Ball {
    int radius;
    static Color ballColor;

    /*
    opera d'arte, considero la velocità come "movimenti rimanenti"
    in base alla direzione
    */
    int movimentoRimanenteY;
    int movimentoRimanenteX;

    int posizioneX;
    int posizioneY;
// get e set di fiducia

    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public Color getColor() {
        return ballColor;
    }
    public static void setColore(Color colore) { ballColor = colore;}
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
        ballColor = new Color(0,0,0);
        this.radius = 10;
        this.posizioneX = getXposition()+400;
        this.posizioneY= getYposition()+150;
        this.movimentoRimanenteX = getMovimentoRimanenteX();
        this.movimentoRimanenteY = getMovimentoRimanenteY();
    }

    public void paintComponents (Graphics g){
        g.setColor(getColor());
        g.fillOval(posizioneX-5,posizioneY-5, radius *2, radius *2);

    }

    public void MoveBall(){
        while(getMovimentoRimanenteX()!=0 && getMovimentoRimanenteY()!=0){
            if(getMovimentoRimanenteX()>0){
                setXposition(getXposition()+1);
                setMovimentoRimanenteX(getMovimentoRimanenteX()-1);
            }
            if(getMovimentoRimanenteX()<0){
                setXposition(getXposition()-1);
                setMovimentoRimanenteX(getMovimentoRimanenteX()+1);
            }
            if(getMovimentoRimanenteY()>0){
                setYposition(getYposition()+1);
                setMovimentoRimanenteY(getMovimentoRimanenteY()-1);
            }
            if(getMovimentoRimanenteY()<0){
                setYposition(getYposition()-1);
                setMovimentoRimanenteY(getMovimentoRimanenteY()+1);
            }
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
