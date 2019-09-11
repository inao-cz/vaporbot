package me.vapor.botforgod.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import me.vapor.botforgod.commands.Command;

public class Config {
    @SerializedName("messages")
    @Expose
    private LinkedTreeMap<String, String> messages;

    @SerializedName("settings")
    @Expose
    private LinkedTreeMap<String, Boolean> settings;

    @SerializedName("options")
    @Expose
    private LinkedTreeMap<String, String> options;

    @SerializedName("apis")
    @Expose
    private LinkedTreeMap<String, String> apis;

    public String getMessage(String message, String[][] map, Command command){
        String msg = messages.get(message);
        if(msg.contains("%command%")){
            msg = msg.replace("%command%", this.apis.get("prefix") + command.getCommand());
        }
        if(map != null){
            for (String[] strings : map) {
                msg = msg.replace(strings[0], strings[1]);
            }
        }
        return msg;
    }

    public boolean getSetting(String key){ return settings.get(key); }

    public String getOption(String key){
        return options.get(key);
    }

    public String getApi(String key){
        return apis.get(key);
    }

}
