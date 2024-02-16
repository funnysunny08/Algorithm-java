package 기출.카카오;

// 카카오: 양궁대회
public class 양궁대회 {

    static int[] lion;
    static int[] aPeach;
    static int[] answer;
    static int diff = Integer.MIN_VALUE;

    public static void dfs(int num, int idx) {
        if (num == 0) {
            check();
            return;
        }
        if (idx == -1) return;
        for (int j = num; j >= 0; j--) { // (10 - idx) 점을 몇 번 맞출지!
            lion[idx] = j; // (10 - i) 점에 j번 맞추겠다!
            dfs(num - j, idx - 1); // 화살 쏠 기회 줄이고, 다음 맞춰야 할 점수 업데이트에서 dfs 돌리기
        }
    }

    public static void check() {
        int l = 0;
        int a = 0;
        for (int i = 0; i < 11; i++) {
            if (lion[i] == 0 && aPeach[i] == 0) continue;
            if (lion[i] > aPeach[i]) {
                l += (10 - i);
            } else {
                a += (10 - i);
            }
        }
        if (l > a) {
            if (diff < l - a) {
                diff = l - a;
                answer = lion.clone();
            }
        }
    }

    public static int[] solution(int n, int[] info) {
        aPeach = info;
        lion = new int[11];
        dfs(n, 10);
        if (answer == null) return new int[]{-1};
        return answer;
    }

    public static void main(String[] args) {
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int[] ans = solution(5, info);

        for (int a : ans) {
            System.out.print(a + " ");
        }
    }
}
