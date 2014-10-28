package net.wazim.jordan;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.stub.AmazonIndividualPageBuilder;
import net.wazim.jordan.stub.AmazonStub;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JordanListingUpdaterTest {

    private AmazonStub stub;

    @Before
    public void setup() {
        stub = new AmazonStub();
    }

    @After
    public void tearDown() {
        stub.stopServer();
    }

    @Test
    public void jordanUpdatesListings() {
        BluRayDatabase database = new InMemoryPersistableDatabase();
        database.saveBluRay(new BluRay("The Godfather", 1.10, 1.10, "http://localhost:11511/movie/TheGodfather", true));

        primeAmazonWithNewPrice("The Godfather", 0.99, 0.59);

        JordanListingUpdater updater = new JordanListingUpdater(database);
        updater.updateFilms();

        assertThat(database.findBluRayByName("The Godfather").getPriceNew(), is(0.99));
        assertThat(database.findBluRayByName("The Godfather").getPriceUsed(), is(0.59));
    }

    @Test
    public void jordanUpdaterRemovesOutOfRangePriceBluray() {
        BluRayDatabase database = new InMemoryPersistableDatabase();
        database.saveBluRay(new BluRay("The Godfather", 1.10, 1.15, "http://localhost:11511/movie/TheGodfather", true));

        primeAmazonWithNewPrice("The Godfather", 5.60, 7.59);

        JordanListingUpdater updater = new JordanListingUpdater(database);
        updater.updateFilms();

        assertThat(database.getAllBluRays().size(), is(0));
    }

    private void primeAmazonWithNewPrice(String blurayName, double newPrice, double usedPrice) {
        stub.createPageAndPrimeResponse("/movie/" + blurayName.replace(" ", ""), 200,
                new AmazonIndividualPageBuilder()
                        .withName(blurayName).withNewPrice(newPrice).withUsedPrice(usedPrice).build());
    }

}