package me.vapor.botforgod;

import com.google.gson.Gson;
import me.vapor.botforgod.commands.Command;
import me.vapor.botforgod.commands.commandpack.*;
import me.vapor.botforgod.config.Config;
import me.vapor.botforgod.listeners.MessageListener;
import me.vapor.botforgod.listeners.BanListener;
import me.vapor.botforgod.listeners.JoinListener;
import me.vapor.botforgod.classes.Captcha;
import me.vapor.botforgod.classes.Countgame;
import me.vapor.botforgod.classes.Gitlab;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.UserStatus;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public final class NewMain {
    private ArrayList<Command> commands = new ArrayList<>();
    private ArrayList<String> muted = new ArrayList<>();
    private ArrayList<Captcha> captchas = new ArrayList<>();
    private Server id;
    private DiscordApi api;
    private Config config;
    private Countgame countgame = null;
    private int version = 30;
    public void init(){
        /*!--------------------------------------------------! Bot init*/
        Gson gson = new Gson();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader("config.json"));
            config = gson.fromJson(reader, Config.class);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(config.getSetting("production")){
            api = new DiscordApiBuilder().setToken(config.getApi("discordProductionApi")).login().join();
        }else{
            api = new DiscordApiBuilder().setToken(config.getApi("discordTestingApi")).login().join();
        }
        api.updateStatus(UserStatus.IDLE);
        api.updateActivity(ActivityType.WATCHING, "on this server.");
        /*!--------------------------------------------------!*/

        /*!--------------------------------------------------! Listener start*/
        api.getServers().forEach(server -> id=server);
        if(this.getConfig().getSetting("Gitlab")) new Gitlab(this).check();
        api.addMessageCreateListener(new MessageListener(this));
        if(config.getSetting("production")){
            api.addServerMemberJoinListener(new JoinListener(this));
            if(config.getSetting("Bans")) api.addServerMemberBanListener(new BanListener(this));
        }
        /*!--------------------------------------------------!*/

        /*!--------------------------------------------------! Command registration*/
        new About(this);
        if(config.getSetting("Antipihoda")) new Antipihoda(this);
        if(config.getSetting("Approve")) new Approve(this);
        if(config.getSetting("Count")) new Count(this);
        if(config.getSetting("Delete")) new Delete(this);
        if(config.getSetting("Mute")) new Mute(this);
        if(config.getSetting("Nicksgen")) new Nicksgen(this);
        if(config.getSetting("Skid")) new Skid(this);
        if(config.getSetting("Ticket")) new Ticket(this);
        if(config.getSetting("Unmute")) new Unmute(this);
        if(config.getSetting("Youtube")) new Youtube(this);
        if(!config.getSetting("production")){
            new Ping(this);
        }
        new Help(this); //help should be last command.
        /*!--------------------------------------------------!*/
    }
    /*!--------------------------------------------------! Getters*/
    public ArrayList<Command> getCommands() {
        return commands;
    }
    public DiscordApi getApi() {
        return api;
    }
    public String getId(){
        return id.getIdAsString();
    }
    public Config getConfig() { return this.config; }
    public int getVersion() {
        return version;
    }
    public ArrayList<String> getMuted() {
        return muted;
    }
    public Countgame getCountgame() {
        return countgame;
    }
    public ArrayList<Captcha> getCaptchas() {
        return captchas;
    }
    /*!--------------------------------------------------!*/

    /*!--------------------------------------------------! Setters*/
    public void setCountgame(Countgame countgame) {
        this.countgame = countgame;
    }
    /*!--------------------------------------------------!*/
}
