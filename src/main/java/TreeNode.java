import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    public void printPreorder() {
        printPreorder(this);
        System.out.println();
    }

    private void printPreorder(TreeNode curr) {
        if (curr != null) {
            printPreorder(curr.left);
            System.out.print(curr.val + " ");
            printPreorder(curr.right);
        }
    }
}