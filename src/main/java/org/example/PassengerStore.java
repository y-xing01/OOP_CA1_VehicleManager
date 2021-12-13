package org.example;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class PassengerStore{
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
        Collections.sort(this.passengerList);
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
                if (checkDuplicatePassenger(p)) {
                    System.out.println("Name and Email is used.");
                    return null;
                }
            }
        }
        passengerList.add(newP);
        return newP;
    }

    public void editPassenger(int id) throws IOException{
        Scanner kb = new Scanner(System.in);
        Passenger p = null;
        for(Passenger p1 : passengerList){
            if(p1.getId() == id){
                p = p1;
                final String EDIT_MENU = "\nEDIT PASSENGER MENU\n"
                        + "1. EDIT PASSENGER NAME\n"
                        + "2. EDIT PASSENGER LOCATION\n"
                        + "3. EDIT PASSENGER EMAIL\n"
                        + "4. EDIT PASSENGER PHONE\n"
                        + "5. EXIT\n"
                        + "Enter Option [1-5]";

                final int EDIT_PASSENGER_NAME = 1;
                final int EDIT_PASSENGER_LOCATION = 2;
                final int EDIT_PASSENGER_EMAIL = 3;
                final int EDIT_PASSENGER_PHONE = 4;
                final int EXIT = 5;

                int option = 0;
                do{
                    System.out.println("\n" + EDIT_MENU);
                    try{
                        String usersInput = kb.nextLine();
                        option = Integer.parseInt(usersInput);
                        switch (option){
                            case EDIT_PASSENGER_NAME:
                            System.out.println("Enter new Passenger Name");
                                String newPassengerName = kb.nextLine();
                                p1.setName(newPassengerName);
                                break;
                            case EDIT_PASSENGER_LOCATION:
                                System.out.println("Enter new Passenger Latitude");
                                double newLatitude = kb.nextDouble();
                                System.out.println("Enter new Passenger Longitude");
                                double newLongitude = kb.nextDouble();
                                p1.setLocation(newLatitude, newLongitude);
                                kb.nextLine();
                                break;
                            case EDIT_PASSENGER_EMAIL:
                                System.out.println("Enter new Passenger Email");
                                String newEmail = kb.nextLine();
                                p1.setEmail(newEmail);
                                break;
                            case EDIT_PASSENGER_PHONE:
                                System.out.println("Enter new Passenger Phone");
                                String newPhone = kb.nextLine();
                                p1.setPhone(newPhone);
                                break;
                        }
                    } catch(InputMismatchException | NumberFormatException e) {
                        System.out.println("Invalid option - please enter number in range");
                    }
                } while (option != EXIT);

            }
        }
        if(p != null){
            System.out.println(p);
        }
        else{
            System.out.println("Passenger ID is not found");
        }
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
                        + "," + p.getLocation().getLatitude() + "," + p.getLocation().getLongitude() + "\n");
            }

            System.out.println("Passenger File updated");
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

    public Passenger getPassengerById(int id) {
        for(Passenger p : passengerList){
            if(id == p.getId())
                return p;
        }
        return null;
    }

    public Passenger getPassengerByName(String name) {
        for(Passenger p : passengerList){
            if(name.equalsIgnoreCase(p.getName()))
                return p;
        }
        return null;
    }

    public boolean deletePassengerByID (int id) {
        for (Passenger p : passengerList) {
            this.passengerList.removeIf(passengerId -> passengerId.getId() == id);
            return true;
        }
        return false;
    }

    public boolean deletePassengerByName (String name) {
        for (Passenger p : passengerList) {
            this.passengerList.removeIf(pName -> pName.getName() == name);
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
