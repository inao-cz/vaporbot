package me.inao.botforgod.classes;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.utils.Message;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {
    public Log(String message, NewMain instance){
        new Message("Log " + new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss").format(Calendar.getInstance().getTime()), message.replace("$$", "\n"), Color.BLACK, instance.getServer().getChannelsByName("admin-log").get(0).asServerTextChannel().get());
    }
    public Log(String message){
        System.out.println("[" + new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss").format(Calendar.getInstance().getTime()) + "] " + message);
    }
}
