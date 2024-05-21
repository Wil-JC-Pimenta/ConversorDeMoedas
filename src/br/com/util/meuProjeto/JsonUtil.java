package br.com.util.meuProjeto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonUtil {

    public static JsonObject parseJsonString(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JsonObject.class);
    }
}
