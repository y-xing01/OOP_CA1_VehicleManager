package org.example;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {
    private final String fileName = "passengers.txt";
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

    public Passenger addPassenger(String name, String email, String phone, double latitude, double longitude) {
        Passenger newP = new Passenger(name, email, phone, latitude, longitude);
        if(passengerList.contains(newP)) {
            System.out.println("Passenger already exist.");
            return null;
        }
        else{
            for (Passenger p : passengerList) {
                if (p.getEmail().equals(newP.getEmail())) {
                    System.out.println("Email is used.");
                    return null;
                }
            }
        }
        passengerList.add(newP);
        return newP;
    }

    public void savePassengersToFile() {
        BufferedWriter bw = null;
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, false);
            bw = new BufferedWriter(fw);

            for (Passenger p : passengerList) {
                bw.write(p.getId() + "," + p.getName() + "," + p.getEmail() + "," + p.getPhone()
                        + "," + p.getLocation() + "\n");
            }

            System.out.println("File written Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
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

    public Passenger getPassengerByName(String name) {
        for(Passenger p : passengerList){
            if(name.equals(p.getName()))
                return p;
        }
        return null;
    }

    public boolean deletePassengerByID(int id) {
        for (Passenger p : passengerList) {
            if(id == p.getId())
                this.passengerList.remove(p.getId());
            return true;
        }
        return false;
    }

    public boolean deletePassengerByName(String name) {
        for (Passenger p : passengerList) {
            if(name == p.getName())
                this.passengerList.remove(p.getName());
            return true;
        }
        return false;
    }

    public boolean checkDuplicatePassenger(Passenger p){
        for(Passenger ps : passengerList){
            if(ps.getName() == p.getName() && ps.getEmail() == p.getEmail()){
                return false;
            }
        }
        return true;
    }
} // end class
