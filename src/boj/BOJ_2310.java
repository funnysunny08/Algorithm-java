package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 백준 2310번: 어드벤처 게임
public class BOJ_2310 {

    static int N;
    static int[][] info; // type -> 0: 빈방, 1: 레프리콘, 2: 트론
    static List<Integer>[] path;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            info = new int[N + 1][2]; // info[i][0]: room type, info[i][1]: cost
            path = new List[N + 1];

            // 정보 입력
            for (int i = 1; i <= N; i++) {
                path[i] = new ArrayList<>();
                String[] arr = br.readLine().split(" ");

                String type = arr[0];
                if (Objects.equals(type, "E")) info[i][0] = 0;
                else if (Objects.equals(type, "L")) info[i][0] = 1;
                else info[i][0] = 2;

                info[i][1] = Integer.parseInt(arr[1]);

                for (int j = 2; j < arr.length - 1; j++) {
                    path[i].add(Integer.parseInt(arr[j]));
                }
            }

            // dfs
            visited = new boolean[N + 1];
            visited[1] = true;
            if (dfs(1, 0)) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        System.out.println(sb);
    }

    public static boolean dfs(int room, int money) {
        if (room == N) {
            return canPass(room, money) >= 0;
        }

        int newMoney = canPass(room, money);
        if (newMoney >= 0) {
            for (int next: path[room]) {
                if (!visited[next]) {
                    visited[next] = true;
                    if (dfs(next, newMoney)) return true;
                    visited[next] = false; // 이 방을 다른 방 먼저 들렀다 올 수도 있기 때문에 다시 복구해준다!
                }
            }
        }
        return false;
    }

    public static int canPass(int room, int money) {
        if (info[room][0] == 0) return money; // 빈방
        else if (info[room][0] == 1) { // 레프리콘
            return Math.max(money, info[room][1]);
        } else { // 트롤
            return money - info[room][1];
        }
    }
}
