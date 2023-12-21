package 오답.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2110번: 공유기 설치
public class 공유기설치 {

    /*
    * 구하라 => 가장 인접한 두 공유기 사이의 최대 거리
    * 파라메트릭 서치
    * mid => 공유기 사이의 최소 거리
    * */

    static int N, C; // 집의 개수, 공유기 개수
    static int[] homes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        homes = new int[N];
        for (int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(homes);

        long left = 1;
        long right = homes[N - 1] - homes[0] + 1;
        long answer = Long.MIN_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canInstall(mid)) { // 설치 가능 => 공유기 사이의 최소 거리를 늘려볼까?
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }

    public static boolean canInstall(long distance) {
        int count = 1;
        int value = homes[0];
        for (int i = 1; i < N; i++) {
            if (homes[i] - value >= distance) {
                count++;
                value = homes[i]; // 여기에 설치해줌!
            }
        }
        return count >= C;
    }
}
