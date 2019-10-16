package me.inao.botforgod.server.actions.pack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.server.actions.Action;
import me.inao.botforgod.utils.Message;
import org.javacord.api.entity.channel.Channel;

import java.awt.*;

public class MessageAction extends Action {
    public MessageAction(NewMain instance, String origin, String channel, String message){
        super(instance, "MessageAction", origin, channel, message);
    }

    @Override
    public void onAction(String message, String origin, Channel channel) {
        new Message(origin + " Remote message", message, Color.magenta, channel.asTextChannel().get());
    }
}
