class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long totalCost = 0;
        long common = Math.min(need1, need2);
        long effectiveCostBoth = Math.min((long) costBoth, (long) cost1 + cost2);
        totalCost += common * effectiveCostBoth;
        long rem1 = need1 - common;
        long rem2 = need2 - common;
        long effectiveCost1 = Math.min((long) costBoth, (long) cost1);
        totalCost += rem1 * effectiveCost1;
        long effectiveCost2 = Math.min((long) costBoth, (long) cost2);
        totalCost += rem2 * effectiveCost2;
        
        return totalCost;
    }
}