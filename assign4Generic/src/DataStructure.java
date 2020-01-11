import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Assignment 4, 30-Nov-19
 * store reference to the root, and various intermediate methods for manipulating, displaying and writing the tree
 * generic binary tree data structure implementation, requires object to be stored to implement DataInterface interface
 *
 * @author Van Nam Doan 040943291
 * @version 1.0
 */
class DataStructure<T extends DataInterface> {
    private DataNode<T> root;

    DataStructure() {
        root = null;
    }

    /**
     * (searchMode == false) add a DataNode object to the tree. Return true if successful
     * (searchMode == true) look up a DataNode object on the tree. Return true if found
     *
     * @param node       DataNode object as subject for adding (searchMode == false) or for look up (searchMode == true)
     * @param searchMode true if in searchMode. Method will not add a node in search mode
     * @return true if node successfully added (searchMode == false). True if node found (searchMode == true)
     */
    boolean addAndSearchNode(DataNode<T> node, boolean searchMode) {
        if (!searchMode && root == null) {
            root = node;
            return true;
        } else if (!searchMode) {
            return root.addAndSearchNode(node, false);
        }
        return !root.addAndSearchNode(node, true);
    }

    /**
     * check if tree has any node in it, return the display message if there is
     *
     * @return display message
     */
    @Override
    public String toString() {
        if (root == null) {
            return "List is Empty";
        }
        return root.toString();
    }

    /**
     * check if tree has any node in it, return the connection display message if there is, "List is Empty" if there is not
     *
     * @param relation string to display relation between parent and child
     * @param deadEnd  string to display leaf
     * @return data tree recursive display message
     */
    String displayData(String relation, String deadEnd) {
        if (root == null) {
            return "List is Empty";
        }
        return root.displayTree(relation, deadEnd);
    }

    /**
     * save the binary tree data structure in to a file. File will be written in alphabetical order. Connections are not saved
     *
     * @param fileName file to be written to
     * @return true if file writing processes successfully
     */
    boolean toFile(String fileName) {
        ArrayList<String> tmp = new ArrayList<>();
        if (root != null) {
            root.toFile(tmp);
        }
        Path file = Paths.get(fileName);
        try {
            Files.write(file, tmp, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
