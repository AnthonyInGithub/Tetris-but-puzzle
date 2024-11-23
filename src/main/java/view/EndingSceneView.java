package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple view with an image and two buttons.
 */
public class EndingSceneView extends JPanel implements ActionListener {

    private final JButton button1;
    private final JButton button2;
    private final int WINDOW_WIDTH = 960;
    private final int WINDOW_HEIGHT = 540;

    public EndingSceneView() {
        // Set up the panel layout
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Set the layout for the main frame
        setLayout(new BorderLayout());

// Create a topPanel to hold gameArea and sideArea
        JPanel topPanel = new JPanel(new BorderLayout());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create and add an image label
        JLabel imageLabel = new JLabel();
        ImageIcon originalImage = new ImageIcon("images/GUI.png"); // Replace with your image path
        Image scaledImage = originalImage.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH); // Scale image to fit
        imageLabel.setIcon(new ImageIcon(scaledImage)); // Replace with your image path
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(imageLabel);

        // Add spacing between components
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create and add Button 1
        ImageIcon button1Icon = new ImageIcon("images/GUI.png");
        ImageIcon finalbutton1Icon = new ImageIcon(button1Icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH)); // Scale image to fit
        button1 = new JButton(finalbutton1Icon);
        button1.addActionListener(this);
        buttonPanel.add(button1);

        // Add spacing between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Create and add Button 2
        button2 = new JButton("Button 2");
        button2.addActionListener(this);
        buttonPanel.add(button2);

        // Add the button panel to the main panel
        topPanel.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            System.out.println("Button 1 clicked");
        } else if (e.getSource() == button2) {
            System.out.println("Button 2 clicked");
        }
    }


}
