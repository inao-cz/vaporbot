package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.Captcha;
import me.inao.botforgod.classes.Captcha2;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;

import java.awt.*;
import java.util.Arrays;

public final class Ping extends Command {
    public Ping(NewMain instance){
        super(instance, "ping", "ping", "production", new String[]{"png"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        new me.inao.botforgod.utils.Message(message.getAuthor(), "Pingerino", new Captcha2(instance, message.getUserAuthor().get()).crawler(), Color.CYAN, message.getChannel());
        //new me.inao.botforgod.utils.Message(message.getAuthor(), "Ping", Arrays.toString(instance.getCaptchas().toArray()), Color.RED, message.getChannel());
    }
}
