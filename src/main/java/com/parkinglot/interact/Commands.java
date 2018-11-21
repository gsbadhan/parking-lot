package com.parkinglot.interact;

/**
 * User commands
 * 
 * @author gusi
 *
 */
public final class Commands {
	private Commands() {
	}

	public static final String HELP = "help";
	public static final String EXIT = "exit";
	public static final String FILE = "file";
	public static final String CRAETE_PARKING_LOT = "create_parking_lot";
	public static final String PARK = "park";
	public static final String LEAVE = "leave";
	public static final String STATUS = "status";
	public static final String REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR = "registration_numbers_for_cars_with_colour";
	public static final String SLOT_NUMBERS_FOR_CARS_WITH_COLOUR = "slot_numbers_for_cars_with_colour";
	public static final String SLOT_NUMBER_FOR_REGISTRATION_NUMBER = "slot_number_for_registration_number";

	public static final String INFO = "commands help, input should be space seprated: \n* file <file path> \n* create_parking_lot <number> \n* park <plate number> <color> \n* leave <plate number> <color> \n* registration_numbers_for_cars_with_colour <color> \n* slot_numbers_for_cars_with_colour <color> \n* slot_number_for_registration_number <plate number> \n* exit \n* help";

}
