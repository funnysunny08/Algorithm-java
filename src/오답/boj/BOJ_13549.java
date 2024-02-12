package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 13549번: 숨바꼭질 3
public class BOJ_13549 {

    static int N, K;
    static int MAX = 100000;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    static class Node {
        int x, time;
        Node (int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    public static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(N, 0));

        while (!q.isEmpty()) {
            Node currNode = q.poll();
            visited[currNode.x] = true;

            if (currNode.x == K) {
                answer = Math.min(answer, currNode.time);
            }

            if (currNode.x * 2 <= MAX && !visited[currNode.x * 2]) {
                q.add(new Node(currNode.x * 2, currNode.time));
            }

            if (currNode.x + 1 <= MAX && !visited[currNode.x + 1]) {
                q.add(new Node(currNode.x + 1, currNode.time + 1));
            }

            if (currNode.x - 1 >= 0 && !visited[currNode.x - 1]) {
                q.add(new Node(currNode.x - 1, currNode.time + 1));
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[MAX + 1];
        System.out.println(bfs());
    }
}
