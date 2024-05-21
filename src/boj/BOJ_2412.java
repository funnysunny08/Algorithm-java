package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2412번: 암벽 등반
public class BOJ_2412 {

    static int n, T;
    static HashSet<Point> points = new HashSet<>();
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
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
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));
        int steps = 0;
        boolean reached = false;

        L:
        while (!q.isEmpty() && !reached) {
            int size = q.size();
            steps++;

            for (int i = 0; i < size; i++) {
                Point current = q.poll();

                for (int dx = -2; dx <= 2; dx++) {
                    for (int dy = -2; dy <= 2; dy++) {
                        if (dx == 0 && dy == 0) continue;
                        Point point = new Point(current.x + dx, current.y + dy);
                        if (!points.contains(point)) continue;
                        if (point.y == T) {
                            reached = true;
                            break L;
                        }
                        q.offer(new Point(point.x, point.y));
                        points.remove(point);
                    }
                }
            }
        }
        System.out.println(reached ? steps : -1);

    }
}
