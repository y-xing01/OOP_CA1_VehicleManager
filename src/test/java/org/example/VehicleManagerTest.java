package org.example;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class VehicleManagerTest  {
    private final String vehicleTxt = "vehicles.txt";

    @Test
    public void findVehicleByIDTestSuccess() throws Exception{
        VehicleManager vm = new VehicleManager(vehicleTxt);
        System.out.println("JUnit Test Find Vehicle by ID Success");
        int id = 105;
        Vehicle v = vm.findVehicleById(id);
        String expValue = "Van{loadSpace=140.8} Van{id:105, type:Van, make=Fiat, model=Runround, milesPerKm=6.0, registration=151D987105, costPerMile=6.0, lastServicedDate=2020-12-15, mileage=100034, depotGPSLocation=LocationGPS{latitude=53.3456, longitude=-6.3421}}";
        String actValue = v.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findVehicleByIDTestFail() throws Exception{
        VehicleManager vm = new VehicleManager(vehicleTxt);
        System.out.println("JUnit Test Find Vehicle by ID Success");
        int id = 105;
        Vehicle v = vm.findVehicleById(id);
        String expValue = "105,Van,Fiat,Runround,6,151D987105,6.00,2020,12,15,100034,53.3456,-6.3421,140.8";
        String actValue = v.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findVehicleByMakeTestSuccess() throws Exception{
        VehicleManager vm = new VehicleManager(vehicleTxt);
        System.out.println("JUnit Test Find Vehicle by Make Success");
        String make = "Ford";
        ArrayList v = vm.findVehicleByMake(make);
        String expValue = "[Van{loadSpace=160.0} Van{id:106, type:Van, make=Ford, model=Transit, milesPerKm=4.0, registration=132LH234106, costPerMile=6.0, lastServicedDate=2021-03-30, mileage=106000, depotGPSLocation=LocationGPS{latitude=53.7654, longitude=-6.6235}}, Car{numOfSeats=5.0} Car{id:108, type:Car, make=Ford, model=Transit, milesPerKm=4.0, registration=172LH234106, costPerMile=6.0, lastServicedDate=2021-03-30, mileage=106000, depotGPSLocation=LocationGPS{latitude=53.7654, longitude=-6.6235}}]";
        String actValue = v.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findVehicleByMakeTestFail() throws Exception{
        VehicleManager vm = new VehicleManager(vehicleTxt);
        System.out.println("JUnit Test Find Vehicle by Make Fail");
        String make = "Ford";
        ArrayList v = vm.findVehicleByMake(make);
        String expValue = "Ford";
        String actValue = v.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findVehicleByRegistrationTestSuccess() throws Exception{
        VehicleManager vm = new VehicleManager(vehicleTxt);
        System.out.println("JUnit Test Find Vehicle by Registration Success");
        String reg = "151D987105";
        ArrayList v = vm.findVehicleByRegistration(reg);
        String expValue = "[Van{loadSpace=140.8} Van{id:105, type:Van, make=Fiat, model=Runround, milesPerKm=6.0, registration=151D987105, costPerMile=6.0, lastServicedDate=2020-12-15, mileage=100034, depotGPSLocation=LocationGPS{latitude=53.3456, longitude=-6.3421}}]";
        String actValue = v.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findVehicleByRegistrationTestFail() throws Exception{
        VehicleManager vm = new VehicleManager(vehicleTxt);
        System.out.println("JUnit Test Find Vehicle by Registration Fail");
        String reg = "151D987105";
        ArrayList v = vm.findVehicleByRegistration(reg);
        String expValue = "Fail";
        String actValue = v.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findVehicleByTypeTestSuccess() throws Exception{
        VehicleManager vm = new VehicleManager(vehicleTxt);
        System.out.println("JUnit Test Find Vehicle by Type Success");
        String type = "Car";
        ArrayList v = vm.findVehicleByType(type);
        String expValue = "[Car{numOfSeats=5.0} Car{id:108, type:Car, make=Ford, model=Transit, milesPerKm=4.0, registration=172LH234106, costPerMile=6.0, lastServicedDate=2021-03-30, mileage=106000, depotGPSLocation=LocationGPS{latitude=53.7654, longitude=-6.6235}}, Car{numOfSeats=5.0} Car{id:109, type:Car, make=Nisan, model=Urvan, milesPerKm=4.0, registration=101MN6538107, costPerMile=6.0, lastServicedDate=2021-05-24, mileage=126000, depotGPSLocation=LocationGPS{latitude=53.2543, longitude=-6.4444}}, Car{numOfSeats=5.0} Car{id:110, type:Car, make=Nisan, model=Urvan, milesPerKm=4.0, registration=100MN6538107, costPerMile=6.0, lastServicedDate=2021-05-24, mileage=126000, depotGPSLocation=LocationGPS{latitude=53.2543, longitude=-6.4444}}]";
        String actValue = v.toString();
        assertEquals(expValue,actValue);
    }
    @Test
    public void findVehicleByTypeTestFail() throws Exception{
        VehicleManager vm = new VehicleManager(vehicleTxt);
        System.out.println("JUnit Test Find Vehicle by Type Fail");
        String type = "Car";
        ArrayList v = vm.findVehicleByType(type);
        String expValue = "Fail";
        String actValue = v.toString();
        assertEquals(expValue,actValue);
    }
}