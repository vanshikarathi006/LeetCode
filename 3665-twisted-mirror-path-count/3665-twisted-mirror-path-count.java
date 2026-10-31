class Solution {
    public int uniquePaths(int[][] grid) {
         int m = grid.length, n = grid[0].length;
        final int MOD = 1_000_000_007;

        // goR[i][j] = cell where robot lands if it tries to move right from (i,j)
        // goD[i][j] = cell where robot lands if it tries to move down from (i,j)
        // encoded as x * n + y, or -1 if out of bounds
        int[][] goR = new int[m][n];
        int[][] goD = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // try right
                if (j + 1 >= n) goR[i][j] = -1;
                else if (grid[i][j + 1] == 0) goR[i][j] = i * n + (j + 1);
                else goR[i][j] = goD[i][j + 1];   // mirror: reflected down, continues from there

                // try down
                if (i + 1 >= m) goD[i][j] = -1;
                else if (grid[i + 1][j] == 0) goD[i][j] = (i + 1) * n + j;
                else goD[i][j] = goR[i + 1][j];   // mirror: reflected right, continues from there
            }
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0 || grid[i][j] == 1) continue;
                int t = goR[i][j];
                if (t != -1) {
                    int x = t / n, y = t % n;
                    dp[x][y] = (dp[x][y] + dp[i][j]) % MOD;
                }
                t = goD[i][j];
                if (t != -1) {
                    int x = t / n, y = t % n;
                    dp[x][y] = (dp[x][y] + dp[i][j]) % MOD;
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}