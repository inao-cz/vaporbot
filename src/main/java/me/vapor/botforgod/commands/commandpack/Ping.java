package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.message.Message;

import java.awt.*;

public final class Ping extends Command {
    public Ping(NewMain instance){
        super(instance, "ping", "ping", new String[]{"png"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {

        new me.vapor.botforgod.utils.Message("ping", instance.getConfig().getMessage("messageGenericSuccess", null, this), Color.yellow, message.getChannel());

        new me.vapor.botforgod.utils.Message(message.getAuthor(), "Ping", "pong", Color.RED, message.getChannel());
    }
}
