package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.Captcha;
import me.inao.botforgod.classes.ExceptionCatcher;
import me.inao.botforgod.commands.Command;
import me.inao.botforgod.server.AesUtility;
import org.javacord.api.entity.message.Message;

import java.awt.*;
import java.util.Arrays;

public final class Ping extends Command {
    public Ping(NewMain instance){
        super(instance, "ping", "ping", "production", new String[]{"png"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
//        new Captcha(instance, instance.getApi().getUserById(
//                "588686886223216640"
//        ).join());
        new me.inao.botforgod.utils.Message(message.getAuthor(), "Ping", Arrays.toString(instance.getCaptchas().toArray()), Color.RED, message.getChannel());
    }
}
