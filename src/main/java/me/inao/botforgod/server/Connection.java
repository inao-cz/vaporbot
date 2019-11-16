package me.inao.botforgod.server;

import com.google.gson.Gson;
import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.ExceptionCatcher;
import me.inao.botforgod.classes.Log;
import me.inao.botforgod.server.actions.Action;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String json = new AesUtility(instance).getDecrypted(reader.readLine());
            Packet object = new Gson().fromJson(json, Packet.class);
            for(String key : instance.getConfig().getOption("allowedKeys").split(",")){
                if(object.getToken().equals(key)){
                    if(exec(object.getAction(), object.getMessage(), object.getOrigin(), object.getChannel())){
                        new Log("Executed " + object.getAction() + " sent from " + socket.getInetAddress());
                    }
                    break;
                }
            }
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
    }

    private boolean exec(String name, String message, String origin, String cid){
        for(Action exec : instance.getServerActions()){
            if(exec.getName().equals(name)){
                exec.onAction(instance, message, origin, instance.getApi().getChannelById(cid).get());
                return true;
            }
        }
        return false;
    }

    public void send(String message){
        String encryted = new AesUtility(instance).getEncrypted(message);
        writer.println(encryted);
    }
}
