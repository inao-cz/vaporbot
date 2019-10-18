package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.util.UUID;

public class GenSrvKey extends Command {
   public GenSrvKey(NewMain instance){
       super(instance, "genacckey", "genacckey", "externalServer", null);
   }
    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
       if(!message.getAuthor().isServerAdmin()){
           return;
       }
        message.getAuthor().asUser().ifPresent(user ->
                new MessageBuilder().setEmbed(
                        new EmbedBuilder()
                                .setColor(Color.BLACK)
                                .setAuthor(message.getAuthor())
                                .addField("Access Key", instance.getConfig().getMessage(
                                        "messageKeyGenerated",
                                        new String[][]{{"%key%", UUID.randomUUID().toString().replaceAll("-", "")},
                                                        {"%iv%", ""}
                                        },
                                        this
                                        )
                                )
                ).send(user));
    }
}
