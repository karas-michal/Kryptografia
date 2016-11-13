package com.company;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by karas on 12.11.2016.
 */
public class FileEncrytption {
    private static String cipherParameters = "AES/CBC/PKCS5Padding";
    private static byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private static IvParameterSpec ivspec = new IvParameterSpec(iv);
    public static void encrypt(File inputFile, File outputFile, String keyAlias,
                               String pass, String keystoreLocation, String encParam)
            throws CryptoException, IOException {
        SecretKey secretKey = KeystoreMethods.getKeyFromKeystore(pass, keystoreLocation,
                keyAlias);
        if (encParam.equals("CTR")){
            cipherParameters = "AES/CTR/PKCS5Padding";
        }
        else if(encParam.equals("GCM"))
            cipherParameters = "";
        doCrypto(Cipher.ENCRYPT_MODE, secretKey, inputFile, outputFile);
    }

    public static void encrypt(File inputFile, File outputFile)
            throws CryptoException, IOException {
        byte[] decodedKey = Base64.getDecoder().decode("Hjfr35usjm9vajt2SRaNRg==");
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        doCrypto(Cipher.ENCRYPT_MODE, secretKey, inputFile, outputFile);
    }

    public static void decrype(File inputFile, File outputFile)
            throws CryptoException, IOException {
        byte[] decodedKey = Base64.getDecoder().decode("Hjfr35usjm9vajt2SRaNRg==");
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        doCrypto(Cipher.DECRYPT_MODE, secretKey, inputFile, outputFile);
    }

    public static void decrypt(File inputFile, File outputFile, String keyAlias,
                               String pass, String keystoreLocation, String encParam)
            throws CryptoException, IOException {
        SecretKey secretKey = KeystoreMethods.getKeyFromKeystore(pass, keystoreLocation,
                keyAlias);
        doCrypto(Cipher.DECRYPT_MODE, secretKey, inputFile, outputFile);
    }

    private static void doCrypto(int cipherMode, SecretKey secretKey, File inputFile,
                                 File outputFile) throws CryptoException {
        try {
            Cipher cipher = Cipher.getInstance(cipherParameters);
            cipher.init(cipherMode, secretKey, ivspec);
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
