package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class GenKey extends Command {
    private NewMain instance;
    public GenKey(NewMain instance){
        super(instance, "genkey", "", "GenKey", null);
        this.instance = instance;
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(message.getAuthor().isServerAdmin()){
            String key = "";
            message.getAuthor().asUser().ifPresent(user ->
                    new MessageBuilder().setEmbed(
                            new EmbedBuilder()
                                    .setColor(Color.BLACK)
                                    .setAuthor(message.getAuthor())
                                    .addField("Key", instance.getConfig().getMessage(
                                            "messageKeyGenerated",
                                            new String[][]{{"%key%", key}},
                                            this
                                            )
                                    )
                    ).send(user));
        }
    }
}
