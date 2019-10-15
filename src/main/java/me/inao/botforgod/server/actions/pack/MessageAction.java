package me.inao.botforgod.server.actions.pack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.server.Connection;
import me.inao.botforgod.server.actions.Action;
import me.inao.botforgod.utils.Message;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.Channel;

import java.awt.*;

public class MessageAction extends Action {
    public MessageAction(NewMain instance, String[] args){
        super(instance, "MessageAction", args);
    }
    @Override
    public void onAction(String[] args) {

    }
}
