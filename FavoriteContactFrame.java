import java.awt.*;

import javax.swing.*;

/**
 * This class helps load and display an image from a file.
 * Original source:  http://beginwithjava.blogspot.com/2009/01/loading-and-displaying-image-in-java.html
 *
 * @version 1.2
 * @since 2016-10-09
 */
public class FavoriteContactFrame extends JPanel {

    private Image image; // Declare a name for our Image object.
    private Dimension window;  //Declare the dimensions of the window.
    private String contactName;
    private String contactNumber;
    private String contactEmail;
    private String contactNote;

    /**
     * Initializes the JPanel instance variables to default values and image to null
     */
    public FavoriteContactFrame() {

        super();  //Calling parent constructor to initialize all the instance variables in the JPanel class
        image = null;
        window = new Dimension(600, 400);  //Sets default window dimensions of 600 pixels wide X 400 pixel high
        this.contactName = "N/a";
        this.contactNumber = "N/a";
        this.contactEmail = "N/a";
        this.contactNote = "N/a";

    }

    /**
     * Initializes the JPanel instance variables to default values and gets image with given directory
     *
     * @param directory - the location of the image to be displayed
     */
    public FavoriteContactFrame(String directory, int fWidth, int fHeight, String name, String number, String email, String note) {
        super();
        this.window = new Dimension(fWidth, fHeight);    //Sets window dimensions of fWidth pixels wide X fHeight pixel high
        this.image = Toolkit.getDefaultToolkit().getImage(directory);  // Load an image file into our Image object.
        this.contactName = name;
        this.contactNumber = number;
        this.contactEmail = email;
        this.contactNote = note;
    }


    /**
     * Sets the image to be displayed in the frame
     *
     * @param path - the location of the image, as a string
     */
    public void setImage(String path) {

        this.image = Toolkit.getDefaultToolkit().getImage(path);  // Load an image file into our Image object.

    }

    /**
     * Sets the dimensions of the frame
     *
     * @param fWidth  - the width of the frame, as an integer
     * @param fHeight - the height of the frame, as an integer
     */
    public void setDimensions(int fWidth, int fHeight) {
        this.window.width = fWidth;
        this.window.height = fHeight;
    }

    /**
     * Overrides the one in JPanel. This is where the drawing happens. We don't have to call it in our program, it gets called
     * automatically whenever the panel needs to be redrawn, like when it it made visible or moved.
     *
     * @param g - Graphics object used to draw the image
     */
    public void paintComponent(Graphics g) {

        // Resizing our image to fit the window
        Dimension originalImg = new Dimension(image.getWidth(this), image.getHeight(this));
        Dimension resizedImg = getScaledDimension(originalImg, window);

        Graphics2D g2d = (Graphics2D) g.create();
        //g2d.rotate(Math.toRadians(90), resizedImg.width / 2, resizedImg.height / 2); //rotate 90 deg, clockwise about the center of the image
        // Draw our Image object.
        g2d.drawImage(image, 10, 10, resizedImg.width, resizedImg.height, this); // at location 10,10, with scaled width and height
        g2d.dispose();

        //Draw other text
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString(contactName, resizedImg.width + 100, 100);
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.drawString("Number: " + contactNumber, resizedImg.width + 100, 150);
        g.drawString("Email: " + contactEmail, resizedImg.width + 100, 200);
        g.drawString("Notes: " + contactNote, resizedImg.width + 100, 250);

    }

    /**
     * Displays the image of the this object
     */
    public void displayContactFrame() {

        //Creating the window
        JFrame frame = new JFrame("Favorite Contact: " + this.contactName);  //title of the frame will be "Favorite Contact"
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Exits the window when it is closed
        frame.setSize(window.width, window.height);            //Sets the window size

        //Displaying the image in the frame
        frame.setContentPane(this);
        frame.setVisible(true);
    }


    // helper method that scales the image based on window dimensions
    private static Dimension getScaledDimension(Dimension imgSize, Dimension windowSize) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int window_width = windowSize.width;
        int window_height = windowSize.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > window_width / 2) {
            //scale width to fit within 1/2 the window width
            new_width = window_width / 2;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > window_height) {
            //scale height to fit
            new_height = window_height - 50;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    /*--------------- Class Tester ----------------*/
    public static void main(String arg[]) {
        int fWidth = 900;
        int fHeight = 600;
        // "/src/Projects/Project3/" + "img.jpg"
        FavoriteContactFrame contact2 = new FavoriteContactFrame("C:\\Users\\nsav\\Documents\\" + "img.jpg", fWidth, fHeight, "Kathy", "(555) 555-555", "kapiva@ucla.edu", "Some notes");
        contact2.displayContactFrame();
    }
}