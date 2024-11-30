package view;

import javax.swing.*;
import java.awt.*;
import interface_adapter.LevelSelect.LevelSelectController;
import interface_adapter.LevelSelect.LevelSelectViewModel;

/**
 * View for the Level Select Use Case.
 * Displays buttons for selecting levels with corresponding images and a custom background.
 */
public class LevelSelectView extends JPanel {
    private LevelSelectController controller;
    private final LevelSelectViewModel levelSelectViewModel;

    public LevelSelectView(LevelSelectViewModel levelSelectViewModel) {
        this.levelSelectViewModel = levelSelectViewModel;
        // Set layout and panel size
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Add custom background
        JLabel backgroundLabel = new JLabel(new ImageIcon("images/GUI.png"));
        backgroundLabel.setLayout(new GridBagLayout()); // Use GridBagLayout for positioning
        add(backgroundLabel, BorderLayout.CENTER);

        // Set up constraints for button layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Padding between buttons
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Create buttons with corresponding images
        JButton level1Button = createImageButton("images/GUI.png", () -> controller.selectLevel(1));
        JButton level2Button = createImageButton("images/GUI.png", () -> controller.selectLevel(2));
        JButton level3Button = createImageButton("images/GUI.png", () -> controller.selectLevel(3));

        // Add buttons to the background with positioning
        gbc.gridy = 0;
        backgroundLabel.add(level1Button, gbc);

        gbc.gridy = 1;
        backgroundLabel.add(level2Button, gbc);

        gbc.gridy = 2;
        backgroundLabel.add(level3Button, gbc);
    }

    /**
     * Creates a button with a custom image and action.
     *
     * @param imagePath The path to the button image.
     * @param action    The action to perform when the button is clicked.
     * @return The configured JButton.
     */
    private JButton createImageButton(String imagePath, Runnable action) {
        // Load the image icon for the button
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);

        // Set button properties
        button.setPreferredSize(new Dimension(200, 80));
        button.setContentAreaFilled(false); // Make button background transparent
        button.setBorderPainted(false);     // Remove button border
        button.setFocusPainted(false);     // Remove focus border

        // Add action listener for the button
        button.addActionListener(e -> action.run());

        return button;
    }
    public void setLevelSelectController(LevelSelectController controller) {
        this.controller = controller;
    }

}