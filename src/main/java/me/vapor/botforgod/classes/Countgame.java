package me.vapor.botforgod.classes;

import me.vapor.botforgod.NewMain;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import java.awt.*;

public class Countgame {
    private long num, max;
    private NewMain instance;
    private TextChannel channel;
    public Countgame(NewMain instance, long num, long max, TextChannel channel){
        this.instance = instance;
        this.num = num;
        this.max = max;
        this.channel = channel;
        new me.vapor.botforgod.utils.Message("CountGame Created!", instance.getConfig().getMessage("messageCountgameCreate", new String[][]{
                {"%number%", String.valueOf(max)}
        }, null), Color.CYAN, channel);
    }
    public void onSend(Message message){
        if(!(message.getChannel().getId() == channel.getId()) || message.isPrivateMessage()){
            return;
        }
        if(message.getAuthor().canCreateChannelsOnServer() && message.getContent().equalsIgnoreCase("close")){
            new me.vapor.botforgod.utils.Message("CountGame complete!", instance.getConfig().getMessage("messageCountgameClosed", new String[][]{{"%user%", message.getUserAuthor().get().getDiscriminatedName()}}, null), Color.yellow, channel);
            instance.setCountgame(null);
            channel.asServerTextChannel().get().updateTopic("");
            return;
        }
        if(!message.getContent().equals(String.valueOf(num)) && !message.getAuthor().isYourself()){
            message.delete();
            return;
        }
        if(num == max){
            new me.vapor.botforgod.utils.Message("CountGame complete!", instance.getConfig().getMessage("messageCountgameComplete", new String[][]{{"%user%", message.getUserAuthor().get().getDiscriminatedName()}}, null), Color.yellow, channel);
            instance.setCountgame(null);
            channel.asServerTextChannel().get().updateTopic("");
            return;
        }
        channel.asServerTextChannel().get().updateTopic("Next number is: " + String.valueOf(num + 1));
        num++;
    }
}
