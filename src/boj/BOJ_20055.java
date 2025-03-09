package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 20055. 컨베이어 벨트 위의 로봇
public class BOJ_20055 {
    static int N, K, M;
    static int[] durability;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = 2 * N;
        durability = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) durability[i] = Integer.parseInt(st.nextToken());

        int time = 0;
        int start = 0;
        boolean[] robot = new boolean[M];
        while (true) {
            time++;
            // 1.
            start = (start - 1 + M) % M;

            // 2.
            int end = start + N - 1;
            robot[end % M] = false; // 내리는 위치에 있는 로봇은 바로 내려줌
            for (int i = end - 1; i > start; i--) {
                int now = i % M;
                int next = (now + 1) % M;
                if (robot[now] && durability[next] > 0 && !robot[next]) {
                    durability[next]--;
                    robot[now] = false;
                    robot[next] = true;
                    if (durability[next] == 0) K--;
                }
            }
            robot[end % M] = false; // 내리는 위치로 오게된 로봇도 바로 내려줌

            // 3.
            if (durability[start] > 0) {
                durability[start]--;
                robot[start] = true;
                if (durability[start] == 0) K--;
            }

            // 4.
            if (K <= 0) break;
        }

        System.out.println(time);
    }
}
