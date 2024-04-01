import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary search tree and an array <b>queries</b> of size n,
 * find a 2D array <b>answer</b> of size n where answer[i] = [min<sub>i</sub>, max<sub>i</sub>]:
 * <li>min<sub>i</sub> is the largest value in the tree that is <= queries[i].
 * If such value does not exist, add -1 instead.</li>
 * <li>max<sub>i</sub> is the smallest value in the tree that is >= queries[i].
 * If such value does not exist, add -1 instead.</li>
 */
public class ClosestNodesQuery {
    /**
     * Given a tree and a list of queries, for each query, find:
     *  1. The node in this tree with the largest value that is still <= query.
     *  2. Another node in this tree with the smallest value that is still >= query.
     *
     * First, make the tree balanced (because one of the test case involves a
     * highly unbalanced tree).
     * Then call the helper method findClosest(query) for each query.
     */
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        TreeNode newTree = makeBalanced(root);
        List<List<Integer>> result = new LinkedList<>();
        for (Integer num : queries) {
            List<Integer> partialRes = findClosest(newTree, num);
            result.add(partialRes);
        }
        return result;
    }

    /**
     * Rebuilds a given tree, and makes it more balanced.
     * @param root the tree that is not balanced
     * @return a tree that is more balanced
     */
    public TreeNode makeBalanced(TreeNode root) {
        int[] arr = new int[100000]; // max number of nodes is 10^5
        /* Do an in-order traversal of the tree, filling the array.
            arr is a sparse array, only indexes from 0 to length - 1 will be filled */
        int length = populateArrayInOrder(root, arr, 0);
        TreeNode newRoot = pickRoot(arr, 0, length - 1);
        return newRoot;
    }

    /**
     * Helper method for makeBalanced(node).
     * Builds a tree recursively by picking the middle element
     * in the array.
     * @param array the array
     * @param low the starting index in the array
     * @param high the last index in the array
     * @return a new tree that is approximately balanced.
     */
    private TreeNode pickRoot(int[] array, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high)/2;
        TreeNode root = new TreeNode(array[mid]);
        root.left = pickRoot(array, low, mid - 1);
        root.right = pickRoot(array, mid + 1, high);
        return root;
    }

    /**
     * Fill the given array using inorder traversal, such that
     * at the end, the array will be sorted.
     * @param root the TreeNode
     * @param array the array to fill
     * @param index where in the array should we fill next
     * @return the updated index (which is the length of the array)
     */
    private int populateArrayInOrder(TreeNode root, int[] array, int index) {
        if (root == null) {
            return index;
        }
        int newIndex = populateArrayInOrder(root.left, array, index);
        array[newIndex++] = root.val;
        int updatedIndex = populateArrayInOrder(root.right, array, newIndex);
        return updatedIndex;
    }

    /**
     * Given a BST and an integer, find the largest number that is smaller than target.
     * And the smallest number that is larger than target.
     * Example:
     *                6
     *             /     \
     *           2        13
     *          / \       / \
     *         1  4     9   15
     *                     /
     *                    14
     * queries = [2,5,16]
     * output: [[2,2],[4,6],[15,-1]]
     *
     * Explanation:
     *  The largest number that is <= 2 is 2, and
     *  the smallest number that is >= 2 is 2.
     *  So the answer to the first query is [2,2].
     *
     *  The largest number that is <= 5 is 4, and
     *  the smallest number that is >= 5 is 6.
     *  So the answer to the second query is [4,6].
     *
     *  The largest number that is <= 16 is 15, and
     *  the smallest number that is >= 16 does not exist.
     *  So the answer for the third query is [15,-1].
     */
    private List<Integer> findClosest(TreeNode root, int target) {
        TreeNode curr = root;
        int largest = -1;
        int smallest = -1;
        while (curr != null) {
            if (curr.val == target) {
                largest = curr.val;
                smallest = curr.val;
                break;
            }else if (curr.val < target) {
                largest = curr.val;
                curr = curr.right;
            } else {
                smallest = curr.val;
                curr = curr.left;
            }
        }

        List<Integer> res = new LinkedList<>();
        res.add(largest);
        res.add(smallest);
        return res;
    }

    /**
     * Generate this tree:
     *                6
     *             /     \
     *           2        13
     *          / \       / \
     *         1  4     9   15
     *                     /
     *                    14
     */
    public TreeNode generateTree() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(15);
        root.right.right.left = new TreeNode(14);
        return root;
    }

    /**
     * 4
     *  \
     *   9
     */
    public TreeNode generateTree2() {
        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(9);
        return root;
    }

    public static void main(String[] args) {
        ClosestNodesQuery nd = new ClosestNodesQuery();
        TreeNode root = nd.generateTree();
        root.printPreorder();
        List<Integer> queries = new LinkedList<>();
        queries.add(2);
        queries.add(5);
        queries.add(16);
        List<List<Integer>> res = nd.closestNodes(root, queries);
        System.out.println(res);
    }
}
