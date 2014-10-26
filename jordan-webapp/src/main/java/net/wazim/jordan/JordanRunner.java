package net.wazim.jordan;

import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.properties.JordanProductionProperties;
import net.wazim.jordan.properties.JordanProperties;

public class JordanRunner {

    private final JordanProperties properties;
    private final BluRayDatabase database;

    public static void main(String[] args) {
        new JordanRunner(new JordanProductionProperties(), new InMemoryPersistableDatabase());
    }

    public JordanRunner(JordanProperties properties, BluRayDatabase database) {
        this.properties = properties;
        this.database = database;

        AmazonGoer amazonGoer = new AmazonGoer(database);
        amazonGoer.go(properties.getRequestUrl());

        new JordanServer(properties, database);
    }

}
