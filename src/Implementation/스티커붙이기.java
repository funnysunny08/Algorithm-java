package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 18808번: 스티커 붙이기
public class 스티커붙이기 {

    static int N, M, K; // 노트북 세로, 가로, 스티커 수
    static int[][] laptop = new int[42][42];
    static int R, C; // 스티커 세로, 가로
    static int[][] sticker = new int[12][12];

    static boolean postable(int x, int y) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (laptop[x + i][y + j] * sticker[i][j] == 1) {
                    return false;
                }
            }
        }
        // 스티커를 불일 수 있음 -> 붙이자!
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sticker[i][j] == 1) {
                    laptop[x + i][y + j] = 1;
                }
            }
        }
        return true;
    }

    static void rotate() {
        int[][] copy = new int[12][12];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copy[i][j] = sticker[i][j];
            }
        }

        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                sticker[i][j] = copy[R - 1 - j][i];
            }
        }
        int tmp = R;
        R = C;
        C = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        while (K-- > 0) {
            // 입력 받기
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 수행
            check:
            for (int rot = 0; rot < 4; rot++) {
                for (int x = 0; x <= N - R; x++) {
                    for (int y = 0; y <= M - C; y++) {
                        if (postable(x, y)) {
                            break check;
                        }
                    }
                }
                if (rot != 3) rotate();
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (laptop[i][j] == 1) cnt++;
            }
        }
        System.out.println(cnt);
    }
}
