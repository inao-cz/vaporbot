package me.inao.botforgod.commands.commandpack;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.commands.Command;
import me.inao.botforgod.utils.Connectiontoweb;
import me.inao.botforgod.utils.JsonParser;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.Role;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Youtube extends Command {
    private NewMain instance;
    private List<State> users = new ArrayList<>();
    public Youtube(NewMain instance){
        super(instance, "youtube", "youtube <start | verify> <channelid (only if start)>", "Youtube", new String[]{"yt"});
        this.instance = instance;
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        message.delete();
        if(args.length < 1){
            new me.inao.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericArgsErr", null, this), Color.RED,  message.getChannel());
            return;
        }
        List<Role> user = message.getUserAuthor().get().getRoles(this.instance.getApi().getServerById(instance.getId()).get());
        for (Role role : user){
            if(role.getName().equalsIgnoreCase("YouTube")){
                new me.inao.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageYoutubeAlreadyLinked", null, this), Color.RED, message.getChannel());
                return;
            }
        }
        if(args[0].equalsIgnoreCase("start")){
            if(args.length != 2) {
                new me.inao.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageGenericArgsErr", null, this), Color.RED,  message.getChannel());
                return;
            }
            if(!args[1].matches(".{24}")){
                new me.inao.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageYoutubeNotValidId", null, this), Color.RED, message.getChannel());
                return;
            }

            for (State state : users){
                if(state.getUserid().equals(message.getAuthor().getIdAsString())){
                    new me.inao.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageYoutubeAlreadyLinking", null, this), Color.RED, message.getChannel());
                    return;
                }
            }

            users.add(new State(message.getAuthor().getIdAsString(), args[1]));

            for (State state : users){
                if(state.getUserid().equals(message.getAuthor().getIdAsString())){
                    new MessageBuilder().setEmbed(new EmbedBuilder()
                            .setColor(Color.CYAN)
                            .addField("Code", state.getToken())
                            .addField("How to use", instance.getConfig().getMessage("messageYoutubePM", null, this))
                    ).send(message.getUserAuthor().get());
                }
            }
        }

        if(args[0].equalsIgnoreCase("verify")){
            State state = null;
            for (State record : users){
                if(record.getUserid().equals(message.getAuthor().getIdAsString())){
                    if(work(record)){
                        new me.inao.botforgod.utils.Message("YouTube Verfiy",
                                instance.getConfig().getMessage("messageYoutubeSuccess", new String[][]{
                                        {"%ytchannel%", record.getUsername()},
                                        {"%user%", message.getAuthor().getDisplayName()},
                                        {"%subs%", record.getSubs()}
                                }, this)
                                , Color.yellow, instance.getApi().getServerById(instance.getId()).get().getTextChannelsByName("yt-verify").get(0).asTextChannel().get());
                        message.getAuthor().asUser().get().addRole(instance.getApi().getServerById(instance.getId()).get().getRolesByName("YouTube").get(0));
                        state = record;
                        return;
                    }else {
                        new me.inao.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageYoutubeInvalid", null, this), Color.RED, message.getChannel());
                        state = record;
                    }
                }
            }
            new me.inao.botforgod.utils.Message(message.getAuthor(), "Error!", instance.getConfig().getMessage("messageYoutubeProcessNotStarted", null, this), Color.RED, message.getChannel());
            if(state != null){
                users.remove(state);
            }
        }
    }
    private boolean work(State record){
        String json = new Connectiontoweb("https://www.googleapis.com/youtube/v3/channels?part=snippet%2Cstatistics&id=" + record.getChannelid() + "&key=" + instance.getConfig().getApi("YouTubeDataApi")).getContent();
        if(Integer.parseInt(new JsonParser().parseObject(json).get("pageInfo").getAsJsonObject().get("totalResults").getAsString()) == 0){
            return false;
        }
        if(Integer.parseInt(new JsonParser().parseObject(json).get("items").getAsJsonArray().get(0).getAsJsonObject().get("statistics").getAsJsonObject().get("subscriberCount").getAsString()) < 100){
            return false;
        }
        if(new JsonParser().parseObject(json).get("items").getAsJsonArray().get(0).getAsJsonObject().get("snippet").getAsJsonObject().get("description").getAsString().contains(record.getToken())) {
            record.setUsername(new JsonParser().parseObject(json).get("items").getAsJsonArray().get(0).getAsJsonObject().get("snippet").getAsJsonObject().get("title").getAsString());
            record.setSubs(new JsonParser().parseObject(json).get("items").getAsJsonArray().get(0).getAsJsonObject().get("statistics").getAsJsonObject().get("subscriberCount").getAsString());
        }
        return true;
    }
}

class State{
    private String userid, channelid, token, username, subs;
    State(String userid, String channelid){
        this.userid = userid;
        this.channelid = channelid;
        this.token = genCode();
    }
    private String genCode(){
        char[] charset = "abcdefghijkmnqrstuvwxyzABCDEFGHIJKLMNQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 63; i++){
            builder.append(charset[new Random().nextInt(charset.length)]);
        }
        return builder.toString();
    }

    public String getUserid() {
        return userid;
    }

    public String getChannelid() {
        return channelid;
    }

    public String getToken() {
        return token;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubs() {
        return subs;
    }

    public void setSubs(String subs) {
        this.subs = subs;
    }
}