package com.example.projectds3;

public class Department implements Comparable<Department> {
    private String Name;
    private HashTable<Student> list;

    public HashTable<Student> getList () {
        return list;
    }

    public void setList ( HashTable<Student> list ) {
        this.list = list;
    }

    public Department ( String name ) {
        Name = name;
        list = new HashTable<>( 5 );
    }

    public String getName () {
        return Name;
    }

    public void setName ( String name ) {
        Name = name;
    }

    @Override
    public String toString () {
        return Name;
    }

    @Override
    public int compareTo ( Department o ) {
        return this.getName().compareToIgnoreCase( o.getName() );
    }
}
