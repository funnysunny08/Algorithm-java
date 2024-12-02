package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_2141 {
    static class Town {
        int x, a; // 위치, 인원
        public Town(int x, int a) {
            this.x = x;
            this.a = a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Town[] towns = new Town[N];
        StringTokenizer st;
        long people = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            towns[i] = new Town(x, a);
            people += a;
        }

        Arrays.sort(towns, Comparator.comparingInt(t -> t.x));

        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += towns[i].a;
            if (sum >= (people + 1) / 2) {
                System.out.println(towns[i].x);
                System.exit(0);
            }
        }
    }
}
