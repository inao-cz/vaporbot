package me.vapor.botforgod.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonParser {
    String getGitlabJson(String json, String section){
        JsonObject object = new com.google.gson.JsonParser().parse(json).getAsJsonObject();
        StringBuilder builder = new StringBuilder();
        for(JsonElement element : object.get(section).getAsJsonArray()){
            builder.append("**").append(element.getAsString()).append("**").append("\n");
        }
        return builder.toString().replace("+", "[+]").replace("-", "[-]").replace("_", "[Comment]");
    }
    public JsonObject parseObject(String json){
        return new com.google.gson.JsonParser().parse(json).getAsJsonObject();
    }
}
