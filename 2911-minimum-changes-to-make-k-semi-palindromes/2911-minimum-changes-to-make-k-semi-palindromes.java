class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 100000;

        int[][] cost = new int[n][n];

        // Step 1: Precompute semi-palindrome costs
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                cost[i][j] = INF;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    for (int offset = 0; offset < d; offset++) {
                        int left = i + offset;
                        int right = i + offset
                                + ((len - 1 - offset) / d) * d;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[i][j] = Math.min(cost[i][j], changes);
                }
            }
        }

        // Step 2: Partition DP
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[n][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = n - 2 * p; i >= 0; i--) {

                for (int j = i + 1;
                     j <= n - 2 * (p - 1) - 1; j++) {

                    dp[i][p] = Math.min(
                        dp[i][p],
                        cost[i][j] + dp[j + 1][p - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}