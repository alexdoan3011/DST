/**
 * Assignment 4, 30-Nov-19
 * an object to be stored in binary tree DataStructure
 *
 * @author Van Nam Doan 040943291
 * @version 1.0
 */
public class Data implements DataInterface {
    private String phoneNum;
    private String name;

    /**
     * data constructor for a contact data
     *
     * @param name     name of this contact
     * @param phoneNum phone number of this contact
     */
    Data(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    /**
     * denormalize phone number from ########### to #-###-###-#### in prepare for toString method
     *
     * @param phoneNum phone number to be denormalized
     * @return denormalized phone number
     */
    private String phoneNameDenormalize(String phoneNum) {
        StringBuilder tmpPhoneNum = new StringBuilder();
        for (int i = 0; i < phoneNum.length(); i++) {
            if (i == 1 || i == 4 || i == 7) {
                tmpPhoneNum.append("-");
            }
            tmpPhoneNum.append(phoneNum.charAt(i));
        }
        return tmpPhoneNum.toString();
    }

    /**
     * return the display message for the current node with name of contact and denormalized phone number
     *
     * @return display message
     */
    @Override
    public String toString() {
        String tmpPhoneNum;
        tmpPhoneNum = phoneNameDenormalize(phoneNum);
        return name + ": " + tmpPhoneNum;
    }

    /**
     * method implementation of DataInterface
     * representation of the data when display relations
     *
     * @return String representing the data
     */
    @Override
    public String displayData() {
        return name;
    }

    /**
     * method implementation of DataInterface
     * rasterize data into string for writing to file
     *
     * @return rasterized data
     */
    public String toFile() {
        return name + "\n" + phoneNum;
    }

    /**
     * Comparable implementation required by DataInterface
     *
     * @param o DataNode object to compare to
     * @return String compare of the names if names are different, String compare the phone number if not
     */
    @Override
    public int compareTo(DataInterface o) {
        return this.toString().toLowerCase().compareTo(o.toString().toLowerCase());
    }
}
