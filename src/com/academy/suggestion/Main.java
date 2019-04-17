package com.academy.suggestion;
import java.util.Scanner;
import static com.academy.suggestion.Message.*;
public class Main {
	
	
	
	private static Scanner scan = new Scanner(System.in);
	private static Database database = new Database();
	private static int INPUT = -1;

	private static void resetInput() {
		INPUT = -1;
	}

	public static void main(String[] args) {

		System.out.println("The database is loading...");
		database.initialize();
		System.out.println("The database is loaded successfully.");

		do {
			printWelcomeMenu();
			INPUT = scan.nextInt();
			switch (INPUT) {

			case 1:                    // Login Menu
				do {
					printLoginMenu();
					INPUT = scan.nextInt();

					switch (INPUT) {
					case 1:           // Login as Client

						printLoginForm();
						String username = scan.next();
						String password = scan.next();
						Client loggedClient = database.loginAsClient(username, password);
						             // Check if the user is found
						if (loggedClient != null) {
							System.out.println("User not found");
						}
						else {        // When user is found
							printClientHomePage();
							INPUT = scan.nextInt();
							switch(INPUT) {
							case 1:  // View profile
								database.getClients();
								break;
							case 2:
								database.getCategories().toString();
								break;
							}
							
						}
						break;
						
					case 2:          // Login as Admin
						printLoginForm();
						String username2 = scan.next();
						String password2 = scan.next();
						Admin loggedAdmin = database.loginAsAdmin(username2, password2);
						if (loggedAdmin != null) {
							System.out.println("User not found");
						}
						else {  //  Admin HomePage
							printAdminHomePage();
							INPUT = scan.nextInt();
							switch(INPUT) {
							case 1:   // Create Category
								System.out.println("Enter the Name of the Category");
								String categoryName = scan.next();
								Category category1 = new Category(categoryName);
								database.getCategories().add(category1);
								System.out.println("Category: "+ category1 +" was added successfully.");
								break;
								
								
							}
						}
						break;

					}

				} while (INPUT != 0);
				resetInput();
				break;
			case 2: // Register Menu

				break;
			default:
				System.out.println("Invalid input value!");
				
				break;
				
			}

		} while (INPUT != 0);
		printGoodBye();
		scan.close();
	}

}
