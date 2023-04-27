package BackTracking;
// 백트래킹 - 조합
public class Combination {
    static int n, m;
    static int[] combi;
    public void DFS(int L, int s) { // s는 스타트하는 값
        if (L == m) {
            for (int x : combi) System.out.print(x + " ");
            System.out.println();
        } else {
            for (int i = s; i <= n; i++) {
                combi[L] = i;
                DFS(L + 1, i + 1); // 원소가 중복이 안되면서 순서 또한 고려하지 않기 떄문에 한번 뽑은 위치의 원소는 다시는 나오지 않도록 시작 인덱스를 하나씩 올려준다.
            }
        }
    }

    public static void main(String[] args) {
        Combination combination = new Combination();
        n = 3;
        m = 2; // -> 3C2
        combi = new int[m];
        combination.DFS(0, 1); // 조합의 원소는 1부터 시작
    }
}
