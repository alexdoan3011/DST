/**
 * Represents the Binary Tree data structure
 *
 * @author Linda Crane
 * @author Melissa Sienkiewicz
 */
class BinaryTree {

    /**
     * Reference to the root Node of the tree
     */
    private BinaryTreeNode root = null;

    /**
     * Insert the data into the tree
     *
     * @param newData New int to store in the tree
     */
    void insertInTree(int newData) {
        if (root == null)
            root = new BinaryTreeNode(newData);
        else
            root.insert(newData);
    }

    /**
     * Method to display the contents of the tree
     */
    void displayInOrder() {
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

    int displayHeight(BinaryTreeNode currentNode, int height) {
        if (root == null) {
            return 0;
        }
        height++;
        if (currentNode == null) {
            currentNode = root;
        }
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            return height;
        } else {
            if (currentNode.getLeft() == null) {
                return displayHeight(currentNode.getRight(), height);
            } else if (currentNode.getRight() == null) {
                return displayHeight(currentNode.getLeft(), height);
            } else {
                return Math.max(displayHeight(currentNode.getRight(), height), displayHeight(currentNode.getLeft(), height));
            }
        }
    }
}
