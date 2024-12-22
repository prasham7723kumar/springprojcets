package com.myapp.springjpa01.controller;

public class ResourceNotFoundException extends Exception {
	public ResourceNotFoundException(String string) {
		super(string);
	}
}
