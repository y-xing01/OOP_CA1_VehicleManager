package org.example;

import java.io.IOException;
import java.util.*;

public class AppMenu {
    // Components (objects) used in this App
    PassengerStore passengerStore;  // encapsulates access to list of Passengers
    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
    BookingManager bookingManager;  // deals with all bookings

    public static void main(String[] args) {
        AppMenu app = new AppMenu();
        app.start();
    }

    public void start() {
        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");

        // create VehicleManager, and load all vehicles from text file
        vehicleManager = new VehicleManager("vehicles.txt");

        bookingManager = new BookingManager();

        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }


        //   vehicleManager.displayAllVehicles();


        //   String registration = "172LH234106";
        //   Vehicle vehicle = vehicleManager.findVehicleByReg(registration);
        //   if (vehicle == null)
        //       System.out.println("No vehicle with registration " + registration + " was found.");
        //   else
        //       System.out.println("Found Vehicle: " + vehicle.toString());

        // Create BookingManager and load all bookings from file
        // bookingManager = new BookingManager("bookings.txt");

        //pMgr.saveToFile();

        System.out.println("Program ending, Goodbye");
    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Passengers\n"
                + "2. Vehicles\n"
                + "3. Bookings\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int PASSENGERS = 1;
        final int VEHICLES = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case PASSENGERS:
                        System.out.println("Passengers option chosen");
                        displayPassengerMenu();
                        break;
                    case VEHICLES:
                        System.out.println("Vehicles option chosen");
                        displayVehicleMenu();
                        break;
                    case BOOKINGS:
                        System.out.println("Bookings option chosen");
                        displayBookingMenu();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        bookingManager.saveBookingsToFile();
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    // Sub-Menu for Passenger operations
    //
    private void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add passenger\n"
                + "4. Exit\n"
                + "Enter Option [1,3]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int EXIT = 4;

        Scanner kb = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = kb.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Passengers");
                        passengerStore.displayAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("Enter passenger name: ");
                        String name = kb.nextLine();
                        passengerStore.findPassengerByName(name);
                        break;
                    case ADD_PASSENGER:
                        Scanner kb1 = new Scanner(System.in);
                        System.out.print("enter passenger name:");
                        String input_name = kb1.nextLine();


                        System.out.print("enter passenger email:");
                        String email = kb1.next();

                        System.out.print("enter passenger phonenumber:");
                        String phonenumber = kb1.next();

                        System.out.print("enter passenger latitude:");
                        double latitude = kb1.nextDouble();

