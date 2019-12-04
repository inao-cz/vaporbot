package me.inao.botforgod.classes;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.utils.Message;
import org.javacord.api.entity.message.Reaction;

import java.awt.*;
import java.util.TimerTask;

public class Vote extends TimerTask {
    private String id;
    private String voteId;
    private NewMain instance;
    public Vote(NewMain instance, String id, String voteId){
        this.instance = instance;
        this.voteId = voteId;
        this.id = id;
    }
    @Override
    public void run() {
        int y = instance.getServer().getChannelsByName("vote").get(0).asServerTextChannel().get().getMessageById(id).join().getReactionByEmoji("✔").get().getCount() - 1;
        int n = instance.getServer().getChannelsByName("vote").get(0).asServerTextChannel().get().getMessageById(id).join().getReactionByEmoji("❌").get().getCount() - 1;

        String result;
        if(y == n) result = "50/50!";
        else{
            result = y > n ? "YES WINS!" : "NO WINS!";
        }
        new Message(String.format("Closing vote (%s)", voteId),
                String.format("Final results are\n%d for YES\n%d for NO\n%s", y, n, result),
                Color.RED, instance.getServer().getChannelsByName("vote").get(0).asTextChannel().get());
    }
}
