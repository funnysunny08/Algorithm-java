package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 백준 11559번: 뿌요뿌요
public class 뿌요뿌요 {

    static int N = 12, M = 6;
    static char[][] map = new char[N][M];
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x;
        int y;
        char val;
        Node (int x, int y, char val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    static boolean isPossible() {
        boolean[][] visited = new boolean[N][M];
        Queue<Node> q = new LinkedList<>();
        Stack<Node> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || map[i][j] == '.') continue;
                int cnt = 0;
                q.add(new Node(i, j, map[i][j]));
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    Node now = q.poll();
                    st.push(now); // 스택에는 임시로 넣어주기 (연쇄 불가능하면 뺄거임)
                    cnt++;
                    for (int k = 0; k < 4; k++) {
                        int nx = now.x + dx[k];
                        int ny = now.y + dy[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if (!visited[nx][ny] && map[nx][ny] == now.val) {
                            q.add(new Node(nx, ny, map[nx][ny]));
                            visited[nx][ny] = true;
                        }
                    }
                }
                if (cnt < 4) { // 같은 뿌요 4개 안되면 원상복구 시켜줘야 함
                    for (int l = 0; l < cnt; l++) st.pop();
                    // visited 는 원상 복구시키지 않음 -> 왜냐면 이미 살릴 수 없는 애라는 것 알았으니깐!
                }
            }
        }
        if (st.isEmpty()) return false;
        while (!st.isEmpty()) {
            Node now = st.pop();
            map[now.x][now.y] = '.';
        }
        reload();
        return true;
    }

    static void reload() {
       for (int y = 0; y < M; y++) {
           down(y);
       }
    }

    static void down(int j) {
        Queue<Node> puyo = new LinkedList<>();
        int idx = N - 1;

        for(int i = N - 1; i >= 0; i--) {
            if(map[i][j] != '.') {
                puyo.add(new Node(i, j, map[i][j]));
                map[i][j] = '.';
            }
        }
        // 뿌요를 가장 밑에 있는 빈 칸 부터 채워나감
        while(!puyo.isEmpty()) {
            Node p = puyo.poll();
            map[idx][j] = p.val;
            idx--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0 ; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int result = 0;
        while (true) {
            if (isPossible()) { // 해당 라운드에서 연쇄가 가능한지 체크하고 가능하다면 연쇄 작업 실행
                result++;
            } else {
                break;
            }
        }

        System.out.println(result);
    }
}
