package immaginiMovimento.entities.GameBas;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_SET=120;
    private final int UPS_SET=200; //update per seconds

    //constructor    head method
    public Game(){
        gamePanel=new GamePanel();
        gameWindow=new GameWindow(gamePanel);
        gamePanel.requestFocus(); //richiede input
        startGameLoop();

    }

    private void startGameLoop(){
        gameThread=new Thread(this);
        gameThread.start();

    }


    @Override
    public void run() {

        double timePerFrame= 1000000000.0/FPS_SET; //durata del frame in nanosecondi
        double timePerUpdate=1000000000.0/UPS_SET;
        long lastFrame=System.nanoTime();
        long now=System.nanoTime();

        long previousTime=System.nanoTime();
        int updates=0;
        int frames=0;
        long lastCheck=System.currentTimeMillis();
        double deltaU=0;

        while(true){
            now=System.nanoTime();
            long currentTime=System.nanoTime();
            deltaU+=(currentTime-previousTime)/timePerUpdate;
            previousTime=currentTime;

            if(deltaU>=1){
                updates++;
                deltaU--;
            }

            if(now-lastFrame >= timePerFrame){
                gamePanel.repaint();
                lastFrame=now;
                frames++;
            }

            if(System.currentTimeMillis() -lastCheck >=1000){
                lastCheck=System.currentTimeMillis();
                System.out.println("FPS:"+frames+" | UPS:"+updates);
                frames=0;
                updates=0;
            }
        }

    }
}
