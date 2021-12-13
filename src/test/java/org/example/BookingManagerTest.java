package org.example;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class BookingManagerTest {
    private final String bookingTxt = "bookings.txt";

    @Test
    public void deleteBookingByIdSuccess() throws Exception{
        BookingManager bm = new BookingManager();

        System.out.println("JUnit Test Delete Booking by ID Success");
        int id = 103;
        boolean b = bm.deleteBookingByID(id);
        boolean expValue = true;
        boolean actValue = b;
        assertEquals(expValue,actValue);
    }
    @Test
    public void deleteBookingByIdFail() throws Exception{
        BookingManager bm = new BookingManager();

        System.out.println("JUnit Test Delete Booking by ID Fail");
        int id = 103;
        boolean b = bm.deleteBookingByID(id);
        boolean expValue = false;
        boolean actValue = b;
        assertEquals(expValue,actValue);
    }
    @Test
    public void checkAvailabilitySuccess() throws Exception{
        BookingManager bm = new BookingManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("JUnit Test Check Availability Success");
        LocalDateTime lDateTime = LocalDateTime.parse("2021-12-27 18:20", formatter);
        boolean b = bm.checkAvailability(lDateTime, 106);
        boolean expValue = false;
        boolean actValue = b;
        assertEquals(expValue,actValue);
    }
    @Test
    public void checkAvailabilityFail() throws Exception{
        BookingManager bm = new BookingManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("JUnit Test Check Availability Fail");
        LocalDateTime lDateTime = LocalDateTime.parse("2021-12-27 18:20", formatter);
        boolean b = bm.checkAvailability(lDateTime, 106);
        boolean expValue = true;
        boolean actValue = b;
        assertEquals(expValue,actValue);
    }
}