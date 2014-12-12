package net.wazim.jordan;

import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.persistence.LocalStorage;
import net.wazim.jordan.properties.JordanProductionProperties;
import net.wazim.jordan.properties.JordanProperties;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JordanRunner {

    private static final Logger log = LoggerFactory.getLogger(AmazonGoer.class);

    public static void main(String[] args) throws SchedulerException {
        log.info("Welcome to Project Jordan!");
        BluRayDatabase database = new InMemoryPersistableDatabase();

        readTitlesFromLocalStorage(database);

        JordanProperties properties = new JordanProductionProperties();

        new JordanScheduler(properties, database);
        new JordanRunner(properties, database, Integer.parseInt(System.getenv("PORT")));
    }

    public JordanRunner(JordanProperties properties, BluRayDatabase database, int port) {
        new JordanServer(properties, database, port);

        JordanListingUpdater updater = new JordanListingUpdater(database);
        updater.updateFilms();

        AmazonGoer amazonGoer = new AmazonGoer(database);
        amazonGoer.go(properties.getRequestUrl());
    }

    private static void readTitlesFromLocalStorage(BluRayDatabase database) {
        LocalStorage localStorage = new LocalStorage(database);
        try {
            localStorage.readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
