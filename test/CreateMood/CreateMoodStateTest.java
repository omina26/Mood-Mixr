package CreateMood;

import interface_adapter.create_mood.CreateMoodState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateMoodStateTest {

    @Test
    public void TestCreateMoodStateWithCopy(){
        CreateMoodState copy = new CreateMoodState(1);
        CreateMoodState main = new CreateMoodState(copy);

        assertEquals(main.getName(), copy.getName());
        assertEquals(main.getSaveError(), copy.getSaveError());
        assertEquals(main.getAcousticness(), copy.getAcousticness());
        assertEquals(main.getDanceability(), copy.getDanceability());
        assertEquals(main.getEnergy(), copy.getEnergy());
        assertEquals(main.getInstrumentalness(), copy.getInstrumentalness());
        assertEquals(main.getLiveness(), copy.getLiveness());
        assertEquals(main.getSpeechiness(), copy.getSpeechiness());
        assertEquals(main.getValence(), copy.getValence());
    }
}
