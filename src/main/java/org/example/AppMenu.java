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
                        passengerStore.savePassengersToFile();
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
                + "2. Add Passenger\n"
                + "3. Edit Passenger\n"
                + "4. Find Passenger By ID\n"
                + "5. Find Passenger By Name\n"
                + "6. Delete Passenger By ID\n"
                + "7. Delete Passenger By Name\n"
                + "8. Exit\n"
                + "Enter Option [1,8]";

        final int SHOW_ALL = 1;
        final int ADD_PASSENGER = 2;
        final int EDIT_PASSENGER = 3;
        final int GET_PASSENGER_BY_ID = 4;
        final int GET_PASSENGER_BY_NAME = 5;
        final int DELETE_PASSENGER_BY_ID = 6;
        final int DELETE_PASSENGER_BY_NAME = 7;
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
                        System.out.println("Display ALL Passengers");
                        passengerStore.displayAllPassengers();
                        break;
                    case ADD_PASSENGER:
                        Scanner kb1 = new Scanner(System.in);
                        System.out.print("Enter Passenger name:");
                        String name = kb1.nextLine();

                        System.out.print("Enter Passenger email:");
                        String email = kb1.nextLine();

                        System.out.print("Enter Passenger phone number:");
                        String phonenumber = kb1.nextLine();

                        System.out.print("Enter Passenger latitude:");
                        double latitude = kb1.nextDouble();

                        System.out.print("Enter Passenger longitude:");
                        double longitude = kb1.nextDouble();
                        passengerStore.addPassenger(name, email, phonenumber, latitude, longitude);
                        break;
                    case EDIT_PASSENGER:
                        System.out.println("Enter an ID to edit");
                        int opt = kb.nextInt();
                        passengerStore.editPassenger(opt);
                        kb.nextLine();
                        break;
                    case GET_PASSENGER_BY_ID:
                        System.out.println("Enter Passenger ID");
                        int findID = kb.nextInt();
                        System.out.println(passengerStore.getPassengerById(findID).toString());
                        kb.nextLine();
                        break;
                    case GET_PASSENGER_BY_NAME:
                        System.out.println("Enter Passenger Name");
                        String findName = kb.nextLine();
                        System.out.println(passengerStore.getPassengerByName(findName));
                        break;
                    case DELETE_PASSENGER_BY_ID:
                        System.out.println("Enter Passenger ID to delete");
                        int deleteID = kb.nextInt();
                        passengerStore.deletePassengerByID(deleteID);
                        if(passengerStore.deletePassengerByID(deleteID)) {
                            System.out.println("Passenger ID " + deleteID + " is deleted");
                        }
                        else{
                            System.out.println("Invalid ID " + deleteID);
                        }
                        kb.nextLine();
                        break;
                    case DELETE_PASSENGER_BY_NAME:
                        System.out.println("Enter Passenger name to delete");
                        String deleteName = kb.nextLine();
                        if(passengerStore.deletePassengerByName(deleteName)) {
                            System.out.println("Passenger Name " + deleteName + " is deleted");
                        }
                        else{
                            System.out.println("Invalid Name " + deleteName);
                        }
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;

                }
            } catch (InputMismatchException | NumberFormatException | IOException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }

    private void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** Vehicle MENU ***\n"
                + "1. Show all Vehicle\n"
                + "2. Find Vehicle by ID\n"
                + "3. Find Vehicle by Make\n"
                + "4. Find Vehicle by Registration\n"
                + "5. Find Vehicle by Type\n"
                + "6. Find Vehicle by Seat\n"
                + "7. Exit\n"
                + "Enter Option [1,7]";

        final int SHOW_ALL = 1;
        final int FIND_BY_ID = 2;
        final int FIND_BY_MAKE = 3;
        final int FIND_BY_REGISTRATION = 4;
        final int FIND_BY_TYPE = 5;
        final int FIND_BY_SEAT = 6;
        final int EXIT = 7;

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
                    case FIND_BY_ID:
                        System.out.print("Enter Vehicle ID : ");
                        int id = kb.nextInt();
                        Vehicle v = vehicleManager.findVehicleById(id);
                        System.out.println("\nVehicle details from findVechicleById : ");

                        if (v != null) {
                            System.out.println(v);
                        } else {
                            System.out.println("No vehicle with that id found");
                        }
                        kb.nextLine();
                        break;
                    case FIND_BY_MAKE:
                        System.out.print("Enter Vehicle Make : ");
                        String make = kb.nextLine();
                        ArrayList<Vehicle> vehicleMaking = vehicleManager.findVehicleByMake(make);

                        if (vehicleMaking != null && vehicleMaking.size() != 0){
                            System.out.println(vehicleMaking);
                        }
                        else {
                            System.out.println("No vehicle with that make found");
                        }
                        break;
                    case FIND_BY_REGISTRATION:
                        System.out.print("Enter Vehicle Registration : ");
                        String reg = kb.nextLine();
                        ArrayList<Vehicle> vehicleRegistration = vehicleManager.findVehicleByRegistration(reg);

                        if (vehicleRegistration != null && vehicleRegistration.size() != 0) {
                            System.out.println(vehicleRegistration);
                        }
                        else {
                            System.out.println("No vehicle with that registration found");
                        }
                        break;
                    case FIND_BY_TYPE:
                        System.out.print("Enter Vehicle Type : ");
                        String type = kb.nextLine();
                        ArrayList<Vehicle> vehiclesType = vehicleManager.findVehicleByType(type);

                        if (vehiclesType != null && vehiclesType.size() != 0) {
                            System.out.println(vehiclesType);
                        }
                        else {
                            System.out.println("No vehicle with that type found");
                        }
                        break;
                    case FIND_BY_SEAT:
                        System.out.print("Enter Vehicle Seat Number : ");
                        int seat = kb.nextInt();
                        if (seat != 0) {
                            vehicleManager.getVehiclesbySeats(seat);
                        }
                        else {
                            System.out.println("Seat cannot be zero");
                        }
                        kb.nextLine();
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
                + "2. Show all Future Bookings\n"
                + "3. Show all Bookings By Passenger Name\n"
                + "4. Find Bookings by ID\n"
                + "5. Add a Booking\n"
                + "6. Edit Booking Details\n"
                + "7. Delete Booking By ID\n"
                + "8. Find total cost average\n"
                + "9. Exit\n"
                + "Enter Option [1,9]";

        final int SHOW_ALL = 1;
        final int SHOW_ALL_FUTURE = 2;
        final int SHOW_ALL_BY_PASSENGERNAME = 3;
        final int FIND_BOOKING_BY_ID = 4;
        final int ADD_A_BOOKING = 5;
        final int EDIT_BOOKING_DETAILS = 6;
        final int DELETE_BOOKING_BY_ID = 7;
        final int TOTAL_COST_AVERAGE = 8;
        final int EXIT = 9;

        Scanner kb = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = kb.nextLine();
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
                        System.out.println("Enter a Passenger name to display ALL Bookings");
                        String name = kb.nextLine();
                        Passenger p = passengerStore.getPassengerByName(name);
                        if (p != null) {
                            bookingManager.displayBookingByPassengerID(p.getId());
                        }
                        else{
                            System.out.println("Passenger not found");
                        }
                        break;
                    case FIND_BOOKING_BY_ID:
                        System.out.print("Enter Booking ID : ");
                        int bookingId = kb.nextInt();
                        System.out.println("Booking details from ID : ");
                        bookingManager.displayBookingByID(bookingId);
                        kb.nextLine();
                        break;
                    case ADD_A_BOOKING:
                        int passengerId;
                        do {
                            System.out.println("Enter Passenger Id");
                            passengerId = kb.nextInt();
                            if (passengerStore.getPassengerById(passengerId) != null) {
                                break;
                            } else {
                                System.out.println("Passenger not found, please type a valid passenger");
                            }
                        } while (true);
                        System.out.println("Enter Vehicle Id");
                        int vehicleId = kb.nextInt();
                        System.out.println("Enter Year");
                        int year = kb.nextInt();
                        System.out.println("Enter Month");
                        int month = kb.nextInt();
                        System.out.println("Enter day");
                        int day = kb.nextInt();
                        System.out.println("Enter Hours (0-23)");
                        int hour = kb.nextInt();
                        System.out.println("Enter Minutes (0-59)");
                        int minutes = kb.nextInt();
                        System.out.println("Enter start latitude");
                        double slatitude = kb.nextDouble();
                        System.out.println("Enter start longitude");
                        double slongitude = kb.nextDouble();
                        System.out.println("Enter end latitude");
                        double elatitude = kb.nextDouble();
                        System.out.println("Enter end longitude");
                        double elongitude = kb.nextDouble();
                        Vehicle v = vehicleManager.findVehicleById(vehicleId);
                        if (v != null) {
                            Vehicle.Type type = v.getType();
                            int mileage = v.getMileage();
                            bookingManager.addBooking(passengerId, vehicleId, year, month, day, hour, minutes, slatitude, elatitude, slongitude, elongitude, type, mileage);
                        } else {
                            System.out.println("Vehicle not Found");
                        }
                        kb.nextLine();
                        break;
                    case EDIT_BOOKING_DETAILS:
                        System.out.println("Enter an ID to edit");
                        int opt = kb.nextInt();
                        bookingManager.editBooking(opt);
                        kb.nextLine();
                        break;
                    case DELETE_BOOKING_BY_ID:
                        System.out.println("Enter an ID to delete from Booking");
                        int deleteID = kb.nextInt();
                        if(bookingManager.deleteBookingByID(deleteID)) {
                            System.out.println("Booking ID " + deleteID + " is deleted");
                        }
                        else{
                            System.out.println("Invalid ID " + deleteID);
                        }
                        kb.nextLine();
                        break;
                    case TOTAL_COST_AVERAGE:
                        System.out.println("Total Average Cost of All Bookings is :");
                        System.out.println(bookingManager.averageLength());
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException | IOException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);
    }
}
