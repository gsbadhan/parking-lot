package com.parkinglot.interact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.parkinglot.exception.InvalidCommand;
import com.parkinglot.exception.InvalidSlotSize;
import com.parkinglot.parking.Parking;
import com.parkinglot.parking.ParkingToken;
import com.parkinglot.parking.Slot;
import com.parkinglot.vehicle.Car;
import com.parkinglot.vehicle.Color;
import com.parkinglot.vehicle.Size;
import com.parkinglot.vehicle.Vehicle;

public class ParkingSystem {

	private static final String SPACE_DELIMETER = " ";
	private static Parking parking = null;

	public static void main(String[] args) {
		defaultInit();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\nEnter option:");
			String[] data = validateInput(scanner.nextLine());
			if (data[0].equals(Commands.FILE)) {
				fileProcessing(data);
			} else {
				perfomAction(data);
			}
		}
	}

	private static String[] validateInput(String userInput) {
		if (userInput.isEmpty())
			System.out.println("enter valid command !!");
		return userInput.split(SPACE_DELIMETER);
	}

	public static void defaultInit() {
		try {
			parking = new Parking(UUID.randomUUID().toString(), Collections.emptyList());
			System.out.println("parking system initialised :)");
			System.out.println("use `help` command to see all commands\n");
			parking.parkingInfo();

		} catch (InvalidSlotSize e) {
			System.out.println("default initialisation failed..!!" + e);
		}
	}

	private static void fileProcessing(String[] data) {
		BufferedReader reader = null;
		try {
			checkArguments(data, 2);
			reader = new BufferedReader(new FileReader(data[1]));
			String line = reader.readLine();
			while (line != null && !line.isEmpty()) {
				perfomAction(line.trim().split(SPACE_DELIMETER));
				line = reader.readLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	private static void perfomAction(String[] data) {
		try {
			String cmd = data[0];
			switch (cmd) {
			case Commands.CRAETE_PARKING_LOT:
				checkArguments(data, 2);
				List<Slot> slots = defaultSlots(Integer.parseInt(data[1].trim()));
				parking.reset(slots);
				break;
			case Commands.PARK:
				checkArguments(data, 3);
				ParkingToken token = parking.parkVehicle(new Car(data[1], Color.valueOf(data[2].toUpperCase())));
				System.out.println(token);
				break;
			case Commands.LEAVE:
				checkArguments(data, 3);
				parking.unparkVehicle(new Car(data[1], Color.valueOf(data[2].toUpperCase())));
				System.out.println("leave " + data[1]);
				break;
			case Commands.STATUS:
				parking.parkingInfo();
				break;
			case Commands.SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
				checkArguments(data, 2);
				System.out.println("slot number for vehicle [" + data[1] + "]-" + parking.getSlot(data[1]));
				break;
			case Commands.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
				checkArguments(data, 2);
				Collection<Vehicle> vehicles = parking.getRegistrationNumbers(Color.valueOf(data[1].toUpperCase()));
				System.out.println("all vehicles for color [" + data[1] + "]-" + vehicles);
				break;
			case Commands.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
				checkArguments(data, 2);
				Collection<Slot> slotxs = parking.getSlots(Color.valueOf(data[1].toUpperCase()));
				System.out.println("all slots for color [" + data[1] + "]-" + slotxs);
				break;
			case Commands.HELP:
				System.out.println(Commands.INFO);
				break;
			case Commands.EXIT:
				System.exit(0);
				break;

			default:
				throw new InvalidCommand(cmd + ": invalid command !!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void checkArguments(String[] data, int expectedNumberOfArguments) {
		if (data.length < expectedNumberOfArguments)
			throw new IllegalArgumentException("aruguments should be:" + expectedNumberOfArguments);
	}

	private static List<Slot> defaultSlots(int slotCount) {
		List<Slot> slots = new ArrayList<>(slotCount);
		for (int i = 1; i <= slotCount; i++) {
			slots.add(new Slot(i, 0, Size.MEDIUM));
		}
		return slots;
	}

}
