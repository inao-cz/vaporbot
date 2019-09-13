package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import java.awt.*;
import java.util.Arrays;

public final class Help extends Command {
    public Help(NewMain instance){
        super(instance, "help", "help", null, null);
    }
    @Override
    public void onCommand(Message message, NewMain instance, String[] args){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(message.getAuthor().getDisplayName(), null, message.getAuthor().getAvatar());
        builder.setColor(Color.orange);
        for(Command command : instance.getCommands()){
            builder.addField("Command: " + command.getCommand(), "Usage: " + instance.getConfig().getOption("prefix") + command.getUsage() + "\n" + "Aliases: " + Arrays.toString(command.getAliases()).replaceAll("null", "None").replaceAll("\\[", "").replaceAll("\\]", ""));
        }
        new MessageBuilder().setEmbed(builder).send(message.getChannel());

    }
}
