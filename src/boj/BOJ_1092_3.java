package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1092. 배
public class BOJ_1092_3 {
    static int N, M; // 크레인 수, 박스 수
    static List<Integer> cranes = new ArrayList<>();
    static List<Integer> boxes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cranes.add(Integer.parseInt(st.nextToken()));
        cranes.sort(Comparator.reverseOrder());

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) boxes.add(Integer.parseInt(st.nextToken()));
        boxes.sort(Comparator.reverseOrder());

        int[] pointer = new int[N];
        boolean[] visited = new boolean[M];

        if (boxes.get(0) > cranes.get(0)) {
            System.out.println(-1);
            System.exit(0);
        }

        int time = 0;
        while (true) {
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                while (true) {
                    if (pointer[i] >= M) break;
                    if (!visited[pointer[i]] && boxes.get(pointer[i]) <= cranes.get(i)) {
                        visited[pointer[i]] = true;
                        pointer[i]++;
                        flag = true;
                        break;
                    } else {
                        pointer[i]++;
                    }
                }
            }
            if (!flag) break;
            time++;
        }
        System.out.println(time);
    }
}
