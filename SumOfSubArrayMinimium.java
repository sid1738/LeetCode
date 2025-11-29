import java.util.*;

public class SumOfSubArrayMinimium {


    private int[] beforeSmaller(int[]nums){

        Stack<Integer> st = new Stack<>();

        int n = nums.length;

        int []res = new int[n];

        for(int i=0;i<n;i++){

            while(!st.isEmpty() && nums[i]<=nums[st.peek()]){
                st.pop();
            }

            if(!st.isEmpty()){
                res[i] = st.peek();
            }else{
                res[i] = -1;
            }
            st.push(i);
        }

        return res;

    }

    private int[]afterSmaller(int[]nums){

        Stack<Integer> st = new Stack<>();

        int n = nums.length;

        int []res = new int[n];

        for(int i=n-1;i>=0;i--){

            while(!st.isEmpty() && nums[i]<=nums[st.peek()]){
                st.pop();
            }

            if(!st.isEmpty()){
                res[i] = st.peek();
            }else{
                res[i] = n;
            }
            st.push(i);
        }

        return res;
    }
    public int minSubArraySum(int []nums){

        int[] forward = beforeSmaller(nums);
        int[] backward = afterSmaller(nums);

        int sum =0;

        int n = nums.length;

        for(int i=0;i<n;i++){
            int l = i-forward[i];
            int r = backward[i]-i;

            sum+=l*r*nums[i];
        }

        return sum;
    }

    public static void main(String []args){
        int []nums = {3,1,2,4};

        SumOfSubArrayMinimium obj = new SumOfSubArrayMinimium();
        int ans = obj.minSubArraySum(nums);

        System.out.println(ans);
    }
}
