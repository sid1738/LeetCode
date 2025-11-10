package BiWeeklyContest163;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution6 {
    public int dfs(int[][] grid, int i, int j, int k,int[][][]dp) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if(i==grid.length-1 && j==grid[0].length-1)return 0;

        if((grid[i][j]>=grid[grid.length-1][grid[0].length-1] && k>=1)){
            return grid[i][j];
        }

        if(dp[i][j][k]!=-1)return dp[i][j][k];


        int rcost=dfs(grid,i,j+1,k,dp);


        if(rcost!=Integer.MAX_VALUE){
            rcost+=grid[i][j];
        }

        
        int dcost=dfs(grid,i+1,j,k,dp);


        if(dcost!=Integer.MAX_VALUE){
            dcost+=grid[i][j];
        }

        int cost=Math.min(rcost,dcost);
        if(k>0){
            for(int x=i; x<grid.length; x++) {
                for(int y=j; y<grid[0].length; y++) {
                    if( !(x == i && y == j) && grid[i][j]>=grid[x][y]) {
                        int tele = dfs(grid, x, y, k - 1, dp);
                        if (tele != Integer.MAX_VALUE) {
                            
                            cost = Math.min(cost, tele + grid[i][j]);
                        }
                    }
                }
            }
        }

        return dp[i][j][k]=cost;
    }
    public int minCost(int[][] grid, int k) {
   
        int[][][]dp=new int[grid.length][grid[0].length][k+1];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                for(int p=0;p<=k;p++){
                    dp[i][j][p]=-1;
                }
            }
        }
        int cost = dfs(grid, 0, 0, k,dp)-grid[0][0];

        return cost;

    }
}



