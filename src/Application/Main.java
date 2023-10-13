package Application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    String ID;
    String Name;
    String Address;
    String Birthdate;
    String Bio;
    ArrayList<String> Occupations;
    ArrayList<String> Genres;
    ArrayList<String> Awards;

    public Main(String ID, String name, String address, String birthdate, String bio,
                  ArrayList<String> occupations, ArrayList<String> genres, ArrayList<String> awards) {
        this.ID = ID;
        this.Name = name;
        this.Address = address;
        this.Birthdate = birthdate;
        this.Bio = bio;
        this.Occupations = occupations;
        this.Genres = genres;
        this.Awards = awards;
    }

    public boolean addArtist() {
    	System.out.println("called");
        if (!isValidID(ID) || !isValidBirthdate(Birthdate) || !isValidAddress(Address) ||
                !isValidBio(Bio) || !isValidOccupations(Occupations) || !isValidAwards(Awards) ||
                !isValidGenres(Genres)) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("artists.txt", true))) {
            // Append artist information to the file
            writer.write("ID: " + ID + "\n");
            writer.write("Name: " + Name + "\n");
            writer.write("Address: " + Address + "\n");
            writer.write("Birthdate: " + Birthdate + "\n");
            writer.write("Bio: " + Bio + "\n");

            writer.write("Occupations: ");
            for (String occupation : Occupations) {
                writer.write(occupation + ", ");
            }
            writer.write("\n");

            writer.write("Genres: ");
            for (String genre : Genres) {
                writer.write(genre + ", ");
            }
            writer.write("\n");

            writer.write("Awards: ");
            for (String award : Awards) {
                writer.write(award + ", ");
            }
            writer.write("\n\n");

            System.out.println("Artist information added to the file successfully.");

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
            return false;
        }

        return true;
    }

    // Helper methods for validation

    private boolean isValidID(String id) {
    	
    	// Check if the length is exactly 10 characters
        if (id.length() != 10) {
            return false;
        }
        
        // Check the first three characters are numbers between 5 and 9
        for (int i = 0; i < 3; i++) {
            char digit = id.charAt(i);
            
            if (digit < '5' || digit > '9') {
                return false;
            }
        }

        // Check characters 4th to 8th are upper case letters (A - Z)
        for (int i = 3; i < 8; i++) {
            char letter = id.charAt(i);
            if (letter < 'A' || letter > 'Z') {
                return false;
            }
        }

        // Check the last two characters are special characters
        char secondLastChar = id.charAt(8);
        char lastChar = id.charAt(9);
        if (Character.isLetterOrDigit(secondLastChar) || Character.isLetterOrDigit(lastChar)) {
            return false;
        }

        return true;

    }

    private boolean isValidBirthdate(String birthdate) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d\\d$";
        return birthdate.matches(regex);
    }

    private boolean isValidAddress(String address) {
        String regex = "^[A-Za-z]+\\|[A-Za-z]+\\|[A-Za-z]+$";
        return address.matches(regex);
    }

    private boolean isValidBio(String bio) {
    	
    	// Split the bio into words using spaces
        String[] words = bio.split("\\s+");
        // Check if the number of words is between 10 and 30 (inclusive)
        int wordCount = words.length;
        return wordCount >= 10 && wordCount <= 30;
    }

    private boolean isValidOccupations(ArrayList<String> occupations) {
        return occupations.size() >= 1 && occupations.size() <= 5;
    }

    private boolean isValidAwards(ArrayList<String> awards) {
        for (String award : awards) {
            String[] parts = award.split(", ");
            if (parts.length != 2 || parts[1].split("\\s+").length < 4 || parts[1].split("\\s+").length > 10) {
                return false;
            }
        }
        return awards.size() <= 3;
    }

    private boolean isValidGenres(ArrayList<String> genres) {
        if (genres.size() < 2 || genres.size() > 5) {
            return false;
        }

        boolean hasPop = false;
        boolean hasRock = false;

        for (String genre : genres) {
            if (genre.equalsIgnoreCase("pop")) {
                if (hasRock) {
                    return false; // Cannot be active in 'pop' and 'rock' genres at the same time
                }
                hasPop = true;
            } else if (genre.equalsIgnoreCase("rock")) {
                if (hasPop) {
                    return false; // Cannot be active in 'pop' and 'rock' genres at the same time
                }
                hasRock = true;
            }
        }

        return true;
    }
    
    public boolean updateArtist(String newID, String newName, String newAddress, String newBirthdate,
            String newBio, ArrayList<String> newOccupations,
            ArrayList<String> newGenres, ArrayList<String> newAwards) {
    		System.out.println(" lol "+!isValidGenres(newGenres));
    		// Check if the new information meets the conditions
    		if (!isValidID(newID) || !isValidBirthdate(newBirthdate) || !isValidAddress(newAddress) ||
    				!isValidBio(newBio) || !isValidOccupations(newOccupations) ||
    				!isValidAwards(newAwards) || !isValidGenres(newGenres) ||
    				!isOccupationChangeAllowed(newBirthdate, newOccupations) ||
    				!isAwardsChangeAllowed(newAwards)) {
    			return false;
    		}

    		// Update the artist information in the TXT file
    		try {
    			// Read the existing content of the file
    			ArrayList<String> lines = new ArrayList<>();
    			java.nio.file.Path path = java.nio.file.Paths.get("artists.txt");
    			java.nio.charset.Charset charset = java.nio.charset.StandardCharsets.UTF_8;
    			lines.addAll(java.nio.file.Files.readAllLines(path, charset));

    			// Find the index of the artist information
    			int index = findArtistIndex(lines, ID);

    			if (index != -1) {
    				// Update the artist information
    				lines.set(index + 1, "Name: " + newName);
    				lines.set(index + 2, "Address: " + newAddress);
    				lines.set(index + 3, "Birthdate: " + newBirthdate);
    				lines.set(index + 4, "Bio: " + newBio);

    				lines.set(index + 5, "Occupations: ");
    				for (String occupation : newOccupations) {
    					lines.set(index + 5, lines.get(index + 5) + occupation + ", ");
    				}

    				lines.set(index + 6, "Genres: ");
    				for (String genre : newGenres) {
    					lines.set(index + 6, lines.get(index + 6) + genre + ", ");
    				}

    				lines.set(index + 7, "Awards: ");
    				for (String award : newAwards) {
    					lines.set(index + 7, lines.get(index + 7) + award + ", ");
    				}

    				// Write the updated content back to the file
    				try (BufferedWriter writer = new BufferedWriter(new FileWriter("artists.txt"))) {
    					for (String line : lines) {
    						writer.write(line + "\n");
    					}
    				}
    			} else {
    				System.err.println("Artist not found in the file.");
    				return false;
    			}

    			System.out.println("Artist information updated successfully.");

    		} catch (IOException e) {
    			System.err.println("Error updating artist information: " + e.getMessage());
    			return false;
    		}

    		return true;
    }


    private boolean isOccupationChangeAllowed(String newBirthdate, ArrayList<String> newOccupations) {
        // Check if the artist was born before 2000 and the occupation is being changed
        return Integer.parseInt(newBirthdate.substring(6)) >= 2000 || newOccupations.equals(Occupations);
    }

    private boolean isAwardsChangeAllowed(ArrayList<String> newAwards) {
        for (String newAward : newAwards) {
            String[] parts = newAward.split(", ");
            int year = Integer.parseInt(parts[0]);
            if (year < 2000 && !Awards.contains(newAward)) {
                return false;
            }
        }
        return true;
    }
    
    private int findArtistIndex(ArrayList<String> lines, String artistID) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("ID: " + artistID)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Example usage
        ArrayList<String> occupations = new ArrayList<>();
        occupations.add("Singer");
        occupations.add("Songwriter");

        ArrayList<String> genres = new ArrayList<>();
        genres.add("Pop");
        genres.add("Jazz");

        ArrayList<String> awards = new ArrayList<>();
        awards.add("2022, Best Song Written For Visual Media");

        Main artist = new Main("569MMMRR_%", "John Doe", "Melbourne|Victoria|Australia",
                "05-11-1980", "A talented artist with a passion for music. Also a traveller",
                occupations, genres, awards);

       boolean result =  artist.addArtist();
       System.out.println(result);
       
    // Example usage:
       String newID = "569MMMRR_%";
       String newName = "Jane Smith";
       String newAddress = "Melbourne|Victoria|Australia";
       String newBirthdate = "05-11-2000";
       String newBio = "A talented artist with a passion for music. Also a CEO";

       ArrayList<String> newOccupations = new ArrayList<>();
       newOccupations.add("Painter");
       newOccupations.add("Illustrator");

       ArrayList<String> newGenres = new ArrayList<>();
       newGenres.add("Abstract");
       newGenres.add("Surrealism");

       ArrayList<String> newAwards = new ArrayList<>();
       newAwards.add("2015, Outstanding Artistic Contribution Award");

       boolean success = artist.updateArtist(newID, newName, newAddress, newBirthdate, newBio, newOccupations,
               newGenres, newAwards);

       if (success) {
           System.out.println("Artist information updated successfully.");
       } else {
           System.out.println("Failed to update artist information.");
       }

       
    }
}


