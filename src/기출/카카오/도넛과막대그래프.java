package 기출.카카오;

// 카카오: 도넛과 막대 그래프
public class 도넛과막대그래프 {

    static int[] in = new int[1000001]; // 해당 정점으로 들어오는 간선 수
    static int[] out = new int[1000001]; // 해당 정점으로부터 나가는 간선 수


    public static int[] solution(int[][] edges) {
        // 0.
        int maxNumber = Integer.MIN_VALUE;
        for (int i = 0; i < edges.length; i++) {
            out[edges[i][0]]++;
            in[edges[i][1]]++;
            maxNumber = Math.max(maxNumber, Math.max(edges[i][0], edges[i][1]));
        }

        // 1. 생성된 정점 찾기
        int center = 0;
        for (int i = 1; i <= maxNumber; i++) {
            if (in[i] == 0 && out[i] >= 2) {
                center = i;
                break;
            }
        }

        // 2. 생성된 정점과 연결된 간선 제거
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == center) {
                in[edges[i][1]]--;
            }
        }

        // 3. 총 그래프 수
        int total = out[center];

        // 4. 그래프별 개수 찾기
        int a = 0, b = 0, c = 0; // 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수
        for (int i = 1; i <= maxNumber; i++) {
            if (i == center) continue;
            if (in[i] == 0) b++;
            else if (in[i] == 2 && out[i] == 2) c++;
        }
        a = total - b - c;

        return new int[]{center, a, b, c};
    }

    public static void main(String[] args) {
        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        int[] answer = solution(edges);
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2] + " " + answer[3]);
    }
}
