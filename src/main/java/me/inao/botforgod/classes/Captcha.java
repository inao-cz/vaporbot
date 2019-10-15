package me.inao.botforgod.classes;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.utils.Message;
import me.inao.botforgod.utils.Renderer;
import me.inao.botforgod.utils.SQLite;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerTextChannelBuilder;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.PermissionsBuilder;
import org.javacord.api.entity.user.User;
import java.awt.*;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.concurrent.ThreadLocalRandom;

public class Captcha {
    private byte action;
    private int result, part1, part2;
    private User user;
    private String imgName;
    private NewMain instance;
    public Captcha(NewMain instance, User user){
        this.instance = instance;
        this.user = user;
        genCaptcha();
    }
    public int getResult() {
        return result;
    }
    public int getPart1() {
        return part1;
    }
    public int getPart2() {
        return part2;
    }

    public User getUser() {
        return user;
    }

    private ServerTextChannel channel;

    private void genCaptcha(){
        part1 = ThreadLocalRandom.current().nextInt(1, 101);
        part2 = ThreadLocalRandom.current().nextInt(1, 101);
        action = (byte) ThreadLocalRandom.current().nextInt(1, 3);

        switch (action){
            case 1:
                result = part1 + part2;
                imgName = new Renderer().genImg(part1 + "+" + part2);
                setChannel();
                break;
            case 2:
                result = part1 - part2;
                imgName = new Renderer().genImg(part1 + "-" + part2);
                setChannel();
                break;
        }
    }
    private void setChannel(){
        channel = new ServerTextChannelBuilder(instance.getServer()).setName("captcha-"+imgName)
                .setCategory(instance.getServer().getChannelCategoriesByName("captcha").get(0))
                .addPermissionOverwrite(user ,new PermissionsBuilder().setAllowed(PermissionType.READ_MESSAGES).build())
                .addPermissionOverwrite(instance.getServer().getRolesByName("captcha").get(0), new PermissionsBuilder().setDenied(PermissionType.READ_MESSAGES).build())
                .addPermissionOverwrite(instance.getServer().getRolesByName("@everyone").get(0), new PermissionsBuilder().setDenied(PermissionType.READ_MESSAGES).build())
                .create()
                .join();
        instance.getCaptchas().add(imgName + ":" + result + ":" + user.getIdAsString());
//        File file = new FileOperation().getFile("captcha.txt");
//        new FileOperation().writeFile(file, imgName + ":" + result+ ":" + user.getIdAsString() + "\n");

        try{
            if(instance.getSqLite().getConnection() == null){
                instance.getSqLite().openConnection();
            }
            PreparedStatement stmt = instance.getSqLite().getConnection().prepareStatement("INSERT INTO captchas VALUES (?, ?, ?)");
            stmt.setString(1, imgName);
            stmt.setInt(2, result);
            stmt.setString(3, user.getIdAsString());
            if(!instance.getSqLite().execute(stmt)){
                System.out.println("Failed to insert captcha data.");
            }
        }catch (Exception e){
            new ExceptionCatcher(e);
        }

        new Message("Captcha", instance.getConfig().getMessage("messageCaptchaWelcome", null, null), Color.BLACK, new File(imgName + ".png"), channel);
    }
}
