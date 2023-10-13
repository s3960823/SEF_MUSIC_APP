package Application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class MainTest {

    // Test case 1: Adding a new artist with valid data
    @Test
    void testAddArtistValidData() {
        ArrayList<String> occupations1 = new ArrayList<>();
        occupations1.add("Singer");

        ArrayList<String> genres1 = new ArrayList<>();
        genres1.add("Pop");
        genres1.add("Abstract");

        ArrayList<String> awards1 = new ArrayList<>();
        awards1.add("2022, Best New Artist Award");

        Main artist1 = new Main("569MMMLL_%", "John Doe", "NewYork|NY|USA",
                "01-12-1990", "Talented musician. Talented musician. Talented musician. Talented musician. Talanted Musician", occupations1, genres1, awards1);

        ArrayList<String> occupations2 = new ArrayList<>();
        occupations2.add("Painter");

        ArrayList<String> genres2 = new ArrayList<>();
        genres2.add("Abstract");
        genres2.add("POP");

        ArrayList<String> awards2 = new ArrayList<>();
        awards2.add("2020, Best Visual Artist Award");

         Main artist2 = new Main("569PPMLL_%", "Ali John", "London|England|UK",
                "03-10-1988", "Passionate about abstract art. Passionate about abstract art. Passionate about abstract art", occupations2, genres2, awards2);

        assertTrue(artist1.addArtist(), "Adding a new artist with valid data should return true");
        assertTrue(artist2.addArtist(), "Adding another new artist with valid data should return true");
    }

    // Test case 2: Adding a new artist with an invalid ID
    @Test
    void testAddArtistInvalidID() {
        ArrayList<String> occupations1 = new ArrayList<>();
        occupations1.add("Painter");

        ArrayList<String> genres1 = new ArrayList<>();
        genres1.add("Abstract");

        ArrayList<String> awards1 = new ArrayList<>();
        awards1.add("2019, Best Artistic Contribution");

        Main artist1 = new Main("InvalidID123", "Jane Smith", "Los Angeles|CA|USA",
                "05-20-1985", "Visual artist", occupations1, genres1, awards1);

        ArrayList<String> occupations2 = new ArrayList<>();
        occupations2.add("Dancer");

        ArrayList<String> genres2 = new ArrayList<>();
        genres2.add("Contemporary");

        ArrayList<String> awards2 = new ArrayList<>();
        awards2.add("2017, Best Dance Performance");

        Main artist2 = new Main("InvalidID456*", "Bob Johnson", "Paris|France",
                "12-05-1995", "Talented dancer", occupations2, genres2, awards2);

        assertFalse(artist1.addArtist(), "Adding a new artist with an invalid ID should return false");
        assertFalse(artist2.addArtist(), "Adding another new artist with an invalid ID should return false");
    }

    // Test case 3: Updating artist information with valid data
    @Test
    void testUpdateArtistValidData() {
        ArrayList<String> newOccupations1 = new ArrayList<>();
        newOccupations1.add("Painter");

        ArrayList<String> newGenres1 = new ArrayList<>();
        newGenres1.add("Abstract");
        newGenres1.add("JAZZ");

        ArrayList<String> newAwards1 = new ArrayList<>();
        newAwards1.add("2020, Best Visual Artist Award");

        Main artist1 = new Main("569MMMRR_%", "Alice Johnson", "London|England|UK",
                "03-10-1988", "A talented artist with a passion for music. Also a Manager", newOccupations1, newGenres1, newAwards1);

        ArrayList<String> newOccupations2 = new ArrayList<>();
        newOccupations2.add("Dancer");

        ArrayList<String> newGenres2 = new ArrayList<>();
        newGenres2.add("Contemporary");
        newGenres2.add("JAZZ");

        ArrayList<String> newAwards2 = new ArrayList<>();
        newAwards2.add("2020, Best Dance Performance Award");

        Main artist2 = new Main("569MMMPP_%", "Chris Brown", "Los Angeles|CA|USA",
                "05-05-1989", "A talented artist with a passion for music. Also a CTO.", newOccupations2, newGenres2, newAwards2);

        assertTrue(artist1.updateArtist("569MMMRR_%", "Alice Smith", "London|England|UK",
                "03-10-1988", "A talented artist with a passion for music. Also a CTO.", newOccupations1, newGenres1, newAwards1),
                "Updating artist information with valid data should return true");

        assertTrue(artist2.updateArtist("569MMMPP_%", "Chris Rock", "LosAngeles|CA|USA",
                "05-05-1989", "A talented artist with a passion for music. Also a CFO.", newOccupations2, newGenres2, newAwards2),
                "Updating another artist information with valid data should return true.");
    }

    // Test case 4: Updating artist information with an invalid ID
    @Test
    void testUpdateArtistInvalidID() {
        ArrayList<String> newOccupations1 = new ArrayList<>();
        newOccupations1.add("Dancer");

        ArrayList<String> newGenres1 = new ArrayList<>();
        newGenres1.add("Contemporary");

        ArrayList<String> newAwards1 = new ArrayList<>();
        newAwards1.add("2017, Best Dance Performance");

        Main artist1 = new Main("InvalidID456*", "Bob Johnson", "Paris|France",
                "12-05-1995", "Talented dancer", newOccupations1, newGenres1, newAwards1);

        ArrayList<String> newOccupations2 = new ArrayList<>();
        newOccupations2.add("Actor");

        ArrayList<String> newGenres2 = new ArrayList<>();
        newGenres2.add("Drama");

        ArrayList<String> newAwards2 = new ArrayList<>();
        newAwards2.add("2019, Best Actor");

        Main artist2 = new Main("InvalidID789#", "Emma Watson", "London|England|UK",
                "04-15-1990", "Versatile actress", newOccupations2, newGenres2, newAwards2);

        assertFalse(artist1.updateArtist("InvalidID456*", "Bob Smith", "Paris|France",
                "12-05-1995", "Talented dancer and choreographer", newOccupations1, newGenres1, newAwards1),
                "Updating artist information with an invalid ID should return false");

        assertFalse(artist2.updateArtist("InvalidID789#", "Emma Watson", "London|England|UK",
                "04-15-1990", "Versatile actress with new award", newOccupations2, newGenres2, newAwards2),
                "Updating another artist information with an invalid ID should return false");
    }

    // Test case 5: Updating artist information with invalid occupations
    @Test
    void testUpdateArtistInvalidOccupations() {
        ArrayList<String> newOccupations1 = new ArrayList<>();
        newOccupations1.add("InvalidOccupation");

        ArrayList<String> newGenres1 = new ArrayList<>();
        newGenres1.add("Rock");

        ArrayList<String> newAwards1 = new ArrayList<>();
        newAwards1.add("2021, Best Rock Album");

        Main artist1 = new Main("789HIJ123@", "Chris Brown", "Los Angeles|CA|USA",
                "05-05-1989", "Rock artist", newOccupations1, newGenres1, newAwards1);

        assertFalse(artist1.updateArtist("789HIJ123@", "Chris Rock", "Los Angeles|CA|USA",
                "05-05-1989", "Rock artist with new genre", newOccupations1, newGenres1, newAwards1),
                "Updating artist information with invalid occupations should return false");
    }

    // Test case 6: Updating artist information with an invalid award
    @Test
    void testUpdateArtistInvalidAward() {
        ArrayList<String> newOccupations1 = new ArrayList<>();
        newOccupations1.add("Singer");

        ArrayList<String> newGenres1 = new ArrayList<>();
        newGenres1.add("Pop");

        ArrayList<String> newAwards1 = new ArrayList<>();
        newAwards1.add("InvalidYear, InvalidCategory");

        Main artist1 = new Main("123ABCDE%^", "John Doe", "New York|NY|USA",
                "01-15-1990", "Talented musician", newOccupations1, newGenres1, newAwards1);

        assertFalse(artist1.updateArtist("123ABCDE%^", "John Doe", "New York|NY|USA",
                "01-15-1990", "Talented musician", newOccupations1, newGenres1, newAwards1),
                "Updating artist information with an invalid award should return false");
    }


}
