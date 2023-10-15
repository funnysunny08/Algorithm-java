package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 15685번: 치킨 배달
public class 치킨배달 {

    static int N;
    static int M;
    static ArrayList<Location> chickens;
    static ArrayList<Location> homes;
    static Location[] selected;
    static int answer = Integer.MAX_VALUE;

    static class Location {
        int r;
        int c;
        Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static void backTracking(int L, int s) {
        if (L == M) { // 치킨집 M개 고름!
            // 치킨 거리 구해서 최솟값 업데이트!
            int chickenDistance = calculateChickenDistance();
            answer = Math.min(chickenDistance, answer);
        } else {
            for (int i = s; i < chickens.size(); i++) {
                selected[L] = chickens.get(i);
                backTracking(L + 1, i + 1);
            }
        }
    }

    static int calculateChickenDistance() {
        int totalDistance = 0;
        for (int i = 0; i < homes.size(); i++) {
            int minDistance = Integer.MAX_VALUE;
            Location home = homes.get(i);
            for (int j = 0; j < M; j++) { // 선택된 치킨집 돌면서 치킨 거리 계산
                Location chicken = selected[j];
                int distance = Math.abs(home.r - chicken.r) + Math.abs(home.c - chicken.c);
                minDistance = Math.min(distance, minDistance);
            }
            totalDistance += minDistance;
        }
        return totalDistance;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chickens = new ArrayList<>();
        homes = new ArrayList<>();
        selected = new Location[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) homes.add(new Location(i, j));
                if (x == 2) chickens.add(new Location(i, j));
            }
        }

        backTracking(0, 0);
        System.out.println(answer);
    }
}