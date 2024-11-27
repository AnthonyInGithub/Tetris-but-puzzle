package view;

import interface_adapter.EndingScene.EndingSceneController;
import interface_adapter.EndingScene.EndingSceneViewModel;
import interface_adapter.NormalGiven.NormalGivenController;
import interface_adapter.NormalGiven.NormalGivenViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple view with an image and two buttons.
 */
public class EndingSceneView extends JPanel implements ActionListener {

    private final String viewName = "EndingSceneView";

    private final JButton button1;
    private final JButton button2;
    private final int WINDOW_WIDTH = 960;
    private final int WINDOW_HEIGHT = 540;
    private EndingSceneViewModel endingSceneViewModel;
    private EndingSceneController endingSceneController;


    public EndingSceneView(EndingSceneViewModel endingSceneViewModel) {
        this.endingSceneViewModel = endingSceneViewModel;

        // Set up the panel layout
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create an image label
        JLabel imageLabel = new JLabel();
        ImageIcon originalImage = new ImageIcon("images/GUI.png");
        Image scaledImage = originalImage.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(imageLabel);

        // Add spacing
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create Button 1
        button1 = new JButton(new ImageIcon(scaledImage));
        button1.addActionListener(this);
        buttonPanel.add(button1);

        // Add spacing between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Create Button 2
        button2 = new JButton("Button 2");
        button2.addActionListener(this);
        buttonPanel.add(button2);

        // Add the button panel
        add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            endingSceneController.execute(true, true);
        } else if (e.getSource() == button2) {
            System.out.println("Button 2 clicked");
        }
    }
    public void setEndingSceneController(EndingSceneController endingSceneController) {
        this.endingSceneController = endingSceneController;
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(EndingSceneView::new);
    }
    public String getViewName() {
        return viewName;
    }


}
