package BackTracking;
import java.util.*;

// 백트래킹 - 부분 집합
public class PowerSet {
    public static void subset(int[] arr, int idx, int[] select, int sidx) {
        // r개를 고르지 않아도, 원본 배열을 모두 돌았으면 출력
        if (idx == arr.length) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < sidx; i++) {
                list.add(select[i]);
            }
            System.out.println(list);
            return;
        }

        subset(arr, idx + 1, select, sidx);
        select[sidx] = arr[idx];
        subset(arr, idx + 1, select, sidx + 1);
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 7};
        int[] select = new int[arr.length];
        subset(arr, 0, select, 0);
    }
}
