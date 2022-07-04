package com.example.projectds3;

import javafx.application.Application;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    @Override
    public void start ( Stage stage ) {
        MainWindow user = new MainWindow();
        user.PrintMainWindow();

    }

    public static void main ( String[] args ) {
        launch();
    }
}