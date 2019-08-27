package me.vapor.botforgod.utils;

import me.vapor.botforgod.NewMain;
import java.util.Timer;
import java.util.TimerTask;

public class Changer {
    private NewMain instance;
    private byte position = 0;
    public Changer(NewMain instance){
        this.instance = instance;
        renamer();
    }
    private void renamer(){
        String[] names = {"G o o d", "O l d", "i n a o ' s", "D i s c o r d", "S e r v e r"};
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(position == names.length){
                    position = 0;
                }
                instance.getApi().getServerById(instance.getId()).get().updateName(names[position]);
                position++;
            }
        }, 0, 30000);
    }
}
