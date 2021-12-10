package org.example;

// Car class to represent 4x4 and Cars
//
public class Car extends Vehicle
{
    private double numOfSeats;

    public Car(String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int numOfSeats)
    {
        // call superclass constructor to initialize the fields defined in Vehicle
        super(type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.numOfSeats = numOfSeats;
    }

    // Constructor version to be used to recreate a Van that was read from file.
    // It will have already been allocated an id.
    //
    public Car(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int numOfSeats)
    {
        // call superclass constructor to initialize the fields defined in Vehicle
        super(id,type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.numOfSeats = numOfSeats;
    }

    public double getNumOfSeats() {
        return numOfSeats;
    }
    public void setNumOfSeats(double numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    @Override
    public String toString() {
        return "Van{" +
                "numOfSeats=" + numOfSeats +
                "} " + super.toString();
    }
}
