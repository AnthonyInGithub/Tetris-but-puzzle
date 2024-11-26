package interface_adapter.EndingScene;

import interface_adapter.NormalGiven.ViewModelMain;

public class EndingSceneViewModel extends ViewModelMain<EndingSceneState> {
    private boolean isWin;
    public EndingSceneViewModel() {
        super("EndingScene");
        setState(new EndingSceneState());
    }
    public boolean getIsWin(){
        return isWin;
    }
    public void setIsWin(boolean isWin){
        this.isWin = isWin;
    }
}
