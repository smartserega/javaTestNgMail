package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModelsParser {
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = builder.create();

    public static JsonObject jsonReadFile(String filePath) throws IOException {
        StringBuilder builder = new StringBuilder();
        try {
            FileInputStream fstream = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String row;
            while ((row = br.readLine()) != null) {
                builder.append(row);
            }

        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
            System.exit(1);
        }

        return JsonParser.parseString(builder.toString()).getAsJsonObject();
    }

    public static UserModel jsonParsingUser(String absolutePath) throws IOException {
        JsonObject objectWithDataFromJson = jsonReadFile(absolutePath);
        return gson.fromJson(objectWithDataFromJson, UserModel.class);
    }
}
