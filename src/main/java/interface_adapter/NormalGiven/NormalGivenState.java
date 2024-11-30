package interface_adapter.NormalGiven;

public class NormalGivenState {
    private String gamingState = "playing";

    private String imgAddress;

    public String getGamingState() {
        return gamingState;
    }

    public void setGamingState(String newGamingState) {
        this.gamingState = newGamingState;
    }

    public String getImgAddress() {
        return imgAddress;
    }
    public void setImgAddress(String newImgAddress) {
        this.imgAddress = newImgAddress;
    }
}
