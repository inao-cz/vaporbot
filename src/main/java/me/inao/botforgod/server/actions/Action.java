package me.inao.botforgod.server.actions;

import lombok.Getter;
import me.inao.botforgod.NewMain;

@Getter
public abstract class Action {
    private String name;
    private String origin;
    private String message;
    private NewMain instance;

    public Action(NewMain instance, String name, String origin, String message){
        this.instance = instance;
        this.name = name;
        this.origin = origin;
        this.message = message;
    }

    public abstract void onAction(String message, String origin);
}
