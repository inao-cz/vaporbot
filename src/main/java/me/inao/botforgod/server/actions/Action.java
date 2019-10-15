package me.inao.botforgod.server.actions;

import lombok.Getter;
import me.inao.botforgod.NewMain;

@Getter
public abstract class Action {
    private String name;
    private String[] args;
    private NewMain instance;

    public Action(NewMain instance, String name, String[] args){
        this.instance = instance;
        this.name = name;
        this.args = args;
    }

    public abstract void onAction(String[] args);
}
