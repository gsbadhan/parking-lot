package com.parkinglot.vehicle;

public class Bike extends Vehicle {
	private String plateNumber;

	public Bike(String plateNumber, Color color) {
		super(plateNumber, color, Size.SMALL);
		this.plateNumber = plateNumber;
	}

	@Override
	public String toString() {
		return "Bike [plateNumber=" + plateNumber + ", color=" + color + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plateNumber == null) ? 0 : plateNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bike other = (Bike) obj;
		if (plateNumber == null) {
			if (other.plateNumber != null)
				return false;
		} else if (!plateNumber.equals(other.plateNumber))
			return false;
		return true;
	}

}
