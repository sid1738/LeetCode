class Solution {
    private double solve(int total, int n, int k, int maxPts) {
        // If Alice stops drawing
        if (total >= k) {
            return total <= n ? 1.0 : 0.0;
        }
        double prob = 0.0;
        for (int draw = 1; draw <= maxPts; draw++) {
            prob += solve(total + draw, n, k, maxPts);
        }
        return prob / maxPts;
    }

    public double new21Game(int n, int k, int maxPts) {
        return solve(0, n, k, maxPts);
    }
}