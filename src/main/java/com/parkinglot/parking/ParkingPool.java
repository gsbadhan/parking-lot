package com.parkinglot.parking;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.Pair;

import com.parkinglot.exception.InvalidSlotSize;
import com.parkinglot.exception.SlotNotAvailable;
import com.parkinglot.vehicle.Color;
import com.parkinglot.vehicle.Vehicle;

public class ParkingPool {
	private static Comparator<Slot> sortBySlotIdAndLevel = Comparator.comparing(Slot::getLevel)
			.thenComparing(Slot::getId);
	// free slots, size wise
	private Set<Slot> freeSmallSize = Collections.emptySet();
	private Set<Slot> freeMediumSize = Collections.emptySet();
	private Set<Slot> freeLargeSize = Collections.emptySet();
	private Set<Slot> freeExtraLargeSize = Collections.emptySet();
	// vehicle and slot allocation mapping
	protected Map<String, Slot> allocatedSlots = Collections.emptyMap();
	// color wise vehicle,slot allocation mapping
	protected Map<Color, Map<Vehicle, Slot>> colorWiseAllocatedSlots = Collections.emptyMap();

	public ParkingPool(final List<Slot> slots) throws InvalidSlotSize {
		initiliazeMeta(slots);
	}

	protected void initiliazeMeta(List<Slot> slots) throws InvalidSlotSize {
		freeSmallSize = new TreeSet<>(sortBySlotIdAndLevel);
		freeMediumSize = new TreeSet<>(sortBySlotIdAndLevel);
		freeLargeSize = new TreeSet<>(sortBySlotIdAndLevel);
		freeExtraLargeSize = new TreeSet<>(sortBySlotIdAndLevel);
		spaceBuilder(slots);
		allocatedSlots = new HashMap<>(slots.size());
		colorWiseAllocatedSlots = new HashMap<>();
		for (Color color : Color.values()) {
			colorWiseAllocatedSlots.put(color, new HashMap<>());
		}
	}

	private void spaceBuilder(List<Slot> slots) throws InvalidSlotSize {
		for (Slot slot : slots) {
			switch (slot.getSize()) {
			case SMALL:
				this.freeSmallSize.add(slot);
				break;
			case MEDIUM:
				this.freeMediumSize.add(slot);
				break;
			case LARGE:
				this.freeLargeSize.add(slot);
				break;
			case EXTRA_LARGE:
				this.freeExtraLargeSize.add(slot);
				break;
			default:
				throw new InvalidSlotSize(
						"invalid slot size:" + slot.getSize() + " for id/level" + slot.getId() + "/" + slot.getLevel());
			}
		}
	}

	public Slot placeVehicle(Vehicle vehicle) throws InvalidSlotSize, SlotNotAvailable {
		Pair<Slot, Set<Slot>> slotInfo = null;
		switch (vehicle.getSize()) {
		case SMALL:
			slotInfo = scanFreePools(this.freeSmallSize, this.freeMediumSize);
			break;
		case MEDIUM:
			slotInfo = scanFreePools(this.freeMediumSize, this.freeLargeSize);
			break;
		case LARGE:
			slotInfo = scanFreePools(this.freeLargeSize, this.freeExtraLargeSize);
			break;
		case EXTRA_LARGE:
			slotInfo = scanFreePools(this.freeExtraLargeSize);
			break;
		default:
			throw new InvalidSlotSize(
					"invalid vehicle size:" + vehicle.getSize() + " for number" + vehicle.getRegistrationNumber());
		}
		allocate(vehicle, slotInfo.getLeft(), slotInfo.getRight());
		return slotInfo.getLeft();
	}

	private Pair<Slot, Set<Slot>> scanFreePools(Set<Slot>... pools) throws SlotNotAvailable {
		for (Set<Slot> pool : pools) {
			if (pool.isEmpty()) {
				continue;
			} else {
				Slot slot = pool.stream().findFirst().get();
				return Pair.of(slot, pool);
			}
		}
		throw new SlotNotAvailable("parking not available !!");
	}

	private void allocate(Vehicle vehicle, Slot slot, Set<Slot> pool) {
		allocatedSlots.put(vehicle.getRegistrationNumber(), slot);
		colorWiseAllocatedSlots.get(vehicle.getColor()).put(vehicle, slot);
		pool.remove(slot);
	}

	public boolean unPlaceVehicle(Vehicle vehicle) {
		Slot slot = allocatedSlots.remove(vehicle.getRegistrationNumber());
		colorWiseAllocatedSlots.get(vehicle.getColor()).remove(vehicle);
		if (slot == null)
			return false;
		try {
			spaceBuilder(Arrays.asList(slot));
		} catch (InvalidSlotSize e) {
			return false;
		}
		return true;
	}

	public int totalSlots() {
		return totalFreeSlots() + totalAllocatedSlots();
	}

	public int totalFreeSlots() {
		return freeSmallSize.size() + freeMediumSize.size() + freeLargeSize.size() + freeExtraLargeSize.size();
	}

	public int totalAllocatedSlots() {
		return allocatedSlots.size();
	}
}
