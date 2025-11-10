public class KMP {

    private int[]lps(String pattern){
        int n = pattern.length();

        int[]lps = new int[n];

        int i=1,j=0;

        while(i<n){
            if(pattern.charAt(i)==pattern.charAt(j)){
                lps[i] = j+1;
                j++;
                i++;
            }else{
                if(j>0){
                    j = lps[j-1];
                }else{
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public boolean kmp(String text, String pattern){


        int[]lps= lps(pattern);

        int n = text.length();
        int m = pattern.length();

        int i=0;
        int j=0;

        while(i<n){


            if(text.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
                if(j==m){
                    System.out.println("Yes");
                    return true;
                }
            }else{
                if(j>0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }
        System.out.println("No");
        return false;
    }

    public static void main(String[] args){
        KMP k = new KMP();
        String text = "abxabcabcaby";
        String pattern = "abcaby";

        boolean ans = k.kmp(text, pattern);

        System.out.println(ans);
    }
}
