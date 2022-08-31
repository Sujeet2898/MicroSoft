1.
/*  Link:   https://www.youtube.com/watch?v=VMtyGnNcdPw
Problem Statement: Maximum SubArray Sum
You are given an array (ARR) of length N, consisting of integers. You have to find the sum of the subarray (including empty subarray) having maximum sum among all subarrays.
A subarray is a contiguous segment of an array. In other words, a subarray can be formed by removing 0 or more integers from the beginning, and 0 or more integers from the end of an array.
Note :
The sum of an empty subarray is 0.
Kadane’s Algorithm:
The idea of Kadane’s algorithm is to maintain a maximum subarray sum ending at every index ‘i’ of the given array and update the maximum sum obtained by comparing it with the maximum sum of the subarray ending at every index ‘i’.
Time complexity: O(N), where N is the number of elements in the array, as we traverse the array once to get the maximum subarray sum.
Space complexity: O(1), as no extra space is required.
*/

        package Kadanes_Algorithm;

import java.util.Scanner;

public class MaximumSubArraySum_1 {

/*
    public static long maxSubarraySum(int[] arr, int n){
        int currentSum = arr[0];
        int overAllSum = arr[0];
        for (int i = 1; i < arr.length; i++){
            // Join the incoming train if it is +ve
            if (currentSum >= 0){
                currentSum += arr[i];
            }else{
                // Form a new train if the incoming train is -ve
                currentSum = arr[i];
            }
            // Updating the overall sum
            if (currentSum > overAllSum){
                overAllSum = currentSum;
            }
        }
        return overAllSum;
    }
    */
    //     OR

    public static long maxSubarraySum(int[] arr, int n){
        int currentSum = arr[0];
        int overAllSum = arr[0];

        for (int i = 1; i < arr.length; i++){

            // Join the incoming train if it is +ve
            // Form a new train if the incoming train is -ve
            currentSum = Math.max(arr[i], currentSum + arr[i]);

            // Updating the overall sum
            overAllSum = Math.max(overAllSum, currentSum);
        }
        return overAllSum;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(maxSubarraySum(arr,n));
    }
}

/*
Input:
18
4 3 -2 6 -14 7 -1 4 5 7 -10 2 9 -10 -5 -9 6 1
Output:
23
Input:
19
4 3 -2 6 7 -10 -10 4 5 9 -3 4 7 -28 2 9 3 2 11
Output:
27
 */

2.
/*
Problem Statement: Flip Bits
You are given an array of integers ARR[] of size N consisting of zeros and ones. You have to select a subset and flip bits of that subset. You have to return the count of maximum one’s that you can obtain by flipping chosen sub-array at most once.
A flip operation is one in which you turn 1 into 0 and 0 into 1.
 */

        package Kadanes_Algorithm;

        import java.util.Scanner;

public class FlipBits_2 {

    public static int flipBits(int[] arr, int n) {
        int totalOnes = 0;
        int currentSum = 0;
        int overAllSum = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 1) {
                totalOnes++;
            }

            // Converting into max SubArray Sum Problem and let us solve using Kadane’s Algorithm
            int val = arr[i] == 1 ? -1 : 1;

            // Join the incoming train if it is +ve
            if (currentSum >= 0){
                currentSum += val;

            }else{
                // Form a new train if the incoming train is -ve
                currentSum = val;
            }

            // Updating the overall sum
            if(currentSum > overAllSum){
                overAllSum = currentSum;
            }
        }
        return totalOnes + overAllSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(flipBits(arr,n));
    }
}

/*
Input:
5
1 0 0 1 0
Output:
4
Explanation: We can perform a flip operation in the range [1,2]. After the flip operation, the array is: 1 1 1 1 0 and so the answer will be 4
Input:
4
1 1 1 0
Output:
4
Explanation: We can perform a flip operation in the range [3,3]. After the flip operation, the array is: 1 1 1 1 and so the answer will be 4.
 */

3.
/*
Problem Statement: Maximum subarray sum after K concatenation
You have been given a vector/list 'ARR' consisting of ‘N’ integers. You are also given a positive integer ‘K’.
Let’s define a vector/list 'CONCAT' of size 'N * K' formed by concatenating 'ARR' ‘K’ times. For example, if 'ARR' = [0, -1, 2] and 'K' = 3, then 'CONCAT' is given by [0, -1, 2, 0, -1, 2, 0, -1, 2].
Your task is to find the maximum possible sum of any non-empty subarray (contagious) of 'CONCAT'.
 */

        package Kadanes_Algorithm;

        import java.util.Scanner;

public class MaximumSubarraySumAfterKConcatenation_3 {

    public static int kadanes(int[] arr) {
        int currentSum = arr[0];
        int overAllSum = arr[0];

        for (int i = 1; i < arr.length; i++) {

            // Join the incoming train if it is +ve
            if (currentSum >= 0) {
                currentSum += arr[i];
            } else {
                // Form a new train if the incoming train is -ve
                currentSum = arr[i];
            }

            // Updating the overall sum
            if (currentSum > overAllSum) {
                overAllSum = currentSum;
            }
        }
        return overAllSum;
    }

    public static int kadanesOfTwo(int[] arr) {
        int[] newArr = new int[arr.length * 2];
        // 1st Copy
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        // 2nd Copy
        for (int i = 0; i < arr.length; i++) {
            newArr[i + arr.length] = arr[i];
        }
        return kadanes(newArr);
    }

    public static long maxSubSumKConcat(int[] arr, int k, long sum) {
        if (k == 1) {
            return kadanes(arr);

        } else if (sum < 0) {
            return kadanesOfTwo(arr);

        } else {
            // sum >= 0
            return kadanesOfTwo(arr) + (k - 2) * sum;
        }
    }

    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int arr[] = new int[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
            sum += arr[i];
        }
        int k = scn.nextInt();
        System.out.println(maxSubSumKConcat(arr, k, sum));
    }
}

