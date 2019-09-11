package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import java.awt.*;

public final class Unmute extends Command {
    private NewMain instance;
    public Unmute(NewMain instance){
        super(instance, "unmute", "unmute <id>", new String[]{"unmut"});
        this.instance = instance;
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(!message.getAuthor().canMuteMembersOnServer()){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageGenericNoPerms", null, this), Color.RED, message.getChannel());
            return;
        }
        message.delete();
        if(instance.getMuted().size() == 0){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageUnmuteNoMutes", null, this), Color.RED, message.getChannel());
            return;
        }
        if(args.length == 0){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageGenericArgsErr", null, this), Color.RED, message.getChannel());
            return;
        }
        if(instance.getMuted().remove(args[0])){
            new me.vapor.botforgod.utils.Message("Unmute", instance.getConfig().getMessage("messageUnmuteSuccess", new String[][]{
                    {"%user%", instance.getApi().getUserById(args[0]).join().getDiscriminatedName()},
                    {"%id%", args[0]}
            }, this), Color.GREEN, message.getServer().get().getChannelsByName("public-mute").get(0).asTextChannel().get());
            return;
        }
        new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageUnmuteNotMuted", null, this), Color.RED, message.getChannel());
    }
}
