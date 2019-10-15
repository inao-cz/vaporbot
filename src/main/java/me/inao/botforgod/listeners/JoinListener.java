package me.inao.botforgod.listeners;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.Captcha;
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
        if(instance.getServer().getTextChannelsByName("welcome").size() >= 1){
            instance.getServer().getTextChannelsByName("welcome").get(0).asTextChannel().ifPresent(textChannel ->
                    new MessageBuilder().setEmbed(new EmbedBuilder()
                            .setAuthor("Server")
                            .addField("Join", "User " + user.getDiscriminatedName() + " has joined. Welcome!")
                            .setColor(Color.yellow))
                            .send(textChannel)
            );
        }
        if(instance.getConfig().getSetting("Approve")){
            instance.getServer().getRolesByName("captcha").get(0).addUser(user);
            new Captcha(instance, serverMemberJoinEvent.getUser());
        }
    }
}
