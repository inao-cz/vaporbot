package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.ExceptionCatcher;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;

import java.awt.*;
import java.util.Random;

public class Vote extends Command{
    public Vote(NewMain instance){
        super(instance, "vote", "vote <minutes> <what>", "Vote", null);
    }
    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(!message.getAuthor().isServerAdmin()){
            new me.inao.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageGenericNoPerms", null, this), Color.RED, message.getChannel());
            return;
        }
        if(args.length < 2){
            new me.inao.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageGenericArgsErr", null, this), Color.RED, message.getChannel());
            return;
        }
        StringBuilder id = new StringBuilder();
        char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_".toCharArray();
        for(int i = 0; i < 7; i++){
            id.append(chars[new Random().nextInt(chars.length)]);
        }
        StringBuilder about = new StringBuilder();
        for(int a = 1; a < args.length; a++){
            about.append(args[a]).append(" ");
        }
        new me.inao.botforgod.utils.Message(
                "Voting ("+id.toString()+")",
                about.toString(),
                "React with ✔ for YES and ❌ for NO",
                Color.GREEN,
                instance.getServer().getChannelsByName("vote").get(0).asTextChannel().get()
        );

        instance.getServer().getChannelsByName("vote").get(0).asServerTextChannel().ifPresent(channel->{
            try{
                channel.getMessages(1).get().getNewestMessage().get().addReactions("✔", "❌");
                instance.getTimer().schedule(new me.inao.botforgod.classes.Vote(instance, channel.getMessages(1).get().getNewestMessage().get().getIdAsString(), id.toString()), (Long.parseLong(args[0]) * 60000));
            }catch (Exception e){
                new ExceptionCatcher(e);
            }
        });
    }
}
