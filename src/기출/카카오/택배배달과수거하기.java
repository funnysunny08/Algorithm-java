package 기출.카카오;

public class 택배배달과수거하기 {

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {

        long answer = 0;
        int dSize = n, pSize = n;

        for (int i = dSize - 1; i >= 0; i--) {
            if (deliveries[i] > 0) break;
            dSize--;
        }
        for (int i = pSize - 1; i >= 0; i--) {
            if (pickups[i] > 0) break;
            pSize--;
        }
        long size = Math.max(dSize, pSize);

        while (size > 0) {
            // deliveries 뒤에서 부터 cap 만큼 제거
            int dCap = cap;
            for (int i = dSize - 1; i >= 0 && dCap > 0; i--) {
                if (deliveries[i] > 0) {
                    if (deliveries[i] >= dCap) {
                        deliveries[i] -= dCap;
                        break;
                    } else {
                        dCap -= deliveries[i];
                        deliveries[i] = 0;
                    }
                }
            }
            for (int i = dSize - 1; i >= 0; i--) {
                if (deliveries[i] > 0) break;
                dSize--;
            }

            // pickups 뒤에서 부터 cap 만큼 제거
            int pCap = cap;
            for (int i = pSize - 1; i >= 0 && pCap > 0; i--) {
                if (pickups[i] > 0) {
                    if (pickups[i] >= pCap) {
                        pickups[i] -= pCap;
                        break;
                    } else {
                        pCap -= pickups[i];
                        pickups[i] = 0;
                    }
                }
            }
            for (int i = pSize - 1; i >= 0; i--) {
                if (pickups[i] > 0) break;
                pSize--;
            }
            answer += size;
            size = Math.max(dSize, pSize);
        }
        return answer * 2;
    }

    public static void main(String[] args) {
        int[] d = {1, 0, 3, 1, 2};
        int[] d1 = {1, 0, 2, 0, 1, 0, 2};
        int[] p = {0, 3, 0, 4, 0};
        int[] p1 = {0, 2, 0, 1, 0, 2, 0};
//        System.out.println(solution(4, 5, d, p));
//        System.out.println(solution(2, 7, d1, p1));
        int[] dd = {0, 0};
        int[] pp = {0, 0};
        System.out.println(solution(2, 2, dd, pp));
    }
}
