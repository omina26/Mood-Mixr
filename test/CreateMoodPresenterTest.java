import interface_adapter.ViewManagerModel;
import interface_adapter.create_mood.CreateMoodPresenter;
import interface_adapter.create_mood.CreateMoodViewModel;
import interface_adapter.view_moods.ViewMoodsViewModel;
import org.junit.Test;
import use_case.create_mood.CreateMoodOutputData;

import javax.swing.text.View;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateMoodPresenterTest {

    @Test
    public void testPrepareMoodListView() {
        HashSet<String> mockMoodsList = new HashSet<>();
        mockMoodsList.add("mock");
        CreateMoodOutputData data = new CreateMoodOutputData(mockMoodsList, false);

        CreateMoodViewModel mockCreateMoodViewModel = new CreateMoodViewModel();
        ViewMoodsViewModel mockViewMoodsViewModel = new ViewMoodsViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        CreateMoodPresenter presenter = new CreateMoodPresenter(mockCreateMoodViewModel, mockViewMoodsViewModel,
                mockViewManagerModel);

        presenter.prepareMoodListView(data);

        assertTrue(mockViewMoodsViewModel.getState().getMoodsList().equals(mockMoodsList));
        assertEquals(mockViewManagerModel.getActiveView(), mockViewMoodsViewModel.getViewName());
    }

    @Test
    public void testPrepareFailView(){
        CreateMoodViewModel mockCreateMoodViewModel = new CreateMoodViewModel();
        ViewMoodsViewModel mockViewMoodsViewModel = new ViewMoodsViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        CreateMoodPresenter presenter = new CreateMoodPresenter(mockCreateMoodViewModel, mockViewMoodsViewModel,
                mockViewManagerModel);
        presenter.prepareFailView("mock error");
        assertEquals(mockCreateMoodViewModel.getState().getSaveError(), "mock error");
    }
}
