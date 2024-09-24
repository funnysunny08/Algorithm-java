package leet;

// 1448. Count Good Nodes in Binary Tree
public class Count_Good_Nodes_in_Binary_Tree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    static int goodCnt;

    public static int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return goodCnt;
    }

    public static void dfs(TreeNode node, int max) {
        if (node.val >= max) goodCnt++;
        int newMax = Math.max(node.val, max);
        if (node.left != null) dfs(node.left, newMax);
        if (node.right != null) dfs(node.right, newMax);
    }

    public static void main(String[] args) {

    }

}
