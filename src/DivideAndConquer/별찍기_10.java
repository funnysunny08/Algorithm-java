package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2447번: 별 찍기 -10
public class 별찍기_10 {

    static int N;
    static StringBuilder[] sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        sb = new StringBuilder[N]; // 행별로 StringBuilder! -> 그래야 빈칸으로 바꾸기 쉬움
        for (int i = 0; i < N; i++) {
            sb[i] = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb[i].append("*");
            }
        }
        star(0, 0, N);

        for (int i = 0; i < N; i++) {
            System.out.println(sb[i]);
        }
    }

    static void star(int x, int y, int size) {
        makeBlank(x, y, size); // 현재 들어온 영역 빈칸 만들기!!
        int newSize = size / 3;
        if (newSize < 3) return; // size가 3보다 작은 경우 -> 끝!!

        star(x, y, newSize);
        star(x, y + newSize, newSize);
        star(x, y + newSize * 2, newSize);
        star(x + newSize, y, newSize);
        star(x + newSize, y + newSize, newSize);
        star(x + newSize, y + newSize * 2, newSize);
        star(x + newSize * 2, y, newSize);
        star(x + newSize * 2, y + newSize, newSize);
        star(x + newSize * 2, y + newSize * 2, newSize);
    }

    static void makeBlank(int x, int y, int size) {
        int value = size / 3;
        for (int i = x + value; i < x + value * 2; i++) {
            for (int j = y + value; j < y + value * 2; j++) {
                sb[i].setCharAt(j, ' ');
            }
        }
    }
}
