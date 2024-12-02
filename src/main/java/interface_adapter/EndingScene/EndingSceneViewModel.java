package interface_adapter.EndingScene;

import interface_adapter.ViewModelMain;

public class EndingSceneViewModel extends ViewModelMain<EndingSceneState> {
    private boolean savedSuccess;

    public EndingSceneViewModel() {
        super("EndingSceneView");
        setState(new EndingSceneState());
    }

    public boolean getSavedSuccess(){
        return savedSuccess;
    }
    public void setSavedSuccess(boolean savedSuccess){
        this.savedSuccess = savedSuccess;
    }
}
