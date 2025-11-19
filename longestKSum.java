import java.util.HashMap;
import java.util.Map;

public class longestKSum {


    private int klongest(int []arr, int k){


        Map<Integer, Integer> m = new HashMap<>();
        int sum = 0;
        int maxlen = 0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum == k){
                maxlen = Math.max(maxlen ,i+1);
            }

            if(m.containsKey(sum-k)){
                maxlen = Math.max(maxlen, i-m.get(sum-k));
            }

            if(!m.containsKey(sum)){
                m.put(sum, i);
            }
        }
        return maxlen;
    }

    public static void main(String[] args){
        int []arr = { -1, 1, 1};
        int k = 1;


        longestKSum obj = new longestKSum();


        int ans = obj.klongest(arr, k);

        System.out.println(ans);

    }
}
