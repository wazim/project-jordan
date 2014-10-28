package net.wazim.jordan.utils;

import org.junit.Test;

import static net.wazim.jordan.utils.BluRayNameCleaner.cleanName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BluRayNameCleanerTest {

    @Test
    public void replacesSquareBracketsWithRoundedBracketsBluRays() {
        assertThat(cleanName("Batman Begins [Blu-ray] [2005] [Region Free]"), is("Batman Begins (Blu-ray) (2005) (Region Free)"));
    }

    @Test
    public void replacesPlusSymbolWithMinusSymbolBluRays() {
        assertThat(cleanName("Batman Begins [Blu-ray + DVD]"), is("Batman Begins (Blu-ray - DVD)"));
    }

    @Test
    public void replacesAmpersandWithAnd() {
        assertThat(cleanName("BLU-RAY FAST & FURIOUS: NEUES MODELL..."), is("BLU-RAY FAST and FURIOUS: NEUES MODELL..."));
    }

}