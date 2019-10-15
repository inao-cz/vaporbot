package me.inao.botforgod.classes;

public class ExceptionCatcher {
    public ExceptionCatcher(Exception e){
        System.out.println("---- EXCEPTION CATCHED! ----");
        e.printStackTrace();
        System.out.println("----------------------------");
    }
}
