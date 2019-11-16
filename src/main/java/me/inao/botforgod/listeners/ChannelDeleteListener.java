package me.inao.botforgod.listeners;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.ExceptionCatcher;
import org.javacord.api.event.channel.server.ServerChannelDeleteEvent;
import org.javacord.api.listener.channel.server.ServerChannelDeleteListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

public class ChannelDeleteListener implements ServerChannelDeleteListener{
    private NewMain instance;
    public ChannelDeleteListener(NewMain instance){
        this.instance = instance;
    }
    @Override
    public void onServerChannelDelete(ServerChannelDeleteEvent e) {
        if(e.getChannel().getName().contains("-")){
            if(e.getChannel().getName().split("-")[0].equals("captcha")){
                for(String captcha : instance.getCaptchas()){
                    if(captcha.split(":")[0].equalsIgnoreCase(e.getChannel().getName().split("-")[1])){
                        try{
                            Connection connection = instance.getSqLite().openConnection();
                            if(connection == null){
                                instance.getSqLite().openConnection();
                            }
                            PreparedStatement stmt = connection.prepareStatement(
                                    "DELETE FROM captchas WHERE id = ?"
                            );
                            stmt.setString(1, captcha.split(":")[0]);
                            instance.getSqLite().execute(connection, stmt);
                        }catch (Exception ex){
                            new ExceptionCatcher(ex);
                        }
                        instance.getCaptchas().remove(captcha);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(instance.getCaptchas().toArray()));
    }
}
