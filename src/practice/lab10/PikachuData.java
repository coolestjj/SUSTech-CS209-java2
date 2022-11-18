package practice.lab10;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PikachuData {

    public static String getRawJson(String url) throws IOException {

        URL restURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();

        conn.setRequestMethod("GET"); // POST GET PUT DELETE
        conn.setRequestProperty("Accept", "*/*");

//        System.out.println("Connecting...");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        System.out.println("Json get!");
        String rawJson = br.readLine();
        br.close();
        return rawJson;
    }

    public static void translateJson(String rawJson) {
        JSONObject jsonObject = JSONObject.parseObject(rawJson);
        JSONArray jsonArray = jsonObject.getJSONArray("abilities");
        List<String> abilities = new ArrayList<>();
        for (int i = 0 ; i < jsonArray.size() ; i++) {
            abilities.add(jsonArray.getJSONObject(i).getJSONObject("ability").getString("name"));
        }

        System.out.println("Name: " + jsonObject.getString("name"));
        System.out.println("Height: " + jsonObject.getIntValue("height"));
        System.out.println("Weight: " + jsonObject.getIntValue("weight"));
        System.out.println("Abilities:" + abilities);
    }

    public static void main(String[] args) throws IOException {
        String rawJson = getRawJson("https://pokeapi.co/api/v2/pokemon/pikachu/");
        translateJson(rawJson);
    }
}
