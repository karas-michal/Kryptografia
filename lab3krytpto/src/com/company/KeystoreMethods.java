package com.company;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

/**
 * Created by karas on 12.11.2016.
 */
public class KeystoreMethods {
    public static int createKeystore(String pass, String filename){
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance("JCEKS");
            char[] password = pass.toCharArray();
            ks.load(null, password);
            FileOutputStream fos = new FileOutputStream(filename);
            ks.store(fos, password);
            fos.close();
            return 1;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int WrtiteKeyToKeystore(String filemane, String password, String keyAlias) throws IOException {
        KeyStore.ProtectionParameter protParam =
                new KeyStore.PasswordProtection(password.toCharArray());
        KeyStore ks;
        OutputStream fis = null;
        try {
            ks = KeyStore.getInstance("JCEKS");
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey sk = kg.generateKey();
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(sk);
            ks.load(null, password.toCharArray());
            ks.setEntry(keyAlias, skEntry, protParam);
            fis = new java.io.FileOutputStream(filemane);
            ks.store(fis, password.toCharArray());
            return 1;
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException e1) {
            e1.printStackTrace();
        } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        return 0;
    }

    public static SecretKey getKeyFromKeystore(String pass, String filename, String keyAlias) throws IOException {
        KeyStore.ProtectionParameter protParam =
                new KeyStore.PasswordProtection(pass.toCharArray());
        KeyStore ks;
        FileInputStream fis = null;
        try {
            ks = KeyStore.getInstance("JCEKS");
            fis = new java.io.FileInputStream(filename);
            ks.load(fis, pass.toCharArray());
            KeyStore.SecretKeyEntry skEntry = (KeyStore.SecretKeyEntry)
                    ks.getEntry(keyAlias, protParam);
            return skEntry.getSecretKey();
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | UnrecoverableEntryException e1) {
            e1.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return null;

    }
}
