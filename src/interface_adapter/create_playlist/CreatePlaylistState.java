package interface_adapter.create_playlist;

public class CreatePlaylistState {
    private String selectedMood;

    public CreatePlaylistState(String defaultMessage) {
        this.selectedMood = defaultMessage;
    }

    public String getSelectedMood() {return selectedMood;}

    public void setSelectedMood(String selectedMood) {
        this.selectedMood = selectedMood;
    }

    public void setSaveError(String error) {
    }
}
