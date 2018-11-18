package com.parkinglot.parking;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.parkinglot.exception.DataNotFound;
import com.parkinglot.exception.SlotNotAvailable;
import com.parkinglot.vehicle.Bus;
import com.parkinglot.vehicle.Car;
import com.parkinglot.vehicle.Color;
import com.parkinglot.vehicle.Size;
import com.parkinglot.vehicle.Truck;
import com.parkinglot.vehicle.Vehicle;

public class ParkingTest {
	private Parking parking;
	private String parkingId;
	private List<Slot> slots = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		parkingId = "unitech-wing-A";
		// level 0
		slots.add(new Slot(1, 0, Size.SMALL));
		slots.add(new Slot(2, 0, Size.SMALL));
		slots.add(new Slot(3, 0, Size.MEDIUM));
		// level 1
		slots.add(new Slot(1, 1, Size.MEDIUM));
		slots.add(new Slot(2, 1, Size.MEDIUM));
		slots.add(new Slot(3, 1, Size.LARGE));
		// level 2
		slots.add(new Slot(1, 2, Size.LARGE));
		slots.add(new Slot(2, 2, Size.EXTRA_LARGE));
		slots.add(new Slot(3, 2, Size.EXTRA_LARGE));

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParkVehicle() throws Exception {
		parking = new Parking(parkingId, slots);
		Vehicle KAAQPB7845 = new Car("KA-AQ-PB-7845", Color.BLACK);
		Vehicle KAAQPB7846 = new Car("KA-AQ-PB-7846", Color.SILVER);
		Vehicle KAAQPB7847 = new Car("KA-AQ-PB-7847", Color.WHITE);
		Vehicle KAAQPB7848 = new Car("KA-AQ-PB-7848", Color.BLUE);
		Vehicle KAAQPB7849 = new Car("KA-AQ-PB-7849", Color.YELLOW);

		ParkingToken parkingTokenKAAQPB7845 = parking.parkVehicle(KAAQPB7845);
		assertNotNull(parkingTokenKAAQPB7845);
		ParkingToken parkingTokenKAAQPB7846 = parking.parkVehicle(KAAQPB7846);
		assertNotNull(parkingTokenKAAQPB7846);
		ParkingToken parkingTokenKAAQPB7847 = parking.parkVehicle(KAAQPB7847);
		assertNotNull(parkingTokenKAAQPB7847);
		ParkingToken parkingTokenKAAQPB7848 = parking.parkVehicle(KAAQPB7848);
		assertNotNull(parkingTokenKAAQPB7848);
		ParkingToken parkingTokenKAAQPB7849 = parking.parkVehicle(KAAQPB7849);
		assertNotNull(parkingTokenKAAQPB7849);
	}

	@Test(expected = SlotNotAvailable.class)
	public void testParkVehicleSlotNotAvailable() throws Exception {
		parking = new Parking(parkingId, slots);
		Vehicle KAAQPB7845 = new Truck("KA-AQ-PB-7845", Color.BLACK);
		Vehicle KAAQPB7846 = new Truck("KA-AQ-PB-7846", Color.SILVER);
		Vehicle KAAQPB7847 = new Truck("KA-AQ-PB-7847", Color.WHITE);

		ParkingToken parkingTokenKAAQPB7845 = parking.parkVehicle(KAAQPB7845);
		assertNotNull(parkingTokenKAAQPB7845);
		ParkingToken parkingTokenKAAQPB7846 = parking.parkVehicle(KAAQPB7846);
		assertNotNull(parkingTokenKAAQPB7846);
		ParkingToken parkingTokenKAAQPB7847 = parking.parkVehicle(KAAQPB7847);
		assertNotNull(parkingTokenKAAQPB7847);
	}

