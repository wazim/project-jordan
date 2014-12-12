package net.wazim.jordan.persistence;

import net.wazim.jordan.domain.BluRay;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalStorage {

    private static final Logger log = LoggerFactory.getLogger(LocalStorage.class);
    public static final String LOCAL_STORAGE_JSON_FILE = "localstorage.json";
    private final BluRayDatabase database;

    public LocalStorage(BluRayDatabase database) {
        this.database = database;
    }

    public void writeToFile() throws JSONException, IOException {
        try{
            Files.delete(Paths.get(LocalStorage.LOCAL_STORAGE_JSON_FILE));
        } catch(Exception e) {
            log.info(String.format("Can't find %s to delete", LOCAL_STORAGE_JSON_FILE));
        }

        JSONArray jsonArray = new JSONArray();
        for (BluRay bluRay : database.getAllBluRays()) {
            JSONObject jsonBluRay = new JSONObject();
            jsonBluRay.put("id", bluRay.getId());
            jsonBluRay.put("name", bluRay.getName());
            jsonBluRay.put("usedPrice", bluRay.getPriceUsed());
            jsonBluRay.put("newPrice", bluRay.getPriceNew());
            jsonBluRay.put("isInteresting", bluRay.getIsInteresting());
            jsonBluRay.put("url", bluRay.getUrl());
            jsonBluRay.put("rating", bluRay.getRating());
            jsonArray.put(jsonBluRay);
        }

        Path file = Files.createFile(Paths.get(LOCAL_STORAGE_JSON_FILE));
        FileUtils.write(file.toFile(), jsonArray.toString());
    }

    public void readFromFile() throws IOException, JSONException {
        String json = FileUtils.readFileToString(Paths.get(LOCAL_STORAGE_JSON_FILE).toFile());
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i<jsonArray.length(); i++) {
            JSONObject jsonBluray = new JSONObject(jsonArray.get(i).toString());
            BluRay bluRay = new BluRay(
                    jsonBluray.getString("name"),
                    jsonBluray.getDouble("newPrice"),
                    jsonBluray.getDouble("usedPrice"),
                    jsonBluray.getString("url"),
                    jsonBluray.getBoolean("isInteresting"),
                    jsonBluray.getInt("rating")
            );
            log.info("Loading "+jsonBluray.getString("name")+" from local storage");
            database.saveBluRay(bluRay);
        }
    }
}
