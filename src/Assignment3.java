import java.util.Arrays;

public class Assignment3 {

    /**
     * Prints node values at given depth, with root being at depth 0
     * @param root The root node
     * @param depth The level on which to print nodes
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
     * @throws IllegalArgumentException If user passes in an array of length 0
     */
    public static double findMedian(int[] input) throws IllegalArgumentException{
        if(input.length == 0) {
            //Alert user if array has length 0
            throw new IllegalArgumentException("Array cannot be empty");
        }

        //Sort the input array
        mergeSort(input);

        //Return the middle element if array has odd length and the average of middle 2 elements if array has even length
        int mid = input.length / 2;
        double result = (input.length % 2 == 1 ? input[mid] : (double) (input[mid] + input[mid - 1]) / 2);

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

        //For debugging purposes
        System.out.println("Input array: " + Arrays.toString(arr) + "\n");

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

        //For debugging purposes
        System.out.println("Splitting into subarrays: " + Arrays.toString(left) + " and " + Arrays.toString(right) + "\n");

        //Recursive calls to sort the left and right subarrays
        mergeSort(left);
        mergeSort(right);


        //For debugging purposes
        System.out.println("Merging subarrays: " + Arrays.toString(left) + " and " + Arrays.toString(right) + "\n");

        //Merge the two subarrays into the original array after they are sorted
        merge(arr, left, right, left.length, right.length);


        //For debugging purposes
        System.out.println("Sorted and merged subarrays: " + Arrays.toString(arr) + "\n");

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

    /**
     * Finds and returns median of the array that is passed in
     * @param input Array of length greater than 0
     * @return The median of the array
     * @throws IllegalArgumentException If user passes in an array of length 0
     */
    public static double quickFindMedian(int[] input) throws IllegalArgumentException{
        if(input.length == 0) {
            //Alert user if array has length 0
            throw new IllegalArgumentException("Array cannot be empty");
        }

        //Initialize variables for midpoint and answer
        double ans;
        int mid = input.length / 2;

        if(input.length % 2 == 1) {
            //Return middle element of sorted array if odd length
            ans = quickSelect(input, 0, input.length - 1, mid + 1);
        } else {
            //Return average of middle 2 elements of sorted array if even length
            ans = (quickSelect(input, 0, input.length - 1, mid) + quickSelect(input, 0, input.length - 1, mid + 1)) / 2;
        }
        return ans;
    }

    /**
     * Finds and returns kth smallest element in the array from index l to r
     * @param arr The input array
     * @param l The left bound of the search
     * @param r The right bound of the search
     * @param k The position of the desired element in the sorted list, corresponds to index of k - 1
     * @return The kth smallest element in the array from index l to r
     */
    public static double quickSelect(int[] arr, int l, int r, int k) {
        if(l < r) {
            //If search range is valid, partition the search range of the array around a random pivot and return the index of our pivot value
            int pivot = partition(arr, l, r);
            if(pivot == k - 1) {
                //If the pivot index equals the position of the element we are looking for, we can return it because we know it is in the right place
                return arr[pivot];
            } else if(pivot > k - 1) {
                //If the pivot comes after the element we are looking for, make a recursive call to perform a pivot on the elements from left bound to pivot and check again
                return quickSelect(arr, l, pivot, k);
            } else {
                //If the pivot comes before the element we are looking for, make a recursive call to perform a pivot on the elements from pivot + 1 to right bound and check again
                return quickSelect(arr, pivot + 1, r, k);
            }
        }

        return -99999999;
    }

    /**
     * Arranges elements within the specified range around a random pivot value such that all elements left of the pivot
     * value are less than the pivot value and all elements right of the pivot value are greater than the pivot value,
     * ensuring that if the array were to be completely sorted in ascending order, the pivot value would already be
     * in its correct place
     * @param arr The input array
     * @param l The left bound of the partition range
     * @param r The right bound of the partition range
     * @return The index of the pivot value
     */
    public static int partition(int[] arr, int l, int r) {

        //For debugging purposes
        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("Start index: " + l + " End index: " + r + " Pivot value: " + arr[l]);

        //Pick leftmost element in search range as our random pivot value and use store to track the position of the leftmost element greater than the pivot value
        int pivot = l, store = l + 1;

        for(int i = l + 1; i < r + 1; i++) {
            //Iterate through the search range from the position right of the pivot value
            if(arr[i] < arr[pivot]) {
                //If current element is less than the pivot value, swap it with the leftmost element greater than the pivot value and increment store
                swap(arr, i, store);
                store++;
            }
        }

        //Once we finish iterating through the search range, swap the pivot value into its correct place and return its index
        swap(arr, pivot, store - 1);

        //For debugging purposes
        System.out.println("Output: " + Arrays.toString(arr) + "\n");

        return store - 1;
    }

    /**
     * Helper function to swap two elements in an array
     * @param arr The input array
     * @param i Index of first element to be swapped
     * @param j Index of second element to be swapped
     */
    public static void swap(int[] arr, int i, int j) {
        //Do nothing if trying to swap an element with itself
        if(i == j) return;

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        TreeNode
                //Level 0
                root = new TreeNode(2),
                //Level 1
                n_L = new TreeNode(7),
                n_R = new TreeNode(5),
                //Level 2
                n_LL = new TreeNode(2),
                n_LR = new TreeNode(6),
                n_RR = new TreeNode(9),
                //Level 3
                n_LRL = new TreeNode(5),
                n_LRR = new TreeNode(11),
                n_RRL = new TreeNode(4);

        n_LR.left = n_LRL;
        n_LR.right = n_LRR;
        n_RR.left = n_RRL;
        n_L.left = n_LL;
        n_L.right = n_LR;
        n_R.right = n_RR;
        root.left = n_L;
        root.right = n_R;

        System.out.println("\n------------------------------PRINTLEVEL-------------------------------\n");
        printLevel(root, 3);

        int[] arr = {22, 17, 5, 40, 3, 20, 4};
        System.out.println("\n------------------------------FINDMEDIAN-------------------------------\n");
        System.out.println("Median is: " + findMedian(arr));
        System.out.println("\n----------------------------QUICKFINDMEDIAN-----------------------------\n");
        System.out.println("Median is: " + quickFindMedian(arr));
    }

}