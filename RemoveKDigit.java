import java.util.Stack;

public class RemoveKDigit {

    public String removeKDigits(String s, int k){


        char[] chars = s.toCharArray();

        int n = s.length();

        Stack<Character> st =new Stack<>();

        for(int i=0;i<n;i++){
            while(!st.isEmpty() && chars[i]-'a'<=st.peek()-'a' && k>0){
                st.pop();
                k--;
            }
            st.add(chars[i]);
        }

        while(k>0){
            st.pop();
            k--;
        }

        String res = "";

        while(!st.isEmpty()){
            res = st.peek()+res;
            st.pop();
        }
        int i=0;
        while(res.charAt(i)=='0'){
            i++;
        }

        return res.substring(i);
    }

    public static void main(String[] args){
        String num = "1432219";
        int k = 3;

        RemoveKDigit obj = new RemoveKDigit();
        String ans = obj.removeKDigits(num, k);

        System.out.println(ans);
    }
}
