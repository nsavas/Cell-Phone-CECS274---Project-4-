import java.util.*;

public class CellPhone {
	
	public static void main(String[] args) {
		Log callHistory = new Log();
		PhoneBook phoneBook = new PhoneBook();
		Favorites favorites = new Favorites();
		
		Contacts[] favoritesList = favorites.getFavorites();
		
		boolean continueProgram = true;
		
		while (continueProgram) {
			System.out.println("\n----------------------------------------------------------------------------------\n"
					         + "                                    Main Menu                                     \n"
					         + "----------------------------------------------------------------------------------\n"
					         + "\nPlease select an application from the following options:\n\n"
					         + "[1] Call\n"
					         + "[2] Contacts\n"
					         + "[3] Favorites\n"
					         + "[4] Display Call History\n"
					         + "[5] Power Off\n");
			
			int userChoice = getUserChoice(1, 5);
			
			if (userChoice == 1) {
				call(callHistory, phoneBook, favoritesList);
			}
		
			else if (userChoice == 2) {
				contacts(phoneBook);
			}
		
			else if (userChoice == 3) {
				favorites(phoneBook, favorites, favoritesList);
			}
		
			else if (userChoice == 4) {
				displayCallLog(callHistory);
			}
			
			else if (userChoice == 5) {
				System.out.println("Powering off...");
				continueProgram = false;
			}
		}
	}
	
	public static void call(Log callHistory, PhoneBook phonebook, Contacts[] favoritesList) {
		System.out.println("\n----------------------------------------------------------------------------------\n"
				         + "                               Make/Receive Call                                    \n"
				         + "----------------------------------------------------------------------------------\n"
				+ "\nPlease select a feature from the following options:\n\n"
				+ "[1] Make a call\n"
				+ "[2] Receive a call\n");
		
		int userChoice = getUserChoice(1, 2);

		Scanner in = new Scanner(System.in);
		Date date;
		
		String type = null;
		
		if (userChoice == 1) {
			type = "Incoming";
		}
		
		else if (userChoice == 2) {
			type = "Outgoing";
		}
		
		ArrayList<String> names = new ArrayList<>();
		ArrayList<Long> phoneNumbers = new ArrayList<>();
		
		for (Contacts contact : phonebook.getContacts()) {
			names.add(contact.getName().toLowerCase());
			phoneNumbers.add(contact.getPhoneNumber());
		}
		
		boolean validCall = false;
		boolean validPhoneNumber;
		Contacts favorite;
		
		while (!validCall) {
			// Get user dial input
			System.out.println("\nDialpad [Enter a contact name, speed dial (1 - 5), or phone number]: ");
			String dial = in.nextLine().trim();
			
			// Check if input is a valid phone number
			// This boolean will be used later if input is not a valid speed dial
			validPhoneNumber = true;
			
			if (dial.length() != 7 && dial.length() != 10) { // Valid phone numbers must be of length 7
				validPhoneNumber = false;
			}
			
			for (int i = 0; i < dial.length(); i++) { // Each character must be a digit
				if (!Character.isDigit(dial.charAt(i))) {
					validPhoneNumber = false;
				}
			}
			
			// First check if user input is a valid speed dial
			if (dial.length() == 1 && Character.isDigit(dial.charAt(0)) && 
			   (Integer.parseInt(dial) >= 1 && Integer.parseInt(dial) <= 5) && 
			   (favoritesList[Integer.parseInt(dial) - 1] != null)) {
				date = new Date();
				
				favorite = favoritesList[Integer.parseInt(dial) - 1];
				Call userCall = new Call(favorite.getName(), favorite.getPhoneNumber(), date, type);
				
				callHistory.addCall(userCall);
				validCall = true;
			}
			
			// Next, check if it's a valid phone number
			// Also check if the number is not the name of a contact
			// Also check if it is not the phone number of a contact
			else if (validPhoneNumber && !names.contains(dial.toLowerCase()) && !phoneNumbers.contains(Long.parseLong(dial))) {
				date = new Date();
				
				Call userCall = new Call(Long.parseLong(dial), date, type);
				callHistory.addCall(userCall);
				
				validCall = true;
			}
			
			// Lastly, check if the input is a name within the phone book
			else if (names.contains(dial.toLowerCase()) || (validPhoneNumber && phoneNumbers.contains(Long.parseLong(dial)))) {
				date = new Date();
				
				if (validPhoneNumber) {
					Call userCall = new Call(names.get(phoneNumbers.indexOf(Long.parseLong(dial))), Long.parseLong(dial), date, type);
					callHistory.addCall(userCall);
				}
				else {
					Call userCall = new Call(dial, phoneNumbers.get(names.indexOf(dial.toLowerCase())), date, type);
					callHistory.addCall(userCall);
				}
				
				validCall = true;
			}
			
			else {
				System.out.println("\nError! Your input is not a valid contact name nor valid phone number.\n\n"
						+ "Please select an option from the following list.\n\n"
						+ "[1] Re-enter dial\n"
						+ "[2] Return to Main Menu\n");
				
				userChoice = getUserChoice(1, 2);
				
				if (userChoice == 2) {
					break;
				}
			}
		}
		
		in.close();
	}
	
