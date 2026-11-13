class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        int LOG = 17;

        int[][] up = new int[LOG][n];
        long[][] sum = new long[LOG][n];

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Step 1: Find Next Greater Element
        int[] nge = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {

            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            nge[i] = (top == -1) ? n : stack[top];

            stack[++top] = i;
        }

        // Step 2: Build Binary Lifting Table
        for (int i = 0; i < n; i++) {
            up[0][i] = nge[i];

            sum[0][i] =
                (long) (nge[i] - i) * nums[i];
        }

        for (int p = 1; p < LOG; p++) {

            for (int i = 0; i < n; i++) {

                int mid = up[p - 1][i];

                if (mid == n) {
                    up[p][i] = n;
                    sum[p][i] = sum[p - 1][i];
                } else {
                    up[p][i] = up[p - 1][mid];

                    sum[p][i] =
                        sum[p - 1][i] +
                        sum[p - 1][mid];
                }
            }
        }

        // Step 3: Calculate prefix maximum sum
        long ans = 0;

        for (int l = 0; l < n; l++) {

            int low = l;
            int high = n - 1;
            int best = l - 1;

            while (low <= high) {

                int r = low + (high - low) / 2;

                // Sum of prefix maximums from l to r
                long maxSum = 0;
                int cur = l;

                for (int p = LOG - 1; p >= 0; p--) {

                    if (cur < n && up[p][cur] <= r + 1) {

                        maxSum += sum[p][cur];
                        cur = up[p][cur];
                    }
                }

                if (cur <= r) {
                    maxSum +=
                        (long) (r - cur + 1) * nums[cur];
                }

                // Minimum operations required
                long cost = maxSum -
                            (prefix[r + 1] - prefix[l]);

                if (cost <= k) {
                    best = r;
                    low = r + 1;
                } else {
                    high = r - 1;
                }
            }

            ans += best - l + 1;
        }

        return ans;
    }
}