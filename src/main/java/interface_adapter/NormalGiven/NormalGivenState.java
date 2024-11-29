package interface_adapter.NormalGiven;

public class NormalGivenState {
    private String gamingState = "playing";

    public String getGamingState() {
        return gamingState;
    }

    public void setGamingState(String newGamingState) {
        this.gamingState = newGamingState;
    }
}
