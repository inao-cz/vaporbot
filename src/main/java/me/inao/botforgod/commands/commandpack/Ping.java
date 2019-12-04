package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.Captcha;
import me.inao.botforgod.classes.Captcha2;
import me.inao.botforgod.classes.ExceptionCatcher;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;

public final class Ping extends Command {
    public Ping(NewMain instance){
        super(instance, "ping", "ping", "production", new String[]{"png"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        //new me.inao.botforgod.utils.Message(message.getAuthor(), "Pingerino", new Captcha2(instance, message.getUserAuthor().get()).crawler(), Color.CYAN, message.getChannel());
        Captcha2 captcha2 = new Captcha2(instance, message.getUserAuthor().get());
        int cat = captcha2.category(0);
        try{
            new MessageBuilder().setEmbed(
                    new EmbedBuilder()
                            .setColor(Color.RED)
                            .setTitle("Images are downloaded from FLICKR")
                            .addField("Question", "Is on any of images " + captcha2.getCategories()[cat].replace("_", " ") + "?")
                   ).addAttachment(new URL(captcha2.crawler(captcha2.category(1))))
                    .addAttachment(new URL(captcha2.crawler(cat)))
                    .send(message.getChannel());
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
    }
}