/*
Input:
// sum lie in combination of kadanes
5
3 -2 -3 -1 2
2
Output:
5
Input:
// sum lie in ist kadanes, but we take combination of kadanes
5
2 3 -2 -3 -1
2
Output:
5
Input:
3
1 -2 1
2
Output:
2
Input:
2
1 3
3
Output:
12
 */


/* *************************************************************** */

/*
Problem Statement: Maximum subarray sum after K concatenation
You have been given a vector/list 'ARR' consisting of ‘N’ integers. You are also given a positive integer ‘K’.
Let’s define a vector/list 'CONCAT' of size 'N * K' formed by concatenating 'ARR' ‘K’ times. For example, if 'ARR' = [0, -1, 2] and 'K' = 3, then 'CONCAT' is given by [0, -1, 2, 0, -1, 2, 0, -1, 2].
Your task is to find the maximum possible sum of any non-empty subarray (contagious) of 'CONCAT'.
 */
/*
    public static long maxSubSumKConcat(ArrayList<Integer> arr, int n, int k){
        long sum = 0;
        return kadanesKConcat(arr,k,sum);
    }
    public static int kadanes(ArrayList<Integer> arr){
        int currentSum = arr.get(0);
        int overAllSum = arr.get(0);
        for (int i = 1; i < arr.size(); i++){
            // Join the incoming train if it is +ve
            if (currentSum >= 0){
                currentSum += arr.get(i);
            }else{
                // Form a new train if the incoming train is -ve
                currentSum = arr.get(i);
            }
            // Updating the overall sum
            if (currentSum > overAllSum){
                overAllSum = currentSum;
            }
        }
        return overAllSum;
    }
    public static int kadanesOfTwo(ArrayList<Integer> arr){
        ArrayList<Integer> newArr = new ArrayList<>(arr.size() * 2);     //int[arr.length * 2];
        // 1st Copy
        for (int i = 0; i < arr.size(); i++){
            newArr.set(i, arr.get(i));
        }
        // 2nd Copy
        for (int i = 0; i < arr.size(); i++){
            newArr.set(i + arr.size(), arr.get(i));
        }
        return kadanes(newArr);
    }
    public static long kadanesKConcat(ArrayList<Integer> arr, int k, long sum){
        if (k == 1){
            return kadanes(arr);
        }else if (sum < 0){
            return kadanesOfTwo(arr);
        }else {
            // sum >= 0
            return kadanesOfTwo(arr) + (k - 2) * sum;
        }
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ArrayList<Integer> arr = new ArrayList<>(n);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            arr.add(i, scn.nextInt());
            sum += arr.get(i);
        }
        int k = scn.nextInt();
        System.out.println(maxSubSumKConcat(arr,n,k));
    }
 */
//}

/*
Input:
3
1 -2 1
2
Output:
2
Input:
2
1 3
3
Output:
12
 */

4.
/*
Link: 1. https://www.youtube.com/watch?v=yCQN096CwWM
      2. https://www.youtube.com/watch?v=pbajVSlZYDA
Problem Statement: Maximum Sum Rectangle
You are given a matrix ‘ARR’ with ‘N’ rows and ‘M’ columns. Your task is to find the maximum sum rectangle in the matrix.
Maximum sum rectangle is a rectangle with the maximum value for the sum of integers present within its boundary, considering all the rectangles that can be formed from the elements of that matrix.
 Time Complexity: O(cols*cols*row)
 Space Complexity: O(rows)
 */

        package Kadanes_Algorithm;

        import java.util.Scanner;

public class MaximumSumRectangle_4 {

    public static int maxSumRectangle(int[][] arr, int n, int m){
        return maxSumRec(arr);
    }

    public static int maxSumRec(int[][] arr){
        int rows = arr.length;
        int cols = arr[0].length;
        int temp[] = new int[rows];
        int ans = 0;
            
        // j and k tranverse through columns
        for (int left = 0; left < cols; left++){  // for(int j = 0; j < cols; j++){
            for (int i = 0; i < rows; i++){  // initially temp[] is empty
                temp[i] = 0;
            }
            for (int right = left; right < cols; right++){ // for(int k = j; k < cols; k++){
                // filling the row sum
                for (int i = 0; i < rows; i++){
                    temp[i] += arr[i][right];  // temp[i] += arr[i][k];
                }
                ans = Math.max(ans, maxSubarraySum(temp));
            }
        }
        return ans;
    }

    public static int maxSubarraySum(int[] arr){
        int currentSum = arr[0];
        int overAllSum = arr[0];

        for (int i = 1; i < arr.length; i++){

            // Join the incoming train if it is +ve
            if (currentSum >= 0){
                currentSum += arr[i];
            }else{
                // Form a new train if the incoming train is -ve
                currentSum = arr[i];
            }

            // Updating the overall sum
            if (currentSum > overAllSum){
                overAllSum = currentSum;
            }
        }
        return overAllSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] rectangle = new int[rows][cols];

        //input
        //rows
        for(int i=0; i<rows; i++) {
            //columns
            for(int j=0; j<cols; j++) {
                rectangle[i][j] = sc.nextInt();
            }
        }
        System.out.println(maxSumRectangle(rectangle, rows, cols));
    }
}

/*
Input:
4
5
1 2 -1 -4 -20
-8 -3  4 2 1
3  8 10 1 3
-4 -1 1 7 -6
Output:
29
Input:
2
2
-1 1
2 2
Output:
4
 */


