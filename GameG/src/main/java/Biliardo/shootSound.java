package Biliardo;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class shootSound {
    public static void shSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner s=new Scanner(System.in);
        File f=new File("GameG/src/main/resources/audio/THUNK2.wav");
        AudioInputStream a= AudioSystem.getAudioInputStream(f);
        Clip clip=AudioSystem.getClip();
        clip.open(a);
        clip.start();


    }





}
