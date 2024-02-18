package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// 백준 8972번: 미친 아두이노
public class BOJ_8972 {

    static int R, C;
    static char[][] map;
    static Node me; // 내 위치
    static List<Node> crazy = new ArrayList<>(); // 미친 아두이노 위치
    static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    static class Node {
        int x, y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void moveCrazy(Node node) {
        int dist = Integer.MAX_VALUE;
        int newX = -1;
        int newY = -1;

        for (int i = 1; i <= 9; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];

            if (dist > Math.abs(me.x - nx) + Math.abs(me.y - ny)) {
                dist = Math.abs(me.x - nx) + Math.abs(me.y - ny);
                newX = nx;
                newY = ny;
            }
        }
        node.x = newX;
        node.y = newY;
    }

    public static void removeDuplicate() {
        Map<String, Integer> coordinatesCount = new HashMap<>();
        for (Node node : crazy) {
            String coordinates = node.x + "," + node.y;
            coordinatesCount.put(coordinates, coordinatesCount.getOrDefault(coordinates, 0) + 1);
        }

        for (Iterator<Node> iterator = crazy.iterator(); iterator.hasNext();) {
            Node node = iterator.next();
            String coordinates = node.x + "," + node.y;
            if (coordinatesCount.get(coordinates) > 1) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    crazy.add(new Node(i, j));
                } else if (map[i][j] == 'I') {
                    me = new Node(i, j);
                }
            }
        }

        int x = -1;
        String commands = br.readLine();
        for (int i = 0; i < commands.length(); i++) {
            int command = Integer.parseInt(String.valueOf(commands.charAt(i)));
            // 1. 내 아두이노 이동 시키기
            map[me.x][me.y] = '.';
            me.x += dx[command];
            me.y += dy[command];

            if (map[me.x][me.y] == 'R') {
                x = i + 1;
                break;
            }
            map[me.x][me.y] = 'I';

            // 2. 미친 아두이노 이동 시키기
            for (int j = 0; j < crazy.size(); j++) {
                map[crazy.get(j).x][crazy.get(j).y] = '.'; // 일단 비우기!
                moveCrazy(crazy.get(j));
                if (map[crazy.get(j).x][crazy.get(j).y] == 'I') {
                    x = i + 1;
                    break;
                }
            }

            // 3. 보드 상태 갱신
            removeDuplicate();
            for (Node c : crazy) {
                map[c.x][c.y] = 'R';
            }
            if (map[me.x][me.y] == 'R') {
                x = i + 1;
                break;
            }
        }

        if (x == -1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
        } else {
            System.out.println("kraj " + x);
        }
    }
}
