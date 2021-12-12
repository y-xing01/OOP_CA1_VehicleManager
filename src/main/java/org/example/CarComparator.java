package org.example;
import java.util.Comparator;

public class CarComparator implements Comparator<Car>{
    public int compare(Car car1, Car car2)
    {
        return car1.getRegistration().compareTo(car2.getRegistration());
    }
}
