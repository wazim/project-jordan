package net.wazim.jordan;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.client.fixtures.TestOnlyPersistableDatabase;
import net.wazim.jordan.persistence.BluRayDatabase;
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
        BluRay myBluRay = new BluRay("Kung Fu Hustle", "£5.00", "£4.00", false);
        bluRayDatabase.saveBluRay(myBluRay);

        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").name(), is(myBluRay.name()));
        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").priceNew(), is(myBluRay.priceNew()));
        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").priceUsed(), is(myBluRay.priceUsed()));
        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").isOwned(), is(myBluRay.isOwned()));

        assertThat(bluRayDatabase.getAllBluRays().size(), is(1));
    }

    @Test
    public void severalBluRaysAreStoredInTheDatabase() {
        BluRay oneBluRay = new BluRay("Kung Fu Hustle", "£5.00", "£4.00", false);
        BluRay twoBluRay = new BluRay("King Kong", "£2.00", "£3.00", true);
        BluRay threeBluRay = new BluRay("The Godfather", "£3.99", "£2.00", false);

        bluRayDatabase.saveBluRay(oneBluRay);
        bluRayDatabase.saveBluRay(twoBluRay);
        bluRayDatabase.saveBluRay(threeBluRay);

        assertThat(bluRayDatabase.getAllBluRays().size(), is(3));
    }

}
