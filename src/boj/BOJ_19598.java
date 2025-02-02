package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 19598. 최소 회의실 개수
public class BOJ_19598 {
    static int N;
    static List<Meeting> meetings = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meetings.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        meetings.sort((o1, o2) -> {
            if (o1.start != o2.start) {
                return o1.start - o2.start;
            } else {
                return o1.end - o2.end; // 시작 시간이 같을 경우 종료 시간으로 정렬
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;

        for (int i = 0; i < N; i++) {
            Meeting now = meetings.get(i);
            while (!pq.isEmpty() && pq.peek() <= now.start) { // 현재의 내 시작 시간보다 먼저 끝나는 회의들 모두 제거
                pq.poll();
            }
            pq.offer(now.end); // 현재의 내 회의의 종료 시간 삽입
            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);
    }

    static class Meeting {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
