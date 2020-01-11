import java.util.ArrayList;
import java.util.Comparator;

/**
 * blueprint for a node, contains various end methods for manipulating, displaying and writing the tree
 */
public class DataNode {
    private static Comparator<DataNode> compare = new Comparator<DataNode>() {
        /**
         * Comparator implementation
         *
         * @param o1 DataNode object to compare
         * @param o2 DataNode object to be compared with
         * @return String compare of the names if names are different, String compare the phone number if not
         */
        @Override
        public int compare(DataNode o1, DataNode o2) {
            return 0;
        }
    };
    private String phoneNum;
    private String name;
    private DataNode leftNode;
    private DataNode rightNode;

    /**
     * DataNode constructor
     *
     * @param name     name of contact
     * @param phoneNum phone number of contact
     */
    DataNode(String name, String phoneNum) {
        this.phoneNum = phoneNum;
        this.name = name;
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
        if (DataNode.compare.compare(this, node) < 0) {
            if (this.leftNode == null && !searchMode) {
                this.leftNode = node;
            } else if (this.leftNode != null) {
                return leftNode.addAndSearchNode(node, searchMode);
            }
        } else if (DataNode.compare.compare(this, node) > 0) {
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
        StringBuilder tmpPhoneNum = new StringBuilder();
        for (int i = 0; i < phoneNum.length(); i++) {
            if (i == 1 || i == 4 || i == 7) {
                tmpPhoneNum.append("-");
            }
            tmpPhoneNum.append(phoneNum.charAt(i));
        }
        if (leftNode != null) {
            tmpToString += leftNode.toString() + "\n";
        }
        tmpToString += name + ": " + tmpPhoneNum;
        if (rightNode != null) {
            tmpToString += "\n" + rightNode.toString();
        }
        return tmpToString;
    }

    /**
     * return the responsibility display message for the current node and its children, skip itself if one or more of its children has the same name
     *
     * @return responsibility display message
     */
    String responsibility() {
        boolean noContact = true;
        boolean leftNodeDisplayed = false;
        if (leftNode == null && rightNode != null && rightNode.name.equals(name)) {
            return rightNode.responsibility();
        }
        if (rightNode == null && leftNode != null && leftNode.name.equals(name)) {
            return leftNode.responsibility();
        }
        String tmp = name + " calls ";
        if (leftNode != null && !leftNode.name.equals(name)) {
            tmp = leftNode.responsibility() + "\n" + tmp;
            tmp += leftNode.name;
            noContact = false;
            leftNodeDisplayed = true;
        }
        if (rightNode != null && !rightNode.name.equals(name)) {
            if (leftNodeDisplayed) {
                tmp += " and ";
            }
            tmp += rightNode.name;
            tmp += "\n" + rightNode.responsibility();
            noContact = false;
        }
        if (noContact) {
            tmp += "No Contacts";
        }
        return tmp;
    }

    ArrayList<String> toFile(ArrayList<String> tmp) {
        if (leftNode != null) {
            leftNode.toFile(tmp);
        }
        tmp.add(name);
        tmp.add(phoneNum);
        if (rightNode != null) {
            rightNode.toFile(tmp);
        }
        return tmp;
    }
}
