package 오답.백트래킹;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 프로그래머스: 퍼즐 조각 채우기
public class 퍼즐조각채우기 {

    static int[] dx = {-1 ,1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static class Block {
        int size;
        int[][] shape = new int[6][2];
    }

    public static int solution(int[][] game_board, int[][] table) {

        // 1. 퍼즐, 빈칸 추출하기
        List<Block> puzzles = new LinkedList<>();
        findBlocks(puzzles, table, 1, 0);
        List<Block> blanks = new ArrayList<>();
        findBlocks(blanks, game_board, 0, 1);

        // 2. 빈칸 퍼즐 맞추기
        int answer = 0;
        for (Block blank : blanks) {
            for (Block puzzle : puzzles) {
                if (canInsert(puzzle, blank)) {
                    answer += puzzle.size;
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
        if (puzzle.size != blank.size) return false;
        for (int i = 0; i < 4; i++) {
            if (isSameShape(puzzle, blank)) return true;
            turn(puzzle);
        }
        return false;
    }

    public static boolean isSameShape(Block block1, Block block2) {
        for (int i = 0; i < block1.size; i++) {
            int j = 0;
            for (; j < block2.size; j++) {
                if (block1.shape[i][0] == block2.shape[j][0] && block1.shape[i][1] == block2.shape[j][1]) break;
            }
            if (j == block2.size) return false;
        }
        return true;
    }

    public static void turn(Block block) {
        for (int i = 0; i < block.size; i++) {
            int temp = -block.shape[i][0];
            block.shape[i][0] = block.shape[i][1];
            block.shape[i][1] = temp;
        }
        setCenter(block);
    }

    public static void findBlocks(List<Block> blocks, int[][] board, int flag, int nflag) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == flag) {
                    blocks.add(findOne(i, j, flag, nflag, board));
                }
            }
        }
    }

    public static Block findOne(int x, int y, int flag, int nflag, int[][] board) {
        Block block = new Block();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        board[x][y] = nflag;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            block.shape[block.size++] = curr;
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || board[nx][ny] == nflag) continue;
                board[nx][ny] = nflag;
                q.add(new int[]{nx, ny});
            }
        }
        setCenter(block);
        return block;
    }

    public static void setCenter(Block block) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (int i = 0; i < block.size; i++) {
            if (block.shape[i][0] < minX) {
                minX = block.shape[i][0];
                minY = block.shape[i][1];
            } else if (block.shape[i][0] == minX && block.shape[i][1] < minY) {
                minY = block.shape[i][1];
            }
        }

        for (int i = 0; i < block.size; i++) {
            block.shape[i][0] -= minX;
            block.shape[i][1] -= minY;
        }
    }

    public static void main(String[] args) {
        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
        System.out.println(solution(game_board, table));
    }
}
