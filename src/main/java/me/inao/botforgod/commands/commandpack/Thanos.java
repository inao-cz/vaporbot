package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Thanos extends Command {
    public Thanos(NewMain instance){
        super(instance, "thanos", "thanos", "Thanos", new String[]{"thns", "select", "slct"});
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(!message.getAuthor().canKickUsersFromServer()){
            new me.inao.botforgod.utils.Message(this, instance.getConfig().getMessage(
                    "messageGenericNoPerms",
                    null,
                    this
            ), Color.RED, message.getChannel());
            return;
        }
        List<User> users = new ArrayList<>();
        for(User user : instance.getServer().getMembers()){
            boolean contains = false;
            for (Role role : user.getRoles(instance.getServer())) {
                if(instance.getConfig().getOption("thanosGroupBlacklist").contains(role.getName())){
                    contains = true;
                    break;
                }
            }
            if(!contains && !users.contains(user)){
                users.add(user);
            }
        }
        StringBuilder chosen = new StringBuilder();
        for(int i = 0; i <= users.size()/2; i++){
            chosen.append(chooseRandom(users, chosen.toString()).getDiscriminatedName()).append("\n");
        }
        new me.inao.botforgod.utils.Message(this, "**Chosen are**\n" + chosen.toString(), Color.GREEN, message.getChannel());
    }
    private User chooseRandom(List<User> collection, String chosen){
        User user = collection.get(new Random().nextInt(collection.size()));
        while (chosen.contains(user.getDiscriminatedName())){
            user = collection.get(new Random().nextInt(collection.size()));
        }
        return user;
    }
}
