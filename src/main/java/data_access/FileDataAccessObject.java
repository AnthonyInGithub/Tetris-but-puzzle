package data_access;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class FileDataAccessObject implements EndingSceneDataAccessInterface{
    // maximum 3
    private int numberOfSavedImages;
    private static final String CSV_FILE = "FiledData/playerProgress.csv";

    public FileDataAccessObject(){
        // initialize number of savedImage
        try {
            File file = new File(CSV_FILE);
            if (!file.exists()) {
                // If the file doesn't exist, create it with a default value
                try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
                    writer.println(0); // Default number
                }
            }

            // Read the number from the CSV file
            try (Scanner scanner = new Scanner(file)) {
                if (scanner.hasNextInt()) {
                    numberOfSavedImages = scanner.nextInt();
                } else {
                    throw new IOException("Invalid file format");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            numberOfSavedImages = 0; // Default number in case of error
        }
    }



    public BufferedImage getEndGameScreenShot(){
        return null;
    };
    public int getNumberOfSavedImages(){
        return numberOfSavedImages;
    };
    public  void setNumberOfSavedImages(int numberOfSavedImages){
        this.numberOfSavedImages = numberOfSavedImages;
        saveToFile();
    };
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            writer.println(numberOfSavedImages);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
