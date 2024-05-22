package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 백준 2412번: 암벽 등반
public class BOJ_2412 {

    static int n, T;
    static Set<Point> points = new HashSet<>();

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));
        int time = 0;
        while (!q.isEmpty()) {
            time++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point now = q.poll();
                for (int dx = -2; dx <= 2; dx++) {
                    for (int dy = -2; dy <= 2; dy++) {
                        if (dx == 0 && dy == 0) continue;
                        Point point = new Point(now.x + dx, now.y + dy);
                        if (!points.contains(point)) continue;
                        if (point.y == T) return time;
                        points.remove(point);
                        q.offer(point);
                    }
                }
            }
        }
        return -1;
    }
}
