package data_access;

/**
 * DataAccessInterface for saving and retrieving the selected level.
 */
public interface LevelSelectDataAccessInterface {
    int getSelectedLevel();
    void setSelectedLevel(int level);
}
