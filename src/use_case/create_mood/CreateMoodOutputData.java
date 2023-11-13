package use_case.create_mood;

import java.util.List;
import java.util.Set;

public class CreateMoodOutputData {

    private final Set<String> moodNames;

    public CreateMoodOutputData(Set<String> moodNames){
        this.moodNames = moodNames;
    }

    public Set<String> getMoodNames(){
        return this.moodNames;
    }
}
