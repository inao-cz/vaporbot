package me.inao.botforgod.classes;

import lombok.AllArgsConstructor;
import me.inao.botforgod.NewMain;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.TimerTask;

@AllArgsConstructor
public class CaptchaKicker extends TimerTask {
    private NewMain instance;
    @Override
    public void run() {
        if(instance.getCaptchas().size() == 0) return;
        instance.getCaptchas().forEach(captcha->{
            instance.getApi().getUserById(captcha.split(":")[2]).join().getJoinedAtTimestamp(instance.getServer()).ifPresent(time->{
                if(time.plus(2L, ChronoUnit.DAYS).isBefore(Instant.now())){
                    String[] split = captcha.split(":");
                    instance.getServer().kickUser(instance.getServer().getMemberById(split[2]).get(), "Captcha time limit expired..");
                    instance.getServer().getTextChannelsByName("captcha-"+split[0].toLowerCase()).get(0).delete();
                }
            });
        });
    }
}
