/**
 * Represents one node in a Binary Tree
 *
 * @author Linda Crane
 * @author Melissa Sienkiewicz
 */
public class BinaryTreeNode {

    /**
     * Stores the integer of this node
     */
    private int data;
    /**
     * Reference to the left BinaryTree
     */
    private BinaryTreeNode left;
    /**
     * Reference to the right BinaryTree
     */
    private BinaryTreeNode right;

    /**
     * Default Constructor
     */
    public BinaryTreeNode() {
        left = null;
        right = null;
        data = 0;
    }

    /**
     * Constructor which specifies the data to be stored
     *
     * @param data - Data to use when creating this node.
     */
    public BinaryTreeNode(int data) {
        left = null;
        right = null;
        this.data = data;
    }

    /**
     * Returns the data stored in this node
     *
     * @return Data member variable
     */
    public int getData() {
        return data;
    }

    /**
     * Returns the left Binary Tree
     *
     * @return Left binary tree
     */
    public BinaryTreeNode getLeft() {
        return left;
    }

    /**
     * Returns the right Binary Tree
     *
     * @return Right binary tree
     */
    public BinaryTreeNode getRight() {
        return right;
    }

    /**
     * Inserts a node into either the left or right tree where appropriate
     *
     * @param newData Reference to the new data
     */
    public void insert(int newData) {
        if (newData < data) {
            if (left == null)
                left = new BinaryTreeNode(newData);
            else
                left.insert(newData);
        } else if (newData > data) {
            if (right == null)
                right = new BinaryTreeNode(newData);
            else
                right.insert(newData);
        } else
            System.out.println("Duplicate - not adding " + newData);
    }
}
