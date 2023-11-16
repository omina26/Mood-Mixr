package interface_adapter.view_moods;

import entity.Mood;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewMoodsState {

    private Set<String> moodsList = new HashSet<String>();

    public ViewMoodsState(){}

    public ViewMoodsState(ViewMoodsState copy) {
        moodsList = copy.moodsList;
    }

    public Set<String> getMoodsList(){
        return moodsList;
    }

    public void setMoodsList(Set<String> moodsList){
        this.moodsList = moodsList;
    }
}
