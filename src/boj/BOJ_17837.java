package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 17837번: 새로운 게임 2
public class BOJ_17837 {

    static int N, K;
    static Node[][] map; // 0은 흰색, 1은 빨간색, 2는 파란색
    static List<int[]> horse = new ArrayList<>(); // x, y, 이동 번호
    static int[] dx = {0, 0, 0, -1, 1}; // 오른쪽, 왼쪽, 위쪽, 아래쪽
    static int[] dy = {0, 1, -1, 0, 0};
    static int[] reverseDirection = {0, 2, 1, 4, 3};

    static class Node {
        int color;
        List<Integer> horses;
        Node (int color) {
            this.color = color;
            this.horses = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Node[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int color = Integer.parseInt(st.nextToken());
                map[i][j] = new Node(color);
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            horse.add(new int[]{x, y, d});
            map[x][y].horses.add(i);
        }

        int turn = 1;
        L:
        while (true) {
            for (int i = 0; i < horse.size(); i++) { // 모든 말들을 순서대로 이동 시킴
                int[] currHorse = horse.get(i);
                int nx = currHorse[0] + dx[currHorse[2]]; // 말의 다음 위치를 구한다.
                int ny = currHorse[1] + dy[currHorse[2]];

                if (isOut(nx, ny) || map[nx][ny].color == 2) { // 다음 위치가 파란색이거나 map을 벗어나는 경우
                    currHorse[2] = reverseDirection[currHorse[2]]; // 방향을 바꿔준다.
                    nx = currHorse[0] + dx[currHorse[2]];
                    ny = currHorse[1] + dy[currHorse[2]];
                    if (isOut(nx, ny) || map[nx][ny].color == 2) { // 뒤로 움직였는데도 파란색이면, 그냥 넘어간다.
                        continue;
                    }
                }

                int count = 0; // 몇개의 말이 이동되었는지!
                List<Integer> currLocationHorses = map[currHorse[0]][currHorse[1]].horses; // 현재 칸에 머무는 말 List
                if (map[nx][ny].color == 0) { // 다음 칸 흰색
                    boolean find = false;
                    for (int j = 0; j < currLocationHorses.size(); j++) {
                        if (currLocationHorses.get(j) == i) find = true; // 현재 칸에 머무는 말들 중에서 이번 round 말부터 이동 시작!
                        if (find) {
                            // 말의 위치 변경
                            horse.get(currLocationHorses.get(j))[0] = nx;
                            horse.get(currLocationHorses.get(j))[1] = ny;

                            // 다음 칸에 추가
                            map[nx][ny].horses.add(currLocationHorses.get(j));

                            count++;
                        }
                    }
                } else if (map[nx][ny].color == 1){ // 다음 칸 빨간색
                    for (int j = currLocationHorses.size() - 1; j >= 0; j--) { // 위의 과정과 반대로 실행!
                        // 말의 위치 변경
                        horse.get(currLocationHorses.get(j))[0] = nx;
                        horse.get(currLocationHorses.get(j))[1] = ny;

                        // 다음 칸에 추가
                        map[nx][ny].horses.add(currLocationHorses.get(j));

                        count++;
                        if (currLocationHorses.get(j) == i) break; // 이번 round 말까지만 이동!
                    }
                }
                // 원래의 칸에 있는 horseList 뒤에서부터 count 만큼 제거
                for (int j = 0; j < count; j++) {
                    currLocationHorses.remove(currLocationHorses.size() - 1);
                }
                if (map[nx][ny].horses.size() >= 4) break L; // 게임 종료 조건 만족하면 while문 종료!
            }
            turn++;
            if (turn > 1000) break;
        }

        System.out.println(turn > 1000 ? -1 : turn);
    }

    public static boolean isOut(int x, int y) {
        return x < 1 || y < 1 || x > N || y > N;
    }
}
