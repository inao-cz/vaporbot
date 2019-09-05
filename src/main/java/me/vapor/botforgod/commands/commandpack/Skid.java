package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public final class Skid extends Command {
    public Skid(NewMain instance) {
        super(instance, "skid", "skid", null);
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        try {
            final ArrayList<String> map = this.getUserMap(message, instance);
            if(map == null){
                return;
            }
            final StringBuilder message1 = new StringBuilder();
            message1.append("**Skid server is ONLINE**");
            if(map.size() > 30){
                for (int i = 0; i <= 30; i++ ) {
                    if (message1.length() > 0) {
                        message1.append("\n");
                    }
                    final String[] splitted = map.get(i).split(":");
                    message1.append("**").append(splitted[0]).append("**");
                    if (splitted[1].equalsIgnoreCase("-")) {
                        message1.append(" is in main menu");
                    } else {
                        message1.append(" is playing on ");
                        message1.append("**").append(splitted[1]).append("**");
                    }
                }
                message1.append("\n").append("**and more ").append(map.size() - 30).append(" users are online.**");
            }else{
                for(String arg : map){
                    if (message1.length() > 0) {
                        message1.append("\n");
                    }
                    final String[] splitted = arg.split(":");
                    message1.append("**").append(splitted[0]).append("**");
                    if (splitted[1].equalsIgnoreCase("-")) {
                        message1.append(" is in main menu");
                    } else {
                        message1.append(" is playing on ");
                        message1.append("**").append(splitted[1]).append("**");
                    }
                }
            }
            message1.append("\n------------\nIn total: **").append(map.size()).append("** players");
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Executed command skid", message1.toString(), Color.LIGHT_GRAY, message.getChannel());
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    private ArrayList<String> getUserMap(Message message, NewMain instance) throws IOException {
        URL url = new URL(instance.getConfig().getApi("SkidServerUrl"));
        try{
            URLConnection connect = url.openConnection();
            connect.setConnectTimeout(1000);
            InputStream instr = connect.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(instr));
            ArrayList<String> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] args = line.split(":");
                if (args.length == 2) {
                    players.add(line);
                }
            }
            reader.close();
            instr.close();
            return players;
        }catch (SocketTimeoutException e){
            new me.vapor.botforgod.utils.Message("Skid Server is down",
                    "Sorry about that but it looks like Skid server is down.",
                    Color.RED,
                    message.getChannel()
            );
        }
        return null;
    }
}
