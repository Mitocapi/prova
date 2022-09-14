package Biliardo.MenuAvvio;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ColorChooser2 extends JFrame{

    public static Color colorePalle2;
    public static int selected=0;
    ColorChooser2(){
        colorePalle2=JColorChooser.showDialog(this,"Choose",Color.CYAN);
        System.out.println(colorePalle2.toString());
        selected=1;
    }


}
