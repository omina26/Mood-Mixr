package app;
import data_access.analyze_playlist.AnalyzePlaylistDataAccessObject;
import interface_adapter.ViewManagerModel;

import data_access.login.UserDataAccessObject;


import data_access.create_mood.MoodDataAccessObject;

import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;

import interface_adapter.analyzed_playlist.AnalyzedPlaylistViewModel;
import interface_adapter.create_mood.CreateMoodViewModel;

import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import interface_adapter.view_moods.ViewMoodsViewModel;

import view.*;


import javax.swing.*;
import java.awt.*;

import view.LoggedInView;


import view.CreateMoodView;
import view.LoginView;
import view.ViewManager;
import view.ViewMoodsView;
import view.AnalyzePlaylistView;

import java.io.File;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("Mood Player!");
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
        AnalyzedPlaylistViewModel analyzedPlaylistViewModel = new AnalyzedPlaylistViewModel();


        MoodDataAccessObject moodDataAccessObject;
        UserDataAccessObject userDataAccessObject;
        AnalyzePlaylistDataAccessObject analyzePlaylistDataAccessObject;


        try {
            moodDataAccessObject = new MoodDataAccessObject(new File("./moods.csv"));
            userDataAccessObject = new UserDataAccessObject(new File("./user.csv"));
            analyzePlaylistDataAccessObject = new AnalyzePlaylistDataAccessObject(new File("./playlistIDs.csv"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        CreateMoodView createMoodView = CreateMoodUseCaseFactory.create(viewManagerModel, createMoodViewModel, viewMoodsViewModel, createPlaylistViewModel, moodDataAccessObject);
        views.add(createMoodView, createMoodView.viewName);

        ViewMoodsView viewMoodsView = new ViewMoodsView(viewMoodsViewModel, viewManagerModel);
        views.add(viewMoodsView, viewMoodsView.viewName);

        AnalyzePlaylistView analyzePlaylistView = AnalyzePlaylistUseCaseFactory.create(viewManagerModel, analyzePlaylistViewModel, analyzedPlaylistViewModel,analyzePlaylistDataAccessObject, userDataAccessObject, createMoodViewModel);
        views.add(analyzePlaylistView, analyzePlaylistView.viewName);

        AnalyzedPlaylistView analyzedPlaylistView = new AnalyzedPlaylistView(analyzedPlaylistViewModel, viewManagerModel);
        views.add(analyzedPlaylistView,analyzedPlaylistView.viewName);

        PlaylistCreatedViewModel playlistCreatedViewModel = new PlaylistCreatedViewModel();


        CreatePlaylistView createPlaylistView = CreatePlaylistUseCaseFactory.create(viewManagerModel, createPlaylistViewModel, playlistCreatedViewModel, userDataAccessObject, moodDataAccessObject);
        views.add(createPlaylistView, createPlaylistView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel);
        views.add(loggedInView, loggedInView.viewName);

        PlaylistCreatedView playlistCreatedView = new PlaylistCreatedView(playlistCreatedViewModel, viewManagerModel);
        views.add(playlistCreatedView, playlistCreatedView.viewName);

        //viewManagerModel.setActiveView(createMoodView.viewName);
        //viewManagerModel.setActiveView(analyzePlaylistViewModel.getViewName());
        //viewManagerModel.setActiveView(analyzedPlaylistView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
