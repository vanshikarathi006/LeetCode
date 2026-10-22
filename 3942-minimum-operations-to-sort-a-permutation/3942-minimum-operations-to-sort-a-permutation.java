class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if(n==1) return 0;
        int zeroIndex = -1;
        int descents = 0;
        int ascents = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIndex = i;
            }
            int next = nums[(i + 1) % n];
            if (nums[i] > next) descents++;
            if (nums[i] < next) ascents++;
        }
        if (descents == 1) {
            return Math.min(zeroIndex, n - zeroIndex + 2);
        }
        if (ascents == 1) {
            int reverseZeroIndex = n - 1 - zeroIndex;
            return 1 + Math.min(reverseZeroIndex,n - reverseZeroIndex);
        }
        return -1;
    }
}