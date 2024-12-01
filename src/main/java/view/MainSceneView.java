//package view;
//import interface_adapter.MainMenu.MainMenuController;
//import javax.swing.*;
//        import java.awt.*;
//        import java.awt.event.ActionListener;
//
//public class MainSceneView {
//
//    private final JFrame frame;
//    private final JPanel mainPanel;
//    private final CardLayout cardLayout;
//    private final MainMenuController controller;
//
//    /**
//     * Constructor for MainSceneView.
//     *
//     * @param controller The controller to handle user interactions.
//     */
//    public MainSceneView(MainMenuController controller) {
//        this.controller = controller;
//
//        // Initialize the main frame
//        frame = new JFrame("Main Scene");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 400);
//
//        // CardLayout to manage different screens
//        cardLayout = new CardLayout();
//        mainPanel = new JPanel(cardLayout);
//
//        // Add the main menu panel
//        mainPanel.add(createMainMenuPanel(), "MainMenu");
//
//        // Add other screens (for navigation)
//        mainPanel.add(createDummyPanel("Levels Page"), "LevelsPage");
//        mainPanel.add(createDummyPanel("History Page"), "HistoryPage");
//        mainPanel.add(createDummyPanel("Battle Page"), "BattlePage");
//
//        // Set up the frame
//        frame.add(mainPanel);
//        frame.setVisible(true);
//    }
//
//    /**
//     * Creates the main menu panel with all buttons.
//     *
//     * @return The JPanel for the main menu.
//     */
//    private JPanel createMainMenuPanel() {
//        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
//        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
//
//        // Add buttons to the main menu
//        JButton startButton = new JButton("Start");
//        JButton battleButton = new JButton("Battle");
//        JButton historyButton = new JButton("History");
//        JButton myOwnUploadButton = new JButton("My Own Upload");
//
//        // Add action listeners for each button
//        addActionListener(startButton, "StartButton");
//        addActionListener(battleButton, "BattleButton");
//        addActionListener(historyButton, "HistoryButton");
//        addActionListener(myOwnUploadButton, "MyOwnUploadButton");
//
//        // Add buttons to the panel
//        panel.add(startButton);
//        panel.add(battleButton);
//        panel.add(historyButton);
//        panel.add(myOwnUploadButton);
//
//        return panel;
//    }
//
//    /**
//     * Adds an action listener to a button to handle user interaction.
//     *
//     * @param button     The JButton to add the listener to.
//     * @param buttonName The name of the button (action identifier).
//     */
//    private void addActionListener(JButton button, String buttonName) {
//        button.addActionListener(e -> controller.handleMainMenuAction(buttonName));
//    }
//
//    /**
//     * Creates a dummy panel to simulate different pages for navigation.
//     *
//     * @param pageName The name of the page.
//     * @return A JPanel representing the dummy page.
//     */
//    private JPanel createDummyPanel(String pageName) {
//        JPanel panel = new JPanel(new BorderLayout());
//        JLabel label = new JLabel(pageName, SwingConstants.CENTER);
//        label.setFont(new Font("Arial", Font.BOLD, 24));
//        panel.add(label, BorderLayout.CENTER);
//        return panel;
//    }
//
//    /**
//     * Displays a message to the user using a dialog.
//     *
//     * @param message The message to display.
//     */
//    public void displayMessage(String message) {
//        JOptionPane.showMessageDialog(frame, message);
//    }
//
//    /**
//     * Navigates to a specific page based on its name.
//     *
//     * @param pageName The name of the page to navigate to.
//     */
//    public void navigateTo(String pageName) {
//        cardLayout.show(mainPanel, pageName);
//    }
//}
package view;
import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuViewModel;
import javax.swing.*;
import java.awt.*;

public class MainSceneView extends JPanel {

    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    private MainMenuViewModel mainMenuViewModel;
    private MainMenuController mainMenuController;


    public MainSceneView(MainMenuViewModel mainMenuViewModel) {
        this.mainMenuViewModel = mainMenuViewModel;
        setBackground(Color.BLACK);
        // setPreferredSize(new Dimension(600, 600));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Set the preferred size of the main panel
        mainPanel.setPreferredSize(new Dimension(800, 650));

        // Add a panel to CardLayout
        mainPanel.add(createMainMenuPanel(), "MainMenu");
        cardLayout.show(mainPanel, "MainMenu");

        add(mainPanel, BorderLayout.CENTER);

        // Validate and repaint the layout
        revalidate();
        repaint();

    }
    public void setMainMenuController(MainMenuController controller) {
        this.mainMenuController = controller;
    }


    /**
     * set the main menu panel with a background image and buttons.
     *
     * @return The JPanel for the main menu.
     */
    private JPanel createMainMenuPanel() {
        ImageIcon backgroundImage = new ImageIcon("images/mainSceneBackground.png");

        // Create a custom JPanel for the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // System.out.println(getWidth() + " " + getHeight());
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

            }
        };
        backgroundPanel.setLayout(null);

        // Add buttons with custom images
        JButton startButton = createImageButton(
                "images/StartButton.png",
                300, 260, 200, 50,
                "StartButton"
        );


        JButton historyButton = createImageButton(
                "images/HistoryButton.png",
                300, 430, 200, 50,
                "HistoryButton"
        );

        JButton myOwnUploadButton = createImageButton(
                "images/UPLOAD.png",
                300, 380, 200, 50,
                "myOwnUploadButton"
        );

        addActionListener(startButton, "StartButton");
        addActionListener(historyButton, "HistoryButton");
        addActionListener(myOwnUploadButton, "MyOwnUploadButton");

        backgroundPanel.add(startButton);
        backgroundPanel.add(historyButton);
        backgroundPanel.add(myOwnUploadButton);

        backgroundPanel.setPreferredSize(new Dimension(600, 600));
        backgroundPanel.revalidate();
        backgroundPanel.repaint();

        return backgroundPanel;
    }
    private void addActionListener(JButton button, String buttonName) {
        button.addActionListener(e -> mainMenuController.handleMainMenuAction(buttonName));
    }

    /**
     * Creates a JButton with an image icon and optional hover effect.
     *
     * @param iconPath       Path to the default button image.
     * @param x              The x-coordinate of the button.
     * @param y              The y-coordinate of the button.
     * @param width          The width of the button.
     * @param height         The height of the button.
     * @param buttonName     The name of the button (action identifier).
     * @return The created JButton.
     */

    private JButton createImageButton(String iconPath, int x, int y, int width, int height, String buttonName) {
        JButton button = new JButton();

        // Set the button's default icon
        ImageIcon icon = new ImageIcon(iconPath);
        button.setIcon(icon);


        // Remove button borders and background
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        // set the button's size and position
        button.setBounds(x, y, width, height);

        return button;
    }

    /**
     * displays a message to the user using a dialog.
     *
     * @param message The message to display.
     */
//    public void displayMessage(String message) {
//        JOptionPane.showMessageDialog(frame, message);
//    }

    /**
     * navigates to a specific page based on its name.
     *
     * @param pageName The name of the page to navigate to.
     */
    public void navigateTo(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

}
