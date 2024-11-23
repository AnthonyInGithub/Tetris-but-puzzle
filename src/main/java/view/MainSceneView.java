package view;
import interface_adapter.NormalGiven.MainMenuController;
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionListener;

public class MainSceneView {

    private final JFrame frame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    private final MainMenuController controller;

    /**
     * Constructor for MainSceneView.
     *
     * @param controller The controller to handle user interactions.
     */
    public MainSceneView(MainMenuController controller) {
        this.controller = controller;

        // Initialize the main frame
        frame = new JFrame("Main Scene");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // CardLayout to manage different screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add the main menu panel
        mainPanel.add(createMainMenuPanel(), "MainMenu");

        // Add other screens (for navigation)
        mainPanel.add(createDummyPanel("Levels Page"), "LevelsPage");
        mainPanel.add(createDummyPanel("History Page"), "HistoryPage");
        mainPanel.add(createDummyPanel("Battle Page"), "BattlePage");

        // Set up the frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    /**
     * Creates the main menu panel with all buttons.
     *
     * @return The JPanel for the main menu.
     */
    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Add buttons to the main menu
        JButton startButton = new JButton("Start");
        JButton battleButton = new JButton("Battle");
        JButton historyButton = new JButton("History");
        JButton myOwnUploadButton = new JButton("My Own Upload");

        // Add action listeners for each button
        addActionListener(startButton, "StartButton");
        addActionListener(battleButton, "BattleButton");
        addActionListener(historyButton, "HistoryButton");
        addActionListener(myOwnUploadButton, "MyOwnUploadButton");

        // Add buttons to the panel
        panel.add(startButton);
        panel.add(battleButton);
        panel.add(historyButton);
        panel.add(myOwnUploadButton);

        return panel;
    }

    /**
     * Adds an action listener to a button to handle user interaction.
     *
     * @param button     The JButton to add the listener to.
     * @param buttonName The name of the button (action identifier).
     */
    private void addActionListener(JButton button, String buttonName) {
        button.addActionListener(e -> controller.handleMainMenuAction(buttonName));
    }

    /**
     * Creates a dummy panel to simulate different pages for navigation.
     *
     * @param pageName The name of the page.
     * @return A JPanel representing the dummy page.
     */
    private JPanel createDummyPanel(String pageName) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(pageName, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Displays a message to the user using a dialog.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    /**
     * Navigates to a specific page based on its name.
     *
     * @param pageName The name of the page to navigate to.
     */
    public void navigateTo(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }
}
