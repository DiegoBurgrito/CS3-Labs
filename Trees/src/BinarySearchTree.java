//© A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class - 
//Lab  -

import static java.lang.System.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
    //inOrder
    public void inOrder() {
        inOrder(root);
        System.out.print("\n");
    }

    private void inOrder(TreeNode tree) {
        if (tree != null) {
            inOrder(tree.getLeft());
            System.out.print(tree.getValue() + " ");
            inOrder(tree.getRight());
        }
    }
    // preOrder
    public void preOrder() {
        preOrder(root);
        out.print("\n");
    }

    public void preOrder(TreeNode tree){
        if (tree != null) {
            System.out.print(tree.getValue() + " ");
            preOrder(tree.getLeft());
            preOrder(tree.getRight());
        }
    }
    // postOrder
    public void postOrder() {
        postOrder(root);
        out.print("\n");
    }

    private void postOrder(TreeNode tree){
        if (tree != null) {
            postOrder(tree.getLeft());
            postOrder(tree.getRight());
            System.out.print(tree.getValue() + " ");
        }
    }
    // revOrder
    public void revOrder() {
        revOrder(root);
        out.print("\n");
    }

    private void revOrder(TreeNode tree) {
        if (tree != null) {
            revOrder(tree.getRight());
            System.out.print(tree.getValue() + " ");
            revOrder(tree.getLeft());
        }
    }

    // levelOrder - use a queue

    public void levelOrder() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            out.print(node.getValue() + " ");

            if (node.getLeft() != null){
                queue.add(node.getLeft());
            }


            if (node.getRight() != null){
                queue.add(node.getRight());
            }

        }
        out.print("\n");
    }
    // zigzagOrder - hint below but could be solved in a different manner
    // loop thru a stack and load all nodes to a new stack(loading is based on direction)
    // set new stack to old and repeat
    public void zigzagOrder() {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> temp = new Stack<>();
        stack.push(root);
        boolean direction = true;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            out.print(node.getValue() + " ");

            if (direction) {
                if (node.getLeft() != null) {
                    temp.push(node.getLeft());
                }
                if (node.getRight() != null) {
                    temp.push(node.getRight());
                }
            } else {
                if (node.getRight() != null) {
                    temp.push(node.getRight());
                }
                if (node.getLeft() != null) {
                    temp.push(node.getLeft());
                }
            }
            if (stack.isEmpty()) {
                stack = temp;
                temp = new Stack<>();
                direction = !direction;
            }
        }
        out.print("\n");

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
        return toString(root);
    }

    private String toString(TreeNode tree) {
        String out = "";
        if (tree != null) {
            out += toString(tree.getLeft())+  tree.getValue() + " " + toString(tree.getRight());
        }
        return out;
    }
}