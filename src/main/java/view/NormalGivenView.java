package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;

import java.util.Timer;
import java.util.TimerTask;

import interface_adapter.NormalGiven.NormalGivenController;
import interface_adapter.NormalGiven.NormalGivenViewModel;
import interface_adapter.NormalGiven.NormalGivenState;

public class NormalGivenView extends JPanel implements PropertyChangeListener {

    private final String viewName = "NormalGivenView";

    private JPanel gameArea;
    private final int widowWidth = 515;
    private final int widowHeight = 635;
    private final int gameAreaWidth = 300;
    private final int gameAreaHeight = 600;
    private NormalGivenViewModel normalGivenViewModel;
    private NormalGivenController normalGivenController;
    private final int squareSize = 30;
    private final int margin = 5;
    private Timer timer;
    private final long timeDelay = 0;
    private final long timePeriod = 1000;
    private boolean firstTime = true;

    public NormalGivenView(NormalGivenViewModel normalGivenViewModel) {

        this.normalGivenViewModel = normalGivenViewModel;
        this.normalGivenViewModel.addPropertyChangeListener(this);
        setPreferredSize(new Dimension(gameAreaWidth, gameAreaHeight));
        // Set the layout for the main frame
        setLayout(new BorderLayout());
        // normalGivenController.execute();
        final Border redline = BorderFactory.createLineBorder(Color.red);

        String imagePath = "images/sampleLevel1.png";
        gameArea = new CustomBackgroundPanel(){
//            private BufferedImage backgroundImage;
//
//            public void setBackgroundImage(String imagePath) {
//                try {
//                    backgroundImage = ImageIO.read(new File(imagePath));
//                    repaint(); // Trigger repaint to apply the new background
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.err.println("Error loading background image: " + imagePath);
//                }
//            }
//
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                if (backgroundImage != null) {
//                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//                }
//            }

            @Override
            public Dimension getPreferredSize() {
                // Set the preferred size of the panel
                return new Dimension(gameAreaWidth, gameAreaHeight); // Width: 500, Height: 400
            }
        };

        ((CustomBackgroundPanel) gameArea).setBackgroundImage(imagePath);
        gameArea.setBorder(redline);
        add(gameArea, BorderLayout.CENTER);

        setupKeyBindings();

        // Make the frame visible
        // setVisible(true);
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

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "keyESCPressed");
        actionMap.put("keyESCPressed", new AbstractAction() {
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
        int[][] solutionMap = normalGivenViewModel.getSolutionMap();
        int[][][] colorMap = normalGivenViewModel.getColorMap();

        for (int i = 0; i < currentMap.length; i++) {

            for (int j = 0; j < currentMap[0].length; j++) {
                if (currentMap[i][j] == 1) {
                    int[] color = colorMap[i][j];
                    if (solutionMap[i][j] == 1) {
                        gameArea.add(squareFactory(margin + squareSize * j,
                                squareSize * i - margin, squareSize, color));
                    } else {
                        gameArea.add(squareFactory(margin + squareSize * j,
                                squareSize * i - margin, squareSize, new int[]{0,0,0}));
                    }

                }
            }
        }
        gameArea.repaint();
    }

    private JLabel squareFactory(int xPosition, int yPosition, int size, int[] color) {
        final JLabel square = new JLabel();
        square.setOpaque(true);
        square.setBackground(new Color(color[0], color[1], color[2]));
        square.setBounds(xPosition, yPosition, size, size); // Initial position and size
        square.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        return square;
    }

    public void setNormalGivenController(NormalGivenController normalGivenController) {
        this.normalGivenController = normalGivenController;
    }

    private TimerTask regularExecution(NormalGivenState normalGivenState){
        return new TimerTask() {
            @Override
            public void run() {
                normalGivenController.execute(false,false,true,false);
                draw();
                System.out.println("regular execution");
                if (firstTime) {
                    System.out.println("first time timer starts -- line 203");
                    String imagePath = normalGivenState.getImgAddress();
                    ((CustomBackgroundPanel) gameArea).setBackgroundImage(imagePath);
                    firstTime = false;
                    gameArea.removeAll();
                    gameArea.repaint();
                }

            }
        };
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        final NormalGivenState normalGivenState = (NormalGivenState) evt.getNewValue();
        if (normalGivenState.getGamingState().equals("playing")) {
            this.timer = new Timer();
            timer.schedule(regularExecution(normalGivenState), timeDelay, timePeriod);
            System.out.println("timer starts");
        } else if (normalGivenState.getGamingState().equals("end")) {
            timer.cancel();
            System.out.println("timer ends");
        }

    }

    public class CustomBackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public void setBackgroundImage(String imagePath) {
            System.out.println(imagePath + " <-- path");
            try {
                backgroundImage = ImageIO.read(new File(imagePath));
                repaint(); // Trigger repaint to apply the new background
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading background image: " + imagePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    public String getViewName(){
        return viewName;
    }

}

