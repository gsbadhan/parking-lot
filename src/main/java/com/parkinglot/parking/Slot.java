package com.parkinglot.parking;

import com.parkinglot.vehicle.Size;

public class Slot {
	private Integer id;
	private Integer level;
	private Size size;

	public Slot(int id, int level, Size size) {
		super();
		this.id = id;
		this.level = level;
		this.size = size;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
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
		Slot other = (Slot) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Slot [id=" + id + ", level=" + level + ", size=" + size + "]";
	}

}
