public class MCM {


    private int helper(int i,int j, int[] matrices){
        if(i==j)return 0;

        int miniSteps = Integer.MAX_VALUE;

        for(int k=i;k<j;k++){
            int steps = helper(i,k, matrices)+helper(k+1,j, matrices)
                    +matrices[i-1]*matrices[k]*matrices[j];
            miniSteps= Math.min(miniSteps, steps);
        }

        return miniSteps;
    }
    public int mcm(int[] matrices){

        int n = matrices.length;

        int ans = helper(1, n-1,matrices);

        return ans;

    }

    public static void main(String[] args){
        int []arr= {10, 20, 30, 40, 50};

        MCM obj = new MCM();
        int ans = obj.mcm(arr);

        System.out.println(ans);
    }
}
