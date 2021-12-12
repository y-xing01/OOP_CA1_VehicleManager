package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects

    public VehicleManager(String fileName) {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList)
            System.out.println(v.toString());
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                Vehicle.Type type = Vehicle.Type.valueOf(sc.next());  // vehicle type
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();


                if (type == Vehicle.Type.Van || type == Vehicle.Type.Truck)
                {
                    double loadSpace = sc.nextDouble();
                    // construct a Van object and add it to the passenger list
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                }
                else if (type == Vehicle.Type.Car || type == Vehicle.Type.FourByFour)
                {
                    int seats = sc.nextInt();
                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            seats));
                }
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }

    }

    //TODO add more functionality as per spec.
    public Vehicle findVehicleById(int id)
    {
        for(Vehicle v : vehicleList)
        {
            if(id == v.getId())
            {
                return v;
            }
        }
        return null;
    }

    public ArrayList<Vehicle> findVehicleByMake(String make)
    {
        ArrayList<Vehicle> vehiclesMatching = new ArrayList<>();
        for(Vehicle v : vehicleList)
        {
            if(v.getMake().equalsIgnoreCase(make))
            {
                vehiclesMatching.add(v);
            }
        }
        return vehiclesMatching;
    }

    public ArrayList<Vehicle> findAllVehicle()
    {
        for(Vehicle v : vehicleList)
        {
            return vehicleList;
        }
        return null;
    }

    public ArrayList<Vehicle> findVehicleByRegistration(String registration)
    {
        ArrayList<Vehicle> vehiclesRegistration = new ArrayList<>();
        for(Vehicle v : vehicleList)
        {
            if(v.getRegistration().equalsIgnoreCase(registration))
            {
                vehiclesRegistration.add(v);
            }
        }
        return vehiclesRegistration;
    }

    public ArrayList<Vehicle> findVehicleByType(String type)
    {
        ArrayList<Vehicle> vehiclesType = new ArrayList<>();
        for(Vehicle v : vehicleList)
        {
            if(v.getType().name().equalsIgnoreCase(type))
            {
                vehiclesType.add(v);
            }
        }
        return vehiclesType;
    }

    public void getVehiclesbySeats(int seats) {
        ArrayList<Car> tempList = new ArrayList<>();
        for(Vehicle v : vehicleList){
            if(v instanceof Car){
                if (((Car) v).getNumOfSeats() == seats) {
                    tempList.add((Car) v);
                }
            }
        }
        CarComparator comp = new CarComparator();
        Collections.sort(tempList,comp);
        System.out.println("Details of vehicles with " + seats + " seats: ");
        for(Car c: tempList)
        {
            System.out.println(c.toString());
        }
    }
}
