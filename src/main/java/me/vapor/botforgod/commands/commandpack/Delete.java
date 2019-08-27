package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import java.awt.*;
import java.util.concurrent.ExecutionException;


public final class Delete extends Command {
    public Delete(NewMain instance){
        super(instance, "delete", "delete <num of msgs || all>", new String[]{"del"});
    }
    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(message.getAuthor().canKickUsersFromServer()){
            if(args.length == 0){
                new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericArgsErr", null, this), Color.RED, message.getChannel());
            }else if(args[0].equals("all")){
                try {
                    message.getChannel().getMessages(-1).get().deleteAll();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    message.getChannel().getMessages(Integer.parseInt(args[0])+1).get().deleteAll();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
