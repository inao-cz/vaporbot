package me.inao.botforgod.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Connectiontoweb {
    private String url;
    public Connectiontoweb(String url){
        this.url = url;
    }
    public String getContent(){
        try{
            URL url = new URL(this.url);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10; Win64; x64; rv:67.0) Gecko/20100101 Firefox/67.0");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line);
            }
            reader.close();
            return builder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
