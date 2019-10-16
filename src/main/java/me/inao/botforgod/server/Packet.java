package me.inao.botforgod.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Packet {
    private String token;
    private String action;
    private String origin;
    private String message;
    public Packet(String token, String action, String origin, String message) {
        this.token = token;
        this.action = action;
        this.origin = origin;
        this.message = message;
    }

}