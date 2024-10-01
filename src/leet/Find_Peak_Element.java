package leet;

// 162. Find Peak Element
public class Find_Peak_Element {

    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int peak_height = nums[0];
        int peak_idx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) continue; // 감소
            if (nums[i] < peak_height) continue;
            peak_height = nums[i];
            peak_idx = i;
        }
        return peak_idx;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(findPeakElement(nums));
    }
}
