package me.vapor.botforgod.utils;

import me.vapor.botforgod.NewMain;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.ExceptionLogger;
import java.awt.*;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Gitlab {
    private NewMain instance;
    private JsonParser parser = new JsonParser();
    private String changelog;
    public Gitlab(NewMain instance) {
        this.instance = instance;
    }
    public void sendUpdate(TextChannel channel){
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
        return new Connectiontoweb("https://git.liquidbounce.net/api/v4/projects/193/" + surl).getContent();
    }
    public void check(){
        this.changelog = getString("repository/files/CHANGELOG/raw?ref=master");

        int gitVer = Integer.parseInt(changelog.replace(" ","").substring(2,4));
        if(gitVer > instance.getVersion()){
            System.out.println("-----------------------------------");
            System.out.println("Attention all Discord Server Owners.");
            System.out.println("This bot has been updated. Please check latest news about it.");
            System.out.println("You are using outdated version.");
            System.out.println("That means you are no longer getting support.");
            System.out.println("Please, update.");
            System.out.println("Shutting down, now.");
            System.out.println("-----------------------------------");
            System.exit(0);
        }

        java.io.File f = new java.io.File("version.txt");
        if(f.isFile()){
            try{
                byte[] encoded = Files.readAllBytes(Paths.get(f.getPath()));
                if(Integer.parseInt(new String(encoded, StandardCharsets.UTF_8)) != instance.getVersion()){
                    sendUpdate(instance.getApi().getServerById(instance.getId()).get().getTextChannelsByName("update").get(0));
                    FileWriter writer = new FileWriter(f);
                    writer.write(String.valueOf(instance.getVersion()));
                    writer.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try{
                if(f.createNewFile()){
                    FileWriter writer = new FileWriter(f);
                    writer.write(String.valueOf(instance.getVersion()));
                    writer.close();
                    sendUpdate(instance.getApi().getServerById(instance.getId()).get().getTextChannelsByName("update").get(0));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
