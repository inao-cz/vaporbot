package me.inao.botforgod.listeners;

import me.inao.botforgod.classes.Log;
import me.inao.botforgod.commands.Command;
import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.Captcha;
import me.inao.botforgod.utils.FileOperation;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public final class MessageListener implements MessageCreateListener {
    private NewMain instance;
    public MessageListener(NewMain instance) {
        this.instance = instance;
    }

    public void onMessageCreate(MessageCreateEvent event) {
        for(String muted : instance.getMuted()){
            if(event.getMessage().getAuthor().getIdAsString().equals(muted)){
                event.getMessage().delete();
                return;
            }
        }
        String captcha = null;
        for(Role r : event.getMessageAuthor().asUser().get().getRoles(instance.getServer())){
            if(r.getName().equals("captcha")){
                for(String c : instance.getCaptchas()){
                    if(c.split(":")[2].equals(event.getMessageAuthor().getIdAsString())){
                        if(event.getMessage().getContent().equals(c.split(":")[1])){
                            instance.getApi().getUserById(c.split(":")[2]).join().removeRole(instance.getServer().getRolesByName("captcha").get(0));
                            instance.getApi().getUserById(c.split(":")[2]).join().addRole(instance.getServer().getRolesByName("not-approved").get(0));
                            captcha = c;
                            break;
                        }
                        if(!(event.getMessage().getContent().equals(String.valueOf(c.split(":")[1]))) || !event.getMessage().isPrivateMessage()) {
                            event.getMessage().delete();
                            break;
                        }
                    }
                }
                if(captcha != null){
                    instance.getCaptchas().remove(captcha);
                    File file = new FileOperation().getFile("captcha.txt");
                    new FileOperation().removeFromFile(file, captcha);
                    event.getMessage().getChannel().asServerTextChannel().get().delete("Solved captcha");
                    break;
                }
            }
        }

        if(instance.getCountgame() != null && !event.getMessage().getContent().startsWith(instance.getConfig().getOption("prefix"))){
            instance.getCountgame().onSend(event.getMessage());
            return;
        }

        //return if messages are empty or not starting with prefix
        if (event.getMessage().getContent().isEmpty() || !event.getMessage().getContent().startsWith(String.valueOf(instance.getConfig().getOption("prefix"))) || event.getMessage().isPrivateMessage() || event.getMessage().getContent().equals(instance.getConfig().getOption("prefix"))){
            return;
        }
        final Message msg = event.getMessage();

        final ArrayList<Command> commands = this.instance.getCommands();
        //get command and substring it
        final String cmd = msg.getContent().split(" ")[0].substring(1);
        //get arguments
        final String[] args = (msg.getContent().length() <= cmd.length() + 2) ? new String[0] : msg.getContent().substring(cmd.length() + 2).split(" ");
        //go trough commands
        for (Command command : commands) {
            if (command.getCommand().equalsIgnoreCase(cmd) || Arrays.toString(command.getAliases()).contains(cmd)) {
                event.getMessage().getChannel().asServerTextChannel().ifPresent(serverTextChannel -> {
                            if(serverTextChannel.getName().equals("bot-commands") || event.getMessageContent().contains("!del") || event.getMessageAuthor().canManageRolesOnServer()){
                                command.onCommand(msg, instance, args);
                                if(instance.getConfig().getSetting("MessageLog")) new Log("Command: " + event.getMessage().getContent() + "$$By: " + event.getMessage().getAuthor().getDiscriminatedName() + "$$In: " + event.getChannel(), instance);
                                if(instance.getConfig().getSetting("ConsoleLog")) new Log("Command " + event.getMessage().getContent() + " was executed by " + event.getMessage().getAuthor().getDiscriminatedName() + " in " + event.getChannel());
                            }else{
                                event.getMessage().getAuthor().asUser().ifPresent(user -> {
                                    new MessageBuilder().setEmbed(new EmbedBuilder().setColor(Color.RED).setTitle("Error!").setDescription("Please use bot-commands room for commands. Thank you :)")).send(user);
                                });
                            }
                        });
                break;
            }
        }
    }
    private void delCap(Captcha c){
        instance.getCaptchas().remove(c);
    }
}
