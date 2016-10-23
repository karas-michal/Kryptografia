package com.company;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


import static com.company.Main.toByteArray;

/**
 * Created by karas on 20.10.2016.
 */
public class My_Decoder implements Runnable{

    private long startPoint;
    private String initVector;
    private String suffix;
    private String encrypted;

    private String decrypt(String key, String initVector, String encrypted) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {
        IvParameterSpec iv = new IvParameterSpec(toByteArray(initVector));
        SecretKeySpec skeySpec = new SecretKeySpec(toByteArray(key), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.decodeFast(encrypted));
        return new String(original);
    }

    public My_Decoder(long startPoint, String initVector, String suffix, String encrypted) {
        this.startPoint = startPoint;
        this.initVector = initVector;
        this.suffix = suffix;
        this.encrypted = encrypted;
    }


    private boolean isMessageCheck(String str){
        for (int i=0; i<str.length()/2; i++) {
            char c = str.charAt(i);
            if(c>0xFFFC){
            //if ((c>0x5 && c < 0x9) || (c>0xb && c < 0x20) || (c>=0x80 && c<=0x9f) || c > 0x17F) {
                //System.out.format("%04x ", (int) c);
                return false;
            }
        }
        return true;
    }


    private String bruteForcing(String suffix, String initVector, String encrypted){
        String output;
        long retryCount = 0L;
        int licznik = 21474836;
        long possibilityCount = 1073741824L;//536870912L;//1073741824L;
        long prefix = startPoint;
        String key;
        //Pattern pattern = Pattern.compile("\\A(\\p{InBASIC_LATIN}|\\p{P}|\\p{Space}|[ążćłóęśź])*\\z");
        while(true) {
            try {
                StringBuilder buf = new StringBuilder(Long.toHexString(prefix));
                licznik--;
                if(licznik == 0){
                    System.out.print("|");
                    licznik = 21474836;
                }
                //prefix++;
                while (buf.toString().length() < (64-suffix.length())) {
                    buf.insert(0, '0');
                }
                if(buf.length()-1 < (64-suffix.length()))
                    key = buf.toString() + suffix;
                else
                    key = suffix;
                prefix++;
                //System.out.println(key);
                output = decrypt(key, initVector, encrypted);
                /*Matcher matcher = pattern.matcher(output);
                /*if(!matcher.matches())*/
                if(!isMessageCheck(output) || key.length()>64)
                    throw new BadPaddingException();
                System.out.println(key);
                break;
            }
            catch (BadPaddingException e){
                if(retryCount > possibilityCount)
                    throw new RuntimeException("Could not execute",e);
                retryCount++;
                //retryCount++;
            }
            catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                    IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
                e.printStackTrace();
                return null;
            }
        }
        return output;

    }

    @Override
    public void run() {
        String a = "zyF/RlyOGc0X/FKraTrmSMCyHcRkaofi0Vdwn+lz/yfQ2Jy1c88c0MVIjjYtaa7v5vpGye13jszj+WXN0SeTLWRULj" +
                "41caez7N8+8MVpNKqoMKfgJV9rHJuDikd/13Z8Qr6gF9c2DLvspHTAXNVTvg==";
        System.out.print(bruteForcing("039f3f4505eac54d300bde42ef161f4ef6d611389927490fed7bcb2f4acaded8",
                "a77a52796d1d5b06360cb5feb227b31a",a));

        System.out.print(bruteForcing(suffix,initVector,encrypted));
    }
}
