/**
 * Assignment 4, 30-Nov-19
 * this interface in required to be implemented by an object, if that object is to be stored in binary tree implementation DataStructure
 *
 * @author Van Nam Doan 040943291
 * @version 1.0
 */
public interface DataInterface extends Comparable<DataInterface> {

    /**
     * representation of the data when display relations
     *
     * @return String representing the data
     */
    String displayData();

    /**
     * rasterize data into string for writing to file
     *
     * @return rasterized data
     */
    String toFile();

    @Override
    int compareTo(DataInterface o);
}
