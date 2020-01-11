import java.util.ArrayList;

/**
 * Assignment 4, 30-Nov-19
 * blueprint for a node, contains various end methods for manipulating, displaying and writing the tree
 * generic binary tree data structure implementation, requires object to be stored to implement DataInterface interface
 *
 * @author Van Nam Doan 040943291
 * @version 1.0
 */
public class DataNode<T extends DataInterface> {
    private T data;
    private DataNode leftNode;
    private DataNode rightNode;

    /**
     * DataNode constructor
     *
     * @param data data to be stored in tree
     */
    DataNode(T data) {
        this.data = data;
        leftNode = null;
        rightNode = null;
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
        if (node.compareTo(this) < 0) {
            if (this.leftNode == null && !searchMode) {
                this.leftNode = node;
            } else if (this.leftNode != null) {
                return leftNode.addAndSearchNode(node, searchMode);
            }
        } else if (node.compareTo(this) > 0) {
            if (this.rightNode == null && !searchMode) {
                this.rightNode = node;
            } else if (this.rightNode != null) {
                return rightNode.addAndSearchNode(node, searchMode);
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * return the display message for the current node and its children
     *
     * @return display message
     */
    @Override
    public String toString() {
        String tmpToString = "";
        if (leftNode != null) {
            tmpToString += leftNode.toString() + "\n";
        }
        tmpToString += this.data.toString();
        if (rightNode != null) {
            tmpToString += "\n" + rightNode.toString();
        }
        return tmpToString;
    }

    /**
     * return the relation display message for the current node and its children
     *
     * @return relation display message
     */
    String displayTree(String relation, String deadEnd) {
        boolean isLeaf = true;
        boolean leftNodeDisplayed = false;
        String tmp = data.displayData() + " " + relation + " ";
        if (leftNode != null) {
            tmp = leftNode.displayTree(relation, deadEnd) + "\n" + tmp;
            tmp += leftNode.data.displayData();
            isLeaf = false;
            leftNodeDisplayed = true;
        }
        if (rightNode != null) {
            if (leftNodeDisplayed) {
                tmp += " and ";
            }
            tmp += rightNode.data.displayData();
            tmp += "\n" + rightNode.displayTree(relation, deadEnd);
            isLeaf = false;
        }
        if (isLeaf) {
            tmp += deadEnd;
        }
        return tmp;
    }

    /**
     * Comparable implementation
     *
     * @param o DataNode object to compare to
     * @return String compare of the names if names are different, String compare the phone number if not
     */
    private int compareTo(DataNode o) {
        return this.data.compareTo(o.data);
    }

    void toFile(ArrayList<String> tmp) {
        if (leftNode != null) {
            leftNode.toFile(tmp);
        }
        tmp.add(data.toFile());
        if (rightNode != null) {
            rightNode.toFile(tmp);
        }
    }
}
