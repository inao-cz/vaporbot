package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;

import java.awt.*;

public final class About extends Command {
    public About(NewMain instance){
        super(instance, "about", "about", null);
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        new me.vapor.botforgod.utils.Message(message.getAuthor(), "About.", "About this bot:\nVersion: b" + instance.getVersion() + "\nProduction: " + instance.getConfig().getSetting("production") + "\nWritten by LiquidDev and vapor.", Color.magenta, message.getChannel());
    }
}
