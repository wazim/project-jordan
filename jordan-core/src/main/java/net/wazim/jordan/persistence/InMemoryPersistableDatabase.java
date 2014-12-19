package net.wazim.jordan.persistence;

import net.wazim.jordan.domain.BluRay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryPersistableDatabase implements BluRayDatabase {

    private List<BluRay> allBluRays = new CopyOnWriteArrayList<BluRay>();
    private static final Logger log = LoggerFactory.getLogger(InMemoryPersistableDatabase.class);

    @Override
    public BluRay findBluRayByName(String name) {
        for (BluRay bluRay : allBluRays) {
            if (bluRay.getName().equals(name)) {
                return bluRay;
            }
        }
        return null;
    }

    @Override
    public BluRay findBluRayById(int id) {
        for (BluRay bluRay : allBluRays) {
            if(bluRay.getId() == id) {
                return bluRay;
            }
        }
        return null;
    }

    @Override
    public BluRay getFirstBluRay() {
        return allBluRays.get(0);
    }

    @Override
    public List<BluRay> getAllBluRays() {
        return allBluRays;
    }

    @Override
    public List<BluRay> getAllInterestingBluRays() {
        List<BluRay> interestingBluRays = new ArrayList<BluRay>();
        for (BluRay bluray : allBluRays) {
            if (bluray.getIsInteresting()) {
                interestingBluRays.add(bluray);
            }
        }
        return interestingBluRays;
    }

    @Override
    public void saveBluRay(BluRay bluRay) {
        if (doesNotExist(bluRay)) {
            allBluRays.add(bluRay);
            log.info(String.format("Added %s to the database", bluRay.getName()));
        } else {
            updateBluray(bluRay);
        }
        try {
            LocalStorage localStorage = new LocalStorage(this);
            localStorage.writeToFile();
        } catch (Exception e) {
            log.info("Failed to write to file "+e);
        }
    }

    public void updateBluray(BluRay bluRay) {
        for (BluRay storedBluray : allBluRays) {
            if (storedBluray.getName().equals(bluRay.getName())) {
                storedBluray.setPriceNew(bluRay.getPriceNew());
                storedBluray.setPriceUsed(bluRay.getPriceUsed());
            }
        }
    }

    @Override
    public void deleteBluRay(BluRay bluRay) {
        for (BluRay storedBluray : allBluRays) {
            if (storedBluray.getName().equals(bluRay.getName())) {
                allBluRays.remove(storedBluray);
            }
        }
    }

    @Override
    public void clearDownDatabase() {
        allBluRays.clear();
    }

    @Override
    public void removeInterest(String name) {
        for (BluRay bluRay : allBluRays) {
            if (bluRay.getName().equals(name)) {
                bluRay.setInteresting(false);
            }
        }
    }

    private boolean doesNotExist(BluRay bluRay) {
        for (BluRay storedBluray : allBluRays) {
            if (storedBluray.getName().equals(bluRay.getName())) {
                return false;
            }
        }
        return true;
    }

}
