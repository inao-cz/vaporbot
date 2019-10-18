package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import me.inao.botforgod.server.AesUtility;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class GenKey extends Command {
    private NewMain instance;
    public GenKey(NewMain instance){
        super(instance, "genkey", "genkey", "externalServer", null);
        this.instance = instance;
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(message.getAuthor().isServerAdmin()){
            String key = new AesUtility(instance).getKey();
            String iv = new AesUtility(instance).getIv();
            message.getAuthor().asUser().ifPresent(user ->
                    new MessageBuilder().setEmbed(
                            new EmbedBuilder()
                                    .setColor(Color.BLACK)
                                    .setAuthor(message.getAuthor())
                                    .addField("Key", instance.getConfig().getMessage(
                                            "messageKeyGenerated",
                                            new String[][]{{"%key%", "Key: " + key},
                                                            {"%iv%", "IV: " + iv}},
                                            this))
                    ).send(user));
        }
    }
}
