package me.inao.botforgod.server.actions;

import lombok.Getter;
import me.inao.botforgod.NewMain;
import org.javacord.api.entity.channel.Channel;

@Getter
public abstract class Action {
    private String name;
    private String origin;
    private String message;
    private Channel channel;
    private NewMain instance;

    public Action(NewMain instance, String name, String origin, String channel, String message){
        this.instance = instance;
        this.name = name;
        this.origin = origin;
        this.channel = instance.getApi().getChannelById(channel).get();
        this.message = message;
        instance.getServerActions().add(this);
    }

    public abstract void onAction(String message, String origin, Channel channel);
}
