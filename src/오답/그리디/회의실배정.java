package 오답.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1931번: 회의실 배정
public class 회의실배정 {

    static int N;
    static Meeting[] meetings;

    static class Meeting {
        int start, end;
        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end);
        }

        // 끝나는 시간 기준 오름차순! 먼저 끝나는 회의들이 앞으로!
        // 이때 종료 시간이 같으면 시작 시간 빠른 애들을 앞으로!!
        Arrays.sort(meetings, ((o1, o2) -> {
            if (o1.end == o2.end) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        }));


        int lastTime = -1;
        int cnt = 0;
        for (Meeting meeting : meetings) {
            if (meeting.start >= lastTime) {
                cnt++;
                lastTime = meeting.end;
            }
        }
        System.out.println(cnt);
    }
}
