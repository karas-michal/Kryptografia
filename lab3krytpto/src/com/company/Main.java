package com.company;

import javafx.application.Application;
import javafx.scene.media.MediaView;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;



public class Main {
    public static String getPassword(){
        Console cons = System.console();
        char[] pass = cons.readPassword("");
        return new String(pass);
    }

    public static void main(String[] args) {
        if(args.length == 2){
            String keystoreLocation = args[0];
            String keyAlias = args[1];
            System.out.println("Password for keystore");
            String password = getPassword();
            System.out.println("PIN");
            String pins = getPassword();
            int pin = Integer.parseInt(pins);
            MusicPlayer mp = new MusicPlayer(keystoreLocation, keyAlias, password, pin, "");
        }
        else if(args.length == 1){
            String fileLocation = args[0];
            System.out.println("PIN");
            int pin = Integer.parseInt(getPassword());
            MusicPlayer mp = new MusicPlayer("","","", pin, fileLocation);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.println("controls: p - play, s - stop, t - seek time");
                String s = br.readLine();
                while (!s.equals(" ")){
                    if(s.equals("p"))
                        mp.play();
                    if(s.equals("s"))
                        mp.stop();
                    if(s.equals("t")){
                        System.out.println("minutes");
                        String min = br.readLine();
                        System.out.println("seconds");
                        String sec = br.readLine();
                        mp.seek(min, sec);
                    }
                    s = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {

        }
/*
        KeystoreMethods.createKeystore("password","keystore");
        try {
            KeystoreMethods.WrtiteKeyToKeystore("keystore", "password", "key");
            FileEncrytption.encrypt(new File("test.wav"), new File("test.enc"), "key",
                    "password", "keystore", "");
        } catch (IOException | CryptoException e) {
            e.printStackTrace();
        }

        try {
            FileEncrytption.decrypt(new File("test.enc"), new File("test.mp3"), "key",
                    "password", "keystore", "");
        } catch (CryptoException | IOException e) {
            e.printStackTrace();
        }
*/
    }
}

