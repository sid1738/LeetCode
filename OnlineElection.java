import java.util.*;

class OnlineElection {

    private int upper_bound(List<Integer> arr, int idx){
        int low = 0;
        int high = arr.size();

        if(arr.size()==0)return arr.size();

        int ans = high;
        while(low<=high){

            int mid = low+(high-low)/2;

            if(arr.get(mid)>idx){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }

        return ans;
    }
    public String findLongestWord(String s, List<String> dictionary) {

        Map<Character, List<Integer>> m = new HashMap<>();

        for(char ch = 'a';ch<='z'; ch++){
            m.put(ch, new ArrayList<>());
        }


        for(int i=0;i<s.length();i++){

            char ch = s.charAt(i);
            List<Integer> list = new ArrayList<>();;
            if(m.containsKey(ch)){
                list = m.get(ch);
            }
            list.add(i);
            m.put(ch, list);

        }

        int max = 0;
        String res ="";

        for(String word: dictionary){

            char []chars = word.toCharArray();
            int idx = -1;
            int flag = 0;

            for(char ch: chars){

                List<Integer> charList = m.get(ch);
                int id = upper_bound(charList, idx);
                if(id == charList.size()){
                    flag = 0;
                    break;
                }
                System.out.print(id+",");
                idx = id;

            }
            System.out.println("");

            if(flag == 1){
                if(max>=word.length()){
                    if(max == word.length()){
                        if(word.compareTo(res)<0){
                            res=word;
                            max = word.length();
                        }
                    }else{
                        res = word;
                        max = word.length();
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args){
        String s = "abpcplea";
        List<String> dictionary = Arrays.asList("ale","apple","monkey","plea");
        OnlineElection obj = new OnlineElection();
        String res = obj.findLongestWord(s, dictionary);
        System.out.println(res);
    }
}