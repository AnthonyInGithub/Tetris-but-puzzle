package data_access;

/**
 * DataAccessInterface for saving and retrieving the selected level.
 */
public interface LevelSelectDataAccessInterface {
    void setSelectedLevel(int level);
    String getImageAddressLevel();
    void setImageAddress();
    void setColorMapAndBinaryMap();
}

