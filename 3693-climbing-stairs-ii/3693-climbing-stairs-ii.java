class Solution {
    public int climbStairs(int n, int[] costs) {
         int prev3 = 0, prev2 = 0, prev1 = 0;

        for (int i = 1; i <= n; i++) {
            int curr = costs[i - 1] + Math.min(
                prev1 + 1,
                Math.min(prev2 + 4, prev3 + 9)
            );

            prev3 = prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}