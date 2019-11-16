package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;

public class Vote extends Command {
    public Vote(NewMain instance){
        super(instance, "vote", "vote <what>", "Vote", null);
    }
    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {

    }
}
