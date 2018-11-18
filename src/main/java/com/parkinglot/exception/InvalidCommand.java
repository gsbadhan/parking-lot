package com.parkinglot.exception;

@SuppressWarnings("serial")
public class InvalidCommand extends Exception {
	public InvalidCommand() {
		super();
	}

	public InvalidCommand(String message) {
		super(message);
	}
}
