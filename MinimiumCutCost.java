import java.util.Arrays;

public class MinimiumCutCost {

    public int cost(int start, int end, int []cuts){
        if(start>end)return 0;

        int minCost = Integer.MAX_VALUE;

        for(int i= start;i<=end;i++){
            int currCost = cuts[end+1]-cuts[start-1]+cost(start, i-1, cuts)+cost(i+1, end, cuts);
            minCost = Math.min(minCost, currCost);
        }


        return minCost;
    }
    public int minimiumCost(int n, int [] cuts){


        int m = cuts.length;
        Arrays.sort(cuts);

        int []nums = new int[m+2];

        nums[0] = 0;
        nums[m+1] = n;

        for(int i=0;i<m;i++){
            nums[i+1] = cuts[i];
        }

        return cost(1, m,  nums);
    }

    public static void main(String []args){
        int [] cuts = {1,3,4,5};

        MinimiumCutCost obj = new MinimiumCutCost();

        int ans = obj.minimiumCost(7, cuts);

        System.out.println(ans);
    }
}
