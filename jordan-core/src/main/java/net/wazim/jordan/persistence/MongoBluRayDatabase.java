package net.wazim.jordan.persistence;

import com.mongodb.*;
import net.wazim.jordan.domain.BluRay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class MongoBluRayDatabase implements BluRayDatabase {

    private final DBCollection allBluRays;
    private static final Logger log = LoggerFactory.getLogger(MongoBluRayDatabase.class);

    public MongoBluRayDatabase(String mongoUri) {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(new MongoClientURI(mongoUri));
        } catch (UnknownHostException e) {
            System.out.println("Cannot connect to Mongo " + e.toString());
            log.error(String.format("Failed to connect to Mongo URI(%s)", mongoUri), e.toString());
        }
        DB blurayDb = mongoClient.getDB("blurays");
        allBluRays = blurayDb.getCollection("myBluRays");
        log.info("Successfully connected to Mongo");
    }

    @Override
    public BluRay findBluRayByName(String name) {
        DBObject retrievedObject = allBluRays.findOne(new BasicDBObject("name", name));

        double priceNew = Double.parseDouble(retrievedObject.get("priceNew").toString());
        double priceUsed = Double.parseDouble(retrievedObject.get("priceUsed").toString());

        return new BluRay(
                retrievedObject.get("name").toString(),
                priceNew,
                priceUsed,
                retrievedObject.get("url").toString(),
                Boolean.valueOf(retrievedObject.get("isInteresting").toString()),
                parseInt(retrievedObject.get("rating").toString()));
    }

    @Override
    public BluRay findBluRayById(int id) {
        DBObject retrievedObject = allBluRays.findOne(new BasicDBObject("id", String.valueOf(id)));

        double priceNew = Double.parseDouble(retrievedObject.get("priceNew").toString());
        double priceUsed = Double.parseDouble(retrievedObject.get("priceUsed").toString());

        return new BluRay(
                retrievedObject.get("name").toString(),
                priceNew,
                priceUsed,
                retrievedObject.get("url").toString(),
                Boolean.valueOf(retrievedObject.get("isInteresting").toString()),
                parseInt(retrievedObject.get("rating").toString()));
    }

    @Override
    public BluRay getFirstBluRay() {
        return null;
    }

    @Override
    public List<BluRay> getAllBluRays() {
        ArrayList<BluRay> myBluRays = new ArrayList<BluRay>();
        DBCursor dbObjects = allBluRays.find();
        for (DBObject dbObject : dbObjects) {
            double priceNew = Double.parseDouble(dbObject.get("priceNew").toString());
            double priceUsed = Double.parseDouble(dbObject.get("priceUsed").toString());
            myBluRays.add(new BluRay(dbObject.get("name").toString(), priceNew, priceUsed, dbObject.get("url").toString(), Boolean.valueOf(dbObject.get("isInteresting").toString()), parseInt(dbObject.get("rating").toString())));
        }
        return myBluRays;
    }

    @Override
    public List<BluRay> getAllInterestingBluRays() {
        List<BluRay> interestingBluRays = new ArrayList<BluRay>();
        List<BluRay> everyBluRay = getAllBluRays();
        for (BluRay bluRay : everyBluRay) {
            if (bluRay.getIsInteresting()) {
                interestingBluRays.add(bluRay);
            }
        }
        return interestingBluRays;
    }

    @Override
    public void saveBluRay(BluRay bluRay) {
        if (allBluRays.find(new BasicDBObject("name", bluRay.getName())).length() == 0) {
            Map<String, Object> bluRayMap = new HashMap<>();
            bluRayMap.put("name", bluRay.getName());
            bluRayMap.put("priceNew", Double.toString(bluRay.getPriceNew()));
            bluRayMap.put("priceUsed", Double.toString(bluRay.getPriceUsed()));
            bluRayMap.put("url", bluRay.getUrl());
            bluRayMap.put("isInteresting", bluRay.getIsInteresting());
            bluRayMap.put("rating", String.valueOf(bluRay.getRating()));
            bluRayMap.put("id", String.valueOf(bluRay.getId()));
            allBluRays.save(new BasicDBObject(bluRayMap));
            log.info(String.format("Added %s to the database", bluRay.getName()));
        } else {
            updateBluray(bluRay);
        }
    }

    @Override
    public void clearDownDatabase() {
        DBCursor dbObjects = allBluRays.find();
        for (DBObject dbObject : dbObjects) {
            allBluRays.remove(dbObject);
        }
    }

    @Override
    public void removeInterest(String name) {
        BasicDBObject updateDocument = new BasicDBObject();
        updateDocument.append("$set", new BasicDBObject()
                        .append("isInteresting", false)
        );

        BasicDBObject searchQuery = new BasicDBObject()
                .append("name", name);

        allBluRays.update(searchQuery, updateDocument);
    }

    @Override
    public void updateBluray(BluRay bluRay) {
        BasicDBObject updateDocument = new BasicDBObject();
        updateDocument.append("$set", new BasicDBObject()
                .append("priceNew", bluRay.getPriceNew())
                .append("priceUsed", bluRay.getPriceUsed())
        );

        BasicDBObject searchQuery = new BasicDBObject()
                .append("name", bluRay.getName());

        allBluRays.update(searchQuery, updateDocument);
    }

    @Override
    public void deleteBluRay(BluRay bluRay) {
        DBObject retrievedObject = allBluRays.findOne(new BasicDBObject("name", bluRay.getName()));

        allBluRays.remove(retrievedObject);
    }
}
