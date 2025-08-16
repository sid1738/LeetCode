package BiWeeklyContest163;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public long perfectPairs(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        Arrays.sort(nums);
        long ans = 0;
        int j = 1;
        for (int i = 0; i < n - 1; i++) {
            if (j <= i) j = i + 1;
            while (j < n && nums[j] - nums[i] <= nums[i]) {
                j++;
            }
            ans += (j - i - 1);
        }
        return ans;
    }
}
