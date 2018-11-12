import java.util.Date;

/**
 * This class will construct call objects containing
 * the associated date, type (incoming/outgoing),
 * name, and phone number
 * 
 *
 */
public class Call {
    private Date date;
    private String type;
    private String name;
    private long phoneNumber;

    /**
     * This is the default constructor that
     * initializes each instance variable
     * to null
     * **/
    public Call(long phoneNumber, Date date, String type) {
        this.date = date;
        this.type = type;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Overloaded constructor that initializes a call object
     * with the phone number, date, time, and type of
     * call (Incoming, outgoing)
     *
     * @param name
     * @param date
     * @param type
     * **/
    public Call(String name, long phoneNumber, Date date, String type) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.phoneNumber = phoneNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
    	return date;
    }

    public String getType() {
    	return type;
    }
}
