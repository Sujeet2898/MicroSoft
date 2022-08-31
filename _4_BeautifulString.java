1.
        Link: https://www.youtube.com/watch?v=F0E7k6X_kt8&t=55s
Question: Minimum Number of Flips to Make the Binary String Alternating
You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

public class Solution {
    public static int makeBeautiful(String str) {

        char[] ch = str.toCharArray();

        // take 2 count
        int c1 = 0;
        int c2 = 0;

        for(int i = 0; i < ch.length; i++){

            // Case1: even = 0 , odd = 1 eg 01010101
            if(i % 2 != 0 && ch[i] == '0'){
                c1++;
            }
            if(i % 2 == 0 && ch[i] == '1'){
                c1++;
            }

            // Case2: even = 1 , odd = 0 eg 10101010
            if(i % 2 != 0 && ch[i] == '1'){
                c2++;
            }
            if(i % 2 == 0 && ch[i] == '0'){
                c2++;
            }
        }
        return Math.min(c1, c2);
    }
}

2.
/*
Link: https://www.youtube.com/watch?v=nqrXHJWMeBc&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=18
Question: Binary Strings with no consecutive 0's
1. You are given a number n.
2. You are required to print the number of binary strings of length n with no consecutive 0's.
 */

        import java.util.Scanner;

public class _11_CountBinaryStrings {

    public static int countBinaryStrings(int n){
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];

        dp0[1] = 1;
        dp1[1] = 1;

        for (int i = 2; i <= n; i++){
            dp1[i] = dp1[i - 1] + dp0[i - 1];
            dp0[i] = dp1[i - 1];
        }
        return dp1[n] + dp0[n];
    }

    public static int countBinaryString(int n){
        int oldCountZeros = 1;
        int oldCountOnes = 1;

        for (int i = 2; i <= n; i++){
            int newCountZeros = oldCountOnes;
            int newCountOnes = oldCountZeros + oldCountOnes;

            oldCountZeros = newCountZeros;
            oldCountOnes = newCountOnes;
        }
        return oldCountZeros + oldCountOnes;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println(countBinaryStrings(n));
        System.out.println(countBinaryString(n));
    }
}

/*
Input:
6
Output:
21
21
 */

3.
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

