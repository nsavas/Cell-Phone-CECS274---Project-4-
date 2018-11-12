/**
 * This class will implement the cell phones
 * favorites feature by allowing the user
 * to add 5 favorites, insert a favorite by position,
 * move a favorite to a different position, and display
 * the favorites within the cell phone class
 **/
public class Favorites {
    /**
     * The only instance variable for this class
     * will be a string array of the users favorites
     **/
    private static Contacts favorites[] = new Contacts[5];

    /**
     * gets the Contact at a given position
     *
     * @param position - the position (1-5) of the Contact to be retrieved
     * @return the contact at the given position
     **/
    public static Contacts getFavorite(int position) {
        return favorites[position - 1];
    }

    /**
     * This method will allow the user to
     * add a favorite to the array of
     * favorites
     *
     * @param name-    the contact to be added to favorites
     * @param position - the position where the contact is to be added
     **/
    public void addFavorite(Contacts name, int position) {
        //for (int i = 4; i > position - 1; i--) {
        //    favorites[i] = favorites[i - 1];
        //}
        //favorites[position - 1] = name;
    	favorites[position - 1] = name;
    }

    /**
     * This method allows the user to move a
     * favorite to a different position
     *
     * @param position    - the position of the contact to be moved
     * @param newPosition - the new position the contact is to be moved
     */
    public void moveFavorite(int position, int newPosition) {
        Contacts temp = favorites[position - 1];
        if (position == newPosition) {
            //Do nothing
        } else if (position > newPosition) {
            for (int i = position - 1; i > newPosition - 1; i--) {
                favorites[i] = favorites[i - 1];
            }
            favorites[newPosition - 1] = temp;
        } else if (position < newPosition) {
            for (int i = position - 1; i < newPosition - 1; i++) {
                favorites[i] = favorites[i + 1];
            }
            favorites[newPosition - 1] = temp;
        }
    }

    /**
     * This method will remove a favorite from
     * the list by passing in the corresponding
     * name as a parameter
     *
     * @param position - the position of the contact to be removed
     * @param name     - the contact to be added in place of the removed contact
     **/
    public void removeFavorite(int position) {
      for(int i = position; i < 4; i++){
        favorites[i - 1] = favorites[i];
      }
      favorites[4] = null;
    }

    /**
     * This method will display a single favorite
     * from the list of favorites as well as display
     * an image of the person
     *
     * @param position -
     **/
    public void displayFavorite(int position) {
        Contacts favorite = favorites[position - 1];
        FavoriteContactFrame display;
        display = new FavoriteContactFrame("C:\\Users\\nsav\\Documents\\" + "img.jpg", 900, 600, favorite.getName(), favorite.formatNumber(), favorite.getEmail(), favorite.getNotes());
        display.displayContactFrame();
    }

    /**
     * This method will list the five favorites
     **/
    public void listFavorites() {
        for (int i = 0; i < 5; i++) {
            if (favorites[i] == null) {
                System.out.println("[" + (i+1) + "] " + "\n");
            }
            else if (favorites[i] != null) {
            	System.out.println("[" + (i+1) + "]" + "\nName: " + favorites[i].getName() + "\nPhone Number: " + favorites[i].getPhoneNumber() + "\nEmail: " + favorites[i].getEmail() + "\n");
            }
        }
    }

    /**
     * This method allows the user to get the
     * array list of favorites
     *
     * @return favorites
     */
    public Contacts[] getFavorites() {
        return favorites;
    }
}