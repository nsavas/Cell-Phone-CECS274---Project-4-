import java.util.Scanner;

/**
 * Contact class with name, email, phone number, notes
 */
public class Contacts implements Comparable<Object> {

    private String name;
    private String email;
    private long phoneNumber;
    private String notes;

    /**
     * Default constructor that initializes name to N/A, email to N/A
     * phone number to 0, and notes to N/A
     **/
    public Contacts() {
        name = "N/A";
        email = "N/A";
        phoneNumber = 0L;
        notes = "N/A";
    }

    /**
     * overloaded constructor initialize the contact's details
     *
     * @param name        - the name of the contact as a string
     * @param email       - the email of the contact as a string
     * @param phoneNumber - the phone number of the contact as an long
     * @param notes       - the notes of the contact as a string
     **/
    public Contacts(String name, String email, long phoneNumber, String notes) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    /**
     * gets the contacts name
     *
     * @return the name of the contact as a String
     **/
    public String getName() {
        return name;
    }

    /**
     * gets the contacts PhoneNumber
     *
     * @return the phone number of the contact as a long
     **/
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * gets the contacts email
     *
     * @return the email of the contact as a String
     **/
    public String getEmail() {
        return email;
    }

    /**
     * gets the contacts notes
     *
     * @return the notes of the contact as a String
     **/
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the name of the Contact
     **/
    public void setName(String s) {
        name = s;
    }

    /**
     * Sets the email of the contact
     *
     * @param s the email to be set as a string
     **/
    public void setEmail(String s) {
        email = s;
    }

    /**
     * Sets the name of the contact
     *
     * @param l the 7 or 10 digit phone number of the contact
     **/
    public void setPhoneNumber(long l) {
        phoneNumber = l;
    }

    /**
     * Sets the notes of the contact
     *
     * @param s the notes to be set as a string
     **/
    public void setNotes(String s) {
        notes = s;
    }

    /**
     * formats the output of phoneNumber
     *
     * @return the formated phone number as a String
     **/
    public String formatNumber() {
        String s;
        if (phoneNumber / 1_000_000_000L > 0) {
            long n1 = phoneNumber / 10_000_000L;
            long n2 = (phoneNumber - n1 * 10_000_000L) / 10_000;
            long n3 = (phoneNumber - n1 * 10_000_000L) - n2 * 10_000;
            s = String.format("(%d) %d-%d", n1, n2, n3);
        } else {
            long num1 = phoneNumber / 10_000;
            long num2 = phoneNumber - num1;
            s = String.format("%d-%d", num1, num2);
        }
        return s;
    }

    /**
     * formats the output of notes
     *
     * @return the formated notes
     **/
    public String formatNotes() {
        return notes;
    }

    /**
     * formats the output of class Contact
     *
     * @return the notes of the contact as a String
     **/
    public String toString() {
        return String.format("Name: %s%n", name);
    }

    /**
     * compares two objects of class Contact, used to sort
     *
     * @return an integer -1,0,1 depending on which name comes first alphabetically
     **/
    public int compareTo(Object o) {
        Contacts otherContact = (Contacts) o;

        return name.compareToIgnoreCase(otherContact.getName());
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
            System.out.print("Enter 7 or 10 digit number: ");
            if (keyboard.hasNextLong()) {
                num = keyboard.nextLong();
                clr = keyboard.nextLine();
                if (num / 1_000_000_000L > 0 && num / 1_000_000_000L < 10) {
                    loop = false;
                } else if (num / 1_000_000L > 0 && num / 1_000_000L < 10) {
                    loop = false;
                } else {
                    System.out.println("Number entered must be 5 or 7 digits");
                    loop = true;
                }
            } else {
                System.out.println("Input is not a number");
                clr = keyboard.nextLine();
                loop = true;
            }
        } while (loop);
        return num;
    }
}