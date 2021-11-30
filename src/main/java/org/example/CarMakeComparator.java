package org.example;
import java.util.Comparator;

public class CarMakeComparator implements Comparator<Car>{
    public int compare(Car car1, Car car2)
    {
        return car1.getMake().compareTo(car2.getMake());

        // The Make of a car is of type String and the String class implements
        // a compareTo() method that returns -1, 0, or +1 as appropriate.
        // So, to compare string fields we simply use the compareTo() method
    }

}
