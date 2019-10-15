package me.inao.botforgod.server;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.ExceptionCatcher;
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
            String[] command = new AesUtility(instance).getDecrypted(reader.readLine()).split("§");
            for(String key : instance.getConfig().getOption("allowedKeys").split(",")){
                if(command[0].equals(key)){
                    exec(command);
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

    private boolean exec(String[] args){

        return true;
    }

    public void send(String message){
        String encryted = new AesUtility(instance).getEncrypted(message);
        writer.println(message);
    }
}
