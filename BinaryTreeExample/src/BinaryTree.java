/**
 * Represents the Binary Tree data structure
 *
 * @author Linda Crane
 * @author Melissa Sienkiewicz
 */
public class BinaryTree {

    /**
     * Reference to the root Node of the tree
     */
    BinaryTreeNode root = null;

    /**
     * Insert the data into the tree
     *
     * @param newData New int to store in the tree
     */
    public void insertInTree(int newData) {
        if (root == null)
            root = new BinaryTreeNode(newData);
        else
            root.insert(newData);
    }

    /**
     * Method to display the contents of the tree
     */
    public void displayInOrder() {
        displayInOrder(root);
    }

    /**
     * Traverse the tree using InOrder traversal and display the content to the console
     *
     * @param subRoot The node to start with
     */
    private void displayInOrder(BinaryTreeNode subRoot) {
        if (subRoot == null)
            return;
        displayInOrder(subRoot.getLeft());
        System.out.print(" " + subRoot.getData() + " ");
        displayInOrder(subRoot.getRight());
    }
}
