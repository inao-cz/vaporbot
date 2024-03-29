package me.inao.botforgod.commands;

import lombok.Getter;
import me.inao.botforgod.NewMain;
import org.javacord.api.entity.message.Message;

@Getter
public abstract class Command {
    /*!--------------------------------------------------! Proměnné*/
    private String command, usage;
    private String[] aliases;
    /*!--------------------------------------------------!*/

    /*!--------------------------------------------------! Constructor*/
    public Command(NewMain instance, String command, String usage, String setting, String[] aliases){
        this.command = command;
        this.usage = usage;
        this.aliases = aliases;
        if(!command.equals("ping") && (setting == null || instance.getConfig().getSetting(setting))){
            instance.getCommands().add(this);
        }else if(!instance.getConfig().getSetting("production")){
            instance.getCommands().add(this);
        }
    }

    /*!--------------------------------------------------!*/
    /*!--------------------------------------------------! abstract void, overriduje se v commandu*/
    public abstract void onCommand(Message message, NewMain instance, String[] args);
    /*!--------------------------------------------------!*/
}
