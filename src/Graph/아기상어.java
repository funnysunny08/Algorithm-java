package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 16236번: 아기 상어
public class 아기상어 {

    static int N;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}; // 상좌하우
    static int[] dy = {0, -1, 0, 1}; // 상좌하우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int[] cur = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) { // 상어의 위치
                    cur = new int[]{i, j}; // 상어의 현재 위치 저장
                    map[i][j] = 0; // 상어가 있던 자리 비움
                }
            }
        }

        int size = 2; // 상어의 현재 사이즈
        int eat = 0; // 먹은 물고기 수
        int move = 0; // 움직인 총 거리

        while (true) {
            PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> {
                if (o1[2] != o2[2]) return Integer.compare(o1[2], o2[2]); // 거리 우선
                else if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]); // row 우선
                else return Integer.compare(o1[1], o2[1]);
            });
            boolean[][] visit = new boolean[N][N];

            que.add(new int[]{cur[0], cur[1], 0}); // 현재 상어의 위치
            visit[cur[0]][cur[1]] = true;

            boolean ck = false; // 상어가 먹이를 먹었는지 체크할 변수

            while (!que.isEmpty()) { // 먹이를 찾을 때까지 움직임!!
                cur = que.poll();
                if (map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size) { // 먹이를 찾았을 때
                    map[cur[0]][cur[1]] = 0; // 먹이를 먹고
                    eat++; // 먹은 물고기 수 증가
                    move += cur[2]; // 움직인 거리 증가
                    ck = true; // 먹이를 먹었음을 체크
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int nx = cur[0] + dx[k];
                    int ny = cur[1] + dy[k];

                    if (ny < 0 || nx < 0 || nx >= N || ny >= N || visit[nx][ny] || map[nx][ny] > size)
                        continue;

                    que.add(new int[]{nx, ny, cur[2] + 1}); // 새로운 좌표, 시간 증가해서 넣기!
                    visit[nx][ny] = true;
                }
            }
            if (!ck) // 큐가 비워질 때까지 먹이를 먹은적이 없다면, 더 이상 먹은 물고기가 없으므로 탈출
                break;

            if (size == eat) { // 사이즈와 먹이를 먹은 수가 동일하다면 상어의 크기를 증가
                size++;
                eat = 0;
            }
        }
        System.out.println(move);
    }
}
