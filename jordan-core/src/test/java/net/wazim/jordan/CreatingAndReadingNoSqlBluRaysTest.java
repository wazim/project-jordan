package net.wazim.jordan;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CreatingAndReadingNoSqlBluRaysTest {

    private BluRayDatabase bluRayDatabase = new InMemoryPersistableDatabase(); // Use if Mongo isn't running...
//    private BluRayDatabase bluRayDatabase = new MongoBluRayDatabase(); // Use to test the actual Mongo instance...

    @Before
    public void setUp() throws UnknownHostException {
        bluRayDatabase.clearDownDatabase();
    }

    @Test
    public void aBluRayIsStoredInTheDatabase() {
        BluRay myBluRay = new BluRay("Kung Fu Hustle", new Double(5.00), new Double(4.00), "http://amazon.co.uk/kungufhustle", false);
        bluRayDatabase.saveBluRay(myBluRay);

        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").getName(), is(myBluRay.getName()));
        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").getPriceNew(), is(myBluRay.getPriceNew()));
        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").getPriceUsed(), is(myBluRay.getPriceUsed()));
        assertThat(bluRayDatabase.findBluRayByName("Kung Fu Hustle").getIsInteresting(), is(myBluRay.getIsInteresting()));

        assertThat(bluRayDatabase.getAllBluRays().size(), is(1));
    }

    @Test
    public void severalBluRaysAreStoredInTheDatabase() {
        BluRay oneBluRay = new BluRay("Kung Fu Hustle", new Double(5.00), new Double(4.00), "http://amazon.co.uk/kungufhustle", false);
        BluRay twoBluRay = new BluRay("King Kong", new Double(2.00), new Double(3.00), "http://amazon.co.uk/kingkong", true);
        BluRay threeBluRay = new BluRay("The Godfather", new Double(3.99), new Double(2.00), "http://amazon.co.uk/thegodfather", false);

        bluRayDatabase.saveBluRay(oneBluRay);
        bluRayDatabase.saveBluRay(twoBluRay);
        bluRayDatabase.saveBluRay(threeBluRay);

        assertThat(bluRayDatabase.getAllBluRays().size(), is(3));
    }

}
