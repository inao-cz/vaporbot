package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import java.awt.*;
import java.util.Arrays;

public final class Help extends Command {
    public Help(NewMain instance){
        super(instance, "help", "help", null);
    }
    @Override
    public void onCommand(Message message, NewMain instance, String[] args){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(message.getAuthor().getDisplayName(), null, message.getAuthor().getAvatar());
        builder.setColor(Color.orange);
        for(Command command : instance.getCommands()){
            builder.addField("Command: " + command.getCommand(), "Usage: " + instance.getConfig().getApi("prefix") + command.getUsage() + "\n" + "Aliases: " + Arrays.toString(command.getAliases()).replaceAll("null", "None").replaceAll("\\[", "").replaceAll("\\]", ""));
        }
        new MessageBuilder().setEmbed(builder).send(message.getChannel());

    }
}
