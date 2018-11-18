package com.parkinglot.exception;

@SuppressWarnings("serial")
public class SlotNotAvailable extends Exception {
	public SlotNotAvailable() {
		super();
	}

	public SlotNotAvailable(String message) {
		super(message);
	}
}
