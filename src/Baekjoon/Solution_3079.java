package Baekjoon;
// 백준 3079번 - 입국 심사 [시간 초과]
import java.util.*;
import java.io.*;
public class Solution_3079 {
    public static int M; // 사람 수
    public static int N; // 입국 심사대 수
    public static int[] cp; // 심사대 별로 심사 소요 시간 저장
    public static int[] cnt; // 심사대 별로 방문자 수 count
    public static int getMinIdx() {
        int min = cp[0] * (cnt[0] + 1);
        int minIdx = 0;
        for (int i = 0; i < N; i++) {
            if (cp[i] * (cnt[i] + 1) < min) {
                min = cp[i] * (cnt[i] + 1);
                minIdx = i;
            }
        }
        return minIdx;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cp = new int[N];
        cnt = new int[N];

        for (int i = 0; i < N; i++) {
            cp[i] = Integer.parseInt(br.readLine());
        }

        // 계산
        for (int i = 0; i < M; i++) {
            int idx = getMinIdx();
            cnt[idx]++;
        }
        // 총 소요시간 구하기
        int max = cp[0] * cnt[0];
        for (int i = 0; i < N; i++) {
            if (cp[i] * cnt[i] > max) max = cp[i] * cnt[i];
        }
        System.out.println(max);
    }
}
