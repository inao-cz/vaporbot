package me.inao.botforgod.utils;

import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.ExceptionLogger;
import java.awt.*;
import java.io.File;

public final class Message {
    public Message(MessageAuthor author, String title, String message, Color color, TextChannel channel){
        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setTitle(title)
                .setDescription(message)
                .setColor(color)
                .setAuthor(author.getDisplayName(), null, author.getAvatar())
        ).send(channel);
    }
    public Message(String title, String message, String footer, Color color, TextChannel channel){
        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setTitle(title)
                .setDescription(message)
                .setColor(color)
                .setFooter(footer)
        ).send(channel);
    }
    public Message(String title, String message, Color color, TextChannel channel){
        new MessageBuilder().setEmbed(new EmbedBuilder().setTitle(title).setDescription(message).setColor(color)).send(channel);
    }
    public Message(String title, String message, Color color, File image, ServerTextChannel channel){
        new MessageBuilder().setEmbed(new EmbedBuilder().setTitle(title).setDescription(message).setColor(color).setImage(image)).send(channel).exceptionally(ExceptionLogger.get());
        try{
            Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }
        image.delete();
    }
    public Message(String title, String message, Color color, ServerTextChannel channel){
        new MessageBuilder().setEmbed(new EmbedBuilder().setTitle(title).setDescription(message).setColor(color)).send(channel).exceptionally(ExceptionLogger.get());
    }
    public Message(Command command, String message, Color color, TextChannel channel){
        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setTitle("Command " + command.getCommand())
                .setDescription(message)
                .setColor(color)
        ).send(channel);
    }
}
