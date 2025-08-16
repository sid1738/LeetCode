import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class RearrangingFruits {
    public static void main(String[] args) {
        int[] basket1 = {4,2,2,2};
        int [] basket2 = {1,4,1,2};
        long ans=minCost(basket1,basket2);
        System.out.println(ans);
    }

    public static long minCost(int[] basket1, int[] basket2) {
        Map<Integer,Integer> countMap1 = new HashMap<>();
        Map<Integer,Integer> countMap2 = new HashMap<>();
        Map<Integer,Integer> countMap = new HashMap<>();
        for (int fruit : basket1) {
            countMap1.put(fruit, countMap1.getOrDefault(fruit, 0) + 1);
            countMap.put(fruit, countMap.getOrDefault(fruit, 0) + 1);
        }  
        for (int fruit : basket2) {
            countMap2.put(fruit, countMap2.getOrDefault(fruit, 0) +1);
            countMap.put(fruit, countMap.getOrDefault(fruit, 0) +1);
        }

        for(Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue()%2!=0) {
                return -1; // Not possible to rearrange
            }
            
            if(countMap1.containsKey(entry.getKey()) && countMap2.containsKey(entry.getKey())) {
                int count1 = countMap1.get(entry.getKey());
                int count2 = countMap2.get(entry.getKey());
                int min=Math.min(count1, count2);
                countMap.put(entry.getKey(), count1 - min);
            }
        }
        int count=0;
        Map<Integer, Integer> countMap2Copy = new TreeMap<>();
        for(Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int val= entry.getValue();
            int key = entry.getKey();
            if(val > 0) {
                countMap2Copy.put(key, val/2);
                count += val/2;
            }
        }

        long ans = 0;
        count = count / 2; // We need to use half of the remaining fruits from basket2
        for(Map.Entry<Integer, Integer> entry : countMap2Copy.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            if(count>val){
                ans+= key * val;
                count -= val;
            }
            else {
                ans += key * count;
                break; // No need to continue if we have used all the count
            }
        }
        return ans;


        
    }

}   