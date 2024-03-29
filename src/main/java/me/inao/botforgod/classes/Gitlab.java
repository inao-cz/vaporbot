package me.inao.botforgod.classes;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.utils.Connectiontoweb;
import me.inao.botforgod.utils.FileOperation;
import me.inao.botforgod.utils.JsonParser;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.PermissionsBuilder;
import org.javacord.api.util.logging.ExceptionLogger;
import java.awt.*;
import java.io.File;
import java.util.TimerTask;

public class Gitlab extends TimerTask {
    private NewMain instance;
    private JsonParser parser = new JsonParser();
    private String changelog;
    public Gitlab(NewMain instance) {
        this.instance = instance;
    }
    @Override
    public void run(){
        if(instance.getConfig().getSetting("Gitlab") && instance.getConfig().getSetting("production")) check();
        else if(!instance.getConfig().getSetting("Gitlab")) new Log("Disabling AutoUpdate Check isn't secure!");
    }
    void sendUpdate(TextChannel channel){
        new MessageBuilder().setEmbed(
                new EmbedBuilder()
                        .setTitle("Update")
                        .setDescription("Version: " + instance.getVersion() + "\nChangelog:\n" + getChangelog() + "\nProduction: " + instance.getConfig().getSetting("production"))
                        .setColor(Color.orange)
        ).send(channel).exceptionally(ExceptionLogger.get());
    }

    private String getChangelog(){
        return parser.getGitlabJson(getString("repository/files/CHANGELOG/raw?ref=WiP"), String.valueOf(instance.getVersion()));
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
            instance.getServer()
                    .createTextChannelBuilder().setName("updator")
                    .addPermissionOverwrite(instance.getServer().getRolesByName("@everyone").get(0), new PermissionsBuilder().setDenied(PermissionType.SEND_MESSAGES, PermissionType.ADD_REACTIONS).build())
                    .create().join()
                    .sendMessage("vaporbot release has been updated. Please download new version..");
        }

        File f = new FileOperation().getFile("version.txt");
        if(f.length() != 0){
            try{
                if(Integer.parseInt(new FileOperation().readFile(f)) != instance.getVersion()){
                    sendUpdate(instance.getServer().getTextChannelsByName("update").get(0));
                    new FileOperation().writeFile(f, String.valueOf(instance.getVersion()));
                }
            }catch (Exception e){
                new ExceptionCatcher(e);
            }
        }else{
            try{
                new FileOperation().writeFile(f, String.valueOf(instance.getVersion()));
                sendUpdate(instance.getServer().getTextChannelsByName("update").get(0));
            }catch (Exception e){
                new ExceptionCatcher(e);
            }
        }
    }
}
