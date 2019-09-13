package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;

import java.awt.*;

public final class Approve extends Command {
    public Approve(NewMain instance){
        super(instance, "approve", "approve <userid>", "Approve", new String[]{"appr", "allow"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(!message.getAuthor().canManageRolesOnServer()){
            new me.inao.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericNoPerms", null, this), Color.RED, message.getChannel());
            return;
        }
        message.getServer().ifPresent(server -> {
            server.getMemberById(args[0]).ifPresent(user -> {
                for (Role role : user.getRoles(server)){
                    if(role.getName().equals("User")){
                        new me.inao.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageApproveAlready", null, this), Color.RED, message.getChannel());
                        return;
                    }
                }
                server.getRolesByName("not-approved").get(0).removeUser(user);
                server.getRolesByName("User").get(0).addUser(user);
            });
        });
        message.delete();
    }
}
