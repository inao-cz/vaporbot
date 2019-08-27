package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import java.awt.*;

public final class Antipihoda extends Command {
    public Antipihoda(NewMain instance){
        super(instance, "antipihoda", "antipihoda", new String[]{"anti", "club", "jointheclub", "pirate", "join"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        for(Role role : message.getAuthor().asUser().get().getRoles(instance.getApi().getServerById(instance.getId()).get())){
            if(role.getName().equals("anti-pihoda")){
                new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageAntipihodaInGroup", null, this), Color.orange, message.getChannel());
                return;
            }
        }
        message.getAuthor().asUser().get().addRole(instance.getApi().getServerById(instance.getId()).get().getRolesByName("anti-pihoda").get(0));
        new me.vapor.botforgod.utils.Message(message.getAuthor(), "You just joined", instance.getConfig().getMessage("messageAntipihodaJoined", null, this), Color.orange, message.getChannel());
    }
}
