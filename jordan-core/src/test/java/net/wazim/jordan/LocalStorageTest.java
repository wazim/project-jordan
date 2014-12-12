package net.wazim.jordan;

import net.wazim.jordan.domain.BluRay;
import net.wazim.jordan.persistence.BluRayDatabase;
import net.wazim.jordan.persistence.InMemoryPersistableDatabase;
import net.wazim.jordan.persistence.LocalStorage;
import org.json.JSONException;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static net.wazim.jordan.fixtures.BluRayDataFixtures.someOwnedBluRay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LocalStorageTest {

    @After
    public void removeTestFile() throws IOException {
        Files.delete(Paths.get(LocalStorage.LOCAL_STORAGE_JSON_FILE));
    }

    @Test
    public void canSaveFilesInJsonFormat() throws IOException, JSONException {
        BluRayDatabase database = new InMemoryPersistableDatabase();
        BluRay firstBluRay = someOwnedBluRay();
        database.saveBluRay(firstBluRay);
        database.saveBluRay(someOwnedBluRay());
        database.saveBluRay(someOwnedBluRay());

        LocalStorage localStorageSaver = new LocalStorage(database);
        localStorageSaver.writeToFile();

        database.clearDownDatabase();

        localStorageSaver.readFromFile();
        assertThat(database.getFirstBluRay().getId(), is(firstBluRay.getId()));
        assertThat(database.getAllBluRays().size(), is(3));
    }

}
