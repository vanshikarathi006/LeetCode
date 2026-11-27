class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int MAX_FACTOR = 1500;
        int[] freq = new int[MAX_FACTOR + 1];
        
        // 1. Count occurrences of each factor
        for (int[] item : items) {
            freq[item[0]]++;
        }
        
        // 2. Precompute how many items are divisible by each factor f
        int[] divisibleCount = new int[MAX_FACTOR + 1];
        for (int f = 1; f <= MAX_FACTOR; f++) {
            if (freq[f] == 0) continue; // Only need to compute for factors present in items
            for (int multiple = f; multiple <= MAX_FACTOR; multiple += f) {
                divisibleCount[f] += freq[multiple];
            }
        }
        
        // 3. Knapsack DP
        int[] dp = new int[budget + 1];
        
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price > budget) continue;
            
            int firstCopyGain = divisibleCount[factor];
            
            // Buy the 1st copy (0/1 transition: iterate backwards)
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstCopyGain);
            }
            
            // Buy 2nd, 3rd, ... copies (Unbounded transition: iterate forwards)
            for (int b = 2 * price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }
        
        int maxItems = 0;
        for (int count : dp) {
            maxItems = Math.max(maxItems, count);
        }
        
        return maxItems;
    }
}