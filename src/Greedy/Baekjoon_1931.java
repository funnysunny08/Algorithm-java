package Greedy;
// 백준 1931번 - 회의실 배정
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// BufferedReader, Arrays.sort
public class Baekjoon_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (t1, t2) -> { // 빨리 끝나는 순으로 정렬 & 끝나는 시간이 같을 때는 빨리 시작하는 순으로 정렬
            if(t1[1] == t2[1]) {
                return t1[0] - t2[0];
            }
            return t1[1] - t2[1];
        });

        for (int i = 0; i < N; i++) {
            System.out.println(arr[i][0] + " : " + arr[i][1]);
        }

    }

}