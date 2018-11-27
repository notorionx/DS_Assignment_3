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

    public static int findMedian(int [] input) {

        // Problem 2
        return 0;
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
    }

}