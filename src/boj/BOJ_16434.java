package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 16434번: 드래곤 앤 던전
public class BOJ_16434 {

    static int N, h_attack;
    static Room[] rooms;
    static long answer = Long.MAX_VALUE;
    static class Room {
        int type, attack, life;

        public Room(int type, int attack, int life) {
            this.type = type;
            this.attack = attack;
            this.life = life;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        h_attack = Integer.parseInt(st.nextToken());
        rooms = new Room[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int attack = Integer.parseInt(st.nextToken());
            int life = Integer.parseInt(st.nextToken());
            rooms[i] = new Room(type, attack, life);
        }
        long left = 0, right = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (play(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean play(long max) {
        long hp = max;
        long power = h_attack;

        for (int i = 0; i < N; i++) {
            if (rooms[i].type == 1) { // 몬스터
                long attackTime = rooms[i].life / power; // 몇 번 공격해야 하는지
                if (rooms[i].life % power != 0) attackTime++;
                hp -= rooms[i].attack * (attackTime - 1);
                if (hp <= 0) return false;
            } else { // 포션
                hp += rooms[i].life;
                if (hp >= max) hp = max;
                power += rooms[i].attack;
            }
        }
        return true;
    }
}
