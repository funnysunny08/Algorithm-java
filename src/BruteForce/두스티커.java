package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 16937번: 두 스티커
public class 두스티커 {

    static int H;
    static int W;
    static int N;
    static ArrayList<Sticker> stickers;
    static Sticker[] combi;
    static int answer;

    static class Sticker {
        int r;
        int c;

        Sticker(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static void combination(int L, int s) { // L: 몇번째(현재 자리), s: stickers idx 어디부터 돌면 돼?
        if (L == 2) {
            if (check(combi[0].r, combi[1].r, combi[0].c, combi[1].c)) {
                int area = combi[0].r * combi[0].c + combi[1].r * combi[1].c;
                answer = Math.max(answer, area);
            }
        } else {
            for (int i = s; i < N; i++) {
                combi[L] = stickers.get(i);
                combination(L + 1, i + 1);
            }
        }
    }

    static boolean check(int r1, int r2, int c1, int c2) {
        int width = r1 + r2;
        int height = Math.max(c1, c2);
        if ((width <= H && height <= W) || (width <= W && height <= H)) {
            return true;
        }

        width = r1 + c2;
        height = Math.max(c1, r2);
        if ((width <= H && height <= W) || (width <= W && height <= H)) {
            return true;
        }

        width = c1 + c2;
        height = Math.max(r1, r2);
        if ((width <= H && height <= W) || (width <= W && height <= H)) {
            return true;
        }

        width = c1 + r2;
        height = Math.max(c2, r1);
        if ((width <= H && height <= W) || (width <= W && height <= H)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());

        stickers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            stickers.add(new Sticker(r, c));
        }

        combi = new Sticker[2];
        combination(0, 0);
        System.out.println(answer);
    }
}