	@Test
	public void testGetSlot() throws Exception {
		parking = new Parking(parkingId, slots);
		Vehicle KAAQPB7845 = new Bus("KA-AQ-PB-7845", Color.BLACK);
		ParkingToken parkingTokenKAAQPB7845 = parking.parkVehicle(KAAQPB7845);
		assertNotNull(parkingTokenKAAQPB7845);
		String KAAQPB7845_slot = parking.getSlot(KAAQPB7845.getRegistrationNumber());
		assertNotNull(KAAQPB7845_slot);
	}

	@Test(expected = DataNotFound.class)
	public void testGetSlotDataNotFound() throws Exception {
		parking = new Parking(parkingId, slots);
		Vehicle KAAQPB7845 = new Bus("KA-AQ-PB-7845", Color.BLACK);
		ParkingToken parkingTokenKAAQPB7845 = parking.parkVehicle(KAAQPB7845);
		assertNotNull(parkingTokenKAAQPB7845);
		parking.getSlot("KA-AQ-PB-1001");
	}

	@Test
	public void testGetRegistrationNumbers() throws Exception {
		parking = new Parking(parkingId, slots);
		Vehicle KAAQPB7845 = new Car("KA-AQ-PB-7845", Color.BLACK);
		Vehicle KAAQPB7846 = new Car("KA-AQ-PB-7846", Color.SILVER);
		Vehicle KAAQPB7847 = new Bus("KA-AQ-PB-7847", Color.BLACK);
		Vehicle KAAQPB7848 = new Truck("KA-AQ-PB-7848", Color.BLUE);

		ParkingToken parkingTokenKAAQPB7845 = parking.parkVehicle(KAAQPB7845);
		assertNotNull(parkingTokenKAAQPB7845);
		ParkingToken parkingTokenKAAQPB7846 = parking.parkVehicle(KAAQPB7846);
		assertNotNull(parkingTokenKAAQPB7846);
		ParkingToken parkingTokenKAAQPB7847 = parking.parkVehicle(KAAQPB7847);
		assertNotNull(parkingTokenKAAQPB7847);
		ParkingToken parkingTokenKAAQPB7848 = parking.parkVehicle(KAAQPB7848);
		assertNotNull(parkingTokenKAAQPB7848);

		Collection<Vehicle> vehicles = parking.getRegistrationNumbers(Color.BLACK);
		assertTrue(vehicles.size() == 2);

		vehicles = parking.getRegistrationNumbers(Color.SILVER);
		assertTrue(vehicles.size() == 1);

		vehicles = parking.getRegistrationNumbers(Color.BLUE);
		assertTrue(vehicles.size() == 1);

	}

	@Test
	public void testGetSlots() throws Exception {
		parking = new Parking(parkingId, slots);
		Vehicle KAAQPB7845 = new Car("KA-AQ-PB-7845", Color.BLACK);
		Vehicle KAAQPB7846 = new Car("KA-AQ-PB-7846", Color.SILVER);
		Vehicle KAAQPB7847 = new Bus("KA-AQ-PB-7847", Color.BLACK);
		Vehicle KAAQPB7848 = new Truck("KA-AQ-PB-7848", Color.SILVER);

		ParkingToken parkingTokenKAAQPB7845 = parking.parkVehicle(KAAQPB7845);
		assertNotNull(parkingTokenKAAQPB7845);
		ParkingToken parkingTokenKAAQPB7846 = parking.parkVehicle(KAAQPB7846);
		assertNotNull(parkingTokenKAAQPB7846);
		ParkingToken parkingTokenKAAQPB7847 = parking.parkVehicle(KAAQPB7847);
		assertNotNull(parkingTokenKAAQPB7847);
		ParkingToken parkingTokenKAAQPB7848 = parking.parkVehicle(KAAQPB7848);
		assertNotNull(parkingTokenKAAQPB7848);

		Collection<Slot> slots = parking.getSlots(Color.BLACK);
		assertTrue(slots.size() == 2);

		slots = parking.getSlots(Color.SILVER);
		assertTrue(slots.size() == 2);

	}

}
