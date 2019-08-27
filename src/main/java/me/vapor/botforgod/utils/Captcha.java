package me.vapor.botforgod.utils;

import me.vapor.botforgod.NewMain;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerTextChannelBuilder;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.PermissionsBuilder;
import org.javacord.api.entity.user.User;
import java.awt.*;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class Captcha {
    private byte action;
    private int result, part1, part2;
    private User user;
    private String imgName;
    private NewMain instance;
    public Captcha(NewMain instance, User user){
        instance.getCaptchas().add(this);
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
        channel = new ServerTextChannelBuilder(instance.getApi().getServerById(instance.getId()).get()).setName("captcha-"+imgName)
                .setCategory(instance.getApi().getServerById(instance.getId()).get().getChannelCategoriesByName("captcha").get(0))
                .addPermissionOverwrite(user ,new PermissionsBuilder().setAllowed(PermissionType.READ_MESSAGES).build())
                .addPermissionOverwrite(instance.getApi().getServerById(instance.getId()).get().getRolesByName("captcha").get(0), new PermissionsBuilder().setDenied(PermissionType.READ_MESSAGES).build())
                .create().join();
        new Message("Captcha", instance.getConfig().getMessage("messageCaptchaWelcome", null, null), Color.BLACK, new File(imgName + ".png"), channel);
    }
}
