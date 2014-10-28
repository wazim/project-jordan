package net.wazim.jordan;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.properties.JordanProperties;
import org.quartz.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static net.wazim.jordan.utils.BluRayParser.parseIntoBluRays;

public class AmazonGoer implements Job {

    private static final Logger log = Logger.getLogger(AmazonGoer.class.getName());
    private BluRayDatabase database;

    private JordanHttpResponse response;
    private ArrayList<BluRay> bluRays;

    public AmazonGoer(BluRayDatabase database) {
        this.database = database;
        bluRays = new ArrayList<BluRay>();
    }

    public AmazonGoer() {
        // Only should be used for Quartz.
    }

    public void go(URI requestUrl) {
        JordanHttpClient client = new JordanHttpClient();

        response = client.getRequest(requestUrl);
//        log.info(response.getResponseBody()); //This logs the entire response body to the console... Is that necessary?
        bluRays = parseIntoBluRays(response, requestUrl);

        saveBluRaysInDatabase();
    }

    private void saveBluRaysInDatabase() {
        for (BluRay bluRay : bluRays) {
            database.saveBluRay(bluRay);
        }
    }

    public int responseCode() {
        return response.getResponseCode();
    }

    public String responseBody() {
        return response.getResponseBody();
    }

    public List<BluRay> bluRays() {
        return bluRays;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            SchedulerContext schedulerContext = jobExecutionContext.getScheduler().getContext();
            BluRayDatabase database = (BluRayDatabase) schedulerContext.get("database");
            JordanProperties properties = (JordanProperties) schedulerContext.get("properties");
            log.info("Starting schedule. Going to " + properties.getRequestUrl() + " and will refresh in " + properties.minutesToRefresh() + " minutes");

            new JordanListingUpdater(database)
                    .updateFilms();

            new AmazonGoer(database)
                    .go(properties.getRequestUrl());

        } catch (SchedulerException e) {
            log.info("Scheduler failed to instantiate.");
            e.printStackTrace();
        }
    }
}
