package Greedy2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1931번: 회의실배정
public class 회의실배정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 회의의 수
        Meeting[] meetings = new Meeting[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end);
        }

        Arrays.sort(meetings, ((o1, o2) -> {
            if (o1.end == o2.end) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        }));

        int count = 0;
        int prev_end_time = 0;

        for(int i = 0; i < N; i++) {
            // 직전 종료시간이 다음 회의 시작 시간보다 작거나 같다면 갱신
            if(prev_end_time <= meetings[i].start) {
                prev_end_time = meetings[i].end;
                count++;
            }
        }
        System.out.println(count);
    }

    static class Meeting {
        int start;
        int end;
        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
