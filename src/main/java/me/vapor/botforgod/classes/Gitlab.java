package me.vapor.botforgod.classes;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.utils.Connectiontoweb;
import me.vapor.botforgod.utils.FileOperation;
import me.vapor.botforgod.utils.JsonParser;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.ExceptionLogger;
import java.awt.*;

public class Gitlab {
    private NewMain instance;
    private JsonParser parser = new JsonParser();
    private String changelog;
    public Gitlab(NewMain instance) {
        this.instance = instance;
    }
    void sendUpdate(TextChannel channel){
        new MessageBuilder().setEmbed(
                new EmbedBuilder()
                        .setTitle("Update")
                        .setDescription("Version: " + instance.getVersion() + "\nChangelog:\n" + getChangelog() + "\nProduction: " + instance.getConfig().getOption("production"))
                        .setColor(Color.orange)
        ).send(channel).exceptionally(ExceptionLogger.get());
    }

    private String getChangelog(){
        return parser.getGitlabJson(getString("repository/files/CHANGELOG/raw?ref=master"), String.valueOf(instance.getVersion()));
    }

    private String getString(String surl){
        return new Connectiontoweb("https://git.liquidbounce.net/api/v4/projects/197/" + surl).getContent();
    }
    public void check(){
        this.changelog = getString("repository/files/CHANGELOG/raw?ref=master");
        int gitVer = Integer.parseInt(new com.google.gson.JsonParser().parse(changelog).getAsJsonObject().entrySet().iterator().next().getKey());

        if(gitVer > instance.getVersion()){
            System.out.println("-----------------------------------");
            System.out.println("Attention all Discord Server Owners.");
            System.out.println("This bot has been updated. Please check latest news about it.");
            System.out.println("You are using outdated version.");
            System.out.println("That means you are no longer getting support.");
            System.out.println("Please, update.");
            System.out.println("Shutting down, now.");
            System.out.println("-----------------------------------");
            instance.getApi().getServerById(instance.getId()).get()
                    .createTextChannelBuilder().setName("updator").create().join()
                    .sendMessage("vaporbot has been updated. Shutting down now!");
            System.exit(0);
        }

        java.io.File f = new FileOperation().getFile("version.txt");
        if(f.isFile()){
            try{
                if(Integer.parseInt(new FileOperation().readFile(f)) != instance.getVersion()){
                    sendUpdate(instance.getApi().getServerById(instance.getId()).get().getTextChannelsByName("update").get(0));
                    new FileOperation().writeFile(f, String.valueOf(instance.getVersion()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try{
                if(f.createNewFile()){
                    new FileOperation().writeFile(f, String.valueOf(instance.getVersion()));
                    sendUpdate(instance.getApi().getServerById(instance.getId()).get().getTextChannelsByName("update").get(0));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
