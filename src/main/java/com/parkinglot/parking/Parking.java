package com.parkinglot.parking;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.parkinglot.exception.DataNotFound;
import com.parkinglot.exception.InvalidSlotSize;
import com.parkinglot.exception.SlotNotAvailable;
import com.parkinglot.vehicle.Color;
import com.parkinglot.vehicle.Vehicle;

public class Parking {
	private final String id;
	private final ParkingPool pool;

	public Parking(final String id, final List<Slot> slots) throws InvalidSlotSize {
		this.id = id;
		pool = new ParkingPool(slots);
	}

	public ParkingToken parkVehicle(Vehicle vehicle) throws InvalidSlotSize, SlotNotAvailable {
		Slot slot = pool.placeVehicle(vehicle);
		return new ParkingToken(vehicle, slot);
	}

	public boolean unparkVehicle(Vehicle vehicle) throws DataNotFound {
		boolean status = pool.unPlaceVehicle(vehicle);
		if (!status)
			throw new DataNotFound(vehicle.getRegistrationNumber() + ":" + "registration-number not found !!");
		return status;
	}

	public String getSlot(String registrationNumber) throws DataNotFound {
		Slot slot = pool.allocatedSlots.get(registrationNumber);
		if (slot == null)
			throw new DataNotFound(registrationNumber + ":" + "registration-number not found !!");
		return slot.toString();
	}

	public Collection<Vehicle> getRegistrationNumbers(Color color) throws DataNotFound {
		Map<Vehicle, Slot> vehicleNdSlot = pool.colorWiseAllocatedSlots.get(color);
		if (vehicleNdSlot == null)
			throw new DataNotFound(color + ":" + "data not found !!");
		return vehicleNdSlot.keySet();

	}

	public Collection<Slot> getSlots(Color color) throws DataNotFound {
		Map<Vehicle, Slot> vehicleNdSlot = pool.colorWiseAllocatedSlots.get(color);
		if (vehicleNdSlot == null)
			throw new DataNotFound(color + ":" + "data not found !!");
		return vehicleNdSlot.values();
	}

	public void reset(final List<Slot> slots) throws InvalidSlotSize {
		pool.initiliazeMeta(slots);
		parkingInfo();
	}

	public void parkingInfo() {
		System.out.println("status: total slots:" + pool.totalSlots() + ", allocated-slots:"
				+ pool.totalAllocatedSlots() + ", freeSlots:" + pool.totalFreeSlots());
	}

	public String getId() {
		return id;
	}

}
