package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import java.awt.*;

public final class Prasoprd extends Command {
    public Prasoprd(NewMain instnace){
        super(instnace, "prasoprd", "prasoprd <msg>", "Prasoprd", new String[]{"spam", "spm"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, java.lang.String[] args) {
        if(args.length == 0){
            new me.inao.botforgod.utils.Message("Error!", instance.getConfig().getMessage(
                    "messageGenericArgsErr",
                    null,
                    this
            ), Color.RED, message.getChannel());
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String arg: args) {
            builder.append(arg).append(" ");
        }
        new MessageBuilder().setEmbed(new EmbedBuilder().setColor(Color.PINK).addField("cs debilku", builder.toString())).send(instance.getApi().getUserById("609557496872304643").join());
        new me.inao.botforgod.utils.Message("Sent!", "PM has been sent to PRASOPRD", Color.BLUE, message.getChannel());
    }
}
