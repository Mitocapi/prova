package GameBasic;

public class Game {

    private GameWindow gameWindow;
    private GamePanel gamePanel;

    //constructor    head method
    public Game(){
        gamePanel=new GamePanel();
        gameWindow=new GameWindow(gamePanel);

    }



}
