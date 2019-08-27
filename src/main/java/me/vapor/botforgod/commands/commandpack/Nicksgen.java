package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import java.awt.*;
import java.security.SecureRandom;

public final class Nicksgen extends Command {
    public Nicksgen(NewMain instance){
        super(instance, "gen", "gen <length> <nick> <count>", new String[]{"nick", "nicks"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(args.length != 3){ new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericArgsErr",
                null, this
                ), Color.RED, message.getChannel()); return; }
        int a, b;
        try{
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[2]);
        }catch (NumberFormatException e){ new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageNicksgenErrNums", null, this), Color.RED, message.getChannel());return; }
        if(!(message.getAuthor().canBanUsersFromServer()) && b > 50){ new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageNicksgenLimit", null, this), Color.RED, message.getChannel()); return; }
        if(b <= 0 || a <= 0){ new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error.", instance.getConfig().getMessage("messageNicksgenMinus", null, this), Color.RED, message.getChannel()); return; }
        char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_*".toCharArray();
        StringBuilder builder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for(int i = 0; i < b; i++){
            builder.append(args[1]).append("_");
            for (int x = 0; x < (a - args[1].length()-1); x++){
                builder.append(chars[random.nextInt(chars.length)]);
            }
            builder.append("\n");
        }
        message.getAuthor().asUser().ifPresent(user -> new MessageBuilder().setEmbed(new EmbedBuilder().setColor(Color.BLACK).setAuthor(message.getAuthor()).addField("Nicks", builder.toString())).send(user));
        new me.vapor.botforgod.utils.Message(message.getAuthor(), "Nicks", instance.getConfig().getMessage("messageNicksgenSuccess", null, this), Color.cyan, message.getChannel());
        message.delete();
        builder.setLength(0);
    }
}
