public class MedianSortedArray {

    private double findMedian(int[] arr1, int arr2[]){

        int n1 = arr1.length;
        int n2 = arr2.length;

        if(n1>n2){
            return findMedian(arr2, arr1);
        }

        int n =(n1+n2+1)/2;

        int low = 0;
        int high = n1;

        while(low<=high){


            int mid1 = low+(high-low)/2;
            int l1=Integer.MIN_VALUE, l2 = Integer.MIN_VALUE, r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            int mid2 = n-mid1;

            if(mid1<n1)r1 = arr1[mid1];
            if(mid2<n2)r2 = arr2[mid2];

            if(mid1-1>=0)l1 = arr1[mid1-1];
            if(mid2-1>=0)l2 = arr2[mid2-1];

            if(l1<=r2 && l2<=r1){
                if((n1+n2)%2==0){
                    return (Math.max(l1, l2)+Math.min(r1, r2))/2.0;
                }else{
                    return (double) Math.max(l1, l2);
                }
            }

            if(l1>r2){
                high = mid1-1;
            }
            else{
                low = mid1+1;
            }


        }
        return -1.0;
    }

    public static void main(String []args){
        MedianSortedArray obj = new MedianSortedArray();
        int []arr1 = {1,2};
        int []arr2 = {3, 4};

        double ans = obj.findMedian(arr1, arr2);

        System.out.println(ans);
    }

}
