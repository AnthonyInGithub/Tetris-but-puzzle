package app;

import javax.swing.*;

/**
 * Main class to start the Tetris application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder.addEndingSceneView()
                                            .addNormalGivenView()
                                            .addEndingSceneUseCase()
                                            .addNormalGivenUseCase()
                                            .build();

        //appBuilder.TestingSwtich();
        application.pack();
        application.setVisible(true);
    }
}
