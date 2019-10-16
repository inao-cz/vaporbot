package me.inao.botforgod.server.actions.pack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.server.actions.Action;

public class MessageAction extends Action {
    public MessageAction(NewMain instance, String origin, String message){
        super(instance, "MessageAction", origin, message);
    }

    @Override
    public void onAction(String message, String origin) {

    }
}