                        System.out.print("enter passenger longtitude:");
                        double longtitude = kb1.nextDouble();
                        passengerStore.addPassenger(input_name, email, phonenumber, latitude, longtitude);
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;

                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }

    private void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** Vehicle MENU ***\n"
                + "1. Show all Vehicle\n"
                + "2. Find all Vehicle\n"
                + "3. Find Vehicle by ID\n"
                + "4. Find Vehicle by Make\n"
                + "5. Find Vehicle by Registration\n"
                + "6. Find Vehicle by Type\n"
                + "7. Find Vehicle by Seat\n"
                + "8. Exit\n"
                + "Enter Option [1,8]";

        final int SHOW_ALL = 1;
        final int FIND_ALL_VEHICLE = 2;
        final int FIND_BY_ID = 3;
        final int FIND_BY_MAKE = 4;
        final int FIND_BY_REGISTRATION = 5;
        final int FIND_BY_TYPE = 6;
        final int FIND_BY_SEAT = 7;
        final int EXIT = 8;

        Scanner kb = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = kb.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Vehicles : ");
                        vehicleManager.displayAllVehicles();
                        break;
                    case FIND_ALL_VEHICLE:
                        System.out.println("Find ALL Vehicles : ");
                        vehicleManager.findAllVehicle();
                        break;

                    case FIND_BY_ID:
                        System.out.print("Enter Vehicle ID name : ");
                        int id = kb.nextInt();
                        Vehicle v = vehicleManager.findVehicleById(id);
                        System.out.println("\nVehicle details from findVechicleById : ");

                        if (v != null){
                            System.out.println(v);
                        }
                        else{
                            System.out.println("No vehicle with that id found");
                        }
                        kb.nextLine();
                        break;
                    case FIND_BY_MAKE:
                        System.out.print("Enter Vehicle Make : ");
                        String make = kb.nextLine();
                        ArrayList<Vehicle> vehiclesMatching = vehicleManager.findVehicleByMake(make);
                        if(vehiclesMatching != null)
                            System.out.println(vehiclesMatching);
                        else
                            System.out.println("No vehicle with that make found");
                        break;
                    case FIND_BY_REGISTRATION:
                        System.out.print("Enter Vehicle Registration : ");
                        String reg = kb.nextLine();
                        ArrayList<Vehicle> vehicleRegistration = vehicleManager.findVehicleByRegistration(reg);

                        if(vehicleRegistration != null && vehicleRegistration.size() != 0)
                            System.out.println(vehicleRegistration);
                        else
                            System.out.println("No vehicle with that registration found");
                        break;
                    case FIND_BY_TYPE:
                        System.out.print("Enter Vehicle Type : ");
                        String type = kb.nextLine();
                        ArrayList<Vehicle> vehiclesType = vehicleManager.findVehicleByType(type);
                        if(vehiclesType != null)
                            System.out.println(vehiclesType);
                        else
                            System.out.println("No vehicle with that type found");
                        break;
                    case FIND_BY_SEAT:
                        System.out.print("Enter Vehicle Seat Number : ");
                        int seat = kb.nextInt();
                        if(seat != 0)
                            vehicleManager.getVehiclesbySeats(seat);
                        else
                            System.out.println("No vehicle with that type found");
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }
    private void displayBookingMenu() {
        final String MENU_ITEMS = "\n*** BOOKING MENU ***\n"
                + "1. Show all Bookings\n"
                + "2. Add a Booking\n"
                + "3. Edit Booking Details\n"
                + "4. Delete Booking\n"
                + "5. Exit\n"
                + "Enter Option [1,5]";

        final int SHOW_ALL = 1;
        final int SHOW_ALL_FUTURE = 2;
        final int SHOW_ALL_BY_PASSENGERNAME = 3;
        final int ADD_A_BOOKING = 4;
        final int EDIT_BOOKING_DETAILS = 5;
        final int DELETE_BOOKING = 6;
        final int EXIT = 7;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Bookings");
                        bookingManager.displayAllBookings();
                        break;
                    case SHOW_ALL_FUTURE:
                        System.out.println("Display ALL Future Bookings");
                        bookingManager.displayFutureBookings();
                        break;
                    case SHOW_ALL_BY_PASSENGERNAME:
                        System.out.println("Display ALL Bookings by Passenger Name");
                        Passenger p = passengerStore.getPassengerByName("Joseph Bailey");
                        //input
                        if(p != null) {
                            bookingManager.displayBookingByPassengerID(p.getId());
                        }
                        break;
                    case ADD_A_BOOKING:
                        int passengerId;
                        do {
                            System.out.println("Enter Passenger Id");
                            passengerId = keyboard.nextInt();
                            if (passengerStore.getPassengerById(passengerId) != null) {
                                break;
                            } else {
                                System.out.println("Passenger not found, please type a valid passenger");
                            }
                        } while (true);
                        System.out.println("Enter Vehicle Id");
                        int vehicleId = keyboard.nextInt();
                        System.out.println("Enter Year");
                        int year = keyboard.nextInt();
                        System.out.println("Enter Month");
                        int month = keyboard.nextInt();
                        System.out.println("Enter day");
                        int day = keyboard.nextInt();
                        System.out.println("Enter Hours (0-23)");
                        int hour = keyboard.nextInt();
                        System.out.println("Enter Minutes (0-59)");
                        int minutes = keyboard.nextInt();
                        System.out.println("Enter start latitude");
                        double slatitude = keyboard.nextDouble();
                        System.out.println("Enter start longitude");
                        double slongitude = keyboard.nextDouble();
                        System.out.println("Enter end latitude");
                        double elatitude = keyboard.nextDouble();
                        System.out.println("Enter end longitude");
                        double elongitude = keyboard.nextDouble();
                        Vehicle v = vehicleManager.findVehicleById(vehicleId);
                        if (v != null) {
                            Vehicle.Type type = v.getType();
                            int mileage = v.getMileage();
                            bookingManager.addBooking(passengerId, vehicleId, year, month, day, hour, minutes, slatitude, elatitude, slongitude, elongitude, type, mileage);
                        }
                        else{
                            System.out.println("Vehicle not Found");
                        }
                        break;
                    case EDIT_BOOKING_DETAILS:

                        break;
                    case DELETE_BOOKING:
                        System.out.println();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }
}
