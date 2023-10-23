package Backtracking2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 16987번 - 계란으로 계란치기
public class 계란으로계란치기 {

    static int N;
    static Egg[] eggs;
    static int answer = Integer.MIN_VALUE;

    static class Egg {
        int durability;
        int weight;

        Egg (int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    static void backtracking(int depth, int value) {
        if (depth == N) {
            answer = Math.max(answer, value);
            return;
        }
        Egg now = eggs[depth]; // 현재 손에 쥐고 있는 달걀
        if (now.durability <= 0) { // 손에 쥐고 있는 게 이미 깨진 달걀이라면 그냥 넘김
            backtracking(depth + 1, value);
            return;
        }
        boolean check = true;
        for (int i = 0; i < N; i++) {
            if (depth == i) continue; // 내가 손에 쥐고 있는 달걀을 집을 수 없음.
            Egg target = eggs[i];
            if (target.durability <= 0) continue; // 이미 깨진 계란이면 pass
            now.durability -= target.weight;
            target.durability -= now.weight;
            if (now.durability <= 0) value++;
            if (target.durability <= 0) value++;
            backtracking(depth + 1, value);
            // 원상 복구
            if (now.durability <= 0) value--;
            if (target.durability <= 0) value--;
            now.durability += target.weight;
            target.durability += now.weight;
            check = false;
        }
        if (check) backtracking(depth + 1, value); // 이번 라운드에서 한번도 재귀 호출 못했으면 강제?로 재귀호출
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(durability, weight);
        }

        backtracking(0, 0);
        if (answer == Integer.MIN_VALUE) answer = 0;
        System.out.println(answer);
    }
}
