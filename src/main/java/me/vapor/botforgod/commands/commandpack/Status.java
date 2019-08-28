package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import java.awt.*;
import java.net.InetAddress;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Status extends Command {
    public Status(NewMain instance){
        super(instance, "status", "status", new String[]{"stat"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(!(LocalTime.now().isAfter(instance.getStatusLimit()))){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageStatusTimeout", new String[][]{
                    {"%time%", instance.getStatusLimit().format(DateTimeFormatter.ofPattern("HH:mm"))}
            }, this), Color.BLACK, message.getChannel());
            return;
        }
        instance.setStatusLimit(LocalTime.now().plusMinutes(5));
        try{
            InetAddress addr = InetAddress.getByName("skidclient.tk");
            boolean reachable = addr.isReachable(1000);
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Status of Skid server", instance.getConfig().getMessage("messageStatusSuccess", new String[][]{
                    {"%stat%", reachable ? "reachable" : "unreachable"}
            }, this), Color.black, message.getChannel());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
