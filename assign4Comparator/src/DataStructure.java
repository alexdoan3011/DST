import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * store reference to the root, and various intermediate methods for manipulating, displaying and writing the tree
 */
class DataStructure {
    private DataNode root;

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
    boolean addAndSearchNode(DataNode node, boolean searchMode) {
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
     * check if tree has any node in it, return the responsibility display message if there is, "List is Empty" if there is not
     *
     * @return responsibility display message
     */
    String responsibilities() {
        if (root == null) {
            return "List is Empty";
        }
        return root.responsibility();
    }

    /**
     * write the tree into file
     *
     * @param fileName file to be written to
     * @return true if file writing processes successfully
     */
    boolean toFile(String fileName) {
        ArrayList<String> tmp = new ArrayList<>();
        if (root != null) {
            tmp = root.toFile(tmp);
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
