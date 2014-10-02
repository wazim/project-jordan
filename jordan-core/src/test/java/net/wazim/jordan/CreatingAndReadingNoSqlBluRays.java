package net.wazim.jordan;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.fixtures.TestOnlyPersistableDatabase;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.persistence.MongoBluRayDatabase;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CreatingAndReadingNoSqlBluRays {

    private BluRayDatabase bluRayDatabase = new TestOnlyPersistableDatabase(); // Use if Mongo isn't running...
//    private BluRayDatabase bluRayDatabase = new MongoBluRayDatabase(); // Use to test the actual Mongo instance...

    @Before
    public void setUp() throws UnknownHostException {
        bluRayDatabase.clearDownDatabase();
    }

    @Test
    public void aBluRayIsStoredInTheDatabase() {
        BluRay myBluRay = new BluRay("Kung Fu Hustle", "£5.00", false);
        bluRayDatabase.saveBluRay(myBluRay);

        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").name(), is(myBluRay.name()));
        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").price(), is(myBluRay.price()));
        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").isOwned(), is(myBluRay.isOwned()));

        assertThat(bluRayDatabase.getAllBluRays().size(), is(1));
    }

    @Test
    public void severalBluRaysAreStoredInTheDatabase() {
        BluRay oneBluRay = new BluRay("Kung Fu Hustle", "£5.00", false);
        BluRay twoBluRay = new BluRay("King Kong", "£2.00", true);
        BluRay threeBluRay = new BluRay("The Godfather", "£3.99", false);

        bluRayDatabase.saveBluRay(oneBluRay);
        bluRayDatabase.saveBluRay(twoBluRay);
        bluRayDatabase.saveBluRay(threeBluRay);

        assertThat(bluRayDatabase.getAllBluRays().size(), is(3));
    }

}
