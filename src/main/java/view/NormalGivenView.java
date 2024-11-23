package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.Timer;
import java.util.TimerTask;

import interface_adapter.NormalGiven.NormalGivenController;
import interface_adapter.NormalGiven.NormalGivenState;
import interface_adapter.NormalGiven.ViewModel;

public class NormalGivenView extends JPanel implements PropertyChangeListener {

    private JPanel gameArea;
    private JPanel sideArea;
    private final int widowWidth = 515;
    private final int widowHeight = 635;
    private final int gameAreaWidth = 300;
    private final int gameAreaHeight = 600;
    private final int sideAreaWidth = 200;
    private final int sideAreaHeight = 400;
    private ViewModel normalGivenViewModel;
    private NormalGivenController normalGivenController;
    private final int squareSize = 30;
    private final int margin = 5;
    private final Timer timer;
    private final long timeDelay = 1000;
    private final long timePeriod = 1000;
    private BufferedImage backgroundImage;

    public NormalGivenView(ViewModel normalGivenViewModel) {
        // Set the title and default close operation
        this.normalGivenViewModel = normalGivenViewModel;
        this.normalGivenViewModel.addPropertyChangeListener(this);
        timer = new Timer();
        setSize(widowWidth, widowHeight);
        // setBackgroundImage();
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
                normalGivenController.execute(true, false, false, false);
                draw();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("A"), "keyAPressed");
        actionMap.put("keyAPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "A" is pressed
                // NormalGivenController executes based on this key
                System.out.println("A");
                normalGivenController.execute(false, true, false, false);
                draw();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("S"), "keySPressed");
        actionMap.put("keySPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "S" is pressed
                // NormalGivenController executes based on this key
                System.out.println("S");
                normalGivenController.execute(false, false, true, false);
                draw();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("D"), "keyDPressed");
        actionMap.put("keyDPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "D" is pressed
                // NormalGivenController executes based on this key
                System.out.println("D");
                normalGivenController.execute(false, false, false, true);
                draw();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "keyDPressed");
        actionMap.put("keyDPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when "esc" is pressed
                // NormalGivenController executes based on this key
                System.out.println("esc");
                normalGivenController.execute(false, false, false, false);
                draw();
            }
        });
    }

    private void draw() {
        gameArea.removeAll();

        int[][] currentMap = normalGivenViewModel.getMap();

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

    private TimerTask regularExecution(){
        return new TimerTask() {
            @Override
            public void run() {
                normalGivenController.execute();
            }
        };
    }

    private void setBackgroundImage(String imagePath){
        try {
            // Load the image from the file
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading image: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the image to fill the entire panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        final NormalGivenState normalGivenState = (NormalGivenState) evt.getNewValue();
        if (normalGivenState.getGamingState().equals("playing")) {
            timer.schedule(regularExecution(), timeDelay, timePeriod);
            System.out.println("timer starts");
        } else if (normalGivenState.getGamingState().equals("end")) {
            timer.cancel();
            System.out.println("timer ends");
        }

    }

}

