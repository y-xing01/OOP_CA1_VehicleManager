package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }

    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

//BOOKING .
//find by id
//is passenger store find passenger by id  (passengerStore.findPassengerById(passengerId) != null)
//    public addBooking(String passengerId, String VehicleId, ...)
//    {
//        if(passengerStore.findPassengerById(passengerId != null))
//        {
//            //checking valid passenger Id
//        }
//        //check valid vehicle Id
//        //convert year, month, week, day, hour, minute, second into local date time
//        //create booking object
//        //bookingList.add(bookingcreated)
//    }

    public Passenger addPassenger(String name, String email, String phone, double latitude, double longitude) {
        Passenger newP = new Passenger(name, email, phone, latitude, longitude);
        for (Passenger p : passengerList) {
            if (p.equals(newP)) {
                System.out.println("Same passenger input");
                return null;
            }
        }
        passengerList.add(newP);
        return newP;

    }

    public Passenger findPassengerByName(String name) {
        for (Passenger p : passengerList) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println("Passenger with name " + name + " is found.");
            } else {
                System.out.println("Passenger name not found");
            }
        }
        return null;
    }

    public Passenger getPassengerById(int id) {
        for(Passenger p : passengerList){
            if(id == p.getId())
                return p;
        }
        return null;
    }

    // TODO - see functional spec for details of code to add

} // end class
