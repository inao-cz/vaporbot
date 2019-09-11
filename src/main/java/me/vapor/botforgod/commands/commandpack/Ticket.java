package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.channel.ServerTextChannelBuilder;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.PermissionsBuilder;
import java.awt.*;
import java.util.Random;

public final class Ticket extends Command {
    public Ticket(NewMain instance){
        super(instance, "ticket", "ticket <create | close> <reason (only if creating) | ticket to close (only if closing)>", new String[]{"tick", "tckt"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(args.length < 1){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericNoPerms", null, this), Color.RED, message.getChannel());
            return;
        }
        if(args[0].equalsIgnoreCase("create")){
            if(args.length < 2){
                new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericNoPerms", null, this), Color.RED, message.getChannel());
                return;
            }
            StringBuilder id = new StringBuilder();
            char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_*".toCharArray();
            for(int i = 0; i < 7; i++){
                id.append(chars[new Random().nextInt(chars.length)]);
            }
            StringBuilder builder = new StringBuilder();
            for(int i = 1; i < args.length; i++){
                builder.append(args[i]).append(" ");
            }
            new ServerTextChannelBuilder(instance.getApi().getServerById(instance.getId()).get())
                    .setCategory(instance.getApi().getServerById(instance.getId()).get().getChannelCategoriesByName("tickets").get(0))
                    .setTopic("Solving: " + builder.toString() + " | ID: " + id)
                    .setName("Ticket-"+id)
                    .addPermissionOverwrite(instance.getApi().getServerById(instance.getId()).get().getRolesByName("@everyone").get(0), new PermissionsBuilder().setDenied(PermissionType.READ_MESSAGES).build())
                    .addPermissionOverwrite(message.getUserAuthor().get(), new PermissionsBuilder().setAllowed(PermissionType.READ_MESSAGES).build())
                    .create();
            log(instance, id.toString(), (byte)0);
        }
        if(args[0].equalsIgnoreCase("close")){
            if(args.length != 2){
                new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericNoPerms", null, this), Color.RED, message.getChannel());
                return;
            }
            if(!message.getAuthor().canKickUsersFromServer()){
                new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericNoPerms", null, this), Color.RED, message.getChannel());
                return;
            }
            for(ServerChannel channel : instance.getApi().getServerById(instance.getId()).get().getChannelCategoriesByName("tickets").get(0).getChannels()){
                if(channel.getName().equalsIgnoreCase("Ticket-"+args[1])){
                    channel.delete();
                    log(instance, args[1], (byte)1);
                    break;
                }
            }
        }
    }
    private void log(NewMain instance, String id, byte action){
        String message = "";
        switch (action){
            case 0:
                message = "Ticket ID: " + id + " has been created.";
                break;
            case 1:
                message = "Ticket ID: " + id + " has been closed.";
                break;
        }
        new me.vapor.botforgod.utils.Message("Ticket", message, Color.BLACK,
                instance.getApi().getServerById(instance.getId()).get().getChannelsByName("log").get(0).asTextChannel().get());
    }
}
