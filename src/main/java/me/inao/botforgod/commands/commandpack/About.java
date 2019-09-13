package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;

import java.awt.*;

public final class About extends Command {
    public About(NewMain instance){
        super(instance, "about", "about",null, null);
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        new me.inao.botforgod.utils.Message(message.getAuthor(), "About.", "About this bot:\nVersion: b" + instance.getVersion() + "\nProduction: " + instance.getConfig().getSetting("production") + "\nWritten by LiquidDev and vapor.", Color.magenta, message.getChannel());
    }
}
