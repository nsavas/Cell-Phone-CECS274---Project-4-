import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;


public class PhoneBook {

	/**
	 * The only instance variable for this class will
	 * be an array list of contact objects
	 */
    private ArrayList<Contacts> contacts;

    /**
     * Default constructor
     * Instantiates the contacts list to an empty arrayList of
     * contact objects
     */
    public PhoneBook() {
        contacts = new ArrayList<Contacts>();
    }

    /**
     * This is a get method to retrieve the
     * array list of contact objects (Phonebook)
     * @return
     */
    public ArrayList<Contacts> getContacts() {
        return contacts;
    }
    
    /**
     * returns the contact at a given index
     * @param index - the index of the contact to be returned
     * **/
    public Contacts getContact(int index){
      return contacts.get(index - 1);
    }
    
    /**
     * adds Contact to PhoneBook
     *
     * @param c the Contact object to be added to phoneBook
     **/
    public void addContact() {
        Scanner keyboard = new Scanner(System.in);
        
        //Get the name of the contact
        System.out.print("Enter the name of contact: ");
        String name = keyboard.nextLine().trim();
        //Get the email of the contact
        System.out.print("Enter the email of contact: ");
        String email = keyboard.nextLine().trim();
        //Get the phone number of the contact
        long num = get7Or10DigitLong();
        //Get the notes of the contact
        System.out.print("Enter any additional notes: ");
        String notes = keyboard.nextLine().trim();
        
        Contacts c = new Contacts(name,email,num,notes);
        
        contacts.add(c);
    }

    /**
     * sorts Contacts in PhoneBook by name
     **/
    public void sortContacts() {
        Collections.sort(contacts);
    }

    /**
     * removes Contacts from PhoneBook
     *
     * @param index the index of the object to be removed
     **/
    public void removeContact(int index) {
        if (contacts.size() == 0) {
            System.out.println("There are no contacts to be removed!");
        } else if (index > contacts.size() || index < 0) {
            System.out.println("Index is out of range");
        } else {
            contacts.remove(index - 1);
        }
    }

    /**
     * display a Contact from Phonebook
     *
     **/
    public void displayContact(int index) {
      System.out.println(contacts.get(index - 1));
    }
    
    /**
     * display all Contacts from Phonebook
     *
     **/
    public void displayAllContact() {
      int i = 1;
      for(Contacts temp : contacts){
        System.out.println("\n" + "[" + i + "] " + "\n" + "Name: " + temp.getName() + "\n" + "Phone Number: " + temp.getPhoneNumber() + "\n" + "Email: " + temp.getEmail());
        i++;
      }
      
      System.out.println("\n");
    }

    /**
     * edit the Contact from Phonebook
     *
     * @param c the Contact object to be edited
     **/
    public void editContact(int index) {
        boolean loop = true;
        
        do {
            displayContact(index);
            System.out.println("Please select the attribute you wish to edit:\n"
                    + "[1] Name\n"
                    + "[2] Email\n"
                    + "[3] Phone Number\n"
                    + "[4] Notes\n"
                    + "[5] Exit\n");

            Scanner keyboard = new Scanner(System.in);
            int userChoice = getUserChoice(1, 5);

            if (userChoice == 1) {
            	System.out.println("\nCurrent name: " + contacts.get(index - 1).getName() + "\n");
                System.out.print("Enter the new name of contact: ");
                
                contacts.get(index - 1).setName(keyboard.nextLine().trim());
            } 
            
            else if (userChoice == 2) {
            	System.out.println("\nCurrent email: " + contacts.get(index - 1).getEmail() + "\n");
                System.out.print("Enter the new email of contact: ");
                
                contacts.get(index - 1).setEmail(keyboard.nextLine().trim());
            } 
            
            else if (userChoice == 3) {
            	System.out.println("\nCurrent phone number: " + contacts.get(index - 1).getPhoneNumber() + "\n");
                
                contacts.get(index - 1).setPhoneNumber(get7Or10DigitLong());
            } 
            
            else if (userChoice == 4) {
            	System.out.println("\nCurrent notes: " + contacts.get(index - 1).getNotes() + "\n");
                System.out.print("Enter the new notes for contact: \n");
                
                contacts.get(index - 1).setNotes(keyboard.nextLine().trim());
            } 
            
            else if (userChoice == 5) {
                loop = false;
            }
            
        } while (loop);
    }

    /**
     * Method that gets the phone number from the user. The phone number must be
     * 7 or 10 digits long
     *
     * @return the 7 or 10 digit long phone number
     */
    public long get7Or10DigitLong() {
        boolean loop;
        long num = 0;
        String clr;
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.print("Enter a 7 or 10 digit phone number: ");
            if (keyboard.hasNextLong()) {
                num = keyboard.nextLong();
                clr = keyboard.nextLine();
                if (num / 1_000_000_000L > 0 && num / 1_000_000_000L < 10) {
                    loop = false;
                } else if (num / 1_000_000L > 0 && num / 1_000_000L < 10) {
                    loop = false;
                } else {
                    System.out.println("Number entered must be 7 or 10 digits! ");
                    loop = true;
                }
            } else {
                System.out.println("Input is not a number :(");
                clr = keyboard.nextLine();
                loop = true;
            }
    	} while (loop);
        return num;
    }
    
    /**
     * Returns the size of the phonebook
     * @return the size of phonebook as an int
     * **/
    public int sizeOfArray(){
      return contacts.size();
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