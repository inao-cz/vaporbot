package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import me.inao.botforgod.config.Config;
import org.javacord.api.entity.message.Message;

import java.awt.*;

public class Reload extends Command {
    public Reload(NewMain instance){
        super(instance, "reload", "", "Reload", null);
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(message.getAuthor().isServerAdmin()){
            instance.loadConfig();
        }
        new me.inao.botforgod.utils.Message(message.getAuthor(), "Reloaded", "Reload complete..", Color.GREEN, message.getChannel());
    }
}
