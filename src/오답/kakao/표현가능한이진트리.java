package 오답.kakao;

// 카카오: 표현 가능한 이진트리
public class 표현가능한이진트리 {

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            String fullBinaryTree = makeFullBinaryTree(binary);
            if (isBinaryTree(fullBinaryTree)) answer[i] = 1;
            else answer[i] = 0;
        }

        return answer;
    }

    public static boolean isBinaryTree(String tree) {
        if (tree.length() == 0) return true;
        int root = tree.length() / 2;

        String leftSubTree = tree.substring(0, root);
        String rightSubTree = tree.substring(root + 1);

        if (tree.charAt(root) == '0') {
            return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
        }
        return isBinaryTree(leftSubTree) && isBinaryTree(rightSubTree);
    }

    public static boolean isZeroTree(String tree) {
        return !tree.contains("1");
    }

    public static String makeFullBinaryTree(String str) {
        int node = 0;
        int level = 1;
        while (node < str.length()) {
            node += level;
            level *= 2;
        }
        int offset = node - str.length();
        return "0".repeat(offset) + str;
    }

    public static void main(String[] args) {
        long[] numbers = {63, 111, 95};
        int[] ans = solution(numbers);
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }
}
