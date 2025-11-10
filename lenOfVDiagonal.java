import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class lenOfVDiagonal {
    
    public int findDiagonalLength(int[][] mat) {
       
        int r=mat.length;
        int c=mat[0].length;

        Map<Integer,Integer> map=new HashMap<>();

        map.put(1,2);
        map.put(2,0);
        map.put(0,2);

        int []dx={1,1,-1,-1};
        int []dy={1,-1,1,-1};

        Map<Integer,Integer> dirs=new HashMap<>();

        dirs.put(0,1);
        dirs.put(1,3);
        dirs.put(2,0);
        dirs.put(3,2);
        int maxV=0;

        Queue<List<Integer>> q=new LinkedList<>();

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(mat[i][j]==1){
                    q.add(Arrays.asList(i,j,1,1,-1));
                }
            }
        }

        
        while(!q.isEmpty()){
            List<Integer> curr=q.poll();
            int x=curr.get(0);
            int y=curr.get(1);
            int len=curr.get(2);
            int turns=curr.get(3);
            int dir=curr.get(4);
            maxV=Math.max(maxV,len);
            if(dir==-1){
                for(int d=0;d<4;d++){
                    int nx=x+dx[d];
                    int ny=y+dy[d];
                    if(nx>=0 && nx<r && ny>=0 && ny<c && mat[nx][ny]==map.get(mat[x][y])){
                        q.add(Arrays.asList(nx,ny,len+1,turns, d));
                    }
                }
            }
            else{
                int nx=x+dx[dir];
                int ny=y+dy[dir];
                if(nx>=0 && nx<r && ny>=0 && ny<c && mat[nx][ny]==map.get(mat[x][y])){
                    q.add(Arrays.asList(nx,ny,len+1,turns, dir));
            
                if(turns>0){
                    // clockwise direction change
                    int ndir=dirs.get(dir);
                    nx=x+dx[ndir];
                    ny=y+dy[ndir];
                    if(nx>=0 && nx<r && ny>=0 && ny<c && mat[nx][ny]==map.get(mat[x][y])){
                        q.add(Arrays.asList(nx,ny,len+1,turns-1, ndir));
                    }
                }
            }
            }
    }
        return maxV;
        
    }
    public static void main(String[] args) {
        lenOfVDiagonal obj=new lenOfVDiagonal();
        int[][] mat={{2,2,1,2,2},{2,0,2,2,0},{2,0,1,1,0},{1,0,2,2,2},{2,0,0,2,2}};
        System.out.println(obj.findDiagonalLength(mat));
    }
}
