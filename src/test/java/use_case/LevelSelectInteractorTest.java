package use_case.LevelSelect;

import data_access.InMemoryDataAccessObject;
import data_access.LevelSelectDataAccessInterface;
import use_case.LevelSelect.LevelSelectOutputBoundary;
import use_case.LevelSelect.LevelSelectOutputData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for LevelSelectInteractor.
 */
public class LevelSelectInteractorTest {

    private LevelSelectInteractor interactor;
    private MockLevelSelectPresenter presenter;
    private LevelSelectDataAccessInterface dataAccess;

    @Before
    public void setUp() {
        // Initialize the mock presenter and data access object
        presenter = new MockLevelSelectPresenter();
        dataAccess = new InMemoryDataAccessObject();

        // Initialize the interactor with the mock presenter and data access
        interactor = new LevelSelectInteractor(presenter, dataAccess);
    }

    @Test
    public void testSelectValidLevel() {
        int selectedLevel = 2;

        // Call the method under test
        interactor.selectLevel(selectedLevel);

        // Verify that the correct image address is set in the data access layer
        String expectedImageAddress = "images/level2.png";
        assertEquals(expectedImageAddress, dataAccess.getImageAddressLevel());

        // Verify that the presenter received the correct output data
        LevelSelectOutputData outputData = presenter.getOutputData();
        assertNotNull(outputData);
        assertEquals(selectedLevel, outputData.getSelectedLevel());
        assertEquals(expectedImageAddress, outputData.getImageAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectInvalidLevel() {
        int invalidLevel = 5;

        // Expect an IllegalArgumentException to be thrown
        interactor.selectLevel(invalidLevel);
    }

    /**
     * Mock presenter to capture the output data for assertions.
     */
    private static class MockLevelSelectPresenter implements LevelSelectOutputBoundary {

        private LevelSelectOutputData outputData;

        @Override
        public void presentLevelSelected(LevelSelectOutputData outputData) {
            this.outputData = outputData;
        }

        public LevelSelectOutputData getOutputData() {
            return outputData;
        }
    }
}