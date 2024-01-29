package 오답.kakao;

// 카카오: 도넛과 막대 그래프
public class 도넛과막대그래프 {

    static int[] in = new int[1000001]; // 정점별 들어오는 간선 수
    static int[] out = new int[1000001]; // 정점별 나가는 간선 수
    static int size = Integer.MIN_VALUE;
    static int graph;

    public static int[] solution(int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            in[edges[i][1]]++;
            out[edges[i][0]]++;
            size = Math.max(size, Math.max(edges[i][0], edges[i][1]));
        }

        // 생성한 정점 찾기
        int node = 0;
        for (int i = 1; i <= size; i++) {
            if (in[i] == 0 && out[i] >= 2) {
                graph = out[i]; // 그래프 수
                node = i;
                break;
            }
        }

        // 생성 정점과 연결된 간선 제거
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == node) {
                in[edges[i][1]]--;
            }
        }

        // 그래프 분류하기
        int a = 0, b = 0, c = 0; // 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수
        for (int i = 1; i <= size; i++) {
            if (i == node) continue;

            if (in[i] == 0) {
                b++;
            } else if (out[i] == 2 && in[i] == 2) {
                c++;
            }
        }

        a = graph - b - c;
        return new int[]{node, a, b, c};
    }

    public static void main(String[] args) {

    }
}
