package me.inao.botforgod.server.actions.pack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.server.actions.Action;
import org.javacord.api.entity.channel.Channel;

public class CaptchaAction extends Action {
    public CaptchaAction(NewMain instance){
        super(instance, "CaptchaAction");
    }
    @Override
    public void onAction(NewMain instance, String message, String origin, Channel channel) {

    }
}
