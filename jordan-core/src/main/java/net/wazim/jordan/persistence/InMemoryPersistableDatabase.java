package net.wazim.jordan.persistence;

import net.wazim.jordan.domain.BluRay;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPersistableDatabase implements BluRayDatabase {

    private ArrayList<BluRay> allBluRays = new ArrayList<BluRay>();

    @Override
    public BluRay findBluRayByName(String name) {
        for (BluRay bluRay : allBluRays) {
            if(bluRay.getName().equals(name)){
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
            if(bluray.getIsInteresting()){
                interestingBluRays.add(bluray);
            }
        }
        return interestingBluRays;
    }

    @Override
    public void saveBluRay(BluRay bluRay) {
        allBluRays.add(bluRay);
    }

    @Override
    public void clearDownDatabase() {
        allBluRays.clear();
    }

    @Override
    public void removeInterest(String movie) {
        for (BluRay bluRay : allBluRays) {
            if(bluRay.getName().replace("+", " ").equals(movie)){
                bluRay.setInteresting(false);
            }
        }
    }

}
