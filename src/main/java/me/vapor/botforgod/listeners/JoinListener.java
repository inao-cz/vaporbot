package me.vapor.botforgod.listeners;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.utils.Captcha;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;
import java.awt.*;

public final class JoinListener implements ServerMemberJoinListener {
    private NewMain instance;
    public JoinListener(NewMain instance){
        this.instance = instance;
    }
    @Override
    public void onServerMemberJoin(ServerMemberJoinEvent serverMemberJoinEvent) {
        User user = serverMemberJoinEvent.getUser();
        instance.getApi().getServerById(instance.getId()).ifPresent(server ->
            {
                if(server.getTextChannelsByName("welcome").size() >= 1){
                    server.getTextChannelsByName("welcome").get(0).asTextChannel().ifPresent(textChannel ->
                        new MessageBuilder().setEmbed(new EmbedBuilder()
                            .setAuthor("Server")
                            .addField("Join", "User " + user.getDiscriminatedName() + " has joined. Welcome!")
                            .setColor(Color.yellow)
                            ).send(textChannel)
                    );
                }
                if(instance.getConfig().getOption("Approve")){
                    server.getRolesByName("captcha").get(0).addUser(user);
                    new Captcha(instance, serverMemberJoinEvent.getUser());
                }
            }
        );
    }
}
