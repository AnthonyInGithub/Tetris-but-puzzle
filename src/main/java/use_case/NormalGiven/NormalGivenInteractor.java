package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

import interface_adapter.NormalGiven.NormalGivenController;
import interface_adapter.NormalGiven.NormalGivenViewModel;

public class GameScreen extends JFrame {

    private JPanel gameArea;
    private JPanel sideArea;
    private final int widowWidth = 515;
    private final int widowHeight = 635;
    private final int gameAreaWidth = 300;
    private final int gameAreaHeight = 600;
    private final int sideAreaWidth = 200;
    private final int sideAreaHeight = 400;
    private NormalGivenViewModel normalGivenViewModel;
    private NormalGivenController normalGivenController;
    private final int squareSize = 30;
    private final int margin = 5;

    public GameScreen() {
        // Set the title and default close operation
        setTitle("Game Screen Layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(widowWidth, widowHeight);

        // Set the layout for the main frame
        setLayout(new BorderLayout());

// Create a topPanel to hold gameArea and sideArea
        JPanel topPanel = new JPanel(new BorderLayout());

// Create a left-aligned panel to hold gameArea
        JPanel gameAreaWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        final Border redline = BorderFactory.createLineBorder(Color.red);
        gameArea = new JPanel();
        gameArea.setBackground(Color.BLACK);
        gameArea.setPreferredSize(new Dimension(gameAreaWidth, gameAreaHeight));
        gameArea.setBorder(redline);
        gameAreaWrapper.add(gameArea);  // Add gameArea to the left-aligned wrapper
        topPanel.add(gameAreaWrapper, BorderLayout.WEST);  // Add wrapper to topPanel

// Create a right-aligned panel to hold sideArea
        JPanel sideAreaWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        final Border yellowline = BorderFactory.createLineBorder(Color.yellow);
        sideArea = new JPanel();
        sideArea.setBackground(Color.GRAY);
        sideArea.setPreferredSize(new Dimension(sideAreaWidth, sideAreaHeight));
        sideArea.setBorder(yellowline);
        sideAreaWrapper.add(sideArea);  // Add sideArea to the right-aligned wrapper
        topPanel.add(sideAreaWrapper, BorderLayout.EAST);  // Add wrapper to topPanel

// Add topPanel to the main frame at the top (NORTH)
        add(topPanel, BorderLayout.NORTH);

        setupKeyBindings();

        // Make the frame visible
        setVisible(true);
    }

    public void setupKeyBindings() {
        /*
        setup listeners for W A S D
        */
        final InputMap inputMap = gameArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        final ActionMap actionMap = gameArea.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("W"), "keyWPressed");
        actionMap.put("keyWPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "B" is pressed
                // NormalGivenController executes based on this key
                System.out.println("W");
                normalGivenController.execute("W");
                draw(normalGivenViewModel);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("A"), "keyAPressed");
        actionMap.put("keyAPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "A" is pressed
                // NormalGivenController executes based on this key
                System.out.println("A");
                normalGivenController.execute("A");
                draw(normalGivenViewModel);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("S"), "keySPressed");
        actionMap.put("keySPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "S" is pressed
                // NormalGivenController executes based on this key
                System.out.println("S");
                normalGivenController.execute("S");
                draw(normalGivenViewModel);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("D"), "keyDPressed");
        actionMap.put("keyDPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "D" is pressed
                // NormalGivenController executes based on this key
                System.out.println("D");
                normalGivenController.execute("D");
                draw(normalGivenViewModel);
            }
        });
    }
    /*
    private void draw(ViewModel v) {
        currentMap = v.getMap();
        for (int i = 0; i < currentMap.length(); i++) {

            for (int j = 0; j < currentMap[0].length; j++) {
                if (currentMap[i][j] == 1) {
                    gameArea.add(squareFactory(squareSize * i, squareSize * j, squareSize));
                }
            }
        }
        gameArea.repaint();
    }
     */
    private void draw(NormalGivenViewModel v) {
        int[][] currentMap = v.getMap();

        for (int i = 0; i < currentMap.length; i++) {

            for (int j = 0; j < currentMap[0].length; j++) {
                if (currentMap[i][j] == 1) {
                    gameArea.add(squareFactory(margin + squareSize * j,
                            squareSize * i - margin, squareSize));
                }
            }
        }
        gameArea.repaint();
    }

    private JLabel squareFactory(int xPosition, int yPosition, int size) {
        final JLabel square = new JLabel();
        square.setOpaque(true);
        square.setBackground(Color.BLUE);
        square.setBounds(xPosition, yPosition, size, size); // Initial position and size
        square.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        return square;
    }

    public void setNormalGivenController(NormalGivenController normalGivenController) {
        this.normalGivenController = normalGivenController;
    }

//    public static void main(String[] args) {
//        // Run the game screen layout
//        SwingUtilities.invokeLater(GameScreen::new);
//    }
}
