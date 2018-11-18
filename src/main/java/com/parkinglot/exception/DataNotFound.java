package com.parkinglot.exception;

@SuppressWarnings("serial")
public class DataNotFound extends Exception {
	public DataNotFound() {
		super();
	}

	public DataNotFound(String message) {
		super(message);
	}
}
