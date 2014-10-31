package net.wazim.jordan;

import net.wazim.jordan.client.JordanHttpClient;
import net.wazim.jordan.client.JordanHttpResponse;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.properties.JordanProperties;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

import static net.wazim.jordan.utils.BluRayParser.parseIntoBluRays;

public class AmazonGoer implements Job {

    private static final Logger log = LoggerFactory.getLogger(AmazonGoer.class);
    private BluRayDatabase database;

    private JordanHttpResponse response;

    public AmazonGoer(BluRayDatabase database) {
        this.database = database;
    }

    public void go(URI requestUrl) {
        JordanHttpClient client = new JordanHttpClient();

        response = client.getRequest(requestUrl);
        log.debug(response.getResponseBody());
        parseIntoBluRays(response, requestUrl, database);
    }

    public int responseCode() {
        return response.getResponseCode();
    }

    public String responseBody() {
        return response.getResponseBody();
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
            log.error("Scheduler failed to instantiate." + e);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public AmazonGoer() {
        // Only should be used for Quartz.
    }

}
