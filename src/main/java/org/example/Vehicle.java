package org.example;

import java.time.LocalDate;

public abstract class Vehicle {
    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");  // get access to the id Generator

    private int id;
    private Type type;
    private String make;
    private String model;
    private double milesPerKwH;
    private String registration;
    private double costPerMile;
    private LocalDate lastServicedDate;
    private int mileage; // mileage recorded at last service
    private LocationGPS depotGPSLocation;

    // Constructor called when a new Vehicle is being created.
    // No vehicle id is passed in as an argument,
    // so the constructor will autogenerate the id.
    //
    public Vehicle(Type type, String make, String model, double milesPerKwH,
                   String registration, double costPerMile,
                   int year, int month, int day,
                   int mileage, double latitude, double longitude) {
        this.id = idGenerator.getNextId();  // auto generated id (new for each run of the system)
        this.type = type;
        this.make = make;
        this.model = model;
        this.milesPerKwH = milesPerKwH;
        this.registration = registration;
        this.costPerMile = costPerMile;
        this.lastServicedDate = LocalDate.of(year, month, day);
        this.mileage = mileage;
        this.depotGPSLocation = new LocationGPS(latitude, longitude);
    }

    // Constructor to create a Vehicle object, when the id is available.
    // So this is called to construct a Vehicle when the vehicle record is read from
    // the vehicles.txt file, and the id is known.
    //
    public Vehicle(int id, Type type, String make, String model, double milesPerKwH,
                   String registration, double costPerMile,
                   int year, int month, int day,
                   int mileage, double latitude, double longitude) {
        this.id = id;
        this.type = type;
        this.make = make;
        this.model = model;
        this.milesPerKwH = milesPerKwH;
        this.registration = registration;
        this.costPerMile = costPerMile;
        this.lastServicedDate = LocalDate.of(year, month, day);
        this.mileage = mileage;
        this.depotGPSLocation = new LocationGPS(latitude, longitude);
    }

    public int getId() {
        return id;
    }

    private void setId() {this.id = id;}

    ; // prevents the id from being set (as it should only come from autogenerator)

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getMilesPerKm() {
        return milesPerKwH;
    }

    public void setMilesPerKm(double milesPerKm) {
        this.milesPerKwH = milesPerKm;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public double getCostPerMile() {
        return costPerMile;
    }

    public void setCostPerMile(double costPerMile) {
        this.costPerMile = costPerMile;
    }

    public LocalDate getLastServicedDate() {
        return lastServicedDate;
    }

    public void setLastServicedDate(LocalDate lastServicedDate) {
        this.lastServicedDate = lastServicedDate;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public LocationGPS getDepotGPSLocation() {
        return depotGPSLocation;
    }

    public void setDepotGPSLocation(double latitude, double longitude) {
        new LocationGPS(latitude, longitude);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean equals(Object otherObject) {

        if (otherObject == null) {
            return false;
        }

        if (getClass() != otherObject.getClass()) {
            return false;
        }

        Vehicle other = (Vehicle) otherObject;

        return id == other.id &&
                type.equals(other.type) &&
                make.equals(other.make) &&
                model.equals(other.model) &&
                registration == other.registration;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + "id:" + this.id + ", type:" + type + ", make=" + make + ", model=" + model
                + ", milesPerKm=" + milesPerKwH + ", registration=" + registration
                + ", costPerMile=" + costPerMile + ", lastServicedDate="
                + lastServicedDate + ", mileage=" + mileage + ", depotGPSLocation="
                + depotGPSLocation + '}';
    }


    public int compareTo(Vehicle o) {
        if (this.getId() > o.getId()) {
            return -1;
        } else if (this.getId() < o.getId()) {
            return 1;
        } else {
            return 0;
        }
    }

    public enum Type {
        Car, Van, FourByFour, Truck
    }
}
