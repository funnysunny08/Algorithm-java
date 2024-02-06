package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 9470번: Strahler 순서
public class BOJ_9470 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // 테스트 케이스 번호
            int m = Integer.parseInt(st.nextToken()); // 노드 개수
            int p = Integer.parseInt(st.nextToken()); // 간선 개수

            int[] seq = new int[m + 1];

            List<List<Integer>> list = new ArrayList<>();
            for (int j = 0; j <= m; j++) list.add(new ArrayList<>()); // 나한테로 들어오는 노드 리스트

            for (int j = 0; j < p; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list.get(to).add(from);
            }

            // 순서 1번인 노드 찾기
            for (int j = 1; j <= m; j++) { // 들어오는 간선이 없는 노드에 순서 1 부여
                if (list.get(j).size() == 0) seq[j] = 1;
            }

            boolean chk = true;
            while (chk) {
                chk = false;
                for (int j = 1; j <= m; j++) {
                    if (seq[j] != 0) continue; // 이미 갱신된적 있음!

                    int num = -1;
                    int cnt = 0;
                    boolean update = true;
                    for (int s = 0; s < list.get(j).size(); s++) {
                        int idx = list.get(j).get(s);
                        if (seq[idx] == 0) { // 순서를 알 수 없는 노드가 포함되어 있어서 현재의 라운드에선 계산 못함.
                            chk = true; // while 문을 한 번 더 돌 수 있도록!
                            update = false; // seq[j] 업데이트할 수 없음!
                            break;
                        }
                        if (num < seq[idx]) {
                            cnt = 0;
                            num = seq[idx];
                        } else if (num == seq[idx]) {
                            cnt++;
                        }
                    }
                    if (update) {
                        if (cnt == 0) {
                            seq[j] = num;
                        } else {
                            seq[j] = num + 1;
                        }
                    }
                }
            }
            sb.append(k).append(" ").append(seq[m]).append("\n");
        }
        System.out.println(sb);
    }
}
