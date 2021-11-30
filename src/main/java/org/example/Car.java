package org.example;

public class Car {
    private String make;
    private String model;
    private String colour;
    private int year;
    private double mileage;
    private int seat;
    public Car() {
    }

    public Car(String make, String model, String colour, int year, double mileage) {
        this.make= make;
        this.model = model;
        this.colour = colour;
        this.year = year;
        this.mileage = mileage;
    }

    // getter methods
    public String getMake() { return this.make; }
    public String getModel() { return this.model; }
    public String getColour() { return this.colour; }
    public int getYear() { return this.year; }
    public double getMileage() { return this.mileage; }

    // override
    public boolean equals( Object otherObject ) {

        if (otherObject == null) { return false; }

        if (getClass() != otherObject.getClass()) { return false; }

        Car other = (Car) otherObject;

        return make.equals(other.make) &&
                model.equals(other.model) &&
                colour.equals(other.colour) &&
                year == other.year;
    }

    public String toString() {
        return getClass().getName() +
                "[make=" + make +
                ", model=" + model +
                ", colour=" + colour +
                ", year=" + year +
                ", mileage=" + mileage + "]"; }
}
