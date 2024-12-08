package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1092_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // cranes input
        int N = Integer.parseInt(br.readLine());
        List<Integer> cranes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cranes.add(Integer.parseInt(st.nextToken()));
        // boxes input
        int M = Integer.parseInt(br.readLine());
        List<Integer> boxes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) boxes.add(Integer.parseInt(st.nextToken()));

        cranes.sort(Comparator.reverseOrder());
        boxes.sort(Comparator.reverseOrder());

        if (cranes.get(0) < boxes.get(0)) {
            System.out.println(-1);
            System.exit(0);
        }

        int time = 0;
        while (!boxes.isEmpty()) {
            time++;
            int craneIdx = 0;
            int boxIdx = 0;
            while (craneIdx < N) {
                if (boxIdx == boxes.size()) break; // 모든 박스를 탐색했음에도 해당 박스를 옮길 수 있는 크레인 없음. 따라서 다시 cranes[0]으로 이동해서 확인해야 함
                if (cranes.get(craneIdx) >= boxes.get(boxIdx)) {
                    boxes.remove(boxIdx);
                    craneIdx++;
                } else boxIdx++;
            }
        }
        System.out.println(time);
    }
}
