package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1561. 놀이 공원
public class BOJ_1561 {
    static int N, M; // N 아이들, M 놀이기구 종류
    static int[] rides;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rides = new int[M];
        st = new StringTokenizer(br.readLine());
        int max = -1;
        for (int i = 0; i < M; i++) {
            rides[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, rides[i]);
        }

        long left = 0;
        long right = (long) max * N;
        long time = 0;

        while (left <= right) {
            long mid = (left + right) / 2; // 해당 시간 안에 아이들을 몇 명 태울 수 있는지

            long cnt = 0;
            for (int i = 0; i < M; i++) {
                cnt += mid / rides[i] + 1;
            }

            if (cnt < N) { // 조건 충족 X => 늘려야 함
                left = mid + 1;
            } else {
                right = mid - 1;
                time = mid;
            }
        }
        System.out.println(getNumber(time));
    }

    private static int getNumber(long time) { // time 까지 진행되었을 때 총 people 만큼 태울 수 있음
        long people = 0;
        for (int i = 0; i < M; i++) people += time / rides[i] + 1;
        for (long t = time; t >= 0; t--) {
            for (int i = M - 1; i >= 0; i--) {
                if (time % rides[i] == 0) {
                    if (people == N) return i + 1;
                    people--;
                }
            }
        }
        return -1;
    }
}
