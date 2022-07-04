package com.example.projectds3;

public class HashTable<T extends Comparable<T>> {
    private HashNode<Student>[] list;
    private int currentSize;

    public HashTable ( int Capacity ) {
        list = new HashNode[getPrimeCapacity( Capacity *2 )];
        initialization();
        currentSize = 0;
    }

    private int getPrimeCapacity ( int Capacity ) {
        while (!isPrime( Capacity )) {
            Capacity++;
        }
        return Capacity;
    }

    private boolean isPrime ( int number ) {
        if (number == 1 || number == 0) {
            return false;
        } else {
            for (int i = 2; i < number / 2; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initialization () {
        for (int i = 1; i < list.length; i++) {
            list[i] = new HashNode<>();
        }
    }

    public void insert ( Student data ) {
        int index = getIndex( data.getFullName().toLowerCase().trim() );
        for (int i = 1; ; i++) {
            if (list[index] != null) {
                if (list[index].isEmpty() || list[index].isDeleted()) {
                    list[index] = new HashNode( data );
                    currentSize++;
                    if (currentSize > list.length / 2) {
                        rehash();
                    }
                    break;
                }
            }
            index = (index + (i * i)) % list.length;
        }
    }

    public HashNode<Student> delete ( Student data ) {
        int location = getIndex( data.getFullName().toLowerCase().trim() );

        for (int i = 1; i < list.length && !list[location].isEmpty(); i++) {
            if (list[location] != null) {
                if (list[location].isFull() && list[location].getData().equals( data )) {
                    HashNode<Student> deleted = list[location];
                    list[location].Delete();
                    currentSize--;
                    if (currentSize < (list.length / 4)) {
                        shrink();
                    }
                    return deleted;
                }
            }
            location = (location + i * i) % list.length;
        }

        return null;

    }

    private void rehash () {
        HashTable<Student> newHash = new HashTable<>( list.length * 2 );

        for (HashNode<Student> s : list) {
            if (s != null) {
                if (s.isFull()) {
                    newHash.insert( s.getData() );
                }
            }
            list = newHash.list;
        }
    }

    private void shrink () {
        HashTable<Student> newHash = new HashTable<>( list.length / 8 );

        for (HashNode<Student> s : list) {
            if (s != null) {
                if (s.isFull()) {
                    newHash.insert( s.getData() );
                }
            }
            list = newHash.list;
        }
    }

    private int getIndex ( String key ) {
        return Math.abs( key.hashCode() % list.length );

    }

    public HashNode<Student> Search ( Student data ) {
        int location = getIndex( data.getFullName().toLowerCase().trim() );
        if (list[location] != null) {
            for (int i = 1; i < list.length && !list[location].isEmpty() && !list[location].isDeleted(); i++) {
                if (list[location].getData().equals( data )) {
                    return list[location];
                }
                location = (location + i * i) % list.length;
            }
        }
        return null;
    }

    public int getSize () {
        return list.length;
    }

    @Override
    public String toString () {
        String info = "";
        for (HashNode<Student> ob : list) {
            if (ob != null) {
                if (ob.isEmpty())
                    info += "null" + "\n";
                else if (ob.isFull()) {
                    info += ob + "\n";
                }
            }

        }
        return info;
    }

}

