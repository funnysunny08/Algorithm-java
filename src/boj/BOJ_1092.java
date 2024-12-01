package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 1092. 배
public class BOJ_1092 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> cranes = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cranes.add(Integer.parseInt(st.nextToken()));
        int M = Integer.parseInt(br.readLine());
        List<Integer> boxes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) boxes.add(Integer.parseInt(st.nextToken()));

        cranes.sort(Collections.reverseOrder());
        boxes.sort(Collections.reverseOrder());

        if (boxes.get(0) > cranes.get(0)) {
            System.out.println(-1);
            System.exit(0);
        }

        int day = 0;
        while (!boxes.isEmpty()) {
            day++;
            int boxIndex = 0, craneIndex = 0;

            while (craneIndex < N) {
                if (boxIndex == boxes.size()) break;
                else if (cranes.get(craneIndex) >= boxes.get(boxIndex)) { // 현재 크레인에서 처리할 수 있는 가장 무거운 박스 발견
                    craneIndex++; // 다음 크레인으로 넘어감
                    boxes.remove(boxIndex); // 현재 박스 remove
                } else boxIndex++; // 현재 크레인에서 처리할 수 없는 박스 -> 더 가벼운 박스로 넘어감
            }
        }
        System.out.println(day);
    }
}
