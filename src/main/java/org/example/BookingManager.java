package org.example;

import javax.xml.stream.Location;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }

    public Booking findBookingById(int id) {
        for (Booking b : bookingList) {
            if (id == b.getBookingId()) {
                return b;
            }
        }
        return null;
    }

    //TODO implement functionality as per specification
    public void addBooking(int passengerId, int vehicleId,
                           int year, int month, int day, int hours, int minutes,
                           double startLatitude, double endLatitude,
                           double startLongitude, double endLongitude) {
        String isoDateString = year + "-" + month + "-" + day + " " + hours + ":" + minutes;
        LocalDateTime ldt1 = LocalDateTime.parse(isoDateString, formatter);
        LocationGPS lgstart = new LocationGPS(startLatitude, startLongitude);
        LocationGPS lgend = new LocationGPS(endLatitude, endLongitude);

        if (checkAvailability(ldt1, vehicleId) != false) {
            // TODO: Cost
            bookingList.add(new Booking(passengerId, vehicleId, ldt1, lgstart, lgend, 0));
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


}
