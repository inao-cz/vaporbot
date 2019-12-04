package me.inao.botforgod;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import me.inao.botforgod.classes.CaptchaKicker;
import me.inao.botforgod.classes.ExceptionCatcher;
import me.inao.botforgod.classes.Gitlab;
import me.inao.botforgod.commands.Command;
import me.inao.botforgod.commands.commandpack.*;
import me.inao.botforgod.listeners.*;
import me.inao.botforgod.config.Config;
import me.inao.botforgod.classes.Countgame;
import me.inao.botforgod.server.AesUtility;
import me.inao.botforgod.server.actions.Action;
import me.inao.botforgod.utils.SQLite;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.UserStatus;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
public final class NewMain {
    private ArrayList<Command> commands = new ArrayList<>();
    private ArrayList<Action> serverActions = new ArrayList<>();
    private ArrayList<String> muted = new ArrayList<>();
    private ConcurrentLinkedQueue<String> captchas = new ConcurrentLinkedQueue<>();
    private Server server;
    private DiscordApi api;
    private Config config;
    private SQLite sqLite = new SQLite();
    private Timer timer = new Timer();
    @Setter
    private Countgame countgame = null;
    private int version = 33;
    public void init(){
        /*!--------------------------------------------------! Bot init*/
        loadConfig();
        if(config.getSetting("production")){
            api = new DiscordApiBuilder().setToken(config.getApi("discordProductionApi")).login().join();
        }else{
            api = new DiscordApiBuilder().setToken(config.getApi("discordTestingApi")).login().join();
        }
        api.setMessageCacheSize(5, 3600);
        api.setAutomaticMessageCacheCleanupEnabled(true);
        if(config.getSetting("externalServer")){
            if(config.getOption("aesKey").equalsIgnoreCase("null")) {
                System.out.println("I can see you have externalServer enabled without AES key. Generating..");
                System.out.println(new AesUtility(this).getKey());
                System.out.println("Please, use this key here and in your application. Exiting now since it's insecure to use server without encryption key.");
                System.out.println("BEWARE! KEY IS BASE64 ENCODED!");
                System.exit(0);
            }
            if(config.getOption("aesIv").equalsIgnoreCase("null")){
                System.out.println("Nice, it looks like you already have AES key. However, I will still need Initialisation Vector (IV)");
                System.out.println("Generating now..");
                System.out.println(new AesUtility(this).getIv());
                System.out.println("Please, use this IV in your client applications (BEWARE IV IS IN BASE64 ENCODING!)");
                System.exit(0);
            }
            new me.inao.botforgod.server.Server(this).start();
            System.out.println("Loaded server!");
        }
        api.updateStatus(UserStatus.IDLE);
        api.updateActivity(ActivityType.WATCHING, "on this server.");
        /*!--------------------------------------------------!*/

        /*!--------------------------------------------------! Listener start*/
        System.out.println("Starting listeners!");
        api.getServers().forEach(server -> this.server = server);
        timer.scheduleAtFixedRate(new Gitlab(this), 0L, 3600000L);
        if(this.getConfig().getSetting("Captcha")){
            api.addServerChannelDeleteListener(new ChannelDeleteListener(this));
            Connection connection = getSqLite().openConnection();
            try{
                PreparedStatement stmt = connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS captchas(" +
                                "id TEXT NOT NULL," +
                                "sol INT NOT NULL," +
                                "uid TEXT NOT NULL" +
                                ")"
                );
                sqLite.execute(connection, stmt);

                connection = sqLite.openConnection();
                stmt = connection.prepareStatement("SELECT * FROM captchas");
                ResultSet set = sqLite.getResults(connection, stmt);
                while(set.next()){
                    captchas.add(set.getString("id") + ":" + set.getInt("sol") + ":" + set.getString("uid"));
                }
            }catch (Exception e){
                new ExceptionCatcher(e);
            }
            timer.scheduleAtFixedRate(new CaptchaKicker(this), 0L, 1800000L);
        }
        if(this.getConfig().getSetting("Backup")){
            Connection connection = this.sqLite.openConnection();
            try{
                PreparedStatement stmt;
                connection = getSqLite().openConnection();
                stmt = connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS backup" +
                                "(" +
                                "type text not null," +
                                "name text," +
                                "data text," +
                                "perm_groups text," +
                                "perm_integers text" +
                                ");"
                );
                sqLite.execute(connection, stmt);
            }catch (Exception e){
                new ExceptionCatcher(e);
            }
        }

        api.addMessageCreateListener(new MessageListener(this));
        if(config.getSetting("Bans")) api.addServerMemberBanListener(new BanListener(this));
        if(config.getSetting("production")) api.addServerMemberJoinListener(new JoinListener(this));

        /*!--------------------------------------------------!*/

        /*!--------------------------------------------------! Command registration*/
        new About(this);
        new Antipihoda(this);
        new Approve(this);
        new Count(this);
        new Delete(this);
        new GenKey(this);
        new GenSrvKey(this);
        new Mute(this);
        new Nicksgen(this);
        new Reload(this);
        new Skid(this);
        new Ticket(this);
        new Thanos(this);
        new Unmute(this);
        new Vote(this);
        new Youtube(this);
        new Ping(this);
        new Help(this); //help should be last command.
    }
    /*!--------------------------------------------------!*/

    /*!--------------------------------------------------! Config loading*/
    public void loadConfig(){
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
    }
    /*!--------------------------------------------------!*/
}
