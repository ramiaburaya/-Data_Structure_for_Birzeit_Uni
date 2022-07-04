package com.example.projectds3;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Read {
    static AVLTree<Department> DepartmentList = new AVLTree<>();
    static FileChooser fileChooser = new FileChooser();
    static List<Department> listForListView = new ArrayList<>();
    private File SelectedFile = fileChooser.showOpenDialog( MainWindow.MainWindowStage );
    private String Department;

    public void ReadDepartments () {
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter( "Text Files", "*.txt" ) );
        try {

            Scanner input = new Scanner( SelectedFile );
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] lineInfo = line.split( "/" );
                Department = lineInfo[0].trim();
                String Students = lineInfo[1].trim();
                File StudentsFile = new File( SelectedFile.getParentFile() + "\\" + Students );
                if (DepartmentList.Search( new Department( Department ) ) == null) {
                    DepartmentList.insert( new Department( Department ) );
                    if (!listForListView.contains( new Department( Department ) )) {
                        listForListView.add( new Department( Department ) );
                    }
                    if (StudentsFile.exists()) {
                        ReadStudents( StudentsFile );
                    } else {
                        continue;
                    }
                }


            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println( "An error occurred." );
        }
    }

    public void ReadStudents ( File StudentsFile ) throws FileNotFoundException {
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter( "Text Files", "*.txt" ) );
        Scanner input = new Scanner( StudentsFile );
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] lineInfo = line.split( "/" );
            String fullName = lineInfo[0].trim();
            String StudentId = lineInfo[1].trim();
            String GBA = lineInfo[2].trim();
            String Gender = lineInfo[3].trim();
            if (DepartmentList.Search( new Department( Department.toLowerCase().trim() ) ).getData().getList().Search( new Student( fullName, Integer.parseInt( StudentId ), 0, null ) ) == null) {
                DepartmentList.Search( new Department( Department.toLowerCase().trim() ) ).getData().getList().insert( new Student( fullName, Integer.parseInt( StudentId ), Double.parseDouble( GBA ), Gender ) );
            }
        }
        input.close();
    }

    public static void SaveToFile ( String Department ) throws IOException {
        FileWriter fWriter = new FileWriter( "C:\\Users\\rami0\\Desktop\\Java\\ProjectDS3\\output\\" + Department + ".txt" );

        try {

            fWriter.write( String.valueOf( DepartmentList.Search( new Department( Department ) ).getData().getList() ) );
            fWriter.close();

        } catch (IOException e) {
            System.out.print( e.getMessage() );
        }

    }

}
