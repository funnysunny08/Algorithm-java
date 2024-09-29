package leet;

import java.util.ArrayList;
import java.util.List;

// 199. Binary Tree Right Side View
public class Binary_Tree_Right_Side_View {
    static int[] arr = new int[101];
    static int maxLevel = 0;
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) dfs(root, 0);
        else return result;

        for (int i = 0; i <= maxLevel; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static void dfs(TreeNode node, int level) {
        arr[level] = node.val;
        maxLevel = Math.max(level, maxLevel);
        if (node.left == null && node.right == null) return; // 현재 level 이 마지막
        if (node.left != null) dfs(node.left, level + 1);
        if (node.right != null) dfs(node.right, level + 1);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

    }
}
