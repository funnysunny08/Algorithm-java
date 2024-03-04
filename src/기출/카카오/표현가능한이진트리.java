package 기출.카카오;

// 카카오: 표현 가능한 이진트리
public class 표현가능한이진트리 {

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            String fullBinary = getFullBinary(binary);
            if (isBinaryTree(fullBinary)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private static String getFullBinary(String binary) { // binary 를 만족하는(포괄하는) 포화 이진트리를 구한다.
        int length = binary.length();
        int nodeCount = 1; // 루트 노드 -> 1로 시작! => 해당 포화 이진트리의 노드 개수!
        int level = 1; // 현재의 레벨에서 노드 개수: 2의 제곱만큼 증가
        while (length > nodeCount) { // binary 이진 트리 노드 수 > 포화 이진트리의 총 노드 수 => 포화 이진트리를 더 키우겠다!
            level *= 2;
            nodeCount += level;
        }
        int offset = nodeCount - length;	// 앞에 추가해야할 0의 개수
        return "0".repeat(offset) + binary;
    }

    private static boolean isBinaryTree(String binary) {
        int len = binary.length();
        if (len == 0) return true; // 모든 서브트리들을 다 쪼개는 거 성공!

        int root = len / 2;
        String leftSubTree = binary.substring(0, root);
        String rightSubTree = binary.substring(root + 1);

        if (binary.charAt(root) == '0') { // 만약 루트 노드가 0이라면 하위 모든 노드들은 0이어야 함!
            // 왼쪽 서브트리와 오른쪽 서브트리가 모두 0으로 구성되어 있는지 확인한다.
            return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
        }

        // 루트 노드 1이라면 각 서브트리들이 이진트리인지 검사한다.
        return isBinaryTree(leftSubTree) && isBinaryTree(rightSubTree);
    }

    public static boolean isZeroTree(String binary) {
        int len = binary.length();
        if (len == 0) return true; // 모든 서브트리들을 다 쪼갰는데 1이 발견이 안되고 최하위까지 도달함!

        int root = len / 2;
        String leftSubTree = binary.substring(0, root);
        String rightSubTree = binary.substring(root + 1);

        if (binary.charAt(root) == '1') return false;

        return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
    }

    public static void main(String[] args) {
        long[] numbers = {63, 111, 95};
        int[] ans = solution(numbers);

        for (int a : ans) {
            System.out.print(a + " ");
        }
    }
}
