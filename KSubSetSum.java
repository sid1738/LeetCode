import java.util.Arrays;

public class KSubSetSum {

    private int KsumCount(int[]arr, int k){

        int n = arr.length;

        Arrays.sort(arr);

        int [][]dp = new int[n+1][k+1];

        for(int i=0;i<=n;i++)dp[i][0]=1;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=k;j++){
                if(arr[i-1]<=j){
                    dp[i][j] = dp[i-1][j]+dp[i-1][j-arr[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }

                System.out.print(dp[i][j]+", ");
            }
            System.out.println("");

        }



        return dp[n][k];
    }


    public static void main(String[] args){
        KSubSetSum obj = new KSubSetSum();

        int ans = obj.KsumCount(new int[]{1,4,4,5}, 5);
        System.out.println(ans);

    }
}
