package org.example;

import java.util.ArrayList;

/**
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 *
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 *
 * "next-id-store.txt" contains one number ("201"),     which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */

public class App
{
    public static void main(String[] args)
    {
        System.out.println("\nWelcome to the VEHICLE BOOKINGS MANAGEMENT SYSTEM - CA1 for OOP\n");

        // create PassengerStore and load it with passenger records from text file
        PassengerStore passengerStore = new PassengerStore("passengers.txt");
        System.out.println("List of all passengers:");
        passengerStore.displayAllPassengers();

        VehicleManager vehicleManager = new VehicleManager("vehicles.txt");
        System.out.println("\nList of all Vehicles:");
        vehicleManager.displayAllVehicles();

        Vehicle v = vehicleManager.findVehicleById(105);
        System.out.println("\nVehicle details from findVechicleById : ");
        if(v != null)
            System.out.println(v);
        else
            System.out.println("No vehicle with that id found");

        ArrayList<Vehicle> vehiclesMatching = vehicleManager.findVehicleByMake("Ford");
        System.out.println("\nVehicles that match make 'Ford' : ");
        for(Vehicle v1 : vehiclesMatching)
        {
            System.out.println(v1);
        }

        ArrayList<Vehicle> vehiclesRegistration = vehicleManager.findVehicleByRegistration("151D987105");
        System.out.println("\n Vehicles that match registration number '151D987105' : ");
        for(Vehicle v1 : vehiclesRegistration)
        {
            System.out.println(v1);
        }

        ArrayList<Vehicle> vehiclesType = vehicleManager.findVehicleByType("Van");
        System.out.println("\nVehicles that match make 'Van'");
        for(Vehicle v1 : vehiclesType)
        {
            System.out.println(v1);
        }

        ArrayList<Vehicle> allVehicles = vehicleManager.findAllVehicle();
        System.out.println("\nFind all Vehicles");
        for(Vehicle v1 : allVehicles)
        {
            System.out.println(v1);
        }

        System.out.println("\nProgram exiting... Goodbye");
    }
}
