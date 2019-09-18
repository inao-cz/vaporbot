package me.inao.botforgod;

import com.google.gson.Gson;
import me.inao.botforgod.classes.Gitlab;
import me.inao.botforgod.commands.Command;
import me.inao.botforgod.commands.commandpack.*;
import me.inao.botforgod.listeners.BanListener;
import me.inao.botforgod.listeners.MessageListener;
import me.inao.botforgod.config.Config;
import me.inao.botforgod.listeners.JoinListener;
import me.inao.botforgod.classes.Countgame;
import me.inao.botforgod.utils.FileOperation;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.UserStatus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public final class NewMain {
    private ArrayList<Command> commands = new ArrayList<>();
    private ArrayList<String> muted = new ArrayList<>();
    private ArrayList<String> captchas = new ArrayList<>();
    private Server id;
    private DiscordApi api;
    private Config config;
    private Countgame countgame = null;
    private int version = 32;
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
        if(this.getConfig().getSetting("Gitlab") && this.getConfig().getSetting("production")) new Gitlab(this).check();
        if(this.getConfig().getSetting("Captcha")){
            File f = new FileOperation().getFile("captcha.txt");
            if(f.length() > 0){
                try{
                    BufferedReader reader1 = new BufferedReader(new FileReader(f));
                    String line;
                    while((line = reader1.readLine()) != null){
                        captchas.add(line);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else System.out.println("No valid captchas found in file.");
        }
        api.addMessageCreateListener(new MessageListener(this));
        if(config.getSetting("production")){
            api.addServerMemberJoinListener(new JoinListener(this));
            if(config.getSetting("Bans")) api.addServerMemberBanListener(new BanListener(this));
        }
        /*!--------------------------------------------------!*/

        /*!--------------------------------------------------! Command registration*/
        new About(this);
        new Antipihoda(this);
        new Approve(this);
        new Count(this);
        new Delete(this);
        new Mute(this);
        new Nicksgen(this);
        new Prasoprd(this);
        new Skid(this);
        new Ticket(this);
        new Thanos(this);
        new Unmute(this);
        new Youtube(this);
        new Ping(this);
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
    public ArrayList<String> getCaptchas() {
        return captchas;
    }
    /*!--------------------------------------------------!*/

    /*!--------------------------------------------------! Setters*/
    public void setCountgame(Countgame countgame) {
        this.countgame = countgame;
    }
    /*!--------------------------------------------------!*/
}
