package 오답.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 16937번: 두 스티커
public class 두스티커 {

    /*
    * 1. 스티커 2개를 고른다 -> DFS
    * 2. 스티커 2개를 고르면, 해당 스티커를 붙일 수 있는지 (스티커를 돌려보며) 그리고 붙일 수 있다면 넓이를 구한다.
    * 3. 최대 넓이를 업데이트한다.
    * */

    static int H, W, N;
    static List<Sticker> stickers = new ArrayList<>();
    static int[] selected = new int[2];
    static boolean[] visited;
    static int answer = Integer.MIN_VALUE;

    static class Sticker {
        int r;
        int c;
        Sticker(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void dfs(int L) {
        if (L == 2) { // 스티커 2개 다 선택함!
            int r1 = stickers.get(selected[0]).r;
            int c1 = stickers.get(selected[0]).c;
            int r2 = stickers.get(selected[1]).r;
            int c2 = stickers.get(selected[1]).c;
            if (check(r1, c1, r2, c2)) {
                answer = Math.max(answer, r1 * c1 + r2 * c2);
            }
        } else {
           for (int i = 0; i < N; i++) {
               if (!visited[i]) { // 해당 스티커를 고른 적이 없다면!
                   selected[L] = i;
                   visited[i] = true;
                   dfs(L + 1);
                   visited[i] = false;
               }
           }
        }
    }

    public static boolean check(int r1, int c1, int r2, int c2) {
        if (r1 + r2 <= W && Math.max(c1, c2) <= H) return true;
        if (r1 + c2 <= W && Math.max(c1, r2) <= H) return true;
        if (c1 + r2 <= W && Math.max(r1, c2) <= H) return true;
        if (c1 + c2 <= W && Math.max(r1, r2) <= H) return true;

        if (r1 + r2 <= H && Math.max(c1, c2) <= W) return true;
        if (r1 + c2 <= H && Math.max(c1, r2) <= W) return true;
        if (c1 + r2 <= H && Math.max(r1, c2) <= W) return true;
        if (c1 + c2 <= H && Math.max(r1, r2) <= W) return true;

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            stickers.add(new Sticker(r, c));
        }

        visited = new boolean[N];
        dfs(0);
        if (answer == Integer.MIN_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
