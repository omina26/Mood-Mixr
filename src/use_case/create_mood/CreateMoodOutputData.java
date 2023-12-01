package use_case.create_mood;

import java.util.List;
import java.util.Set;

/**
 * Represents the Output Data for the Create Mood use case
 */
public class CreateMoodOutputData {

    private final Set<String> moodNames;
    private final boolean useCaseFailed;

    /**
     * The constructor for the Create Mood Output Data object
     * @param moodNames A set of mood names stored
     * @param useCaseFailed Whether the use case failed due to an exception
     */
    public CreateMoodOutputData(Set<String> moodNames, boolean useCaseFailed){
        this.moodNames = moodNames;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the mood names for the created moods
     * @return mood names for the created moods
     */
    public Set<String> getMoodNames(){
        return this.moodNames;
    }

    /**
     * Gets whether the use case failed
     * @return whether the use case failed
     */
    public boolean getUseCaseFailed() { return this.useCaseFailed; }
}
