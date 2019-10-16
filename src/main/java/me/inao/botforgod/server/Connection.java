package me.inao.botforgod.server;

import com.google.gson.Gson;
import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.ExceptionCatcher;
import me.inao.botforgod.classes.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection extends Thread{
    private NewMain instance;
    private Socket socket;
    private BufferedReader reader = null;
    private PrintWriter writer = null;
    public Connection(NewMain instance, Socket socket){
        this.instance = instance;
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String json = new AesUtility(instance).getDecrypted(reader.readLine());
            Packet object = new Gson().fromJson(json, Packet.class);
            for(String key : instance.getConfig().getOption("allowedKeys").split(",")){
                if(object.getToken().equals(key)){
                    if(exec(object.getAction(), object.getOrigin(), object.getMessage())){
                        new Log("Executed " + object.getAction() + " sent from " + socket.getInetAddress());
                    }
                    break;
                }
            }

        }catch (Exception e){
            new ExceptionCatcher(e);
        }
        finally{
            try{
                socket.close();
            }catch (Exception e){
                new ExceptionCatcher(e);
            }
        }
    }

    private boolean exec(String action, String origin, String message){

        return true;
    }

    public void send(String message){
        String encryted = new AesUtility(instance).getEncrypted(message);
        writer.println(encryted);
    }
}
