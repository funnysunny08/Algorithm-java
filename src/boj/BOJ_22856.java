package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 22856번: 트리 순회
public class BOJ_22856 {

    static int N;
    static Node[] nodes;
    static List<Integer> inorderStatus = new ArrayList<>();
    static List<Integer> similarInorderStatus = new ArrayList<>();
    static int lastNode, visitedCnt;
    static boolean[] visited;
    static class Node {
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            nodes[x] = new Node(left, right);
        }

        // 중위 순회
        inorder(1);
        lastNode = inorderStatus.get(inorderStatus.size() - 1);

        // 유사 중위 순회
        visited = new boolean[N + 1];
        similarInorder(1);
    }

    static void similarInorder(int n) {
        similarInorderStatus.add(n);
        if (!visited[n]) {
            visited[n] = true;
            visitedCnt++;
        }
        Node now = nodes[n];
        if (now.left != -1) {
            similarInorder(now.left);
            similarInorderStatus.add(n);
        }
        if (now.right != -1) {
            similarInorder(now.right);
            similarInorderStatus.add(n);
        }

        if (visitedCnt == N && n == lastNode) {
            System.out.println(similarInorderStatus.size() - 1);
            System.exit(0);
        }
    }

    public static void inorder(int n) {
        Node now = nodes[n];
        if (now.left != -1) {
            inorder(now.left);
        }
        inorderStatus.add(n);
        if (now.right != -1) {
            inorder(now.right);
        }
    }
}
