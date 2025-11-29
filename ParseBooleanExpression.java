public class ParseBooleanExpression {

    private int mcm(int i, int j, String expression, boolean isTrue){

        if(i>j)return 0;

        if(i==j){
            if(isTrue){
                if(expression.charAt(i)=='T')return 1;
                else return 0;
            }else{
                if(expression.charAt(i)=='F')return 0;
                else return 1;
            }
        }

        int ways = 0;
        for(int k = i+1;k<=j-1;k+=2){


            int lf = mcm(i, k-1, expression, false);
            int lt = mcm(i, k-1, expression, true);
            int rf = mcm(k+1, j, expression, false);
            int rt = mcm(k+1, j, expression, true);

            if(expression.charAt(k) == '&'){
                if(isTrue){
                    ways+=lt*rt;
                }else{
                    ways+=lt*rf+lf*rt+lf*rf;
                }
            }
            else if(expression.charAt(k) == '|'){
                if(isTrue){
                    ways+=lt*rt+lf*rt+lt*rf;
                }else{
                    ways+=lf*rf;
                }
            }
            else{
                if(isTrue){
                    ways+=lf*rt+lt*rf;
                }else{
                    ways+=lf*rf+rt*lt;
                }
            }

        }

        return ways;



    }


    public int parseBoolExpr(String expression){
        //"&(|(f))"

        int n = expression.length();

        return mcm(0, n-1,  expression, true);
    }

    public static void main(String[] args){
        String expression="";
    }
}
