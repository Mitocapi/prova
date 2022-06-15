package Biliardo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;

public class Menu implements MouseListener {
    public Table tavolo;
    public Rectangle bottonePlay = new Rectangle(Table.BOARD_WIDTH/4+150,240,300,50);
    public Rectangle bottoneChiudi = new Rectangle(Table.BOARD_WIDTH/4+150,540,300,50);
    public Rectangle bottoneAltro = new Rectangle(Table.BOARD_WIDTH/4+150,390,300,50);
    public static Color colorePalle;

    public void drawMenu(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        Color coloreSfondo = new Color(10,100,1);
        g.setColor(coloreSfondo);
        g.fillRect(0,0,Table.BOARD_WIDTH,Table.BOARD_HEIGHT);
        g.setColor(Color.black);
        g.fillOval(300,125,600,600);
        Font fontTitoloMenu = new Font ("titolo",Font.BOLD, 60);
        g.setFont(fontTitoloMenu);
        g.setColor(Color.RED);
        g.drawString("TITOLO COOL",400,100);

        g.setColor(Color.darkGray);
        g2d.draw(bottonePlay);
        g2d.draw(bottoneAltro);
        g2d.draw(bottoneChiudi);
        g.fillRect(Table.BOARD_WIDTH/4+150,240,300,50);
        g.fillRect(Table.BOARD_WIDTH/4+150,540,300,50);
        g.fillRect(Table.BOARD_WIDTH/4+150,390,300,50);

        Font fontbottoni = new Font ("arial", Font.BOLD, 30);
        g.setFont(fontbottoni);
        g.setColor(Color.white);
        g.drawString("Gioca", bottonePlay.x+110, bottonePlay.y+35);
        g.drawString("Chiudi", bottoneChiudi.x+110, bottoneChiudi.y+35 );
        g.drawString("Seleziona Colore", bottoneAltro.x+25, bottoneAltro.y+35 );

    }

    public void deleteMenu(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.clearRect(0,0,Table.BOARD_WIDTH,Table.BOARD_HEIGHT);

    }
    public void optionColor(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Color coloreSfondo = new Color(10,100,1);
        g.setColor(coloreSfondo);
        g.fillRect(0,0,Table.BOARD_WIDTH,Table.BOARD_HEIGHT);
        g.setColor(Color.black);
        g.fillOval(300,125,600,600);
        Font fontTitoloMenu = new Font ("titolo",Font.BOLD, 60);
        g.setFont(fontTitoloMenu);
        g.setColor(Color.RED);
        g.drawString("SELEZIONA COLORE",330,100);

        Font fontbottoni = new Font ("arial", Font.BOLD, 30);
        g.setFont(fontbottoni);
        g.setColor(Color.white);
        g.drawString("ARANCIONE", bottonePlay.x+60, bottonePlay.y+35);
        g.drawString("NERO", bottoneChiudi.x+100, bottoneChiudi.y+35 );
        g.drawString("GIALLO", bottoneAltro.x+90, bottoneAltro.y+35 );
        g2d.draw(bottonePlay);
        g2d.draw(bottoneAltro);
        g2d.draw(bottoneChiudi);

    }
    public void mousePressed (MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        if(mouseX>=Table.BOARD_WIDTH/4 + 150 && mouseX <= Table.BOARD_WIDTH/4 + 450 ){
            if(RunGame.statoAttuale== RunGame.STATO.MENU) {
                if (mouseY >= 240 && mouseY <= 290) {
                    RunGame.statoAttuale = RunGame.STATO.GIOCO;
                    System.out.println(RunGame.statoAttuale);
                    tavolo = new Table();
                    //tavolo.initBoard();
                }
                else if (mouseY >= 540 && mouseY <= 590) {
                    System.exit(1);
                }
                else if (mouseY >= 390 && mouseY <= 440) {
                    RunGame.statoAttuale = RunGame.STATO.COLORI;
                }
            }
            else {
                if (mouseY >= 240 && mouseY <= 290) {
                    colorePalle = Color.orange;
                    RunGame.statoAttuale = RunGame.STATO.MENU;
                }
                else if (mouseY >= 540 && mouseY <= 590) {
                    colorePalle= Color.black;
                    RunGame.statoAttuale = RunGame.STATO.MENU;
                }
                else if (mouseY >= 390 && mouseY <= 440) {
                    colorePalle= Color.YELLOW;
                    System.out.println("il colore é:"+ colorePalle);
                    RunGame.statoAttuale = RunGame.STATO.MENU;
                }
            }
    }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

