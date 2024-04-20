package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 8911번: 거북이
public class BOJ_8911 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    // 북 -> 동 -> 남 -> 서
    // 북 -> 서 -> 남 -> 동

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String command = br.readLine();
            int x = 501, y = 501;
            int minX = x, minY = y;
            int maxX = x, maxY = y;
            int direction = 0;

            for (int i = 0; i < command.length(); i++) {
                char move = command.charAt(i);
                if (move == 'F') {
                    x += dx[direction];
                    y += dy[direction];
                } else if (move == 'B') {
                    x -= dx[direction];
                    y -= dy[direction];
                } else if (move == 'L') {
                    direction = (direction + 1) % 4;
                } else if (move =='R') {
                    direction = (direction + 3) % 4;
                }
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
            }
            int value = Math.abs(maxX - minX) * Math.abs(maxY - minY);
            sb.append(value).append("\n");
        }
        System.out.println(sb);
    }
}
