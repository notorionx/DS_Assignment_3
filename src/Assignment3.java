import java.lang.reflect.Array;
import java.util.Arrays;

public class Assignment3 {

    /**
     * Prints node values at given depth, with root being at depth 0
     * @param root
     * @param depth
     */
    public static void printLevel(TreeNode root, int depth) {
        if(root == null || depth < 0) {
            //If root is null or depth is negative, do nothing
            return;
        }
        if(depth > 0) {
            //If depth is greater than 0, make recursive call to children from left to right and decrement depth
            printLevel(root.left, depth - 1);
            printLevel(root.right, depth - 1);
        } else {
            //If depth is 0, we are at the desired level so print value of node we are at
            System.out.println(root.value);
        }
    }

    /**
     * Finds and returns the median of the array that is passed in
     * @param input Array of length greater than 0
     * @return The median of the array
     * @throws IllegalArgumentException If user passes in null or an array of length 0
     */
    public static double findMedian(int[] input) throws IllegalArgumentException{
        if(input == null || input.length == 0) {
            //Alert user if array is null or has length 0
            throw new IllegalArgumentException("Array cannot be empty or null");
        }

        //Sort the input array
        mergeSort(input);

        //Return the middle element if array has odd length and the average of middle 2 elements if array has even length
        int mid = input.length / 2;
        double result = (input.length % 2 == 1 ? (double) input[mid] : (double) (input[mid] + input[mid - 1]) / 2);

        return result;
    }

    /**
     * Sorts the given array in ascending order in O(nlogn) time
     * @param arr The input array
     */
    public static void mergeSort(int[] arr) {
        if(arr.length < 2){
            //Arrays with length 1 are already sorted, so do nothing
            return;
        }

        System.out.println("Input array: " + Arrays.toString(arr));

        //Initialize and fill two arrays with values corresponding to the left and right subarrays split by the midpoint of the input array
        int mid  = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for(int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for(int j = mid; j < arr.length; j++) {
            right[j - mid] = arr[j];
        }

        System.out.println("Splitting into subarrays: " + Arrays.toString(left) + " and " + Arrays.toString(right));

        //Recursive calls to sort the left and right subarrays
        mergeSort(left);
        mergeSort(right);

        System.out.println("Merging subarrays: " + Arrays.toString(left) + " and " + Arrays.toString(right));

        //Merge the two subarrays into the original array after they are sorted
        merge(arr, left, right, left.length, right.length);

        System.out.println("Sorted and merged subarrays: " + Arrays.toString(arr));

    }

    /**
     * Merges the contents of two sorted subarrays in ascending order in the original input array
     * @param arr The original input array
     * @param left The left subarray
     * @param right The right subarray
     * @param leftLength The length of the left subarray
     * @param rightLength The length of the right subarray
     */
    public static void merge(int[] arr, int[] left, int[] right, int leftLength, int rightLength) {
        //Initialize indices for iterating through left and right arrays
        int i = 0, j = 0;

        while(i < leftLength && j < rightLength) {
            //Iterate through both arrays until we have exhausted at least one of the two subarrays
            if(left[i] < right[j]) {
                //If next element in left is smaller than next element in right, place left element into original array and increment left index
                arr[i + j] = left[i++];
            } else {
                //If next element in left is greater than or equal to next element in right, place right element into original array and increment right index
                arr[i + j] = right[j++];
            }
        }

        while(i < leftLength) {
            //If right array is exhausted first, place remaining elements from left array into original
            arr[i + j] = left[i++];
        }

        while(j < rightLength) {
            //If left array is exhausted first, place remaining elements from right array into original
            arr[i + j] = right[j++];
        }
    }

    public static int quickFindMedian(int[] input) {


        return 0;
    }

    public static int quickSelect(int[] arr, int l, int r, int k) {
        if(l < r) {
            int pivot = partition(arr, l, r);
            if(pivot == k - 1) {
                return arr[pivot];
            } else if(pivot > k - 1) {
                return quickSelect(arr, l, pivot, k);
            } else {
                return quickSelect(arr, pivot + 1, r, k);
            }
        }

        return -99999;
    }

    public static int partition(int[] arr, int l, int r) {
        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("Start: " + l + " End: " + r + " Pivot value: " + arr[l]);
        int pivot = l, store = l + 1;

        for(int i = l + 1; i < r + 1; i++) {
            if(arr[i] < arr[pivot]) {
                swap(arr, i, store);
                store++;
            }
        }

        swap(arr, pivot, store - 1);
        System.out.println("Output: " + Arrays.toString(arr) + "\n");
        return store - 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(2);
//        TreeNode n_L = new TreeNode(7);
//        TreeNode n_R = new TreeNode(5);
//        TreeNode n_LL = new TreeNode(2);
//        TreeNode n_LR = new TreeNode(6);
//        TreeNode n_RR = new TreeNode(9);
//
//        n_L.left = n_LL;
//        n_L.right = n_LR;
//        n_R.right = n_RR;
//        root.left = n_L;
//        root.right = n_R;
//
//        printLevel(root, 0);

        int[] arr = {1, 5, 3, 20, 4, 7};
//        System.out.println("Median is: " + findMedian(arr));
        System.out.println(quickSelect(arr, 0, 5, 1));
    }

}