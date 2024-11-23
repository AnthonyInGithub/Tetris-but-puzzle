import net.coobird.thumbnailator.Thumbnails;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class myOwnFunctionalityTesting {
    public static void main(String[] args) {
        try {
            // Resize the image to 10x20 using Thumbnailator
            BufferedImage resizedImage = Thumbnails.of(new File("images/sampleLevel1.png"))
                    .size(10, 20)
                    .asBufferedImage();

            // Initialize a 2D array for binary representation
            int width = resizedImage.getWidth();
            int height = resizedImage.getHeight();
            int[][] binaryArray = new int[height][width];
            int[][][] coloredMap = new int[height][width][3];

            // Process each pixel to determine binary value
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Get the RGB value of the pixel
                    int rgb = resizedImage.getRGB(x, y);
                    Color color = new Color(rgb);
                    coloredMap[y][x][0] = color.getRed();
                    coloredMap[y][x][1] = color.getGreen();
                    coloredMap[y][x][2] = color.getBlue();
                    // Convert to grayscale for thresholding
                    int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                    // Threshold: convert to binary (1 for dark, 0 for light)
                    binaryArray[y][x] = gray < 128 ? 1 : 0;
                }
            }

            // Print the binary array
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    System.out.print(binaryArray[y][x] + " ");
                }
                System.out.println();
            }
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    System.out.print("[");
                    for (int i = 0; i < 3; i++) {
                        System.out.print(coloredMap[y][x][i] + " ");
                    }
                    System.out.println("]");

                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
