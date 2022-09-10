package Biliardo;

import java.awt.*;

public class PallaBianca extends Ball{

    public PallaBianca(int posx, int posy,int number) {
        super(posx, posy,number);
        setColore(Color.white);
    }
}
