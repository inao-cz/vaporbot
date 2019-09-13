package me.inao.botforgod.listeners;

import me.inao.botforgod.utils.Message;
import me.inao.botforgod.NewMain;
import org.javacord.api.event.server.member.ServerMemberBanEvent;
import org.javacord.api.listener.server.member.ServerMemberBanListener;
import java.awt.*;

public final class BanListener implements ServerMemberBanListener {
    private NewMain instance;
    public BanListener(NewMain instance){
        this.instance = instance;
    }
    @Override
    public void onServerMemberBan(ServerMemberBanEvent serverMemberBanEvent) {
        serverMemberBanEvent.requestBan().join().ifPresent(ban ->
        {
            new Message("Ban.", instance.getConfig().getMessage("messageBannedSuccess",
                    new String[][]{
                            {"%user%", ban.getUser().getDiscriminatedName()},
                            {"%id%", ban.getUser().getIdAsString()}
                    }, null), Color.black, ban.getServer().getChannelsByName("public-bans").get(0).asTextChannel().get());
        });
    }
}
