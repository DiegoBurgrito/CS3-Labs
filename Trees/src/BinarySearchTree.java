//© A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class - 
//Lab  -

import static java.lang.System.*;

import java.util.LinkedList;

public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(Comparable val) {
        root = add(val, root);
    }

    private TreeNode add(Comparable val, TreeNode tree) {
        if (tree == null)
            tree = new TreeNode(val);

        Comparable treeValue = tree.getValue();
        int dirTest = val.compareTo(treeValue);

        if (dirTest < 0)
            tree.setLeft(add(val, tree.getLeft()));
        else if (dirTest > 0)
            tree.setRight(add(val, tree.getRight()));

        return tree;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println("\n");
    }

    private void inOrder(TreeNode tree) {
        if (tree != null) {
            inOrder(tree.getLeft());
            System.out.print(tree.getValue() + " ");
            inOrder(tree.getRight());
        }
    }

    // preOrder
    public String preOrder() {
        return null;
    }

    // postOrder
    public String postOrder() {
        return null;
    }

    // revOrder
    public String revOrder() {
        return null;
    }

    // levelOrder - use a queue
    public String levelOrder() {
        return null;
    }

    // zigzagOrder - hint below but could be solved in a different manner
    // loop thru a stack and load all nodes to a new stack(loading is based on direction)
    // set new stack to old and repeat
    public String zigzagOrder() {
        return null;
    }

    // getNumLevels
    public int getNumLevels() {
        return 0;
    }

    // getNumLeaves
    public int getNumLeaves() {
        return 0;
    }

    // getWidth - insure this works right for the 2nd test case
    public int getWidth() {
        return 0;
    }

    // getHeight
    public int getHeight() {
        return 0;
    }

    // getNumNodes
    public int getNumNodes() {
        return 0;
    }

    // isFull
    public boolean isFull() {
        return false;
    }
    // contains
    // maxNode
    //testing
    // minNode

    // getSmallest

    // getLargest

    // remove - follow adds set up very closely and check powerpoint if needed
    // 1st case = no children
    // 2nd case = one child
    // 3rd case two children

    // ****BONUS****
    // display like a tree


    public String toString() {
        return "";
    }

    private String toString(TreeNode tree) {
        return "";
    }
}