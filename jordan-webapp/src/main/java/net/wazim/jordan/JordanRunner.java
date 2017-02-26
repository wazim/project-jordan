package net.wazim.jordan;

import net.wazim.jordan.mail.JordanMailSender;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.persistence.MongoBluRayDatabase;
import net.wazim.jordan.properties.JordanProductionProperties;
import net.wazim.jordan.properties.JordanProperties;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;

public class JordanRunner {

    private static final Logger log = LoggerFactory.getLogger(AmazonGoer.class);
    private final AmazonGoer amazonGoer;
    private final JordanProperties properties;
    private final JordanServer jordanServer;

    public static void main(String[] args) throws SchedulerException, MessagingException {
        log.info("Welcome to Project Jordan!");

        JordanProperties properties = new JordanProductionProperties();

        int port = (System.getenv("PORT") != null) ? Integer.parseInt(System.getenv("PORT")) : 12500;
        BluRayDatabase database = (System.getenv("MONGOLAB_URL") != null)
                ? new MongoBluRayDatabase(System.getenv("MONGOLAB_URL"))
                : new InMemoryPersistableDatabase();

        //new JordanScheduler(properties, database);
        new JordanRunner(properties, database, new JordanMailSender("smtp.gmail.com"), port);
    }

    public JordanRunner(JordanProperties properties, BluRayDatabase database, JordanMailSender mailSender, int port) {
        this.properties = properties;

        database.setMailSender(mailSender);
        jordanServer = new JordanServer(this.properties, database, port);

        JordanListingUpdater updater = new JordanListingUpdater(database);
        updater.updateFilms();

        amazonGoer = new AmazonGoer(database);
        amazonGoer.go(properties.getRequestUrl());
    }

    public void refresh() {
        amazonGoer.go(properties.getRequestUrl());
    }

    public void stopServer() {
        jordanServer.stopServer();
    }

}
