package Greedy2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 백준 11501번: 주식
public class 주식 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        int[] num;
        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            long answer = 0;
            st = new StringTokenizer(br.readLine(), " ");
            num = new int[N];
            for (int j = 0; j < N; j++) {
                num[j] = Integer.parseInt(st.nextToken());
            }
            int max = num[N - 1]; // 최고 시세 => 역방향 탐색!
            // 역방향 탐색 시작!
            for (int j = N - 2; j >= 0; j--) {
                if (num[j] <= max) { // 최고 시세보다 시세가 작을 경우 => 판매!
                    answer += max - num[j];
                } else { // 최고 시세 업데이트!
                    max = num[j];
                }
            }
            bw.write(answer + "\n");
        }
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
}
