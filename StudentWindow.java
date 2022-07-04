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

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class StudentWindow {
    static Stage StudentStage;
    static ListView<String> DepartmentList;

    public void PrintStudentWindow () {
        StudentStage = new Stage();
        AnchorPane RootPane = new AnchorPane();

        Background background = new Background(
                new BackgroundImage( new Image( "file:BackGround.jpg" ), null, null, null, null ) );
        Image icon = new Image( "file:Logo.png" );

        Label DepartmentListLabel = new Label();
        DepartmentListLabel.setTextFill( Color.WHITE );
        DepartmentListLabel.setFont( Font.font( 15 ) );
        DepartmentListLabel.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
        DepartmentListLabel.setLayoutX( 1204 );
        DepartmentListLabel.setLayoutY( 241 );
        DepartmentListLabel.setPrefSize( 117, 17 );
        RootPane.getChildren().add( DepartmentListLabel );

        DepartmentList = new ListView<>();
        DepartmentList.setLayoutX( 1169 );
        DepartmentList.setLayoutY( 284 );
        DepartmentList.setPrefSize( 176, 200 );
        for (int i = 0; i < Read.listForListView.size(); i++) {
            Collections.sort( Read.listForListView );
            DepartmentList.getItems().add( String.valueOf( Read.listForListView.get( i ) ) );
        }
        DepartmentList.setOnMouseClicked( e -> {
            String Department = DepartmentList.getSelectionModel().getSelectedItem();

            DepartmentListLabel.setText( Department );


        } );
        RootPane.getChildren().add( DepartmentList );

        AnchorPane SecondPane = new AnchorPane();
        SecondPane.setLayoutX( 45 );
        SecondPane.setLayoutY( 160 );
        RootPane.getChildren().add( SecondPane );

        HBox ButtonPane = new HBox( 10 );
        ButtonPane.setPadding( new Insets( 10, 10, 10, 10 ) );
        ButtonPane.setSpacing( 10 );
        ButtonPane.setLayoutX( 118 );
        ButtonPane.setLayoutY( 57 );
        ButtonPane.setPrefSize( 1150, 70 );
        RootPane.getChildren().add( ButtonPane );

        Button DisplayButton = new Button( "Display Student" );
        DisplayButton.setPrefSize( 150, 45 );
        DisplayButton.setTextFill( Color.WHITE );
        DisplayButton.setOnAction( e -> {
            SecondPane.getChildren().clear();

            TextArea DisplayText = new TextArea();
            DisplayText.setLayoutX( 87 );
            DisplayText.setLayoutY( 55 );
            DisplayText.setPrefSize( 922, 335 );
            DisplayText.setEditable( false );
            DisplayText.setStyle( "-fx-border-radius: 3px; -fx-border-color: red" );
            SecondPane.getChildren().add( DisplayText );

            Button DisplayBu = new Button( "Display" );
            DisplayBu.setPrefSize( 150, 45 );
            DisplayBu.setLayoutX( 514 );
            DisplayBu.setLayoutY( 457 );
            DisplayBu.setOnAction( x -> {
                String department = DepartmentList.getSelectionModel().getSelectedItem();
                if (department != null) {
                    Node<Department> toSearchDe = Read.DepartmentList.Search( new Department( department.toLowerCase().trim() ) );
                    DisplayText.setText( "****************************************************************************** Students in :  " + department +
                            " Department *******************************************************************\n"
                            + toSearchDe.getData().getList().toString() );
                } else {
                    Alert error = new Alert( Alert.AlertType.ERROR );
                    error.setContentText( "Select Department First " );
                    error.setTitle( "Display" );
                    error.show();
                }
            } );
            SecondPane.getChildren().add( DisplayBu );

        } );

        Button TableSizeButton = new Button( "TableSize" );
        TableSizeButton.setPrefSize( 150, 45 );
        TableSizeButton.setTextFill( Color.WHITE );
        TableSizeButton.setOnAction( e -> {
            SecondPane.getChildren().clear();
            String department = DepartmentList.getSelectionModel().getSelectedItem();
            if (department != null) {
                Node<Department> toSearchDe = Read.DepartmentList.Search( new Department( department.toLowerCase().trim() ) );
                Alert TableSizeAlert = new Alert( Alert.AlertType.CONFIRMATION );
                TableSizeAlert.setTitle( "Table Size" );
                TableSizeAlert.setContentText( "Table size is : " + toSearchDe.getData().getList().getSize() );
                ImageView size = new ImageView( new Image( "file:Size.png" ) );
                size.setFitWidth( 50 );
                size.setFitHeight( 50 );
                TableSizeAlert.setGraphic( size );
                TableSizeAlert.show();
            } else {
                Alert error = new Alert( Alert.AlertType.ERROR );
                error.setContentText( "Select Department First " );
                error.setTitle( "Display" );
                error.show();
            }

        } );

        Button HashFunButton = new Button( "Display Hash Function" );
        HashFunButton.setPrefSize( 150, 45 );
        HashFunButton.setTextFill( Color.WHITE );
        HashFunButton.setOnAction( e -> {
            SecondPane.getChildren().clear();
            Alert HashFunAlert = new Alert( Alert.AlertType.INFORMATION );
            HashFunAlert.setResizable( true );
            HashFunAlert.setTitle( "Hash Function" );
            HashFunAlert.setContentText( "Hash function Used is Horner's method " + "\n\n" +
                    "h ={ ( s[0] * (31^L-1) ) + ... + ( (s[L-3]^2) * (31^2) ) + ( (s[L-2]^1) * (31^1)  ) + ( (s[L-1]) * (31^0) ) } % 10000" );
            HashFunAlert.show();
        } );

        Button AddStudentButton = new Button( "Add Student" );
        AddStudentButton.setPrefSize( 150, 45 );
        AddStudentButton.setTextFill( Color.WHITE );
        AddStudentButton.setOnAction( e -> {
            SecondPane.getChildren().clear();

            Label FullName = new Label( "Full Name" );
            FullName.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            FullName.setTextFill( Color.WHITE );
            FullName.setFont( Font.font( 15 ) );
            FullName.setLayoutX( 446 );
            FullName.setLayoutY( 168 );
            FullName.setPrefSize( 80, 15 );

            Label StudentId = new Label( "Student Id" );
            StudentId.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            StudentId.setTextFill( Color.WHITE );
            StudentId.setFont( Font.font( 15 ) );
            StudentId.setLayoutX( 446 );
            StudentId.setLayoutY( 227 );
            StudentId.setPrefSize( 80, 15 );

            Label GBA = new Label( "GBA" );
            GBA.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            GBA.setTextFill( Color.WHITE );
            GBA.setFont( Font.font( 15 ) );
            GBA.setLayoutX( 446 );
            GBA.setLayoutY( 273 );
            GBA.setPrefSize( 80, 15 );

            Label Gender = new Label( "Gender" );
            Gender.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            Gender.setTextFill( Color.WHITE );
            Gender.setFont( Font.font( 15 ) );
            Gender.setLayoutX( 446 );
            Gender.setLayoutY( 326 );
            Gender.setPrefSize( 80, 15 );

            TextField FullNameText = new TextField();
            FullNameText.setPromptText( "Ex: Rami Aburaya" );
            FullNameText.setPrefSize( 180, 25 );
            FullNameText.setLayoutX( 562 );
            FullNameText.setLayoutY( 164 );

            TextField StudentIdText = new TextField();
            StudentIdText.setPromptText( "Ex: 1201782" );
            StudentIdText.setPrefSize( 180, 25 );
            StudentIdText.setLayoutX( 562 );
            StudentIdText.setLayoutY( 223 );

            TextField GBAText = new TextField();
            GBAText.setPromptText( "Ex: 84.3" );
            GBAText.setPrefSize( 180, 25 );
            GBAText.setLayoutX( 562 );
            GBAText.setLayoutY( 269 );

            ComboBox<String> GenderText = new ComboBox<>();
            GenderText.setPromptText( "Select" );
            GenderText.setPrefSize( 180, 25 );
            GenderText.setLayoutX( 562 );
            GenderText.setLayoutY( 322 );
            GenderText.getItems().addAll( "M", "F" );

            Button addButton = new Button( "Add" );
            addButton.setPrefSize( 150, 25 );
            addButton.setLayoutX( 487 );
            addButton.setLayoutY( 469 );
            SecondPane.getChildren().addAll( addButton, GenderText, GBAText, StudentIdText, FullNameText, Gender, GBA, StudentId, FullName );
            addButton.setOnAction( x -> {
                if (GenderText.getValue() == null || GBAText.getText().equals( "" ) || StudentIdText.getText().equals( "" ) || FullNameText.getText().equals( "" ) || DepartmentList.getSelectionModel().getSelectedItem() == null) {
                    Alert Error = new Alert( Alert.AlertType.ERROR );
                    Error.setTitle( "Add" );
                    Error.setContentText( "All information is required" );
                    Error.show();
                } else {
                    if (DepartmentList.getSelectionModel().getSelectedItem() != null) {
                        String department = DepartmentList.getSelectionModel().getSelectedItem().toLowerCase().trim();
                        Node<Department> toSearchDe = Read.DepartmentList.Search( new Department( department ) );
                        HashNode<Student> toSearchSt = toSearchDe.getData().getList().Search( new Student( FullNameText.getText().toLowerCase().trim(), Integer.parseInt( StudentIdText.getText().trim() ), 0, null ) );
                        if (toSearchSt == null) {
                            toSearchDe.getData().getList().insert( new Student( FullNameText.getText().toLowerCase().trim(), Integer.parseInt( StudentIdText.getText().trim() ),
                                    Double.parseDouble( GBAText.getText().trim() ), GenderText.getValue() ) );
                            Alert Done = new Alert( Alert.AlertType.CONFIRMATION );
                            Done.setTitle( "Add" );
                            Done.setContentText( "Done" );
                            Done.show();
                        } else {
                            Alert Error = new Alert( Alert.AlertType.ERROR );
                            Error.setTitle( "Add" );
                            Error.setContentText( "Student Already Exist" );
                            Error.show();
                        }
                    } else {
                        Alert Error = new Alert( Alert.AlertType.ERROR );
                        Error.setTitle( "Add" );
                        Error.setContentText( "Select Department" );
                        Error.show();
                    }
                }

            } );
        } );

        Button SearchStudentButton = new Button( "Search for Student" );
        SearchStudentButton.setPrefSize( 150, 45 );
        SearchStudentButton.setTextFill( Color.WHITE );
        SearchStudentButton.setOnAction( e -> {
            SecondPane.getChildren().clear();

            Label FullName = new Label( "Full Name" );
            FullName.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            FullName.setTextFill( Color.WHITE );
            FullName.setFont( Font.font( 15 ) );
            FullName.setLayoutX( 446 );
            FullName.setLayoutY( 168 );
            FullName.setPrefSize( 80, 15 );

            Label StudentId = new Label( "Student Id" );
            StudentId.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            StudentId.setTextFill( Color.WHITE );
            StudentId.setFont( Font.font( 15 ) );
            StudentId.setLayoutX( 446 );
            StudentId.setLayoutY( 227 );
            StudentId.setPrefSize( 80, 15 );

            Label GBA = new Label( "GBA" );
            GBA.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            GBA.setTextFill( Color.WHITE );
            GBA.setFont( Font.font( 15 ) );
            GBA.setLayoutX( 446 );
            GBA.setLayoutY( 273 );
            GBA.setPrefSize( 80, 15 );

            Label Gender = new Label( "Gender" );
            Gender.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            Gender.setTextFill( Color.WHITE );
            Gender.setFont( Font.font( 15 ) );
            Gender.setLayoutX( 446 );
            Gender.setLayoutY( 326 );
            Gender.setPrefSize( 80, 15 );

            TextField FullNameText = new TextField();
            FullNameText.setPromptText( "Ex: Rami Aburaya" );
            FullNameText.setPrefSize( 180, 25 );
            FullNameText.setLayoutX( 562 );
            FullNameText.setLayoutY( 164 );

            TextField StudentIdText = new TextField();
            StudentIdText.setPromptText( "Ex: 1201782" );
            StudentIdText.setPrefSize( 180, 25 );
            StudentIdText.setLayoutX( 562 );
            StudentIdText.setLayoutY( 223 );

            TextField GBAText = new TextField();
            GBAText.setPromptText( "Ex: 84.3" );
            GBAText.setPrefSize( 180, 25 );
            GBAText.setLayoutX( 562 );
            GBAText.setLayoutY( 269 );
            GBAText.setEditable( false );

            ComboBox<String> GenderText = new ComboBox<>();
            GenderText.setPromptText( "Select" );
            GenderText.setPrefSize( 180, 25 );
            GenderText.setLayoutX( 562 );
            GenderText.setLayoutY( 322 );
            GenderText.getItems().addAll( "M", "F" );
            GenderText.setEditable( false );

            Button SearchButton = new Button( "Search" );
            SearchButton.setPrefSize( 150, 25 );
            SearchButton.setLayoutX( 487 );
            SearchButton.setLayoutY( 469 );
            SecondPane.getChildren().addAll( SearchButton, GenderText, GBAText, StudentIdText, FullNameText, Gender, GBA, StudentId, FullName );
            SearchButton.setOnAction( x -> {
                if (StudentIdText.getText().equals( "" ) || FullNameText.getText().equals( "" ) || DepartmentList.getSelectionModel().getSelectedItem() == null) {
                    Alert Error = new Alert( Alert.AlertType.ERROR );
                    Error.setTitle( "Add" );
                    Error.setContentText( "All information is required" );
                    Error.show();
                } else {
                    if (DepartmentList.getSelectionModel().getSelectedItem() != null) {
                        String department = DepartmentList.getSelectionModel().getSelectedItem().toLowerCase().trim();
                        Node<Department> toSearchDe = Read.DepartmentList.Search( new Department( department ) );
                        HashNode<Student> toSearchSt = toSearchDe.getData().getList().Search( new Student( FullNameText.getText().toLowerCase().trim(), Integer.parseInt( StudentIdText.getText().trim() ), 0, null ) );
                        if (toSearchSt != null) {
                            Alert Done = new Alert( Alert.AlertType.CONFIRMATION );
                            Done.setTitle( "Search" );
                            Done.setContentText( "found" );
                            Done.show();
                            Done.setOnCloseRequest( z -> {
                                FullNameText.setText( toSearchSt.getData().getFullName() );
                                StudentIdText.setText( String.valueOf( toSearchSt.getData().getStudentId() ) );
                                GBAText.setText( String.valueOf( toSearchSt.getData().getGBA() ) );
                                GenderText.setPromptText( toSearchSt.getData().getGender() );
                            } );
                        } else {

                            Alert Error = new Alert( Alert.AlertType.ERROR );
                            Error.setTitle( "Add" );
                            Error.setContentText( "Student not Exist" );
                            Error.show();
                        }
                    } else {
                        Alert Error = new Alert( Alert.AlertType.ERROR );
                        Error.setTitle( "Add" );
                        Error.setContentText( "Select Department" );
                        Error.show();
                    }
                }

            } );
        } );

        Button DeleteStudentButton = new Button( "Delete Student" );
        DeleteStudentButton.setPrefSize( 150, 45 );
        DeleteStudentButton.setTextFill( Color.WHITE );
        DeleteStudentButton.setOnAction( e -> {
            SecondPane.getChildren().clear();
            Label FullName = new Label( "Full Name" );
            FullName.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            FullName.setTextFill( Color.WHITE );
            FullName.setFont( Font.font( 15 ) );
            FullName.setLayoutX( 446 );
            FullName.setLayoutY( 168 );
            FullName.setPrefSize( 80, 15 );

            Label StudentId = new Label( "Student Id" );
            StudentId.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            StudentId.setTextFill( Color.WHITE );
            StudentId.setFont( Font.font( 15 ) );
            StudentId.setLayoutX( 446 );
            StudentId.setLayoutY( 227 );
            StudentId.setPrefSize( 80, 15 );

            Label GBA = new Label( "GBA" );
            GBA.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            GBA.setTextFill( Color.WHITE );
            GBA.setFont( Font.font( 15 ) );
            GBA.setLayoutX( 446 );
            GBA.setLayoutY( 273 );
            GBA.setPrefSize( 80, 15 );

            Label Gender = new Label( "Gender" );
            Gender.setStyle( "-fx-alignment: center;-fx-border-radius: 3px;-fx-border-color: #40DFEF" );
            Gender.setTextFill( Color.WHITE );
            Gender.setFont( Font.font( 15 ) );
            Gender.setLayoutX( 446 );
            Gender.setLayoutY( 326 );
            Gender.setPrefSize( 80, 15 );

            TextField FullNameText = new TextField();
            FullNameText.setPromptText( "Ex: Rami Aburaya" );
            FullNameText.setPrefSize( 180, 25 );
            FullNameText.setLayoutX( 562 );
            FullNameText.setLayoutY( 164 );

            TextField StudentIdText = new TextField();
            StudentIdText.setPromptText( "Ex: 1201782" );
            StudentIdText.setPrefSize( 180, 25 );
            StudentIdText.setLayoutX( 562 );
            StudentIdText.setLayoutY( 223 );

            TextField GBAText = new TextField();
            GBAText.setPromptText( "Ex: 84.3" );
            GBAText.setPrefSize( 180, 25 );
            GBAText.setLayoutX( 562 );
            GBAText.setLayoutY( 269 );
            GBAText.setEditable( false );

            ComboBox<String> GenderText = new ComboBox<>();
            GenderText.setPromptText( "Select" );
            GenderText.setPrefSize( 180, 25 );
            GenderText.setLayoutX( 562 );
            GenderText.setLayoutY( 322 );
            GenderText.getItems().addAll( "M", "F" );
            GenderText.setEditable( false );

            Button SearchButton = new Button( "Search" );
            SearchButton.setPrefSize( 150, 25 );
            SearchButton.setLayoutX( 602 );
            SearchButton.setLayoutY( 468 );

            Button DeleteButton = new Button( "Delete" );
            DeleteButton.setPrefSize( 150, 25 );
            DeleteButton.setLayoutX( 367 );
            DeleteButton.setLayoutY( 468 );
            DeleteButton.setDisable( true );
            SecondPane.getChildren().addAll( SearchButton, GenderText, GBAText, StudentIdText, FullNameText, Gender, GBA, StudentId, FullName, DeleteButton );
            SearchButton.setOnAction( x -> {
                if (StudentIdText.getText().equals( "" ) || FullNameText.getText().equals( "" ) || DepartmentList.getSelectionModel().getSelectedItem() == null) {
                    Alert Error = new Alert( Alert.AlertType.ERROR );
                    Error.setTitle( "Add" );
                    Error.setContentText( "All information is required" );
                    Error.show();
                } else {
                    if (DepartmentList.getSelectionModel().getSelectedItem() != null) {
                        String department = DepartmentList.getSelectionModel().getSelectedItem().toLowerCase().trim();
                        Node<Department> toSearchDe = Read.DepartmentList.Search( new Department( department ) );
                        HashNode<Student> toSearchSt = toSearchDe.getData().getList().Search( new Student( FullNameText.getText().toLowerCase().trim(), Integer.parseInt( StudentIdText.getText().trim() ), 0, null ) );
                        if (toSearchSt != null) {
                            Alert Done = new Alert( Alert.AlertType.CONFIRMATION );
                            Done.setTitle( "Search" );
                            Done.setContentText( "found" );
                            Done.show();
                            Done.setOnCloseRequest( z -> {
                                FullNameText.setText( toSearchSt.getData().getFullName() );
                                StudentIdText.setText( String.valueOf( toSearchSt.getData().getStudentId() ) );
                                GBAText.setText( String.valueOf( toSearchSt.getData().getGBA() ) );
                                GenderText.setPromptText( toSearchSt.getData().getGender() );
                                DeleteButton.setDisable( false );
                            } );
                            DeleteButton.setOnAction( a -> {
                                toSearchDe.getData().getList().delete( new Student( FullNameText.getText().toLowerCase().trim(),Integer.parseInt( StudentIdText.getText().trim() ), 0, null  ) );
                                if (toSearchSt.isDeleted()) {
                                    Alert DoneDeleted = new Alert( Alert.AlertType.CONFIRMATION );
                                    DoneDeleted.setTitle( "Delete" );
                                    DoneDeleted.setContentText( "Deleted" );
                                    DoneDeleted.show();
                                } else {
                                    Alert Error = new Alert( Alert.AlertType.ERROR );
                                    Error.setTitle( "Add" );
                                    Error.setContentText( "Try Again Plaese" );
                                    Error.show();
                                }
                            } );
                        } else {

                            Alert Error = new Alert( Alert.AlertType.ERROR );
                            Error.setTitle( "Add" );
                            Error.setContentText( "Student not Exist" );
                            Error.show();
                        }
                    } else {
                        Alert Error = new Alert( Alert.AlertType.ERROR );
                        Error.setTitle( "Add" );
                        Error.setContentText( "Select Department" );
                        Error.show();
                    }
                }

            } );
        } );

        Button SaveToFileButton = new Button( "Save to file" );
        SaveToFileButton.setPrefSize( 150, 45 );
        SaveToFileButton.setTextFill( Color.WHITE );
        SaveToFileButton.setOnAction( e -> SecondPane.getChildren().clear() );
        SaveToFileButton.setOnAction( e -> {
            String department = DepartmentList.getSelectionModel().getSelectedItem();
            if (department != null) {
                try {
                    Read.SaveToFile( department );
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                Alert Done = new Alert( Alert.AlertType.CONFIRMATION );
                Done.setTitle( "Add" );
                Done.setContentText( "Done" );
                Done.show();
            } else {
                Alert Error = new Alert( Alert.AlertType.ERROR );
                Error.setTitle( "Add" );
                Error.setContentText( "Select Department" );
                Error.show();
            }

        } );

        ButtonPane.getChildren().addAll( DisplayButton, TableSizeButton, HashFunButton, AddStudentButton, SearchStudentButton, DeleteStudentButton, SaveToFileButton );

        Button backButton = new Button( "Back" );
        backButton.setPrefSize( 150, 45 );
        backButton.setTextFill( Color.WHITE );
        backButton.setLayoutX( 1189 );
        backButton.setLayoutY( 650 );
        RootPane.getChildren().add( backButton );
        backButton.setOnAction( e -> {
            MainWindow user = new MainWindow();
            user.PrintMainWindow();
            StudentStage.close();
        } );

        RootPane.setBackground( background );
        Scene scene = new Scene( RootPane );
        scene.getStylesheets().add( Objects.requireNonNull( this.getClass().getResource( "Style.css" ) ).toExternalForm() );

        StudentStage.setMaximized( true );
        StudentStage.setScene( scene );
        StudentStage.setTitle( "Student Window" );
        StudentStage.getIcons().add( icon );
        StudentStage.show();
    }

}
