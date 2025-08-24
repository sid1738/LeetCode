import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CountSquares {
    public int countSquares(int[][] matrix) {
        
        int r = matrix.length;
        int c = matrix[0].length;

        int ans = 0;

        int []dx = {0,1,1};
        int []dy = {1,1,0};

        for(int i = 0;i < r; i++){
            for(int j = 0; j < c; j++){
                if(matrix[i][j] == 1){
                    
                    Queue<List<Integer>>q = new LinkedList<>();
                    q.add(Arrays.asList(i, j));

                    int [][] vis =new int[r][c];
                    vis [i][j] = 1;
                    int comeOut=0;

                    while(!q.isEmpty()){
                        
                        ans++;

                        int sz = q.size();
                        for(int m = 0;m < sz; m++){
                            
                            int x = q.peek().get(0);
                            int y = q.peek().get(1);
                            q.remove();

                            for(int k = 0;k < 3; k++){
                                
                                int newx = x + dx[k];
                                int newy = y + dy[k];

                                if(newx < r && newy < c && matrix[newx][newy] == 1 && vis[newx][newy] == 0){
                                    vis[newx][newy] = 1;
                                    q.add(Arrays.asList(newx,newy));
                                }
                                else if(newx < r && newy <c && vis[newx][newy] == 1)continue;
                                else{
                                    comeOut = 1;
                                    break;
                                }
                            }
                            if(comeOut == 1)break;                            
                        }
                        //System.out.println(q.size());
                        if(comeOut == 1)break;

                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CountSquares sol = new CountSquares();
        int[][] matrix = {
            {1, 0, 1},
            {1, 1, 0},
            {1, 1, 1}
        };
        System.out.println(sol.countSquares(matrix)); // Output: 13
    }
}