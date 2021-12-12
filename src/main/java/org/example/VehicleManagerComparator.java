package org.example;

import java.util.Comparator;

public class VehicleManagerComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle v1, Vehicle v2) {
        if(v1.getId() > v2.getId()){
            return 1;
        }else if(v2.getId() > v1.getId()){
            return -1;
        }else{
            return 0;
        }
    }
}
