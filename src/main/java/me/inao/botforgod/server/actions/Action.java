package me.inao.botforgod.server.actions;

import lombok.Getter;
import me.inao.botforgod.NewMain;
import org.javacord.api.entity.channel.Channel;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class Action {
    private String name;
    private NewMain instance;

    public Action(@NotNull NewMain instance, String name){
        this.instance = instance;
        this.name = name;
        instance.getServerActions().add(this);
    }

    public abstract void onAction(NewMain instance, String message, String origin, Channel channel);
}
