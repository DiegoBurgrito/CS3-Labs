import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static java.lang.System.out;

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

    public void preOrder(TreeNode tree) {
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

    private void postOrder(TreeNode tree) {
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

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }


            if (node.getRight() != null) {
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
        boolean dir = true;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            out.print(node.getValue() + " ");

            if (dir) {
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
                dir = !dir;
            }
        }
        out.print("\n");
    }

    // getNumLevels
    public int getNumLevels() {
        return getNumLevels(root);
    }

    public int getNumLevels(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return 1 + Math.max(getNumLevels(tree.getLeft()), getNumLevels(tree.getRight()));
    }

    // getNumLeaves
    public int getNumLeaves() {
        return getNumLeaves(root);
    }

    private int getNumLeaves(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        if (tree.getLeft() == null && tree.getRight() == null) {
            return 1;
        } else {
            return getNumLeaves(tree.getLeft()) + getNumLeaves(tree.getRight());
        }
    }

    // getWidth - insure this works right for the 2nd test case
    public int getWidth(){
        return getWidth(root);
    }
    private int getWidth(TreeNode tree){
        if(tree == null){
            return 0;
        }
        int left = getWidth(tree.getLeft());
        int right = getWidth(tree.getRight());
        int root = getNumLevels(tree.getLeft()) + getNumLevels(tree.getRight()) + 1;
        return Math.max(Math.max(left, root), Math.max(right, root));
    }

    // getHeight
    public int getHeight() {
        return getHeight(root) - 1;
    }

    private int getHeight(TreeNode tree) {
        if (tree == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(tree.getLeft()), getHeight(tree.getRight()));
        }

    }

    // getNumNodes
    public int getNumNodes() {
        return getNumNodes(root);
    }

    private int getNumNodes(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return 1 + getNumNodes(tree.getLeft()) + getNumNodes(tree.getRight());
    }

    // isFull
    public boolean isFull() {
        return isFull(root);
    }

    private boolean isFull(TreeNode tree) {
        if (tree == null) {
            return true;
        }
        if (tree.getLeft() != null && tree.getRight() != null) {
            return isFull(tree.getLeft()) && isFull(tree.getRight());
        }
        return tree.getLeft() == null && tree.getRight() == null;
    }

    // contains
    public boolean contains(Comparable obj) {
        return contains(root, obj);
    }

    private boolean contains(TreeNode tree, Comparable obj) {
        if (tree == null) {
            return false;
        }
        if (tree.getValue() == obj) {
            return true;
        }
        return contains(tree.getRight(), obj) || contains(tree.getLeft(), obj);
    }

    // getSmallest
    public Comparable getSmallest() {
        return minNode(root);
    }

    // getLargest
    public Comparable getLargest() {
        return maxNode(root);
    }
    // remove - follow adds set up very closely and check powerpoint if needed
    // 1st case = no children
    // 2nd case = one child
    // 3rd case two children

    public Comparable maxNode(TreeNode tree) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        Comparable largest = tree.getValue();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.getValue().compareTo(largest) > 0) {
                largest = node.getValue();
            }

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }

        }
        return largest;
    }

    public Comparable minNode(TreeNode tree) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        Comparable smallest = tree.getValue();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.getValue().compareTo(smallest) < 0) {
                smallest = node.getValue();
            }

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }

        }
        return smallest;
    }


    public void remove(Comparable val) {
        root = remove(val, root);
    }

    private TreeNode remove(Comparable val, TreeNode tree) {
        if (tree == null)
            tree = new TreeNode(val);

        Comparable treeValue = tree.getValue();
        int dirTest = val.compareTo(treeValue);

        if (dirTest < 0)
            tree.setLeft(remove(val, tree.getLeft()));
        else if (dirTest > 0)
            tree.setRight(remove(val, tree.getRight()));
        else {
            if(tree.getLeft()==null){
                return tree.getRight();
            }
            else if(tree.getRight()==null){
                return tree.getLeft();
            }
            else{
                tree.setValue(minNode(tree.getRight()));
                tree.setRight(remove(tree.getValue(),tree.getRight()));
            }
        }
        return tree;
    }

    // ****BONUS****
    // display like a tree
    public void clear() {
        root = null;
    }

    public String toString() {
        return toString(root);
    }

    private String toString(TreeNode tree) {
        String out = "";
        if (tree != null) {
            out += toString(tree.getLeft()) + tree.getValue() + " " + toString(tree.getRight());
        }
        return out;
    }
}