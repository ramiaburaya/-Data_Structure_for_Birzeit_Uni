package com.example.projectds3;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class DepartmentOps {

    public void printDepartmentStage () {
        Stage DepartmentStage = new Stage();

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

        AnchorPane SecondPane = new AnchorPane();
        SecondPane.setLayoutX( 4 );
        SecondPane.setLayoutY( 120 );
        SecondPane.setPrefSize( 1360, 545 );

        HBox ButtonPane = new HBox( 10 );
        ButtonPane.setSpacing( 10 );
        ButtonPane.setLayoutX( 224 );
        ButtonPane.setLayoutY( 34 );
        ButtonPane.setPadding( new Insets( 10, 10, 10, 10 ) );
        ButtonPane.setPrefSize( 920, 52 );


        Button DisplayDepartment = new Button( "Display Department" );
        DisplayDepartment.setPrefSize( 200, 70 );
        DisplayDepartment.setTextFill( Color.WHITE );
        ButtonPane.getChildren().add( DisplayDepartment );
        DisplayDepartment.setOnAction( e -> {
            SecondPane.getChildren().clear();

            TextArea DisplayText = new TextArea();
            DisplayText.setLayoutX( 188 );
            DisplayText.setLayoutY( 64 );
            DisplayText.setEditable( false );
            DisplayText.setPrefSize( 1037, 276 );

            Button DisplayButton = new Button( "Display" );
            DisplayButton.setPrefSize( 209, 40 );
            DisplayButton.setLayoutX( 628 );
            DisplayButton.setTextFill( Color.BLACK );
            DisplayButton.setLayoutY( 379 );
            DisplayButton.setOnAction( x -> {
                if (!Read.DepartmentList.isEmpty()) {
                    DisplayText.setText( "************************************************************************************************** Department " +
                            "*****************************************************************************************\n"
                            + Read.DepartmentList.traverseInorder() );
                } else {
                    Alert Error = new Alert( Alert.AlertType.ERROR );
                    Error.setContentText( "Read Department file first" );
                    Error.show();
                }
            } );
            SecondPane.getChildren().addAll( DisplayButton, DisplayText );
        } );

        Button SearchDepartment = new Button( "Search For Department" );
        SearchDepartment.setPrefSize( 200, 70 );
        SearchDepartment.setTextFill( Color.WHITE );
        ButtonPane.getChildren().add( SearchDepartment );
        SearchDepartment.setOnAction( e -> {
            SecondPane.getChildren().clear();

            Label SearchLabel = new Label( "Department Name" );
            SearchLabel.setLayoutX( 470 );
            SearchLabel.setLayoutY( 123 );
            SearchLabel.setTextFill( Color.WHITE );
            SearchLabel.setPrefSize( 130, 20 );
            SearchLabel.setFont( Font.font( 15 ) );
            SearchLabel.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );

            TextField searchText = new TextField();
            searchText.setPrefSize( 180, 25 );
            searchText.setLayoutX( 663 );
            searchText.setLayoutY( 121 );
            searchText.setPromptText( "ex : Nurs" );
            searchText.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );

            Button SearchButton = new Button( "Search" );
            SearchButton.setLayoutX( 580 );
            SearchButton.setLayoutY( 359 );
            SearchButton.setPrefSize( 209, 40 );
            SearchButton.setTextFill( Color.BLACK );

            SearchButton.setOnAction( x -> {
                if (!Read.DepartmentList.isEmpty()) {
                    if (Read.DepartmentList.Search( new Department( searchText.getText().toLowerCase().trim() ) ) != null) {
                        Alert Done = new Alert( Alert.AlertType.CONFIRMATION );
                        ImageView checked = new ImageView( new Image( "file:checked.png" ) );
                        checked.setFitHeight( 50 );
                        checked.setFitWidth( 50 );
                        Done.setTitle( "Search" );
                        Done.setContentText( "Founded " );
                        Done.setGraphic( checked );
                        Done.show();
                    } else {
                        Alert Error = new Alert( Alert.AlertType.INFORMATION );
                        Error.setTitle( "Search" );
                        Error.setContentText( "No department Called ( " + searchText.getText().toLowerCase().trim() + " )" );
                        Error.show();
                    }
                } else {
                    Alert Error = new Alert( Alert.AlertType.ERROR );
                    Error.setTitle( "Read File" );
                    Error.setContentText( "Read Department File first " );
                    Error.show();
                }
            } );
            SecondPane.getChildren().addAll( searchText, SearchLabel, SearchButton );
        } );

        Button InsertDepartment = new Button( "Insert Department" );
        InsertDepartment.setTextFill( Color.WHITE );
        InsertDepartment.setPrefSize( 200, 70 );
        ButtonPane.getChildren().add( InsertDepartment );
        InsertDepartment.setTextFill( Color.WHITE );
        InsertDepartment.setOnAction( e -> {
            SecondPane.getChildren().clear();

            Label NameLabel = new Label( "Department Name" );
            NameLabel.setLayoutX( 470 );
            NameLabel.setLayoutY( 123 );
            NameLabel.setTextFill( Color.WHITE );
            NameLabel.setPrefSize( 130, 20 );
            NameLabel.setFont( Font.font( 15 ) );
            NameLabel.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );

            TextField NameText = new TextField();
            NameText.setPrefSize( 180, 25 );
            NameText.setLayoutX( 663 );
            NameText.setLayoutY( 121 );
            NameText.setPromptText( "ex : Nurs" );
            NameText.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );

            Button AddButton = new Button( "Add" );
            AddButton.setLayoutX( 580 );
            AddButton.setLayoutY( 359 );
            AddButton.setPrefSize( 209, 40 );
            AddButton.setTextFill( Color.BLACK );

            AddButton.setOnAction( x -> {

                if (Read.DepartmentList.Search( new Department( NameText.getText().toLowerCase().trim() ) ) == null) {
                    Read.DepartmentList.insert( new Department( NameText.getText().trim() ) );
                    ImageView checked = new ImageView( new Image( "file:checked.png" ) );
                    checked.setFitHeight( 50 );
                    checked.setFitWidth( 50 );
                    Alert Done = new Alert( Alert.AlertType.CONFIRMATION );
                    Done.setTitle( "Add" );
                    Done.setGraphic( checked );
                    Done.setContentText( "Added successfully" );
                    Done.show();
                    Read.DepartmentList.traverseInorder();

                } else {
                    Alert Error = new Alert( Alert.AlertType.INFORMATION );
                    Error.setTitle( "Add" );
                    Error.setContentText( "Department ( " + NameText.getText().trim() + " ) " + "already exist" );
                    Error.show();
                }
            } );
            SecondPane.getChildren().addAll( NameText, NameLabel, AddButton );

        } );

        Button DeleteDepartment = new Button( "Delete Department" );
        DeleteDepartment.setTextFill( Color.WHITE );
        DeleteDepartment.setPrefSize( 200, 70 );
        ButtonPane.getChildren().add( DeleteDepartment );
        DeleteDepartment.setOnAction( e -> {
            SecondPane.getChildren().clear();

            Label NameLabel = new Label( "Department Name" );
            NameLabel.setLayoutX( 470 );
            NameLabel.setLayoutY( 123 );
            NameLabel.setTextFill( Color.WHITE );
            NameLabel.setPrefSize( 130, 20 );
            NameLabel.setFont( Font.font( 15 ) );
            NameLabel.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );

            TextField NameText = new TextField();
            NameText.setPrefSize( 180, 25 );
            NameText.setLayoutX( 663 );
            NameText.setLayoutY( 121 );
            NameText.setPromptText( "ex : Nurs" );
            NameText.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            Button DeleteButton = new Button( "Delete" );
            DeleteButton.setLayoutX( 580 );
            DeleteButton.setLayoutY( 359 );
            DeleteButton.setPrefSize( 209, 40 );
            DeleteButton.setTextFill( Color.BLACK );
            DeleteButton.setOnAction( x -> {
                if (!Read.DepartmentList.isEmpty()) {
                    Node<Department> toDel = Read.DepartmentList.Search( new Department( NameText.getText().toLowerCase().trim() ) );
                    if (toDel != null) {
                        Read.DepartmentList.delete( toDel.getData() );
                        ImageView checked = new ImageView( new Image( "file:checked.png" ) );
                        checked.setFitHeight( 50 );
                        checked.setFitWidth( 50 );
                        Alert Done = new Alert( Alert.AlertType.CONFIRMATION );
                        Done.setTitle( "Delete" );
                        Done.setGraphic( checked );
                        Done.setContentText( "Delete successfully" );
                        Done.show();
                        Read.DepartmentList.traverseInorder();

                    } else {
                        Alert Error = new Alert( Alert.AlertType.INFORMATION );
                        Error.setTitle( "Delete" );
                        Error.setContentText( "Department ( " + NameText.getText().trim() + " ) " + "Not exist" );
                        Error.show();
                    }
                } else {
                    Alert Error = new Alert( Alert.AlertType.ERROR );
                    Error.setTitle( "Read File" );
                    Error.setContentText( "Read Department File first " );
                    Error.show();
                }
            } );
            SecondPane.getChildren().addAll( NameText, NameLabel, DeleteButton );
        } );

        Button CalculateHeight = new Button( "Print Height" );
        CalculateHeight.setPrefSize( 200, 70 );
        CalculateHeight.setTextFill( Color.WHITE );
        ButtonPane.getChildren().add( CalculateHeight );
        CalculateHeight.setOnAction( e -> {
            SecondPane.getChildren().clear();
            Image Graphic = new Image( "file:height.png" );
            ImageView GraphicLogo = new ImageView( Graphic );
            GraphicLogo.setFitWidth( 80 );
            GraphicLogo.setFitHeight( 50 );
            Alert Height = new Alert( Alert.AlertType.INFORMATION );
            Height.setContentText( "Height is " + Read.DepartmentList.getHeight() );
            Height.setTitle( "Height" );
            Height.setGraphic( GraphicLogo );
            Height.show();
        } );


        Button Back = new Button( "Back" );
        Back.setLayoutX( 1180 );
        Back.setTextFill( Color.WHITE );
        Back.setLayoutY( 659 );
        Back.setPrefSize( 180, 45 );
        Back.setOnAction( e -> {
            MainWindow user = new MainWindow();
            user.PrintMainWindow();
            DepartmentStage.close();
        } );

        RootPane.getChildren().addAll( Back, ButtonPane, SecondPane, logo );

        RootPane.setBackground( background );
        Scene scene = new Scene( RootPane );
        scene.getStylesheets().add(((URL)Objects.requireNonNull(this.getClass().getResource("Style.css"))).toExternalForm());
        DepartmentStage.setMaximized( true );
        DepartmentStage.setScene( scene );
        DepartmentStage.setTitle( "Department Window" );
        DepartmentStage.getIcons().add( icon );
        DepartmentStage.show();
    }

}
