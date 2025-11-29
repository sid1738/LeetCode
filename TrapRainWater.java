import java.util.Stack;

public class TrapRainWater {


    public  int [] nge(int [] heights){

        int n = heights.length;

        Stack<Integer> st = new Stack<>();

        int [] forward = new int[n];

        for(int i=n-1;i>=0;i--){

            while(!st.isEmpty() && heights[i]>=heights[st.peek()]){
                st.pop();
            }

            if(!st.isEmpty()){
                forward[i] = heights[st.peek()];
            }else{
                forward[i] =heights[i];
            }
            st.push(i);
        }
        return forward;

    }

    public  int [] pge(int [] heights){

        int n = heights.length;

        Stack<Integer> st = new Stack<>();

        int [] forward = new int[n];

        for(int i=0;i<n;i++){

            while(!st.isEmpty() && heights[i]>=heights[st.peek()]){
                st.pop();
            }

            if(!st.isEmpty()){
                forward[i] = heights[st.peek()];
            }else{
                forward[i] =heights[i];
            }
            st.push(i);
        }
        return forward;

    }

    public void printArray(int [] nums){
        for(int num: nums){
            System.out.print(num+", ");
        }

        System.out.println("");
    }
    public  int trapWater(int []nums){


        int n = nums.length;

        int water = 0;

        int left =0, right = n-1;


        int maxLeft =0, maxRight = 0;
        while(left<=right){
            if(nums[left]<=nums[right]){

                if(nums[left]>maxLeft){
                    maxLeft = nums[left];
                }else{
                    water+=maxLeft-nums[left];
                }
                left++;
            }else{
                if(nums[right]>maxRight){
                    maxRight = nums[right];
                }else{
                    water+=maxRight-nums[right];
                }
                right--;
            }
        }
        return water;



    }

    public static void main(String []args){
        TrapRainWater obj = new TrapRainWater();

        int [] heights = {0,1,0,2,1,0,1,3,2,1,2,1};

        int ans = obj.trapWater(heights);
        System.out.println(ans);
    }
}
