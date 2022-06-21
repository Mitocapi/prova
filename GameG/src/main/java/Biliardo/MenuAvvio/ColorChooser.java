package Biliardo.MenuAvvio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ColorChooser extends JFrame{

    public static Color colorePalle;
    public static int selected=0;
    ColorChooser(){
        colorePalle=JColorChooser.showDialog(this,"Choose",Color.CYAN);
        System.out.println(colorePalle.toString());
        selected=1;
    }


}