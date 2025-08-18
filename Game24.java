import java.util.ArrayList;
import java.util.List;

class Solution {

    private List<Double> allPossible(List<Double>cards, int i, int j) {
        List<Double> res = new ArrayList<>();
        res.add(cards.get(i) + cards.get(j));
        res.add(cards.get(i) - cards.get(j));
        res.add(cards.get(j) - cards.get(i));
        res.add(cards.get(i) * cards.get(j));
        if (cards.get(j) != 0) {
            res.add(cards.get(i) / cards.get(j));
        }
        if (cards.get(i) != 0) {
            res.add(cards.get(j) / cards.get(i));
        }
        return res;
    }

    public boolean judge(List<Double>cards) {
        if(cards.size() == 1) {
            return Math.abs(cards.get(0) - 24) < 1e-6;
        }

        int n=cards.size();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                List<Double>res=new ArrayList<>();
                res=allPossible(cards, i, j);

                List<Double>nextCards = new ArrayList<>();

                for(int k=0;k<n;k++){
                    if(k!=i && k!=j){
                        nextCards.add(cards.get(k));
                    }
                }
                for(double card:res){
                    nextCards.add(card);
                    if(judge(nextCards)) {
                        return true;
                    }
                    nextCards.remove(nextCards.size() - 1); // backtrack
                }
                
            }
        }
        return false;
    }
    public boolean judgePoint24(int[] cards) {
        List<Double> cardList = new ArrayList<>();
        for (int card : cards) {
            cardList.add((double) card);
        }
        return judge(cardList);
    }
}