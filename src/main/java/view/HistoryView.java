package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.File;
import java.util.ArrayList;

import interface_adapter.History.HistoryController;
import interface_adapter.History.HistoryState;
import interface_adapter.History.HistoryViewModel;

public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String folderPath = "images/historyscreenshot";

    private JLabel labelAtTop;
    private JPanel panelTop;
    private final int topWidth = 800;
    private final int topHeight = 100;
    private JPanel panelBottom;
    private JPanel panelCenter;
    private final int bottomWidth = 800;
    private final int bottomHeight = 100;
    private final int windowHeight = 600;
    private final int windowWidth = 800;
    private JButton backButton;

    private HistoryViewModel historyViewModel;
    private HistoryController historyController;

    public HistoryView(HistoryViewModel historyViewModel) {
        this.historyViewModel = historyViewModel;
        this.historyViewModel.addPropertyChangeListener(this);
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));

        setLayout(new BorderLayout());

        // Top panel with full width
        this.panelTop = new JPanel() {
            private Image backgroundImage = new ImageIcon("images/historyBackgroundTop.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image scaled to fit the panel
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        labelAtTop = new JLabel();
        labelAtTop.setPreferredSize(new Dimension(340, 70));
        ImageIcon icon = new ImageIcon("images/historyLabel.png");
        Image scaledImage = icon.getImage().getScaledInstance(340, 70, Image.SCALE_SMOOTH);

        panelTop.setPreferredSize(new Dimension(topWidth, topHeight)); // Adjust height
        panelTop.setBackground(Color.LIGHT_GRAY);
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        labelAtTop.setIcon(new ImageIcon(scaledImage));
        // labelAtTop.setPreferredSize(new Dimension(100, 100));


        panelTop.add(labelAtTop);
        add(panelTop, BorderLayout.NORTH);

        // Central panel containing three image panels
        this.panelCenter = new JPanel(new GridLayout(1, 3, 10, 10)); // 3 columns, horizontal gaps
        panelCenter.setBackground(Color.WHITE); // Fallback color in case no image is set
        add(panelCenter, BorderLayout.CENTER);

        // Bottom panel containing "Back" button in bottom-right
        this.panelBottom = new JPanel() {
            private Image backgroundImage = new ImageIcon("images/historyBackgroundBottom.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image scaled to fit the panel
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 10));
        panelBottom.setPreferredSize(new Dimension(bottomWidth, bottomHeight)); // Adjust height
        // panelBottom.setBackground(Color.LIGHT_GRAY); // Optional background color

        // Load the image
        ImageIcon originalIcon = new ImageIcon("images/ReturnButtonHistory.png");
        Image scaledImage2 = originalIcon.getImage().getScaledInstance(130, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage2);

        this.backButton = new JButton();
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println("Back button clicked");
                        if (evt.getSource().equals(backButton)) {
                            historyController.execute();
                        }
                    }
                }
        );
        backButton.setPreferredSize(new Dimension(130, 50));
        backButton.setIcon(scaledIcon);
        backButton.setBorderPainted(false);  // Remove border
        backButton.setContentAreaFilled(false);  // Remove content area
        backButton.setFocusPainted(false);  // Remove focus indicator
        panelBottom.add(backButton, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        // Show frame
        // setVisible(true);
    }

    // Method to create a JPanel with an image background
    private JPanel createImagePanel(String imagePath) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(imagePath); // Load the image
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this); // Scale to panel size
            }
        };
    }

    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }

    private ArrayList<String> getScreenshotPath(){

        File folder = new File(folderPath);
        ArrayList<String> addressList = new ArrayList<>();

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles(); //
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getPath()); //
                    addressList.add(file.getPath());
                }
            }
        } else {
            System.out.println("The specified folder does not exist or is not a directory.");
        }

        return addressList;
    }

    private void showHistroyScreenshot(ArrayList<String> addressList) {
        System.out.println("breakpoint1");
        this.panelCenter.removeAll();
        if (!(addressList == null || addressList.isEmpty())) {
            System.out.println("breakpoint2");
            for (String address : addressList) {
                System.out.println(address);
                this.panelCenter.add(createImagePanel(address));
            }
            System.out.println("breakpoint3");

        }
        for (int i = 0; i < 3 - addressList.size(); i++) {
            this.panelCenter.add(createImagePanel("images/emptyHistory.png"));
        }
        repaint();
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("history fire property change");
        showHistroyScreenshot(getScreenshotPath());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new HistoryView().createAndShowGUI());
//    }
}


