package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import me.vapor.botforgod.classes.Countgame;
import org.javacord.api.entity.message.Message;
import java.awt.*;

public final class Count extends Command {
    public Count(NewMain instance) {
        super(instance, "count", "count <finish>", null);
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(!message.getAuthor().canCreateChannelsOnServer()){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericNoPerms", null, this), Color.RED, message.getChannel());
            return;
        }
        if(instance.getCountgame() != null){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericFail", null, this), Color.RED, message.getChannel());
            return;
        }
        if(args.length < 1 || args.length > 2){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericArgsErr", null, this), Color.RED, message.getChannel());
            return;
        }
        try{
            Long.parseLong(args[0]);
            if(args.length == 2){
                Long.parseLong(args[1]);
            }
        }catch (NumberFormatException | NullPointerException e){
            new me.vapor.botforgod.utils.Message("Error!", instance.getConfig().getMessage("messageGenericFail", null, this), Color.RED, message.getChannel());
            return;
        }
        if(Long.parseLong(args[0]) < 3){
            new me.vapor.botforgod.utils.Message("Error!", instance.getConfig().getMessage("messageGenericFail", null, this), Color.RED, message.getChannel());
            return;
        }
        if(args.length == 1){
            instance.setCountgame(new Countgame(instance, 0, Long.parseLong(args[0]), message.getChannel()));
            return;
        }
        instance.setCountgame(new Countgame(instance, Long.parseLong(args[1]), Long.parseLong(args[0]), message.getChannel()));
    }
}
