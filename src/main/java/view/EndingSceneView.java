package view;

import interface_adapter.EndingScene.EndingSceneController;
import interface_adapter.EndingScene.EndingSceneViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple view with an image and two buttons.
 */
public class EndingSceneView extends JPanel implements ActionListener {

    private final String viewName = "EndingSceneView";

    private final JButton saveButton;
    private final JButton returnButton;
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
        saveButton = new JButton(new ImageIcon(scaledImage));
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);

        // Add spacing between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Create Button 2
        returnButton = new JButton("Button 2");
        returnButton.addActionListener(this);
        buttonPanel.add(returnButton);

        // Add the button panel
        add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            endingSceneController.execute(true, false);
            checkSaveSuccess();
        } else if (e.getSource() == returnButton) {
            endingSceneController.execute(false, true);
            resetSaveButton();
        }
    }
    public void setEndingSceneController(EndingSceneController endingSceneController) {
        this.endingSceneController = endingSceneController;
    }

    public String getViewName() {
        return viewName;
    }

    private void checkSaveSuccess() {
        if (endingSceneViewModel.getSavedSuccess()) {
            saveButton.setEnabled(false);
        }
    }
    private void resetSaveButton() {
        saveButton.setEnabled(true);
    }



}
