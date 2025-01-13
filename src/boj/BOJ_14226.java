package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 14226. 이모티콘
public class BOJ_14226 {
    static int S;
    static int MAX = 2000;
    static int[][] visit = new int[MAX + 1][MAX + 1];

    static class Emoticon {
        int len, time, clipboard;
        public Emoticon(int len, int time, int clipboard) {
            this.len = len;
            this.time = time;
            this.clipboard = clipboard;
        }
    }

    private static void bfs() {
        Queue<Emoticon> q = new LinkedList<>();
        q.offer(new Emoticon(1, 0, 0));
        visit[1][0] = 0;

        while (!q.isEmpty()) {
            Emoticon now = q.poll();
            if (now.len == S) {
                System.out.println(now.time);
                return;
            }

            // 복사
            if (now.len > 0 && visit[now.len][now.len] > now.time + 1) {
                q.offer(new Emoticon(now.len, now.time + 1, now.len));
                visit[now.len][now.len] = now.time + 1;
            }

            // 붙여넣기
            if (now.clipboard > 0 && now.len + now.clipboard < MAX &&  visit[now.len + now.clipboard][now.clipboard] > now.time + 1) {
                q.offer(new Emoticon(now.len + now.clipboard, now.time + 1, now.clipboard));
                visit[now.len + now.clipboard][now.clipboard] = now.time + 1;
            }

            // 하나 삭제
            if (now.len > 1 && visit[now.len - 1][now.clipboard] > now.time + 1) {
                q.offer(new Emoticon(now.len - 1, now.time + 1, now.clipboard));
                visit[now.len - 1][now.clipboard] = now.time + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        for (int i = 0; i < MAX; i++) Arrays.fill(visit[i], Integer.MAX_VALUE);
        bfs();
    }
}
