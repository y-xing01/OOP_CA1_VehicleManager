package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PassengerStoreTest {
    private final String passengerTxt = "passengers.txt";

    @Test
    public void findPassengerByNameTestSuccess() throws Exception{
        PassengerStore ps = new PassengerStore(passengerTxt);
        System.out.println("JUnit Test Find Passenger by Name Success");
        String name = "Joseph Bailey";
        Passenger p = ps.getPassengerByName(name);
        String expValue = "Passenger{id=103, name=Joseph Bailey, email=jobal@yahoo.com, phone=087-244322, location=LocationGPS{latitude=53.5656, longitude=-7.0012}}";
        String actValue = p.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findPassengerByNameTestFail() throws Exception{
        PassengerStore ps = new PassengerStore(passengerTxt);
        System.out.println("JUnit Test Find Passenger by Name Fail");
        String name = "Joseph Bailey";
        Passenger p = ps.getPassengerByName(name);
        String expValue = "103,Joseph Bailey,jobal@yahoo.com,087-244322,53.5656,-7.0012";
        String actValue = p.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findPassengerByIDTestSuccess() throws Exception{
        PassengerStore ps = new PassengerStore(passengerTxt);
        System.out.println("JUnit Test Find Passenger by ID Success");
        int id = 103;
        Passenger p = ps.getPassengerById(id);
        String expValue = "Passenger{id=103, name=Joseph Bailey, email=jobal@yahoo.com, phone=087-244322, location=LocationGPS{latitude=53.5656, longitude=-7.0012}}";
        String actValue = p.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findPassengerByIDTestFail() throws Exception{
        PassengerStore ps = new PassengerStore(passengerTxt);
        System.out.println("JUnit Test Find Passenger by ID Fail");
        int id = 103;
        Passenger p = ps.getPassengerById(id);
        String expValue = "103,Joseph Bailey,jobal@yahoo.com,087-244322,53.5656,-7.0012";
        String actValue = p.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void addPassenger() throws Exception{
        PassengerStore ps = new PassengerStore(passengerTxt);
        System.out.println("JUnit Test Add Passenger Success");
        String name = "Joe Joseph";
        String email = "jjoseph@email.com";
        String phone = "0831231212";
        double latitude = 22.22;
        double longitude = 33.33;
        Passenger p = ps.addPassenger(name, email, phone, latitude, longitude);
        String expValue = "Passenger{id=104, name=Joe Joseph, email=jjoseph@email.com, phone=0831231212, location=LocationGPS{latitude=22.22, longitude=33.33}}";
        String actValue = p.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void deletePassengerByIdSuccess() throws Exception{
        PassengerStore ps = new PassengerStore(passengerTxt);
        System.out.println("JUnit Test Delete Passenger by ID Success");
        int id = 103;
        boolean p = ps.deletePassengerByID(id);
        boolean expValue = true;
        boolean actValue = p;
        assertEquals(expValue,actValue);
    }
    @Test
    public void deletePassengerByIdFail() throws Exception{
        PassengerStore ps = new PassengerStore(passengerTxt);
        System.out.println("JUnit Test Delete Passenger by ID Fail");
        int id = 103;
        boolean p = ps.deletePassengerByID(id);
        boolean expValue = false;
        boolean actValue = p;
        assertEquals(expValue,actValue);
    }
    @Test
    public void deletePassengerByNameSuccess() throws Exception{
        PassengerStore ps = new PassengerStore(passengerTxt);
        System.out.println("JUnit Test Delete Passenger by ID Success");
        String name = "Joseph Bailey";
        boolean p = ps.deletePassengerByName(name);
        boolean expValue = true;
        boolean actValue = p;
        assertEquals(expValue,actValue);
    }
    @Test
    public void deletePassengerByNameFail() throws Exception{
        PassengerStore ps = new PassengerStore(passengerTxt);
        System.out.println("JUnit Test Delete Passenger by ID Fail");
        String name = "Joseph Bailey";
        boolean p = ps.deletePassengerByName(name);
        boolean expValue = false;
        boolean actValue = p;
        assertEquals(expValue,actValue);
    }
}