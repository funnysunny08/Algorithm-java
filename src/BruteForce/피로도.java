package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 22864번 - 피로도
public class 피로도 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int piro = 0;
        int work = 0;

        for (int i = 0; i < 24; i++) {
            if (A + piro <= M) {
                work += 1;
                piro += A;
            } else {
                piro -= C;
                if (piro < 0) piro = 0;
            }
        }

        System.out.println(work * B);
    }
}
