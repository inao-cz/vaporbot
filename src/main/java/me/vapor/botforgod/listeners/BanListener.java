package me.vapor.botforgod.listeners;

import me.vapor.botforgod.utils.Message;
import me.vapor.botforgod.NewMain;
import org.javacord.api.event.server.member.ServerMemberBanEvent;
import org.javacord.api.listener.server.member.ServerMemberBanListener;
import java.awt.*;

public class BanListener implements ServerMemberBanListener {
    private NewMain instance;
    public BanListener(NewMain instance){
        this.instance = instance;
    }
    @Override
    public void onServerMemberBan(ServerMemberBanEvent serverMemberBanEvent) {
        serverMemberBanEvent.requestBan().join().ifPresent(ban ->
        {
            new Message("Ban.", "User " + ban.getUser().getDiscriminatedName() + " has been banned from this server.", Color.black, ban.getServer().getChannelsByName("public-bans").get(0).asTextChannel().get());
        });
    }
}
