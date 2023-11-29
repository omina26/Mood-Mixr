package app;



import interface_adapter.ViewManagerModel;

import data_access.UserDataAccessObject;


import data_access.create_mood.MoodDataAccessObject;

import interface_adapter.ViewManagerModel;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import interface_adapter.analyze_playlist.AnalyzePlaylistState;

import interface_adapter.create_mood.CreateMoodViewModel;

import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.view_moods.ViewMoodsViewModel;
import use_case.analyze_playlist.AnalyzePlaylistDataAccessInterface;
import use_case.login.LoginDataAccessInterface;

import view.*;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

import view.CreateMoodView;
import view.LoginView;
import view.ViewManager;
import view.ViewMoodsView;
import view.AnalyzePlaylistView;

import java.io.File;

import java.io.IOException;

public class Main {
    public static void main(String[] args){

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

        AnalyzePlaylistViewModel analyzePlaylistViewModel = new AnalyzePlaylistViewModel();

        MoodDataAccessObject moodDataAccessObject;

        try{
            moodDataAccessObject = new MoodDataAccessObject(new File("./moods.csv"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CreateMoodView createMoodView = CreateMoodUseCaseFactory.create(viewManagerModel, createMoodViewModel, viewMoodsViewModel, moodDataAccessObject);
        views.add(createMoodView, createMoodView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);

        ViewMoodsView viewMoodsView = new ViewMoodsView(viewMoodsViewModel);
        views.add(viewMoodsView, viewMoodsView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);

        views.add(loggedInView, loggedInView.viewName);
        views.add(loggedInView, loggedInView.viewName);


        AnalyzePlaylistView analyzePlaylistView = AnalyzePlaylistUseCaseFactory.create(viewManagerModel, analyzePlaylistViewModel, analyzePlaylistDataAccessObject);
        views.add(analyzePlaylistView, analyzePlaylistView.viewName);

        //viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.setActiveView(analyzePlaylistViewModel.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

}
