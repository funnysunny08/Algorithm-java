package 오답.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1477번: 휴게소 세우기
public class 휴게소세우기 {

    /*
    * 파라메트릭 서치
    * 구하라 => 휴게소가 없는 구간의 최댓값의 최솟값
    * mid => 휴게소가 없는 구간의 최댓값
    * 배열에 휴게소끼리의 간격을 저장!
    * */

    static int N, M, L; // 현재 휴게소의 개수, 더 지으려는 휴게소의 개수, 고속도로 길이
    static int[] station;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        station = new int[N + 2];
        station[0] = 0;
        station[N + 1] = L;
        for (int i = 1; i < N + 1; i++) {
            station[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(station);

        long left = 1;
        long right = L - 1;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (canInstall(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }

    public static boolean canInstall(long distance) {
        long cnt = 0;
        for (int i = 0; i < N + 1; i++) {
            cnt += (station[i + 1] - station[i] - 1) / distance;
        }
        return cnt > M;
    }
}
