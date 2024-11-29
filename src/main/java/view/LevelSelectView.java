package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import interface_adapter.LevelSelect.LevelSelectController;

/**
 * View for the Level Select Use Case.
 * Displays buttons for selecting levels and a custom background.
 */
public class LevelSelectView extends JPanel {
    private final LevelSelectController controller;

    public LevelSelectView(LevelSelectController controller) {
        this.controller = controller;

        // Set layout for the panel
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600)); // Example size

        // Add custom background
        JLabel backgroundLabel = new JLabel(new ImageIcon("path/to/background.png"));
        backgroundLabel.setLayout(new GridBagLayout()); // Set layout manager for buttons
        add(backgroundLabel, BorderLayout.CENTER);

        // Create buttons
        JButton level1Button = createButton("Level 1", () -> controller.selectLevel(1));
        JButton level2Button = createButton("Level 2", () -> controller.selectLevel(2));
        JButton level3Button = createButton("Level 3", () -> controller.selectLevel(3));

        // Add buttons to the layout
        backgroundLabel.add(level1Button);
        backgroundLabel.add(level2Button);
        backgroundLabel.add(level3Button);
    }

    private JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        button.setPreferredSize(new Dimension(120, 50)); // Example button size
        return button;
    }
}