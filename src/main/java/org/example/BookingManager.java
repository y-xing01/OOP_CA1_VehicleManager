package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingManager {
    private final String fileName = "bookings.txt";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ArrayList<Booking> bookingList;

    // Constructor
    public BookingManager() {
        this.bookingList = new ArrayList<>();
        loadBookingsFromFile(fileName);
    }

    public void displayAllBookings() {
        Collections.sort(this.bookingList);
        for (Booking b : bookingList)
            System.out.println(b.toString());
    }

    public void loadBookingsFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int bookingId = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                LocalDateTime bookingDateTime = LocalDateTime.parse(sc.next(), formatter);
                double startLatitude = sc.nextDouble();
                double startLongitude = sc.nextDouble();
                LocationGPS startLocation = new LocationGPS(startLatitude, startLongitude);
                double endLatitude = sc.nextDouble();
                double endLongitude = sc.nextDouble();
                LocationGPS endLocation = new LocationGPS(endLatitude, endLongitude);
                double cost = sc.nextDouble();

                // construct a Booking object and add it to the bookings list
                bookingList.add(new Booking(bookingId, passengerId, vehicleId, bookingDateTime, startLocation, endLocation, cost));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    public void saveBookingsToFile() {
        BufferedWriter bw = null;
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, false);
            bw = new BufferedWriter(fw);

            for (Booking b : bookingList) {
                bw.write(b.getBookingId() + "," + b.getPassengerId() + "," + b.getVehicleId() + "," + b.getBookingDateTime().format(formatter)
                        + "," + b.getStartLocation().getLatitude() + "," + b.getStartLocation().getLongitude()
                        + "," + b.getEndLocation().getLatitude() + "," + b.getEndLocation().getLongitude()
                        + "," + b.getCost() + "\n");
            }

            System.out.println("Booking File updated");
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

    public double calculateCost(Vehicle.Type type, double distance) {
        double total = 0;
        if(type == Vehicle.Type.Car) {
            total = distance*2.00;
        }
        else if(type == Vehicle.Type.FourByFour){
            total = distance*4.00;
        }
        else if(type == Vehicle.Type.Van) {
            total = distance*6.00;
        }
        else if(type == Vehicle.Type.Truck){
            total = distance*10.00;
        }
        else{
            System.out.println("Invalid type of Vehicle");
            total = 0;
        }
        return total;
    }

    public void addBooking(int passengerId, int vehicleId,
                           int year, int month, int day, int hours, int minutes,
                           double startLatitude, double endLatitude,
                           double startLongitude, double endLongitude,
                           Vehicle.Type type, int mileage) {
        String m;
        if(month > 0 && month < 10)
        {
            m = String.valueOf(month);
            m="0"+m;
        }
        else{
            m = String.valueOf(month);
        }

        String d;
        if(day > 0 && day < 10)
        {
            d = String.valueOf(day);
            d ="0"+d;
        }
        else{
            d = String.valueOf(day);
        }

        String h;
        if(hours > 0 && hours < 10)
        {
            h = String.valueOf(hours);
            h ="0"+h;
        }
        else{
            h = String.valueOf(hours);
        }

        String min;
        if(minutes > 0 && minutes < 10)
        {
            min = String.valueOf(minutes);
            min ="0"+min;
        }
        else{
            min = String.valueOf(minutes);
        }

        String toDateString = year + "-" + m + "-" + d + " " + h + ":" + min;
        LocalDateTime lDateTime = LocalDateTime.parse(toDateString, formatter);
        LocationGPS lgstart = new LocationGPS(startLatitude, startLongitude);
        LocationGPS lgend = new LocationGPS(endLatitude, endLongitude);

        if (checkAvailability(lDateTime, vehicleId) != false) {
            bookingList.add(new Booking(passengerId, vehicleId, lDateTime, lgstart, lgend, calculateCost(type,mileage)));
            System.out.println("Booking success.");
        } else {
            System.out.println("Booking Failed , vehicle have been booked!");
        }
    }

    public boolean checkAvailability(LocalDateTime bookDate, int vehicleid) {
        for (int i = 0; i < this.bookingList.size(); i++) {
            if (bookDate.equals(this.bookingList.get(i).getBookingDateTime())) {
                if (vehicleid == this.bookingList.get(i).getVehicleId()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayFutureBookings(){
        ArrayList<Booking> tempList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for(Booking b: bookingList){
            if(b.getBookingDateTime().isAfter(now)){
                tempList.add(b);
            }
        }
        BookingComparator comp = new BookingComparator();
        Collections.sort(tempList,comp);
        for(Booking b: tempList)
        {
            System.out.println(b.toString());
        }
    }

    public void editBooking(int id) throws IOException{
        Scanner kb = new Scanner(System.in);
        Booking b = null;
        for(Booking b1 : bookingList){
            if(b1.getBookingId() == id){
                b = b1;
                final String EDIT_MENU = "\nEDIT BOOKING MENU\n"
                        + "1. EDIT PASSENGER ID\n"
                        + "2. EDIT VEHICLE ID\n"
                        + "3. EDIT DATE\n"
                        + "4. EDIT COORDINATE\n"
                        + "5. EXIT\n"
                        + "Enter Option [1-5]";

                final int EDIT_PASSENGER_ID = 1;
                final int EDIT_VEHICLE_ID = 2;
                final int EDIT_DATE = 3;
                final int EDIT_COORDINATE = 4;
                final int EXIT = 5;

                int option = 0;
                do{
                    System.out.println("\n" + EDIT_MENU);
                    try{
                        String usersInput = kb.nextLine();
                        option = Integer.parseInt(usersInput);
                        switch (option){
                            case EDIT_PASSENGER_ID:
                                System.out.println("Enter new Passenger ID");
                                int newPassengerId = kb.nextInt();
                                if(b1.getBookingId() == newPassengerId)
                                    b1.setPassengerId(newPassengerId);
                                else
                                    System.out.println("Passenger ID not found");
                                kb.nextLine();
                                break;
                            case EDIT_VEHICLE_ID:
                                System.out.println("Enter new Vehicle ID");
                                int newVehicleId = kb.nextInt();
                                b1.setVehicleId(newVehicleId);
                                kb.nextLine();
                                break;
                            case EDIT_DATE:
                                System.out.println("Enter new Year");
                                int year = kb.nextInt();
                                System.out.println("Enter new Month");
                                int month = kb.nextInt();
                                System.out.println("Enter new day");
                                int day = kb.nextInt();
                                System.out.println("Enter new Hours (0-23)");
                                int hour = kb.nextInt();
                                System.out.println("Enter new Minutes (0-59)");
                                int minutes = kb.nextInt();
                                String m;
                                if(month > 0 && month < 10)
                                {
                                    m = String.valueOf(month);
                                    m="0"+m;
                                }
                                else{
                                    m = String.valueOf(month);
                                }

                                String d;
                                if(day > 0 && day < 10)
                                {
                                    d = String.valueOf(day);
                                    d ="0"+d;
                                }
                                else{
                                    d = String.valueOf(day);
                                }

                                String h;
                                if(hour > 0 && hour < 10)
                                {
                                    h = String.valueOf(hour);
                                    h ="0"+h;
                                }
                                else{
                                    h = String.valueOf(hour);
                                }

                                String min;
                                if(minutes > 0 && minutes < 10)
                                {
                                    min = String.valueOf(minutes);
                                    min ="0"+min;
                                }
                                else{
                                    min = String.valueOf(minutes);
                                }
                                String isoDateString = year + "-" + m + "-" + d + " " + h + ":" + min;
                                LocalDateTime ldt1 = LocalDateTime.parse(isoDateString, formatter);
                                b1.setBookingDateTime(ldt1);
                                kb.nextLine();
                                break;
                            case EDIT_COORDINATE:
                                System.out.println("Enter new start Latitude");
                                double newStartLatitude = kb.nextDouble();
                                System.out.println("Enter new start Longitude");
                                double newStartLongitude = kb.nextDouble();
                                System.out.println("Enter new end Latitude");
                                double newEndLatitude = kb.nextDouble();
                                System.out.println("Enter new end Longitude");
                                double newEndLongitude = kb.nextDouble();
                                LocationGPS lgstart = new LocationGPS(newStartLatitude, newStartLongitude);
                                LocationGPS lgend = new LocationGPS(newEndLatitude, newEndLongitude);
                                b1.setStartLocation(lgstart);
                                b1.setEndLocation(lgend);
                                kb.nextLine();
                                break;
                            case EXIT:
                                System.out.println("Exit Menu option chosen");
                                break;
                            default:
                                System.out.print("Invalid option - please enter number in range");
                                break;
                        }
                    } catch(InputMismatchException | NumberFormatException e) {
                        System.out.println("Invalid option - please enter number in range");
                    }
                } while (option != EXIT);
            }
        }
        if(b != null){
            System.out.println(b);
        }
        else{
            System.out.println("Booking ID is not found");
        }
    }

    public void displayBookingByID(int id){
        Booking b = null;
        for(Booking b1: bookingList){
            if(b1.getBookingId() == id){
                b = b1;
                break;
            }
        }
        if(b != null){
            System.out.println(b);
        }
        else {
            System.out.println("Booking with this ID is not found");
        }
    }

    public void displayBookingByPassengerID(int passengerId){
        ArrayList<Booking> tempList = new ArrayList<>();
        for(Booking b: bookingList){
            if(b.getPassengerId() == passengerId){
                tempList.add(b);
            }
        }
        BookingComparator comp = new BookingComparator();
        Collections.sort(tempList,comp);
        if(tempList.size() != 0)
        {
            for(Booking b: tempList)
            {
                System.out.println(b.toString());
            }
        }
        else{
            System.out.println("No Booking made by this passenger");
        }

    }

    public double averageLength() {
        double total = 0;
        for(Booking b : bookingList) {
            total += b.getCost();
        }
        return total/bookingList.size();
    }

    public boolean deleteBookingByID (int id) {

        for (Booking b : bookingList) {
            this.bookingList.removeIf(bookingID -> bookingID.getBookingId() == id);
            return true;
        }
        return false;
    }
}
