package net.wazim.jordan.persistence;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import net.wazim.jordan.domain.BluRay;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static net.wazim.jordan.fixtures.BluRayDataFixtures.someOwnedBluRay;
import static net.wazim.jordan.fixtures.BluRayDataFixtures.someUnownedBluRay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MongoBluRayDatabaseTest {

    private MongoBluRayDatabase database = new MongoBluRayDatabase("mongodb://localhost:12345");
    private static MongodProcess mongod;

    @BeforeClass
    public static void startEmbeddedMongo() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        int port = 12345;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutable;
        mongodExecutable = starter.prepare(mongodConfig);
        mongod = mongodExecutable.start();
    }

    @Before
    public void clearDownDatabaseBeforeEachTest() {
        database.clearDownDatabase();
    }

    @AfterClass
    public static void shutDownEmbeddedMongo() {
        mongod.stop();
    }

    @Test
    public void canSaveAndRetrieveABluRayFromTheMongoDatabase() {
        BluRay bluRay = someUnownedBluRay();
        database.saveBluRay(bluRay);
        BluRay storedBluray = database.findBluRayById(bluRay.getId());

        assertThat(storedBluray.getId(), is(bluRay.getId()));
        assertThat(storedBluray.getName(), is(bluRay.getName()));
        assertThat(storedBluray.getPriceNew(), is(bluRay.getPriceNew()));
        assertThat(storedBluray.getPriceUsed(), is(bluRay.getPriceUsed()));
        assertThat(storedBluray.getIsInteresting(), is(bluRay.getIsInteresting()));
        assertThat(storedBluray.getUrl(), is(bluRay.getUrl()));
        assertThat(storedBluray.getRating(), is(bluRay.getRating()));
    }

    @Test
    public void canSaveAndRetrieveBluRayFromMongoUsingName() {
        BluRay bluRay = someOwnedBluRay();
        database.saveBluRay(bluRay);
        BluRay storedBluray = database.findBluRayByName(bluRay.getName());

        assertThat(storedBluray.getId(), is(bluRay.getId()));
        assertThat(storedBluray.getName(), is(bluRay.getName()));
        assertThat(storedBluray.getPriceNew(), is(bluRay.getPriceNew()));
        assertThat(storedBluray.getPriceUsed(), is(bluRay.getPriceUsed()));
        assertThat(storedBluray.getIsInteresting(), is(true));
        assertThat(storedBluray.getUrl(), is(bluRay.getUrl()));
        assertThat(storedBluray.getRating(), is(bluRay.getRating()));
    }

    @Test
    public void canClearDownDatabase() throws InterruptedException {
        BluRay bluRay = someUnownedBluRay();
        database.saveBluRay(bluRay);

        assertThat(database.getAllBluRays().size(), is(1));

        database.clearDownDatabase();

        assertThat(database.getAllBluRays().size(), is(0));
    }

    @Test
    public void canRemoveInterestFromBluray() {
        BluRay bluRay = someOwnedBluRay();
        database.saveBluRay(bluRay);

        assertThat(database.findBluRayById(bluRay.getId()).getIsInteresting(), is(true));

        database.removeInterest(bluRay.getName());

        assertThat(database.findBluRayById(bluRay.getId()).getIsInteresting(), is(false));
    }

    @Test
    public void canRemoveABluRay() {
        BluRay bluRay = someUnownedBluRay();
        database.saveBluRay(bluRay);

        assertThat(database.getAllBluRays().size(), is(1));

        database.deleteBluRay(bluRay);

        assertThat(database.getAllBluRays().size(), is(0));
    }

    @Test
    public void canUpdateABluRay() {
        BluRay bluRay = someOwnedBluRay();
        database.saveBluRay(bluRay);
        database.updateBluray(new BluRay(bluRay.getName(), 0.10, 0.10, "abc", true, 50));

        assertThat(database.findBluRayById(bluRay.getId()).getPriceUsed(), is(0.10));
        assertThat(database.findBluRayById(bluRay.getId()).getPriceNew(), is(0.10));
    }

    @Test
    public void canGetAllBluRays() {
        for(int i = 0; i<10;i++) {
            BluRay bluRay = someOwnedBluRay();
            database.saveBluRay(bluRay);
        }

        for(int i=0; i<10;i++) {
            BluRay bluRay = someUnownedBluRay();
            database.saveBluRay(bluRay);
        }

        assertThat(database.getAllBluRays().size(), is(20));
    }

    @Test
    public void canGetAllInterestingBluRays() {
        for(int i = 0; i<10;i++) {
            BluRay bluRay = someOwnedBluRay();
            database.saveBluRay(bluRay);
        }

        for(int i=0; i<10;i++) {
            BluRay bluRay = someUnownedBluRay();
            database.saveBluRay(bluRay);
        }

        assertThat(database.getAllInterestingBluRays().size(), is(10));
    }

}
