package com.academy.suggestion;

import java.util.InputMismatchException;
import java.util.Scanner;
import static com.academy.suggestion.Message.*;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	private static Database database = new Database();
	private static int INPUT = -1;
	private static String emailRegEx = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	private static String onlyLetterRegex = "[a-zA-Z]+";

	private static void resetInput() {
		INPUT = -1;
	}

	public static void register() {
		System.out.println("*** Registration Menu ***");
		System.out.println("Enter Name");
		String name = scan.next();
		System.out.println("Enter Username");
		String username = scan.next();
		if (database.existClient(username)) {
			System.out.println("This Username already exists!");
		} else {
			System.out.println("Enter Password");
			String password = scan.next();
			System.out.println("Reenter Password");
			String repeatedPassword = scan.next();
			System.out.println("Enter Email");
			String email = scan.next();
			System.out.println("Enter Gender");
			String gender = scan.next();
			System.out.println("Enter Age");
			int age = 0;
			String errors = "";
			try {
				age = scan.nextInt();
				if (!(age > 10 && age < 100)) {
					errors = errors + "Age must be between 10 - 100! \n";
				}
			} catch (InputMismatchException e) {
				errors = errors + "Age must be a number! \n";
			}

			if (!(name.matches(onlyLetterRegex))) {
				errors = errors + "Name must contain only characters \n";
			}
			if (!password.equals(repeatedPassword)) {
				errors = errors + "You must enter the same password! \n";
			}
			if (password.length() < 5) {
				errors = errors + "Password must not be under 5 characters! \n";
			}

			if (!email.matches(emailRegEx)) {
				errors = errors + "Email is not in the right format! \n";
			}

			if (!(gender.equals(Gender.F.toString()) || gender.equals(Gender.M.toString()))) {
				errors = errors + "Gender must be F or M! \n";
			}

			if (errors.length() == 0) {

				Client client = new Client(username, password);
				client.setName(name);
				client.setPassword(password);
				client.setEmail(email);
				client.setGender(Gender.M);
				if (gender.equals(Gender.F.toString())) {
					client.setGender(Gender.F);
				}
				client.setAge(age);
				database.addClient(client);
			} else {
				System.out.println("Registration Failed!");
				System.out.println(errors);
			}
		}
	}

	public static void editProfile(Client client) {
		System.out.println("*** Edit Profile ***");
		System.out.println("Enter Name");
		String name = scan.next();
		System.out.println("Enter Password");
		String password = scan.next();
		System.out.println("Reenter Password");
		String repeatedPassword = scan.next();
		System.out.println("Enter Email");
		String email = scan.next();
		String errors = "";

		if (!(name.matches(onlyLetterRegex))) {
			errors = errors + "Name must contain only characters \n";
		}
		if (!password.equals(repeatedPassword)) {
			errors = errors + "You must enter the same password! \n";
		}
		if (password.length() < 5) {
			errors = errors + "Password must not be under 5 characters! \n";
		}

		if (!email.matches(emailRegEx)) {
			errors = errors + "Email is not in the right format! \n";
		}

		if (errors.length() == 0) {

			client.setName(name);
			client.setPassword(password);
			client.setEmail(email);

		} else {
			System.out.println("Edit Failed");
			System.out.println(errors);
		}

	}

	private static void createCategory() {
		System.out.println("Enter the Name of the Category");
		String categoryName = scan.next();
		System.out.println("Enter the Description of the Category");
		String descriptionName = scan.next();
		if (database.existCategory(categoryName)) {
			System.out.println("Creation Failed. The name already exists.");
		} else {
			Category category = new Category(categoryName);
			category.setDescription(descriptionName);
			database.addCategory(category);
			System.out.println("Category: " + category + " was added successfully.");
		}
	}

	private static void createSubCategory() {
		System.out.println("*** SubCategory Creation ***");
		printCategories();
		System.out.println("Choose Category Id:  ");
		int index = scan.nextInt();
		if (index < 0 || index > database.getCategories().size() - 1) {
			System.out.println("You have entered an invalid Id!");
		} else {
			System.out.println("Enter the Name of the new SubCategory: ");
			String subCategoryName = scan.next();
			Category subCategory = new Category(subCategoryName);
			database.getCategories().get(index).addSubCategory(subCategory);
			System.out.println("SubCategory  created succesfully. ");
		}
	}

	public static void createPost() {
		System.out.println("*** Create Post ***");
		System.out.println("Choose the Category Id : ");

		printCategories();
		int categoryIndex = scan.nextInt();
		if (categoryIndex < 0 || categoryIndex > database.getCategories().size() - 1) {
			System.out.println("You have entered an invalid Id!");
		} else {
			System.out.println("Choose the SubCategory Id ");
			Category selectedCategory = database.getCategories().get(categoryIndex);
			selectedCategory.printSubCategeory();
			int subIndex = scan.nextInt();
			if (subIndex < 0 || subIndex > selectedCategory.getSubCategories().size() - 1) {
				System.out.println("You have entered an invalid Id!");
			} else {
				System.out.println("Enter the name of the post: ");
				String postName = scan.next();
				System.out.println("Enter the description of the post: ");
				String postDescription = scan.next();
				Post post = new Post(postName, postDescription);
				selectedCategory.getSubCategories().get(subIndex).addPost(post);
				System.out.println("Post added Successfully. ");
			}
		}
	}

	public static void likePost(Client client) {
		System.out.println("*** Like Post ***");
		System.out.println("Choose the Category Id:  ");

		printCategories();
		int categoryIndex = scan.nextInt();
		if (categoryIndex < 0 || categoryIndex > database.getCategories().size() - 1) {
			System.out.println("You have entered an invalid Id!");
		} else {
			System.out.println("Choose the SubCategory Id:  ");
			Category selectedCategory = database.getCategories().get(categoryIndex);
			selectedCategory.printSubCategeory();
			int subIndex = scan.nextInt();
			if (subIndex < 0 || subIndex > selectedCategory.getSubCategories().size() - 1) {
				System.out.println("You have entered an invalid Id!");
			} else {
				Category selectedSubCategory = selectedCategory.getSubCategories().get(subIndex);
				selectedSubCategory.printPost();
				System.out.println("Choose the Id of the post you want to like; ");
				int postIndex = scan.nextInt();
				if (postIndex < 0 || postIndex > selectedSubCategory.getPosts().size() - 1) {
					System.out.println("You have entered an invalid Id! ");
				} else {
					client.likePost(selectedSubCategory.getPosts().get(postIndex));
					System.out.println("You just liked " + selectedSubCategory.getPosts().get(postIndex).getTitle());
				}
			}
		}
	}

	public static void printCategories() {

		if (database.getCategories().isEmpty()) {
			System.out.println("The Categories list is empty.");
		} else {
			for (int i = 0; i < database.getCategories().size(); i++) {
				System.out.println("Id = " + i + " Name= " + database.getCategories().get(i).getName());
			}
		}
	}

	public static void printSuggestions() {

		if (database.getSuggestions().isEmpty()) {
			System.out.println("The Suggestions list is empty.");
		} else {
			for (int i = 0; i < database.getSuggestions().size(); i++) {
				System.out.println("Id = " + i + " Name= " + database.getSuggestions().get(i).getCategory().getName()
						+ " Suggested by: " + database.getSuggestions().get(i).getClient().getUsername());
			}
		}
	}

	public static void suggestCategory(Client loggedClient) {
		System.out.println("Enter Category Name: ");
		String categoryName = scan.next();
		Category category = new Category(categoryName);
		Suggestion suggestion = new Suggestion(category, loggedClient);
		database.addSuggestion(suggestion);
		System.out.println("Suggestion added successfully! ");
	}

	public static void main2(String[] args) {
		System.out.println("The database is loading...");
		database.initialize();
		System.out.println("The database is loaded successfully.");
		System.out.println(database.getClients().size());
		register();
		System.out.println(database.getClients().size());
	}

	public static void manageSuggestions() {
		System.out.println("*** Suggestion Management ***");
		if (database.getSuggestions().isEmpty()) {
			System.out.println("You dont have any Suggestions.");
		} else {
			printSuggestions();
			System.out.println("Enter Suggestion Id you want to Accept Or Decline");
			int index = scan.nextInt();
			if (index < 0 || index > database.getSuggestions().size() - 1) {
				System.out.println("You have entered an invalid Id! ");
			} else {
				Suggestion suggestion = database.getSuggestions().get(index);
				System.out.println("Enter 1 to Accept, AnyOther to Decline:");
				int decision = scan.nextInt();
				if (decision == 1) {
					database.addCategory(suggestion.getCategory());
					System.out.println("Suggestion was accepted!");
					suggestion.getClient().addMessage("Suggestion " + suggestion.getCategory().getName() + " Accepted");
				} else {
					database.getSuggestions().remove(suggestion);
					System.out.println("Suggestion was declined!");
					suggestion.getClient().addMessage("Suggestion " + suggestion.getCategory().getName() + " Declined");

				}
			}
		}
	}

	public static void main(String[] args) {

		System.out.println("The database is loading...");
		database.initialize();
		System.out.println("The database is loaded successfully.");
		do {
			printWelcomeMenu();
			INPUT = scan.nextInt();
			switch (INPUT) {

			case 1: // Login Menu
				do {
					printLoginMenu();
					INPUT = scan.nextInt();
					switch (INPUT) {
					case 1: // Login as Client

						printLoginForm();
						String username = scan.next();
						String password = scan.next();
						Client loggedClient = database.loginAsClient(username, password);
						// Check if the user is found
						if (loggedClient == null) {
							System.out.println("User not found");
						} else { // When user is found
							do {
								printClientHomePage(loggedClient.getUsername());

								INPUT = scan.nextInt();
								switch (INPUT) {
								case 1: // View profile
									loggedClient.showMyInfo();
									break;
								case 2: // Edit Profile
									editProfile(loggedClient);
									break;
								case 3: // view categories
									printCategories();
									break;
								case 4:// suggest category
									suggestCategory(loggedClient);
									break;
								case 5:// like post
									likePost(loggedClient);
									break;
								case 6: // View Messages
									loggedClient.printMessages();
									break;
								case 0:
									System.out.println("Log out successfully");
									break;
								}
							} while (INPUT != 0);
							resetInput();
						}
						break;
					case 2: // Login as Admin
						printLoginForm();
						String username2 = scan.next();
						String password2 = scan.next();
						Admin loggedAdmin = database.loginAsAdmin(username2, password2);
						if (loggedAdmin == null) {
							System.out.println("User not found");
						} else { // Admin HomePage
							do {
								printAdminHomePage();
								INPUT = scan.nextInt();
								switch (INPUT) {
								case 1: // Create Category
									createCategory();
									break;
								case 2:// create subcategory
									createSubCategory();
									break;
								case 3: // create post
									createPost();
									break;
								case 4: // Manage Suggestion Requests
									manageSuggestions();
									break;
								case 0:
									System.out.println("Log out successfully");
									break;
								}
							} while (INPUT != 0);
							resetInput();
						}

						break;

					}

				} while (INPUT != 0);
				resetInput();
				break;
			case 2: // Register Menu
				register();
				break;
			case 0:
				printGoodBye();
				break;
			default:
				System.out.println("Invalid input value!");
				break;
			}
		} while (INPUT != 0);
		scan.close();
	}

}
