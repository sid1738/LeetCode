import java.util.Arrays;
import java.util.Stack;

public class NGE {

    public int[] nge(int[]nums2){

        Stack<Integer> st = new Stack<>();

        int n =nums2.length;

        int []res = new int[n];

        for(int i=n-1;i>=0;i--){

            while(!st.isEmpty() && nums2[i]>=nums2[st.peek()]){
                st.pop();
            }

            if(!st.isEmpty()){
                res[i] = nums2[st.peek()];
            }else{
                res[i] = -1;
            }

            st.push(i);


        }
        return res;
    }


    public static void main(String [] args){
        int [] nums = {1,3,4,2};

        NGE obj = new NGE();
        int[]res = obj.nge(nums);

        for(int num: res){
            System.out.print(num+", ");
        }
    }


}
