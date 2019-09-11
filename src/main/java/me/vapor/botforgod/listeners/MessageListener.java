package me.vapor.botforgod.listeners;

import me.vapor.botforgod.commands.Command;
import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.classes.Captcha;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.awt.*;
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
        Captcha captcha = null;
        for(Role r : event.getMessageAuthor().asUser().get().getRoles(instance.getApi().getServerById(instance.getId()).get())){
            if(r.getName().equals("captcha")){
                for(Captcha c : instance.getCaptchas()){
                    if(c.getUser().getIdAsString().equals(event.getMessageAuthor().getIdAsString())){
                        if(event.getMessage().getContent().equals(String.valueOf(c.getResult()))){
                            c.getUser().removeRole(instance.getApi().getServerById(instance.getId()).get().getRolesByName("captcha").get(0));
                            c.getUser().addRole(instance.getApi().getServerById(instance.getId()).get().getRolesByName("not-approved").get(0));
                            captcha = c;
                            break;
                        }
                        if(!(event.getMessage().getContent().equals(String.valueOf(c.getResult()))) || !event.getMessage().isPrivateMessage()) {
                            event.getMessage().delete();
                            break;
                        }
                    }
                }
                if(captcha != null){
                    instance.getCaptchas().remove(captcha);
                    event.getMessage().getChannel().asServerTextChannel().get().delete("Solved captcha");
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
                                System.out.println("[" + new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss").format(Calendar.getInstance().getTime()) + "] Command " + event.getMessage().getContent() + " was executed by " + event.getMessage().getAuthor().getDiscriminatedName() + " in " + event.getChannel());
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
