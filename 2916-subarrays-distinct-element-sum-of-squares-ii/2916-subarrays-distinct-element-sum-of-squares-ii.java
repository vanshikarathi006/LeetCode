class Solution {
    static final int MOD = 1_000_000_007;

    int[] sum;
    int[] lazy;
    long[] sumSq;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new int[4 * n];
        lazy = new int[4 * n];
        sumSq = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int prev = last[x];

            // Distinct count +1 for affected subarrays
            update(1, 0, n - 1, prev + 1, i);

            // Sum of squared distinct counts for subarrays
            // ending at index i
            ans = (ans + sumSq[1]) % MOD;

            last[x] = i;
        }

        return (int) ans;
    }

    void update(int node, int start, int end,
                int left, int right) {
        if (start > right || end < left) {
            return;
        }

        if (left <= start && end <= right) {
            apply(node, start, end);
            return;
        }

        pushDown(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);

        sum[node] = (int) (
            ((long) sum[node * 2] + sum[node * 2 + 1]) % MOD
        );

        sumSq[node] = (
            sumSq[node * 2] + sumSq[node * 2 + 1]
        ) % MOD;
    }

    void apply(int node, int start, int end) {
        long len = end - start + 1L;
        long oldSum = sum[node];

        sumSq[node] = (
            sumSq[node] + 2L * oldSum + len
        ) % MOD;

        sum[node] = (int) ((oldSum + len) % MOD);

        lazy[node]++;
        if (lazy[node] >= MOD) {
            lazy[node] -= MOD;
        }
    }

    void pushDown(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        int value = lazy[node];

        applyMultiple(node * 2, start, mid, value);
        applyMultiple(node * 2 + 1, mid + 1, end, value);

        lazy[node] = 0;
    }

    void applyMultiple(int node, int start, int end, int value) {
        long len = end - start + 1L;
        long d = value;
        long oldSum = sum[node];

        sumSq[node] = (
            sumSq[node]
            + 2L * d * oldSum
            + d * d % MOD * len
        ) % MOD;

        sum[node] = (int) ((oldSum + d * len) % MOD);

        lazy[node] = (int) ((lazy[node] + d) % MOD);
    }
}
