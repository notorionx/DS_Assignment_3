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

    public static double findMedian(int [] input) {
//        Arrays.sort(input);
        mergeSort(input);
        System.out.println(Arrays.toString(input));
        int mid = input.length / 2;
        double result = (input.length % 2 == 1 ? (double) input[mid] : (double) (input[mid] + input[mid - 1]) / 2);
        return result;
    }

    public static void mergeSort(int[] arr) {


        if(arr.length < 2){
            return;
        }

        System.out.println("Input array: " + Arrays.toString(arr));

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

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right, left.length, right.length);

    }

    public static void merge(int[] arr, int[] left, int[] right, int leftLength, int rightLength) {
        System.out.println("Merging subarrays: " + Arrays.toString(left) + " and " + Arrays.toString(right));

        int i = 0, j = 0;

        while(i < leftLength && j < rightLength) {
            if(left[i] < right[j]) {
                arr[i + j] = left[i++];
            } else {
                arr[i + j] = right[j++];
            }
        }

        while(i < leftLength) {
            arr[i + j] = left[i++];
        }

        while(j < rightLength) {
            arr[i + j] = right[j++];
        }
    }

    public static int quickFindMedian(int [] input) {

        // Problem 3
        return 0;
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

        int[] arr = {1,5,3,20,4,7};
        System.out.println(findMedian(arr));
    }

}