package data_access;

import use_case.LevelSelect.LevelSelectInteractor;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class FileDataAccessObject implements EndingSceneDataAccessInterface,
                                LevelSelectDataAccessInterface {
    // Original fields for EndingScene functionality
    private int numberOfSavedImages;
    private static final String CSV_FILE = "FiledData/playerProgress.csv";

    // New field for LevelSelect functionality
    private int selectedLevel; // Stores the selected level

    // Constructor
    public FileDataAccessObject() {
        try {
            File file = new File(CSV_FILE);
            if (!file.exists()) {
                // If the file doesn't exist, create it with default values
                try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
                    writer.println(0); // Default number of saved images
                    writer.println(1); // Default selected level
                }
            }

            // Read the values from the CSV file
            try (Scanner scanner = new Scanner(file)) {
                if (scanner.hasNextInt()) {
                    numberOfSavedImages = scanner.nextInt(); // Read number of saved images
                } else {
                    throw new IOException("Invalid file format for saved images.");
                }

                if (scanner.hasNextInt()) {
                    selectedLevel = scanner.nextInt(); // Read selected level
                } else {
                    throw new IOException("Invalid file format for selected level.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            numberOfSavedImages = 0; // Default values in case of error
            selectedLevel = 1;      // Default level
        }
    }

    // Original methods for EndingScene functionality
    @Override
    public BufferedImage getEndGameScreenShot() {
        return null; // Placeholder for screenshot functionality
    }

    @Override
    public int getNumberOfSavedImages() {
        return numberOfSavedImages;
    }

    @Override
    public void setNumberOfSavedImages(int numberOfSavedImages) {
        this.numberOfSavedImages = numberOfSavedImages % 3; // Restrict to max 3 images
        saveToFile();
    }

    // New methods for LevelSelect functionality
    @Override
    public int getSelectedLevel() {
        return selectedLevel; // Return the current selected level
    }

    @Override
    public void setSelectedLevel(int level) {
        this.selectedLevel = level; // Update the selected level
        saveToFile(); // Persist the change
    }

    // Helper method to save all data to the file
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            writer.println(numberOfSavedImages); // Save the number of saved images
            writer.println(selectedLevel);       // Save the selected level
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
