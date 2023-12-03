package app;



import data_access.group_playlist.GroupPlaylistDataAccessObject;
import entity.GroupPlaylist;
import entity.User;
import interface_adapter.ViewManagerModel;

import data_access.login.UserDataAccessObject;


import data_access.create_mood.MoodDataAccessObject;

import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;

import interface_adapter.create_mood.CreateMoodViewModel;

import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.group_playlist.GroupPlaylistController;
import interface_adapter.group_playlist.GroupPlaylistViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import interface_adapter.view_moods.ViewMoodsViewModel;

import use_case.group_playlist.GroupPlaylistInteractor;
import view.*;


import javax.swing.*;
import java.awt.*;

import view.LoggedInView;


import view.CreateMoodView;
import view.LoginView;
import view.ViewManager;
import view.ViewMoodsView;

import java.io.File;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("Login!");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();

        CreateMoodViewModel createMoodViewModel = new CreateMoodViewModel();
        ViewMoodsViewModel viewMoodsViewModel = new ViewMoodsViewModel();

        CreatePlaylistViewModel createPlaylistViewModel = new CreatePlaylistViewModel();
        AnalyzePlaylistViewModel analyzePlaylistViewModel = new AnalyzePlaylistViewModel();

        GroupPlaylistViewModel groupPlaylistViewModel = new GroupPlaylistViewModel();

        MoodDataAccessObject moodDataAccessObject;
        UserDataAccessObject userDataAccessObject;
        GroupPlaylistDataAccessObject groupPlaylistDataAccessObject = new GroupPlaylistDataAccessObject();


        try {
            moodDataAccessObject = new MoodDataAccessObject(new File("./moods.csv"));
            userDataAccessObject = new UserDataAccessObject(new File("./user.csv"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        AnalyzePlaylistDataAccessObject analyzePlaylistDataAccessObject;
//
//        try{
//            analyzePlaylistDataAccessObject = new AnalyzePlaylistDataAccessObject(new File("./playlistIDs.csv"));
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        CreateMoodView createMoodView = CreateMoodUseCaseFactory.create(viewManagerModel, createMoodViewModel, viewMoodsViewModel, moodDataAccessObject);
        views.add(createMoodView, createMoodView.viewName);


        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);


        ViewMoodsView viewMoodsView = new ViewMoodsView(viewMoodsViewModel);
        views.add(viewMoodsView, viewMoodsView.viewName);

        GroupPlaylistView groupPlaylistView = GroupPlaylistUseCaseFactory.create(viewManagerModel,
                groupPlaylistViewModel,
                loggedInViewModel,
                groupPlaylistDataAccessObject,
                new User("soph", "BQDToz0yKElYcIftCBYe_TPzSvBr7XvC8hY9S3eJ5RaSbJ1Fnki2PY8_MxMZBZYfAamIc" +
                        "dGG0HiyLU35x_eOg5NAB8mg4LD7b7PDN5-kmqcWKA7csqk6VYH_VIER2lk1lwdTKwTD3DzYpPAUNM3fp0j8XX8hQgNsS" +
                        "11T-oC9hnqPFQYJoJrlGEBCZ9yEBrgaixgyZqELQ1DT-dC0nofMFbUnaOMANbk"));

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel);

        views.add(loggedInView, loggedInView.viewName);
        views.add(loggedInView, loggedInView.viewName);


//        AnalyzePlaylistView analyzePlaylistView = AnalyzePlaylistUseCaseFactory.create(viewManagerModel, analyzePlaylistViewModel, analyzePlaylistDataAccessObject);
//        views.add(analyzePlaylistView, analyzePlaylistView.viewName);

        PlaylistCreatedViewModel playlistCreatedViewModel = new PlaylistCreatedViewModel();
        CreatePlaylistView createPlaylistView = CreatePlaylistUseCaseFactory.create(viewManagerModel, createPlaylistViewModel, playlistCreatedViewModel, userDataAccessObject, moodDataAccessObject);
        views.add(createPlaylistView, createPlaylistView.viewName);

//         viewManagerModel.setActiveView(loginView.viewName);
        //viewManagerModel.setActiveView(createMoodView.viewName);

        //viewManagerModel.setActiveView(loginView.viewName);


        viewManagerModel.setActiveView(loginView.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
