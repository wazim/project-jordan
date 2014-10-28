package net.wazim.jordan;

import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.properties.JordanProductionProperties;
import net.wazim.jordan.properties.JordanProperties;
import org.quartz.SchedulerException;

public class JordanRunner {

    public static void main(String[] args) throws SchedulerException {
        BluRayDatabase database = new InMemoryPersistableDatabase();
        JordanProperties properties = new JordanProductionProperties();

        new JordanScheduler(properties, database);
        new JordanRunner(properties, database);
    }

    public JordanRunner(JordanProperties properties, BluRayDatabase database) {
        new JordanServer(properties, database);

        JordanListingUpdater updater = new JordanListingUpdater(database);
        updater.updateFilms();

        AmazonGoer amazonGoer = new AmazonGoer(database);
        amazonGoer.go(properties.getRequestUrl());
    }

}
