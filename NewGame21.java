class Solution {
    private Double[] dp;

    private double solve(int total, int n, int k, int maxPts) {
        if (total >= k) {
            return total <= n ? 1.0 : 0.0;
        }
        if (dp[total] != null) return dp[total];
        double prob = 0.0;
        for (int draw = 1; draw <= maxPts; draw++) {
            prob += solve(total + draw, n, k, maxPts);
        }
        return dp[total] = prob / maxPts;
    }

    public double new21Game(int n, int k, int maxPts) {
        dp = new Double[n + maxPts + 2];
        return solve(0, n, k, maxPts);
    }
}