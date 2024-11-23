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

    public EndingSceneView() {
        // Set up the panel layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create and add an image label
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("")); // Replace with your image path
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(imageLabel);

        // Add spacing between components
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create and add Button 1
        button1 = new JButton("Button 1");
        button1.addActionListener(this);
        buttonPanel.add(button1);

        // Add spacing between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Create and add Button 2
        button2 = new JButton("Button 2");
        button2.addActionListener(this);
        buttonPanel.add(button2);

        // Add the button panel to the main panel
        this.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            System.out.println("Button 1 clicked");
        } else if (e.getSource() == button2) {
            System.out.println("Button 2 clicked");
        }
    }

    public static void main(String[] args) {
        // Set up the JFrame
        JFrame frame = new JFrame("ImageView");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Add the ImageView panel to the frame
        frame.add(new EndingSceneView());

        // Make the frame visible
        frame.setVisible(true);
    }
}
