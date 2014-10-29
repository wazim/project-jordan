package net.wazim.jordan.ratings;

import net.wazim.jordan.client.MetacriticRatingRetriever;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MetacriticRatingRetrieverTest {

    private MetacriticRatingRetriever ratingRetriever;

    @Before
    public void setup() {
        ratingRetriever = new MetacriticRatingRetriever();
    }

    @Test
    public void canGetARatingForAMovieFromMetaCritic() {
        assertThat(ratingRetriever.getScoreFor("The Godfather"), is(100));
    }

    @Test
    public void canGetAnotherRatingForAMovieFromMetaCritic() {
        assertThat(ratingRetriever.getScoreFor("The Social Network"), is(95));
    }

    @Test
    public void ratingAvailableFromMetacritic() {
        assertThat(ratingRetriever.getScoreFor("The Dark Knight (2 Discs) (Blu-ray) (2008) (Region Free)"), is(82));
    }

}
