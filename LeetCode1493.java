class Solution {
    public int longestSubarray(int[] nums) {
        
        int n=nums.length;
        int front=0;
        int idx0=-1;
        int longest=0;
        for(int i=0;i<n;i++){
            if(nums[i]==0){
                idx0=i;
                break;
            }
            longest=Math.max(longest,i-front+1);
        }

        if(idx0==-1 || idx0==n-1)return Math.max(0,longest-1);

        for(int i=idx0+1;i<n;i++){
            if(nums[i]==0){
                front=idx0+1;
                idx0=i;
            }
            else{
                longest=Math.max(longest,i-front);
            }
        }

        return longest;



    }
}