package view;

import interface_adapter.EndingScene.EndingSceneController;
import interface_adapter.EndingScene.EndingSceneState;
import interface_adapter.EndingScene.EndingSceneViewModel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * A simple view with an image and two buttons.
 */
public class EndingSceneView extends JPanel implements PropertyChangeListener,ActionListener {
    private final JButton saveButton;
    private final JButton returnButton;
    private final int WINDOW_WIDTH = 960;
    private final int WINDOW_HEIGHT = 540;
    private EndingSceneViewModel endingSceneViewModel;
    private EndingSceneController endingSceneController;

    private final int BUTTON_WIDTH = 180;
    private final int BUTTON_HEIGHT = 75;

    private final Image backgroundImage;

    String displayText;
    JLabel titleLabel;




    public EndingSceneView(EndingSceneViewModel endingSceneViewModel) {
        this.endingSceneViewModel = endingSceneViewModel;
        this.endingSceneViewModel.addPropertyChangeListener(this);


        // Set up the panel layout
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // setup background image
        backgroundImage = new ImageIcon("images/EndingSceneBackground.png").getImage();

        // padding at the top
        add(Box.createRigidArea(new Dimension(0, 160)));

        // set ending game text
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
        textPanel.setOpaque(false);
        EndingSceneState endingSceneState = endingSceneViewModel.getState();
        if(endingSceneState.getIsWin()) {
            displayText = "WIN";
        }else{
            displayText = "LOSE";
        }
        titleLabel = new JLabel(displayText); // Example text
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font to bold and size to 36
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE); // Adjust color if needed
        textPanel.add(Box.createRigidArea(new Dimension(30, 0)));//adjust label horizontal position
        textPanel.add(titleLabel);
        add(textPanel);


        // adjust vertical padding
        add(Box.createRigidArea(new Dimension(0, 50)));


        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setOpaque(false);

        buttonPanel.add(Box.createRigidArea(new Dimension(75, 0))); // Adjust horizontal padding


        // Create Button 1
        saveButton = createButtonWithImage("images/ButtonSave.png", BUTTON_WIDTH,BUTTON_HEIGHT);
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);

        // Add spacing between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create Button 2
        returnButton = createButtonWithImage("images/ButtonReturn.png", BUTTON_WIDTH,BUTTON_HEIGHT);
        returnButton.addActionListener(this);
        buttonPanel.add(returnButton);

        // Add the button panel
        add(buttonPanel);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Paint the panel's components

        // Draw the background image, scaled to fill the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    private JButton createButtonWithImage(String imagePath, int buttonWidth, int buttonHeight) {
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage()
                .getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        button.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        button.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
        button.setContentAreaFilled(false); // Make the button area transparent
        button.setBorderPainted(false); // Remove button border
        button.setFocusPainted(false); // Remove focus border
        return button;
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

    private void checkSaveSuccess() {
        if (endingSceneViewModel.getSavedSuccess()) {
            saveButton.setEnabled(false);
        }
    }
    private void resetSaveButton() {
        saveButton.setEnabled(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(endingSceneViewModel.getState().getIsWin()){
            displayText = "WIN";
            titleLabel.setText(displayText);
            playSound("SoundEffect/next-level.wav");

        }else{
            displayText = "LOSE";
            titleLabel.setText(displayText);
            playSound("SoundEffect/game-over.wav");
        }
    }

    private static void playSound(String soundFile) {
        try {
            // Load the audio file
            File file = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            // Get a sound clip resource
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Play the sound
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }



}
