package com.parkinglot.exception;

@SuppressWarnings("serial")
public class InvalidSlotSize extends Exception {
	public InvalidSlotSize() {
		super();
	}

	public InvalidSlotSize(String message) {
		super(message);
	}
}
