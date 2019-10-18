package me.inao.botforgod.server;

import lombok.Getter;

@Getter
public class Packet {
    private String token;
    private String action;
    private String origin;
    private String channel;
    private String message;
    public Packet(String token, String action, String channel, String origin, String message) {
        this.token = token;
        this.action = action;
        this.origin = origin;
        this.channel = channel;
        this.message = message;
    }

}
