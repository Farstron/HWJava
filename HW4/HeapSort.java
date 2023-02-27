package HW4;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,2,3,1,5}; 
        heapSort(arr);
        for ( int i = 0; i< arr.length; i++){
            System.out.println(arr[i] + " ");
        }
    }

    private static void heapSort(int[] arr){
        int n = arr.length;
        for (int i = n/2-1; i>= 0; i--){
            heapify(arr, i, n);
        }

        for( int i =n-1;i>=0; i--){
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            heapify(arr, 0, i);
        }
    }

    private static void heapify(int[] arr, int i, int n) {
        int l = i*2+1;
        int r = i*2+2;
        int larg = i;
        if ( l < n && arr[l] > arr[larg])
            larg=l;
        if ( r < n && arr[r] > arr[larg])
            larg=r;

        if (i != larg){
            int temp = arr[i];
            arr[i] = arr[larg];
            arr[larg] = temp;

            heapify(arr, larg, n);
        }
    }

   
}

