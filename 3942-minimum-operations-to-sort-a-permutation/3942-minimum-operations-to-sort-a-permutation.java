class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            rev[i] = nums[n - 1 - i];
        }
        int[][] dist = new int[2][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        dist[0][0] = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int type = cur[0];
            int shift = cur[1];
            int d = dist[type][shift];
            if (isSorted(nums, rev, type, shift)) {
                return d;
            }
            int nextShift = (shift + 1) % n;
            if (dist[type][nextShift] == -1) {
                dist[type][nextShift] = d + 1;
                q.offer(new int[]{type, nextShift});
            }
            int nextType = 1 - type;
            nextShift = (n - shift) % n;
            if (dist[nextType][nextShift] == -1) {
                dist[nextType][nextShift] = d + 1;
                q.offer(new int[]{nextType, nextShift});
            }
        }
        return -1;
    }
    private boolean isSorted(
        int[] nums, int[] rev, int type, int shift) {
        int n = nums.length;
        int[] arr = type == 0 ? nums : rev;
        for (int i = 0; i < n; i++) {
            if (arr[(i + shift) % n] != i) {
                return false;
            }
        }
        return true;
    }
}