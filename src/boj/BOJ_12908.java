package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 12908. 텔레포트 3
public class BOJ_12908 {

    public static void main(String[] args) throws IOException {
        Node[] nodes = new Node[8];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 출발지
        nodes[0] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        // 도착지
        nodes[7] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        long[][] dist = new long[8][8];
        for (int i = 0; i < 8; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[0][7] = dist[7][0] = Math.abs(nodes[0].x - nodes[7].x) + Math.abs(nodes[0].y - nodes[7].y);
        for (int i = 1; i < 7; i += 2) {
            st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            nodes[i + 1] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            int distance = Math.min(Math.abs(nodes[i].x - nodes[i + 1].x) + Math.abs(nodes[i].y - nodes[i + 1].y), 10);
            dist[i][i + 1] = distance;
            dist[i + 1][i] = distance;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                dist[i][j] = Math.min(dist[i][j], Math.abs(nodes[i].x - nodes[j].x) + Math.abs(nodes[i].y - nodes[j].y));
            }
        }

        for (int k = 0; k < 8; k++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        System.out.println(dist[0][7]);
    }
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
