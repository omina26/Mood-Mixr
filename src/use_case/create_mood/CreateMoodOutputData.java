package use_case.create_mood;

import java.util.List;
import java.util.Set;

public class CreateMoodOutputData {

    private final Set<String> moodNames;
    private final boolean useCaseFailed;

    public CreateMoodOutputData(Set<String> moodNames, boolean useCaseFailed){
        this.moodNames = moodNames;
        this.useCaseFailed = useCaseFailed;
    }

    public Set<String> getMoodNames(){
        return this.moodNames;
    }
}
