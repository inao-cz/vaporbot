package me.inao.botforgod.server.actions.pack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.server.actions.Action;
import me.inao.botforgod.utils.Message;
import org.javacord.api.entity.channel.Channel;

import java.awt.*;

public class MessageAction extends Action {
    public MessageAction(NewMain instance){
        super(instance, "MessageAction");
    }

    @Override
    public void onAction(NewMain instance, String message, String origin, Channel channel) {
        new Message(origin + " Remote message", message, Color.magenta, channel.asTextChannel().get());
    }
}
