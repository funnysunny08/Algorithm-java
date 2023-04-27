package BackTracking;
// 백트래킹 - 순열
public class Permutation {
    // 사전순으로 순열 구하기
    static public void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            print(output, r);
            return;
        } else {
            for (int i = 0; i < n; i++) {
                if (visited[i] == false) {
                    visited[i] = true; // 방문체크
                    output[depth] = arr[i]; // depth가 output의 인덱스 역할을 한다.
                    perm(arr, output, visited, depth + 1, n, r);
                    visited[i] = false; // 원상복구!!
                }
            }
        }
    }

    static void print(int[] arr, int r) {
        for (int i = 0; i < r; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 3;
        int[] arr = {1, 2, 3};
        int[] output = new int[n]; // n개 뽑음 -> nPn -> n!
        boolean[] visited = new boolean[n];

        perm(arr, output, visited, 0, n, 2);
        // 배열, 뽑아낼 원소들 배열, 방문 배열, depth, n (주어진 원소의 갯수), r (만들고 싶은 순열의 길이)

        // 이미 들어간 값은 visited 값을 true로 바꾸어 중복하여 넣지 않도록 한다.
        // depth 값은 output에 들어간 숫자의 길이라고 생각하면 된다.
        // depth의 값이 r만큼 되면 output에 들어있는 값을 출력.
    }
}
