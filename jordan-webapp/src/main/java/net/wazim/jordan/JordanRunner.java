package net.wazim.jordan;

import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.persistence.LocalStorage;
import net.wazim.jordan.persistence.MongoBluRayDatabase;
import net.wazim.jordan.properties.JordanProductionProperties;
import net.wazim.jordan.properties.JordanProperties;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JordanRunner {

    private static final Logger log = LoggerFactory.getLogger(AmazonGoer.class);

    public static void main(String[] args) throws SchedulerException {
        log.info("Welcome to Project Jordan!");

//        readTitlesFromLocalStorage(database);

        JordanProperties properties = new JordanProductionProperties();

        int port = (System.getenv("PORT") != null) ? Integer.parseInt(System.getenv("PORT")) : 12500;
        BluRayDatabase database = (System.getenv("MONGOLAB_URL") != null)
                ? new MongoBluRayDatabase(System.getenv("MONGOLAB_URL"))
                : new InMemoryPersistableDatabase();

        new JordanScheduler(properties, database);
        new JordanRunner(properties, database, port);
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
