import interface_adapter.create_mood.CreateMoodController;
import org.junit.Test;
import use_case.create_mood.CreateMoodInputBoundary;
import use_case.create_mood.CreateMoodInputData;

import static org.junit.Assert.assertEquals;

public class CreateMoodControllerTest {

    @Test
    public void testExecute(){
        MockCreateMoodInteractor mockInteractor = new MockCreateMoodInteractor();

        CreateMoodController controller = new CreateMoodController(mockInteractor);
        controller.execute("mock", 0, 0, 0, 0,
                0, 0, 0);

        assertEquals(mockInteractor.data, "mock 0 0 0 0 0 0 0");
    }
}

class MockCreateMoodInteractor implements CreateMoodInputBoundary{

    String data;

    @Override
    public void execute(CreateMoodInputData createMoodInputData) {
        this.data = createMoodInputData.toString();
    }
}
