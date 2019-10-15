package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import java.awt.*;

public final class Antipihoda extends Command {
    public Antipihoda(NewMain instance){
        super(instance, "antipihoda", "antipihoda", "Antipihoda", new String[]{"anti", "club", "jointheclub", "pirate", "join"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        for(Role role : message.getAuthor().asUser().get().getRoles(instance.getServer())){
            if(role.getName().equals("anti-pihoda")){
                new me.inao.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageAntipihodaInGroup", null, this), Color.orange, message.getChannel());
                return;
            }
        }
        message.getAuthor().asUser().get().addRole(instance.getServer().getRolesByName("anti-pihoda").get(0));
        new me.inao.botforgod.utils.Message(message.getAuthor(), "You just joined", instance.getConfig().getMessage("messageAntipihodaJoined", null, this), Color.orange, message.getChannel());
    }
}
