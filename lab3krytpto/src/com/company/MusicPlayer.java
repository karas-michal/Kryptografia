package com.company;

import javafx.application.Application;
import javafx.scene.media.Media;

import javax.media.Manager;
import javax.media.Player;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by karas on 12.11.2016.
 */
public class MusicPlayer {
    private Media soundFile;
    private Player mediaPlayer;

    public MusicPlayer(String keyStoreLocation, String keyAlias, String pass, int pin, String musicFile) {
        try{
            File f = new File("conf.enc");
            if(f.exists()){
                File t = new File("conf.txt");
                FileEncrytption.decrype(f, t);
                BufferedReader br = new BufferedReader((new FileReader("conf.txt")));
                String pinString = br.readLine();
                if(pin==(Integer.parseInt(pinString)/15173)){
                    keyStoreLocation = br.readLine();
                    keyAlias = br.readLine();
                    pass = br.readLine();
                    FileEncrytption.decrypt(new File(musicFile), new File("file.wav"),
                            keyAlias, pass, keyStoreLocation, "");
                    //soundFile = new Media(Paths.get("file.mp3").toUri().toString());
                    mediaPlayer = Manager.createRealizedPlayer(Paths.get("file.wav").toUri().toURL());
                    //mediaPlayer = new MediaPlayer(soundFile);
                    br.close();
                }
                else {
                    System.out.println("WRONG PIN");
                    t.delete();
                    System.exit(0);
                }
                t.delete();
            }
            else if(musicFile.equals("")){
                File t = new File("conf.txt");
                PrintWriter writer = new PrintWriter("conf.txt", "UTF-8");
                writer.println(Integer.toString(pin * 15173));
                writer.println(keyStoreLocation);
                writer.println(keyAlias);
                writer.println(pass);
                writer.close();
                FileEncrytption.encrypt(t, f);
                t.delete();
                System.out.println("Installation finished");
                System.exit(0);
            }
            else {
                System.out.println("Wrong parameters");
                System.exit(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void play(){
        mediaPlayer.start();
    }

    public void stop(){
        mediaPlayer.stop();
    }

    public void seek(String minutes, String sec){
        long dur = Long.parseLong(minutes)*60;
        dur += Long.parseLong(sec);
        dur *= 1000;
        /* mediaPlayer.
        mediaPlayer.seek(new Duration(dur));*/
    }


}
