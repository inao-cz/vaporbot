package me.inao.botforgod.classes;

import me.inao.botforgod.NewMain;
import org.javacord.api.entity.channel.Channel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.TimerTask;

public class Backup extends TimerTask {
    private NewMain instance;
    public Backup(NewMain instance){
        this.instance = instance;
    }
    @Override
    public void run() {
        for(Channel channel : instance.getApi().getChannels()){
            Connection connection = instance.getSqLite().openConnection();
            try{
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO backup DATA (?, ?, ?, ?, ?)"
                );

            }catch (Exception e){
                new ExceptionCatcher(e);
            }
        }
    }
}
