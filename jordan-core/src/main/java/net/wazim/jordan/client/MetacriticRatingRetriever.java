package net.wazim.jordan.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.slf4j.LoggerFactory;

public class MetacriticRatingRetriever {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MetacriticRatingRetriever.class);

    public int getScoreFor(String movieName) {
        int characterToCutTo = findFirstBracketIn(movieName);
        movieName = movieName.substring(0, characterToCutTo).trim();

        HttpResponse<JsonNode> response;
        try {
            response = Unirest.post("https://byroredux-metacritic.p.mashape.com/find/movie")
                    .header("X-Mashape-Key", "qn231zfbU7msh7xcTUou4Yr07DnKp1JPGNjjsncseZMttmXXAa")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("retry", 4)
                    .field("title", movieName)
                    .asJson();

            int score = response.getBody().getObject().getJSONObject("result").getInt("score");
            log.info(String.format("Metacritic Rating for %s is %d", movieName, score));
            return score;
        } catch (Exception e) {
            log.warn("Failed to connect to Metacritic");
        }
        return 0;
    }

    private int findFirstBracketIn(String movieName) {
        int characterToCutTo;
        if (movieName.contains("(")) {
            characterToCutTo = movieName.indexOf("(");
        } else {
            characterToCutTo = movieName.length();
        }
        return characterToCutTo;
    }

}
