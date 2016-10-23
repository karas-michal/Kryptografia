package com.company;

import javax.xml.bind.DatatypeConverter;

public class Main{

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }


    public static void main(String[] args) {
        String a = "4vf9kw9eXLHPcx1Fvtl6PvGW66gO5OIxXOhEElf/zy/2tVHJFlDG1ASJP7MPOIss3U+83FaICSPjbnhw7" +
                "dL8Tx+LlqWrwDDiytmNuGSsxTSIufhniL0IIPDKg+NRIFGW9TDk7JH8sXk3V/2cIaIhXVMjFffZYfOT1xutwI" +
                "MHoj36WBrIij3f2DaLT7f1va833D63TZwD0ruKCWikz8NAbiDqt+e8CQplTsIwhKsFq3jPWbexyw3CalzQV/+T" +
                "c3aApOurxfiHNm+aKoIy4x4O1A==";
       My_Decoder decoder1 = new My_Decoder(0,"8c4b25e24530d36890721adaf44a0863",
                "2a4d6318e8eddd4bb29ac152aaa3804f083232432b9bb1fe881b9062",a);
        My_Decoder decoder2 = new My_Decoder(1073741824L,"8c4b25e24530d36890721adaf44a0863",
                "2a4d6318e8eddd4bb29ac152aaa3804f083232432b9bb1fe881b9062",a);
        My_Decoder decoder3 = new My_Decoder(1073741824L*2,"8c4b25e24530d36890721adaf44a0863",
                "2a4d6318e8eddd4bb29ac152aaa3804f083232432b9bb1fe881b9062",a);
        My_Decoder decoder4 = new My_Decoder(1073741824L*3,"8c4b25e24530d36890721adaf44a0863",
                "2a4d6318e8eddd4bb29ac152aaa3804f083232432b9bb1fe881b9062",a);
        /*My_Decoder decoder1 = new My_Decoder(1288490184L,"3f5e2ca8cda5cb92cd80f97bd14b8273",
                "2a4d6318e8eddd4bb29ac152aaa3804f083232432b9bb1fe881b9062",a);
        My_Decoder decoder2 = new My_Decoder(1610612730L,"3f5e2ca8cda5cb92cd80f97bd14b8273",
                "2a4d6318e8eddd4bb29ac152aaa3804f083232432b9bb1fe881b9062",a);
        My_Decoder decoder3 = new My_Decoder(1932735276L,"3f5e2ca8cda5cb92cd80f97bd14b8273",
                "2a4d6318e8eddd4bb29ac152aaa3804f083232432b9bb1fe881b9062",a);
        My_Decoder decoder4 = new My_Decoder(2254857822L,"3f5e2ca8cda5cb92cd80f97bd14b8273",
                "2a4d6318e8eddd4bb29ac152aaa3804f083232432b9bb1fe881b9062",a);*/
        for (int i = 0; i < 100; i++) {
            System.out.print("|");
        }
        System.out.println();
        new Thread(decoder1).start();
        new Thread(decoder2).start();
        new Thread(decoder3).start();
        new Thread(decoder4).start();
    }



    public static String toHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }

    /*public static String encrypt(String text, String key, String initVector) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(hexStringToByteArray(initVector));
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(toByteArray(key));
        SecretKeySpec skeySpec = new SecretKeySpec(hexStringToByteArray(key), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encryptedTextBytes = cipher.doFinal(text.getBytes("UTF-8"));

        return DatatypeConverter.printBase64Binary(encryptedTextBytes);
    }*/
}


