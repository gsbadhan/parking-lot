package com.parkinglot.parking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.parkinglot.vehicle.Car;
import com.parkinglot.vehicle.Color;
import com.parkinglot.vehicle.Size;
import com.parkinglot.vehicle.Vehicle;

public class ParkingPoolTest {
	private List<Slot> slots = new ArrayList<>();

	@Before
	public void setUp() throws Exception {

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
	public void testInitiliazeMeta() throws Exception {
		ParkingPool parkingPool = new ParkingPool(slots);
		assertNotNull(parkingPool);
		assertTrue(parkingPool.totalFreeSlots() == slots.size());
		assertTrue(parkingPool.totalSlots() == slots.size());
		assertTrue(parkingPool.totalAllocatedSlots() == 0);
	}

	@Test
	public void testPlaceVehicle() throws Exception {
		ParkingPool parkingPool = new ParkingPool(slots);
		Vehicle KAAQPB7845 = new Car("KA-AQ-PB-7845", Color.BLACK);
		Slot s_KAAQPB7845 = parkingPool.placeVehicle(KAAQPB7845);
		assertNotNull(s_KAAQPB7845);
		Vehicle KAAQPB7846 = new Car("KA-AQ-PB-7846", Color.SILVER);
		Slot s_KAAQPB7846 = parkingPool.placeVehicle(KAAQPB7846);
		assertNotNull(s_KAAQPB7846);
		assertTrue(parkingPool.totalFreeSlots() == (slots.size() - 2));
		assertTrue(parkingPool.totalAllocatedSlots() == 2);
	}

	@Test
	public void testUnPlaceVehicle() throws Exception {
		ParkingPool parkingPool = new ParkingPool(slots);
		Vehicle KAAQPB7845 = new Car("KA-AQ-PB-7845", Color.BLACK);
		parkingPool.placeVehicle(KAAQPB7845);
		Vehicle KAAQPB7846 = new Car("KA-AQ-PB-7846", Color.SILVER);
		parkingPool.placeVehicle(KAAQPB7846);

		assertTrue(parkingPool.unPlaceVehicle(KAAQPB7845));
		assertTrue(parkingPool.unPlaceVehicle(KAAQPB7846));
		assertFalse(parkingPool.unPlaceVehicle(new Car("KA-AQ-PB-784512", Color.BLACK)));
	}

}
