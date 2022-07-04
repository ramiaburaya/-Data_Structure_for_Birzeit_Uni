package com.example.projectds3;

public class Student implements Comparable<Student> {
    private String FullName;
    private int StudentId;
    private double GBA;
    private String Gender; // M: Male, F : Female

    public Student ( String FullName, int StudentId, double GBA, String Gender ) {
        this.FullName = FullName;
        this.StudentId = StudentId;
        this.GBA = GBA;
        this.Gender = Gender;
    }

    public String getFullName () {
        return FullName;
    }

    public void setFullName ( String fullName ) {
        FullName = fullName;
    }

    public int getStudentId () {
        return StudentId;
    }

    public void setStudentId ( int studentId ) {
        StudentId = studentId;
    }

    public double getGBA () {
        return GBA;
    }

    public void setGBA ( double GBA ) {
        this.GBA = GBA;
    }

    public String getGender () {
        return Gender;
    }

    public void setGender ( String gender ) {
        Gender = gender;
    }

    @Override
    public String toString () {
        return "FullName: '" + FullName + '\'' +
                ", StudentId: " + StudentId +
                ", GBA: " + GBA +
                ", Gender: '" + Gender + '\'' ;
    }

    @Override
    public boolean equals ( Object o ) {

        return (this.getFullName().equalsIgnoreCase( ((Student) o).getFullName() ) && this.getStudentId() == ((Student) o).getStudentId());
    }

    @Override
    public int hashCode () {
        char[] s = FullName.toLowerCase().toCharArray(); // needed lowercase for Delete and search for student
        int hash = 0;
        for (char c : s) {
            hash = (c + (31 * hash));
        }
        //System.out.println(Math.abs( Objects.hash( FullName.toLowerCase() )  ));
        return Math.abs( hash % 10000 );
    }

    @Override
    public int compareTo ( Student o ) {
        return this.getFullName().compareToIgnoreCase( o.getFullName() );
    }
}
