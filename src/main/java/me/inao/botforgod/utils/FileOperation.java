package me.inao.botforgod.utils;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOperation {

    public File getFile(String name) {
        File f = new File(name);
        if (f.isFile()) {
            return f;
        }else{
            try{
                if(f.createNewFile()){
                    return f;
                }
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public boolean writeFile(File f, String text) {
        try {
            FileWriter writer = new FileWriter(f);
            writer.write(String.valueOf(text));
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readFile(File f){
        try{
            byte[] encoded = Files.readAllBytes(Paths.get(f.getPath()));
            return new String(encoded, StandardCharsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
