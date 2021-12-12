package org.example;

import java.util.Comparator;

public class PassengerStoreComparator implements Comparator<Passenger> {
    @Override
    public int compare(Passenger p1, Passenger p2) {
        if(p1.getId() > p2.getId()){
            return 1;
        }else if(p2.getId() > p1.getId()){
            return -1;
        }else{
            return 0;
        }
    }
}