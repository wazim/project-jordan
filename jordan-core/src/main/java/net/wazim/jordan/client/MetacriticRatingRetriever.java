package net.wazim.jordan.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

public class MetacriticRatingRetriever {

    public int getScoreFor(String movieName) {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.post("https://byroredux-metacritic.p.mashape.com/find/movie")
                    .header("X-Mashape-Key", "qn231zfbU7msh7xcTUou4Yr07DnKp1JPGNjjsncseZMttmXXAa")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("retry", 4)
                    .field("title", movieName)
                    .asJson();

            return response.getBody().getObject().getJSONObject("result").getInt("score");
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
