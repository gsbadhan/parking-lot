package com.parkinglot.vehicle;

public abstract class Vehicle {
	protected String registrationNumber;
	protected Color color;
	protected Size size;

	public Vehicle(String registrationNumber, Color color, Size size) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = color;
		this.size = size;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public Color getColor() {
		return color;
	}

	public Size getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		return true;
	}

}
