package com.parkinglot.parking;

import com.parkinglot.vehicle.Vehicle;

public class ParkingToken {
	private Vehicle vehicle;
	private Slot slot;

	public ParkingToken(Vehicle vehicle, Slot slot) {
		super();
		this.vehicle = vehicle;
		this.slot = slot;
	}

	public String getToken() {
		return vehicle.toString() + slot.toString();
	}

	@Override
	public String toString() {
		return "ParkingToken [vehicle=" + vehicle + ", slot=" + slot + "]";
	}

}
