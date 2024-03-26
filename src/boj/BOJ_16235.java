package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 16235번: 나무 재테크
public class BOJ_16235 {

    static int N, M, K;
    static int[][] A;
    static List<Tree> trees = new ArrayList<>();
    static int[][] ground;
    static Deque<Integer> deadTrees = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};

    static class Tree {

        int x, y, age;
        boolean dead;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
            dead = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        ground = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, z));
        }

        Collections.sort(trees, (o1, o2) -> o1.age - o2.age);



        for (int k = 0; k < K; k++) {
            // 1. 봄: 양분 먹기
            spring();
            // 2. 여름: 죽은 나무로 양분 추가하기
            summer();
            // 3. 가을: 나무 번식시키기
            fall();
            // 4. 겨울: 양분 뿌리기
            winter();
        }

        System.out.println(trees.size());
    }

    public static void spring() {
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (ground[tree.x][tree.y] < tree.age) {
                deadTrees.add(i);
                continue;
            }
            ground[tree.x][tree.y] -= tree.age;
            tree.age++;
        }
    }

    public static void summer() {
        while (!deadTrees.isEmpty()) {
            int deadTreeIdx = deadTrees.pollLast();
            Tree deadTree = trees.get(deadTreeIdx);
            ground[deadTree.x][deadTree.y] += deadTree.age / 2;
            deadTree.dead = true;
        }
    }

    public static void fall() {
        List<Tree> newTree = new ArrayList<>();
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (tree.dead || tree.age % 5 != 0) continue;
            for (int d = 0; d < 8; d++) {
                int nx = tree.x + dx[d];
                int ny = tree.y + dy[d];
                if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                newTree.add(new Tree(nx, ny, 1));
            }
        }

        for (Tree tree : trees) {
            if (!tree.dead) newTree.add(tree);
        }
        trees = newTree;
    }

    public static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ground[i][j] += A[i][j];
            }
        }
    }
}
