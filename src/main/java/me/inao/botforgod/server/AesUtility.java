package me.inao.botforgod.server;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.ExceptionCatcher;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AesUtility {
    private NewMain instance;
    public AesUtility(NewMain instance){
        this.instance = instance;
    }
    public String getEncrypted(String plain){
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(Base64.getDecoder().decode(instance.getConfig().getOption("aesKey")), "AES");
            IvParameterSpec ivsp = new IvParameterSpec(Base64.getDecoder().decode(instance.getConfig().getOption("aesIv").getBytes()));
            cipher.init(Cipher.ENCRYPT_MODE, spec, ivsp);
            String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(plain.getBytes()));
            return encrypted;
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
        return null;
    }
    public String getDecrypted(String cipherT){
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(Base64.getDecoder().decode(instance.getConfig().getOption("aesKey")), "AES");
            IvParameterSpec ivsp = new IvParameterSpec(Base64.getDecoder().decode(instance.getConfig().getOption("aesIv").getBytes()));
            cipher.init(Cipher.DECRYPT_MODE, spec, ivsp);
            String decrypted = new String(cipher.doFinal(Base64.getDecoder().decode(cipherT.getBytes())));
            return decrypted;
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
        return null;
    }

    public String getIv(){
        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
        return Base64.getEncoder().encodeToString(IV);
    }

    public String getKey(){
        try{
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(256);
            SecretKey key = generator.generateKey();
            return Base64.getEncoder().encodeToString(key.getEncoded());
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
        return null;
    }
}
