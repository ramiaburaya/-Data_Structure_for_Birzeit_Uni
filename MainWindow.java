package com.example.projectds3;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class MainWindow {
    static Stage MainWindowStage = new Stage();

    public void PrintMainWindow () {

        AnchorPane RootPane = new AnchorPane();

        Background background = new Background(
                new BackgroundImage( new Image( "file:BackGround.jpg" ), null, null, null, null ) );
        Image icon = new Image( "file:Logo.png" );

        // Insert logo in Main Scene
        ImageView logo = new ImageView( icon );
        logo.setLayoutY( 14 );
        logo.setLayoutX( 6 );
        logo.setFitWidth( 166 );
        logo.setFitHeight( 111 );

        Button DepartmentButton = new Button( "Department" );
        DepartmentButton.setPrefSize( 200, 45 );
        DepartmentButton.setLayoutX( 370 );
        DepartmentButton.setLayoutY( 386 );
        DepartmentButton.setTextFill( Color.WHITE );
        DepartmentButton.setFont( Font.font( 18 ) );
        DepartmentButton.setOnAction( e -> {
            if (!Read.DepartmentList.isEmpty()) {
                DepartmentOps user = new DepartmentOps();
                user.printDepartmentStage();
                MainWindowStage.close();
            } else {
                Alert Error = new Alert( Alert.AlertType.ERROR );
                Error.setTitle( "Read File" );
                Error.setContentText( "Read Department File first " );
                Error.show();
            }

        } );


        Button StudentButton = new Button( "Student" );
        StudentButton.setPrefSize( 200, 45 );
        StudentButton.setLayoutX( 770 );
        StudentButton.setLayoutY( 386 );
        StudentButton.setFont( Font.font( 18 ) );
        StudentButton.setTextFill( Color.WHITE );
        StudentButton.setOnAction( e -> {
            if (!Read.DepartmentList.isEmpty()) {
                StudentWindow user = new StudentWindow();
                user.PrintStudentWindow();
                MainWindowStage.close();
            } else {
                Alert Error = new Alert( Alert.AlertType.ERROR );
                Error.setTitle( "Read File" );
                Error.setContentText( "Read Department File first " );
                Error.show();
            }
        } );

        Button ReadButton = new Button( "Read" );
        ReadButton.setPrefSize( 200, 45 );
        ReadButton.setLayoutX( 570 );
        ReadButton.setLayoutY( 279 );
        ReadButton.setFont( Font.font( 18 ) );
        ReadButton.setTextFill( Color.WHITE );
        ReadButton.setOnAction( e -> {
            Read file = new Read();
            file.ReadDepartments();

        } );


        Button ExitButton = new Button( "Exit" );
        ExitButton.setLayoutX( 1152 );
        ExitButton.setLayoutY( 633 );
        ExitButton.setFont( Font.font( 18 ) );
        ExitButton.setTextFill( Color.WHITE );
        ExitButton.setPrefSize( 200, 45 );
        ExitButton.setOnAction( e -> MainWindowStage.close() );
        RootPane.getChildren().addAll( ExitButton, ReadButton, logo, DepartmentButton, StudentButton );

        RootPane.setBackground( background );
        Scene scene = new Scene( RootPane );
        scene.getStylesheets().add( Objects.requireNonNull( this.getClass().getResource( "Style.css" ) ).toExternalForm() );

        MainWindowStage.setMaximized( true );
        MainWindowStage.setScene( scene );
        MainWindowStage.setTitle( "Main Window" );
        MainWindowStage.getIcons().add( icon );
        MainWindowStage.show();
    }

}
