package 오답.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 백준 16235번: 나무 재테크
public class BOJ_16235 {

    static int N, M, K;
    static int[][] A;
    static List<Tree> trees = new ArrayList<>();
    static int[][] land;
    static List<Integer> deadTrees = new ArrayList<>();
    static class Tree {
        int x, y, age;
        boolean isDead;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.isDead = false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, z));
        }

        land = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(land[i], 5);

        // Tree 리스트 나이 오름차순으로 정렬
        Collections.sort(trees, (o1, o2) -> o1.age - o2.age);

        for (int i = 0; i < K; i++) {
            // 봄
            spring();
            // 여름
            summer();
            // 가을
            fall();
            // 겨울
            winter();
        }

        System.out.println(trees.size());
    }

    public static void spring() {
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (land[tree.x][tree.y] >= tree.age) { // 양분 먹음
                land[tree.x][tree.y] -= tree.age;
                tree.age++;
            } else { // 죽음
                deadTrees.add(i);
            }
        }
    }

    public static void summer() {
        for (int idx : deadTrees) {
            Tree deadTree = trees.get(idx);
            deadTree.isDead = true;
            land[deadTree.x][deadTree.y] += deadTree.age / 2;
        }
        deadTrees.clear();
    }

    public static void fall() {
        int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] dy = {-1, 1, 0, 0, -1, 1, 1, -1};

        List<Tree> newList = new ArrayList<>();
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (!tree.isDead && tree.age % 5 == 0) {
                for (int j = 0; j < 8; j++) {
                    int nx = tree.x + dx[j];
                    int ny = tree.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    newList.add(new Tree(nx, ny, 1));
                }
            }
        }

        for (int i = 0; i < trees.size(); i++) {
            if (!trees.get(i).isDead) newList.add(trees.get(i));
        }

        trees = newList;
    }

    public static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                land[i][j] += A[i][j];
            }
        }
    }
}