	public static void contacts(PhoneBook phonebook) {
		
		System.out.println("\n----------------------------------------------------------------------------------\n"
				         + "                                    Contacts                                        \n"
				         + "----------------------------------------------------------------------------------\n"
				+ "\nPlease select a feauture from the following options:\n\n"
				+ "[1] Add a contact\n"
				+ "[2] Edit a contact\n"
				+ "[3] Delete a contact\n"
				+ "[4] Display contacts in alphabetical order\n");
		
		int userChoice = getUserChoice(1, 4);
		
		if (userChoice == 1) {
			phonebook.addContact();
		}
		
		else if (userChoice == 2) {
			phonebook.displayAllContact();
			
			if (phonebook.getContacts().size() > 0) {
				System.out.print("Enter the contact index in which you wish to edit\n");
				int index = getUserChoice(1, phonebook.sizeOfArray());
				phonebook.editContact(index);
			}
			
			else {
				System.out.println("\nNo contacts to edit!");
			}
		}
		
		else if (userChoice == 3) {
			phonebook.displayAllContact();
			
			if (phonebook.getContacts().size() > 0) {
				System.out.print("Enter the contact index in which you wish to delete.\n");
				int index = getUserChoice(1, phonebook.sizeOfArray());
				phonebook.removeContact(index);
			}
			
			else {
				System.out.println("\nNo contacts to edit!");
			}
		}
		
		else if (userChoice == 4) {
			if (phonebook.getContacts().size() > 0) {
				phonebook.sortContacts();
				phonebook.displayAllContact();
			}
			
			else {
				System.out.println("\nNo contacts to display!");
			}
		}
	}
	
	public static void favorites(PhoneBook phonebook, Favorites favorites, Contacts[] favoritesList) {
		System.out.println("\n----------------------------------------------------------------------------------\n"
				         + "                                    Favorites                                     \n"
				         + "----------------------------------------------------------------------------------\n"
				+ "\nPlease select a feature from the following options:\n\n"
				+ "[1] Add a favorite\n"
				+ "[2] Remove a favorite\n"
				+ "[3] Rearrange favorite preference\n"
				+ "[4] Display favorites one by one\n");
	
		int userChoice = getUserChoice(1, 5);
		Scanner in = new Scanner(System.in);
	
		if (userChoice == 1) {
			phonebook.displayAllContact();
			System.out.print("Enter the phonebook index of the contact you would like to add to you favorites list.\n");
			int choice = getUserChoice(1, phonebook.sizeOfArray());
			
			System.out.println("\nEnter the position (1 - 5) in which you would like to add the favorite.\n");
			int position = getUserChoice(1, 5);
			favorites.addFavorite(phonebook.getContact(choice), position);
			
			System.out.println("Here is your updated favorites:\n");
			favorites.listFavorites();
		}
		
		else if (userChoice == 2) {
			favorites.listFavorites();
			System.out.println("\nEnter the position of the favorite to be removed.\n");;
			int position = getUserChoice(1, 5);
			favorites.removeFavorite(position);
			
			System.out.println("Here is your updated favorites:\n");
			favorites.listFavorites();
		}
		
		else if (userChoice == 3) {
			favorites.listFavorites();
			
			System.out.print("Enter the favorite to be moved (1-5).\n");
			int position = getUserChoice(1, 5);
			
			System.out.println("Enter the position to be moved to (1-5).\n");
			int positionAfter = getUserChoice(1, 5);
			
			favorites.moveFavorite(position, positionAfter);
			
			System.out.println("Here is your updated favorites:\n");
			favorites.listFavorites();
		}
		
		else if (userChoice == 4) {
			favorites.listFavorites();
			
			System.out.print("Enter which preset favorite (1 - 5) you would like to display.\n");
			
			userChoice = getUserChoice(1, 5);
			
			if (favoritesList[userChoice - 1] != null) {
				favorites.displayFavorite(userChoice);
			}
		}
		
		in.close();
	}
	
	public static void displayCallLog(Log callHistory) {
		callHistory.displayLog();
	}
	
	public static int getUserChoice(int firstChoice, int lastChoice) {
		
		boolean error = false;
		int userChoice = 0;
		
		// Validate that the input is of type integer
		do {
			try {
				System.out.print("Enter choice: ");
				Scanner in = new Scanner(System.in);
				userChoice = in.nextInt();
				
				// Validate that the input is between the choice options (inclusive)
				while (userChoice < firstChoice || userChoice > lastChoice) {
					System.out.println("Error! Please choose an option " + firstChoice + "-" + lastChoice + ".\n");
					System.out.print("Enter choice: ");
					userChoice = in.nextInt();
				}
				
				// If input made it this far, it must be valid
				in.close();
				error = false;
			}
			catch(InputMismatchException e) {
				System.out.println("Error! Enter an integer.\n");
				error = true;
			}
		}
		while (error);

		return userChoice;
	}

}