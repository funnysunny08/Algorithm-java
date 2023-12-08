package DisjointSets;

public class 서로소집합1 {

    static int[] parent = new int[10];
    static int[] rank = new int[parent.length];

    public static void makeSet(int x) { // makeSet: 유일한 멤버 x를 포함하는 새로운 집합 생성
        parent[x] = x;
    }

    public static int findSet(int x) { // findSet: x를 포함하 집합을 찾는 연산 (해당 노드의 부모 정보 갱신)
        if (x == parent[x]) { // 주인 노드 찾으면 return!
            return x;
        } else {
            parent[x] = findSet(parent[x]); // 부모 노드를 찾을 때까지 재귀! & 갱신
            return parent[x];
        }
    }

    public static void union(int x, int y) { // union: x와 y를 포함하는 두 집합을 통합하는 연산
        int px = findSet(x);
        int py = findSet(y);

        if (px != py) {
            parent[py] = px;
        }
    }

    public static void main(String[] args) {

    }

}
