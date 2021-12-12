package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BookingManager {
    private final String fileName = "bookings.txt";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ArrayList<Booking> bookingList;
    private final ArrayList<Passenger> passengerList = new ArrayList<>();

    // Constructor
    public BookingManager() {
        this.bookingList = new ArrayList<>();
        loadBookingsFromFile(fileName);
    }

    public void displayAllBookings() {
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

    public Booking findBookingByID(int id) {
        for (Booking b : bookingList) {
            if (id == b.getBookingId()) {
                return b;
            }
        }
        return null;
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

        String isoDateString = year + "-" + m + "-" + d + " " + h + ":" + min;
        LocalDateTime ldt1 = LocalDateTime.parse(isoDateString, formatter);
        LocationGPS lgstart = new LocationGPS(startLatitude, startLongitude);
        LocationGPS lgend = new LocationGPS(endLatitude, endLongitude);

        if (checkAvailability(ldt1, vehicleId) != false) {
            // TODO: Cost
            bookingList.add(new Booking(passengerId, vehicleId, ldt1, lgstart, lgend, calculateCost(type,mileage)));
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

    public void displayBookingByID(int id){
        for(Booking b: bookingList){
            if(b.getBookingId() == id){
                System.out.println(b.toString());
            }
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
        for(Booking b: tempList)
        {
            System.out.println(b.toString());
        }
    }

    public double averageLength() {
        double total = 0;
        for(Booking b : bookingList) {
            total += b.getCost();
        }
        return total/bookingList.size();
    }

    public boolean deleteBookingByID(int id) {
        for (Booking b : bookingList) {
            if(id == b.getBookingId())
            this.bookingList.remove(b.getBookingId());
            return true;
        }
        return false;
    }

    public boolean deleteBookingByName (String name) {
        for (Passenger p : passengerList) {
            this.passengerList.removeIf(pName -> pName.getName() == name);
            return true;
        }
        return false;
    }


}
