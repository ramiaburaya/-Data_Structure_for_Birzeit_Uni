package com.example.projectds3;

public class HashNode<T extends Comparable<T>> {
    private T data;
    private String flag; // E empty , F full, D deleted

    public HashNode ( T data ) {
        this.data = data;
        flag = "F";
    }

    public HashNode () {
        flag = "E";
    }

    public boolean isFull () {
        return flag.equals( "F" );
    }

    public boolean isEmpty () {
        return flag.equals( "E" );
    }

    public boolean isDeleted () {
        return flag.equals( "D" );
    }

    public void Delete () {
        this.setFlag( "D" );
    }

    @Override
    public String toString () {
        return data + "";
    }

    public T getData () {
        return data;
    }

    public void setData ( T data ) {
        this.data = data;
    }

    public String getFlag () {
        return flag;
    }

    public void setFlag ( String flag ) {
        this.flag = flag;
    }

}
