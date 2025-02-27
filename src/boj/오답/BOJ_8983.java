package boj.오답;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 8983. 사냥꾼
public class BOJ_8983 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] hunters = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) hunters[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(hunters);

        Loc[] animal = new Loc[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animal[i] = new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int x = animal[i].x;
            int y = animal[i].y;

            // x와 가장 가까운 사대 찾기
            int left = 0, right = M - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                int dist = Math.abs(hunters[mid] - x) + y;
                if (dist <= L) {
                    cnt++;
                    break;
                }
                if (x < hunters[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        System.out.println(cnt);
    }

    static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
