package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2056번: 작업
public class BOJ_2056 {

    static int N;
    static int[] in;
    static int[] time;
    static int[] endTime;
    static List<Integer>[] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        in = new int[N + 1];
        time = new int[N + 1];
        info = new List[N + 1];
        endTime = new int[N + 1];
        for (int i = 1; i <= N; i++) info[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                info[x].add(i);
                in[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.offer(i);
                endTime[i] = time[i];
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : info[now]) {
                in[next]--;
                endTime[next] = Math.max(endTime[next], endTime[now] + time[next]);
                if (in[next] == 0) q.offer(next);
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, endTime[i]);
        }
        System.out.println(answer);
    }
}
