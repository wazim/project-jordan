package net.wazim.jordan.client.fixtures;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import java.util.ArrayList;
import java.util.List;

public class TestOnlyPersistableDatabase implements BluRayDatabase {

    private ArrayList<BluRay> allBluRays = new ArrayList<BluRay>();

    @Override
    public BluRay findBluRayByName(String name) {
        for (BluRay bluRay : allBluRays) {
            if(bluRay.name().equals(name)){
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
    public List<BluRay> getAllOwnedBluRays() {
        return null;
    }

    @Override
    public List<BluRay> getAllUnownedBluRays() {
        return null;
    }

    @Override
    public void saveBluRay(BluRay bluRay) {
        allBluRays.add(bluRay);
    }

    @Override
    public void clearDownDatabase() {
        allBluRays.clear();
    }

}
