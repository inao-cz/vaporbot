package me.inao.botforgod.classes;

import me.inao.botforgod.NewMain;
import org.javacord.api.entity.user.User;
import java.util.concurrent.ThreadLocalRandom;

public class Captcha2 {
    private int solution;
    private User user;
    private NewMain instance;
    private String[] categories = {"people", "trafficlight", "bus", "firehydrant", "stairs", "other"};
    public Captcha2(NewMain instance, User user){
        this.instance = instance;
        this.user = user;
    }
    private int random(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    private int category(int mode){
        if(mode == 0){
            return random(1, 5);
        }
        return random(1, 6);
    }
    private String getTextCategory(int category){
        return categories[category];
    }
    private String getResult(){
        return getTextCategory(category(0));
    }
    public String crawler(){
        
        return "cs";
    }
}
