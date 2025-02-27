package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11509. 풍선 맞추기
public class BOJ_11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] cnt = new int[1000001];
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            if (cnt[h] > 0) { // 앞에서 h 높이로 날라오고 있는 화살이 있는 확인
                cnt[h]--;
            } else {
                answer++;
            }
            cnt[h - 1]++; // 여기 풍선이 터졌으니 h - 1 높이의 화살이 있다고 뒤에 알려줌
        }
        System.out.println(answer);
    }
}
