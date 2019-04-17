package com.academy.suggestion;

public class Message {
	
	public static void printWelcomeMenu() {
		System.out.println("Welcome!");
		System.out.println("1 - Login");
		System.out.println("2 - Register");
		System.out.println("0 - Exit");

	}

	public static void printGoodBye() {
		System.out.println(" *** Goodbye ***");
	}

	public static void printLoginMenu() {
		System.out.println("*** Login Menu ***");
		System.out.println("1 - Login as a Client");
		System.out.println("2 - Login as Admin");
		System.out.println("0 - Back to Welcome Menu");
	}

	public static void printLoginForm() {
		System.out.println("*** Login ***");
		System.out.println("Enter your Username: ");
		System.out.println("Enter your Password: ");
	}

	public static void printRegisterMenu() {
		System.out.println("*** Register Menu ***");
		System.out.println("Enter your information below: First Username then the password: ");
		System.out.println();
	}
	
	public static void printClientHomePage() {
		System.out.println("*** Client HomePage ***");
		System.out.println("1 - View Profile");
		System.out.println("2 - View Categories");
		System.out.println("0 - Log Out");
	}
	
	public static void printAdminHomePage() {
		System.out.println("*** Admin HomePage ***");
		System.out.println("1 - Create Category");
		System.out.println("2 - Create SubCategory");
		System.out.println("3 - Add Materials");
		System.out.println("4 - Manage Requests");
	}
	

	
	
	

}
