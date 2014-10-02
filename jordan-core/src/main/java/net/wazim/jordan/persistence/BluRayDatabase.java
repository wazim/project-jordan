package net.wazim.jordan.persistence;

import net.wazim.jordan.domain.BluRay;

import java.util.List;

public interface BluRayDatabase {

    public BluRay findBluRayByName(String name);

    public BluRay getFirstBluRay();

    public List<BluRay> getAllBluRays();

    public List<BluRay> getAllOwnedBluRays();

    public List<BluRay> getAllUnownedBluRays();

    public void saveBluRay(BluRay bluRay);

    public void clearDownDatabase();

}
