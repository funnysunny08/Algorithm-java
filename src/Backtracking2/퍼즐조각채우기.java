package Backtracking2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 프로그래머스: 퍼즐 조각 채우기
public class 퍼즐조각채우기 {

    static class Block {
        int cnt; // 칸의 개수
        int[][] shape = new int[6][2]; // 모양 -> 칸이 최대 6개라 했으므로,
    }

    public static int solution(int[][] game_board, int[][] table) {
        // 퍼즐 추출하기
        List<Block> puzzles = new LinkedList<>(); // remove 메서드 사용하기 위해 LinkedList 사용
        findBlocks(table, puzzles, 1, 0);

        // 빈칸 추출하기
        List<Block> blanks = new ArrayList<>();
        findBlocks(game_board, blanks, 0, 1);

        // 빈칸 - 퍼즐 조합
        int answer = 0;
        for (Block blank : blanks) {
            for (Block puzzle : puzzles) {
                if (canInsert(puzzle, blank)) {
                    answer += puzzle.cnt;
                    puzzles.remove(puzzle);
                    if (puzzles.isEmpty()) { // 더 이상 사용할 퍼즐이 없다면, 바로 종료!
                        return answer;
                    }
                    break; // 해당 빈칸은 채웠으니 넘어감!
                }
            }
        }
        return answer;
    }

    public static boolean canInsert(Block puzzle, Block blank) {
        if (puzzle.cnt != blank.cnt) return false;
        int size = puzzle.cnt;

        for (int i = 0; i < 3; i++) {
            if (isSameShape(size, puzzle.shape, blank.shape)) return true;
            turn(puzzle);
        }
        if (isSameShape(size, puzzle.shape, blank.shape)) return true;
        return false;
    }

    public static void turn(Block block) { // 90도 회전
        for (int i = 0; i < block.cnt; i++) {
            int temp = -block.shape[i][0];
            block.shape[i][0] = block.shape[i][1];
            block.shape[i][1] = temp;
        }
        setCenter(block);
    }

    public static boolean isSameShape(int size, int[][] shape1, int[][] shape2) {
        for (int j = 0; j < size; j++){
            int k = 0;
            for (; k < size; k++){
                if (shape1[j][0] == shape2[k][0] && shape1[j][1] == shape2[k][1]) break;
            }
            if (k == size) return false; // 일치하지 않는 좌표 발견!
        }
        return true;
    }

    public static void findBlocks(int[][] map, List<Block> list, int flag, int nflag) {
        int n = map.length;
        int m = map[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == flag) {
                    list.add(findOne(map, i, j, n, m, flag, nflag));
                }
            }
        }
    }

    public static Block findOne(int[][] map, int x, int y, int n, int m, int flag, int nflag) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Block block = new Block();

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        map[x][y] = nflag; // 방문 처리

        while (!q.isEmpty()) {
            int[] now = q.poll();
            block.shape[block.cnt++] = now;
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == nflag) continue;
                map[nx][ny] = nflag;
                q.add(new int[]{nx, ny});
            }
        }
        setCenter(block);
        return block;
    }

    public static void setCenter(Block block) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int i = 0; i < block.cnt; i++) {
            if (block.shape[i][0] < minX) {
                minX = block.shape[i][0];
                minY = block.shape[i][1];
            } else if (block.shape[i][0] == minX && block.shape[i][1] < minY) {
                minY = block.shape[i][1];
            }
        }

        for (int i = 0; i < block.cnt; i++) {
            block.shape[i][0] -= minX;
            block.shape[i][1] -= minY;
        }
    }

    public static void main(String[] args) {
        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1}, {1,1,0,1,1,1}, {1,0,0,0,1,0}, {0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0}, {1,0,1,0,1,0}, {0,1,1,0,1,1}, {0,0,1,0,0,0}, {1,1,0,1,1,0}, {0,1,0,0,0,0}};
        System.out.println(solution(game_board, table));
    }
}
