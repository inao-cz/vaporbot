package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;

import java.awt.*;

public final class Ping extends Command {
    public Ping(NewMain instance){
        super(instance, "ping", "ping", "production", new String[]{"png"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {

        new me.inao.botforgod.utils.Message("ping", instance.getConfig().getMessage("messageGenericSuccess", null, this), Color.yellow, message.getChannel());

        new me.inao.botforgod.utils.Message(message.getAuthor(), "Ping", "pong", Color.RED, message.getChannel());
    }
}
