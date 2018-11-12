import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class will keep a log of
 * the users call history by storing and
 * formatting the calls within an
 * array list of call objects
 *
 * Call data includes the phone number,
 * data, time, and the type of call (outgoing/incoming)
 * **/
 public class Log {
	
	/**
	 * Instance variables: 
	 * ArrayList<Call> calls
	 **/
	private ArrayList<Call> calls;
    
	/**
	 * Default Constructor
	 * Initializes calls to a new ArrayList of call objects
	 **/
    public Log() {
    	calls = new ArrayList<Call>();
    }

    /**
     * This method will allow call objects to be
     * added to the log
     * @param userCall
     * **/
    public void addCall(Call userCall) {
    	calls.add(userCall);
    }
    
    /**
     * This method will display the call history
     * 
     * Will also allow the user to select a specific name or number
     * and display any calls from that input
     * 
     * If two or more calls are made from the same person on the same day,
     * only one instance of the call is displayed but the information
     * can be accessed via user input.
     */
    public void displayLog() {
    	Call userCall;
    	
    	Scanner in = new Scanner(System.in);
    	
    	int sameCallCount = 0; // Counts number of calls from the same number within the calls array list
    	int printCount = 0; // Used to number each printed call instance
    	ArrayList<Long> printedCalls = new ArrayList<Long>(); // List of phone numbers that have already been printed
    	
    	for (int i = 0; i < calls.size(); i++) {
    		userCall = calls.get(i);
    		sameCallCount = 0;
    		
    		// For each call, count how many calls were made from that number
    		for (int j = 0; j < calls.size(); j++) {
    			if (calls.get(j).getPhoneNumber() == userCall.getPhoneNumber()) {
    				sameCallCount += 1; 
    			}
    		}
    		
    		// If the number has not already been printed, go and print it
    		// This if statement prevents calls from being printed multiple times
    		if (!printedCalls.contains(userCall.getPhoneNumber())) {
    			if (sameCallCount == 1) {
    				// At this point, the call information is going to be printed
    				// Add the call to printed calls and increment print count 
    				printedCalls.add(userCall.getPhoneNumber());
    				printCount += 1;
    				
    				// Display all the information about the call
    				System.out.print("[" + printCount + "]" + "\nName: " + userCall.getName()
    						+ "\nPhone Number: " + userCall.getPhoneNumber() 
    						+ "\nDate: " + userCall.getDate() 
    						+ "\nType: " + userCall.getType()
    						+ "\n\n");
    			}
    			
    			else if (sameCallCount > 1) {
    				printedCalls.add(userCall.getPhoneNumber());
    				printCount += 1;
    				
    				// If the call is associated with a contact name, display the contact name
    				// If not, display the phone number
    				if (userCall.getName() == null) {
    					System.out.print("[" + printCount + "] " + "(" + sameCallCount + ") "
    							+ userCall.getPhoneNumber() + "\n\n");
    				}
    				
    				else if (userCall.getName() != null) {
    					System.out.print("[" + printCount + "] " + "(" + sameCallCount + ") "
    							+ userCall.getName() + "\n\n");
    				}
    			}
    		}
     	}
    	
    	System.out.println("Would you like to access the call information for any of the above calls?\n");
    	System.out.println("[1] Yes\n"
    			+ "[2] No\n");
    	
    	int userChoice = getUserChoice(1, 2);
    	boolean validInput = false;
    	
    	while (!validInput && userChoice == 1) {
    		System.out.println("Enter a name or phone number: ");
    		String userInput = in.nextLine().trim();
    	
    		Call call;
    		boolean validPhoneNumber = true;
    		printCount = 0;
    	
    		// Check if the input is a valid phone number
    		if (userInput.length() != 7 && userInput.length() != 10) { // Valid phone numbers must be of length 7
    			validPhoneNumber = false;
    		}
		
    		for (int i = 0; i < userInput.length(); i++) { // Each character must be a digit
    			if (!Character.isDigit(userInput.charAt(i))) {
    				validPhoneNumber = false;
    			}
    		}
		
        	for (int i = 0; i < calls.size(); i++) {
        		call = calls.get(i); 
    			// Check if the input matches a contact name
    			if ((validPhoneNumber == false) && (call.getName() != null) && userInput.toLowerCase().equals(call.getName().toLowerCase())) {
    				printCount += 1;
    				validInput = true;
    				System.out.println("[" + printCount + "]" + "\nName: " + call.getName()
						+ "\nPhone Number: " + call.getPhoneNumber()
						+ "\nDate: " + call.getDate() 
						+ "\nType: " + call.getType()
						+ "\n\n");
    			}
			
    			else if (validPhoneNumber && call.getPhoneNumber() == Long.parseLong(userInput)) {
    				printCount += 1;
    				validInput = true;
    				System.out.println("[" + printCount + "]" + "\nName: " + call.getName()
    					+ "\nPhone Number: " + call.getPhoneNumber()
    					+ "\nDate: " + call.getDate() 
    					+ "\nType: " + call.getType()
    					+ "\n\n");
    			}
    		}
    		
    		if (validInput) {
    			System.out.println("Would you like to access more call information?\n");
    			System.out.println("[1] Yes\n"
    					+ "[2] No\n");
    			
    			userChoice = getUserChoice(1, 2);
    			if (userChoice == 1) {
    				validInput = false;
    			}
    		}
    		
    		else {
    			System.out.println("Could not find any calls matching that name or phone number!\n");
    			System.out.println("[1] Try again\n"
    					+ "[2] Return to main menu\n");
    			
    			userChoice = getUserChoice(1, 2);
    			if (userChoice == 1) {
    				validInput = false;
    			}
    		}
    	}
    }
    
    /**
     * Input validation method
     * 
     * Uses a InputMismatchException to catch type errors
     * Within the try body is where the range of integers
     * is validated
     * @param firstChoice
     * @param lastChoice
     * @return
     */
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