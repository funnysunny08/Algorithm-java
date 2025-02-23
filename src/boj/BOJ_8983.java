package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 8983. 사냥꾼
public class BOJ_8983 {
    static int M, N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[] hunters = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) hunters[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(hunters);

        Node[] animal = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animal[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            int x = animal[i].x;
            int y = animal[i].y;

            int left = 0, right = M - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                int dist = Math.abs(x - hunters[mid]) + y;
                if (dist <= L) {
                    count++;
                    break;
                }

                if (x < hunters[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        System.out.println(count);
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
