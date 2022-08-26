1.

/*
Question: Pairs With Given Sum
1. You are given a number N and array
2. You are also given a number X.
3. You have to find all valid pairs from array whose sum is equal to X.

 Time Complexity: O(n^2)

 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class pairsWithGivenSumInTwoSortedMatrices {
    public static ArrayList<ArrayList<Integer>> solve(int[] arr, int k) {  // k-sum
        // write your code here
        HashMap<Integer, Integer> map = new HashMap();
        for(int i = 0; i < arr.length; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            int val = arr[i];
            int complementary = k - val;
            if(map.containsKey(complementary) && complementary > val){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(val);
                temp.add(complementary);
                result.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int input[] = new int[n];
        for(int i = 0; i < n; i++) {
            input[i] = s.nextInt();
        }
        int k = s.nextInt();
        System.out.println(solve(input, k));
    }
}

/*
Input:
5
5
1 2 3 4 5

Output:
[[1, 4], [2, 3]]

 */
2.
/*
Link: https://www.youtube.com/watch?v=LuonxZFwG90&list=PL-Jc9J83PIiEp9DKNiaQyjuDeg3XSoVMR&index=42
Question: Pairs With Given Sum In Two Sorted Matrices
1. You are given a number N and two sorted matrices(A and B) of N*N dimensions.
2. You are also given a number X.
3. You have to find the count of all valid pairs from matrices whose sum is equal to X.
4. A pair is called valid if one element of the pair is selected from A and the second element is selected from B.

 Time Complexity: O(n^2)

 */

import java.util.HashMap;
import java.util.Scanner;

public class pairsWithGivenSumInTwoSortedMatrices {
    public static int solve(int[][] num1, int[][] num2, int k) {  // k-sum
        // write your code here
        HashMap<Integer, Integer> map = new HashMap();
        for(int i = 0; i < num1.length; i++){
            for (int j = 0; j < num1[0].length; j++){
                map.put(num1[i][j], map.getOrDefault(num1[i][j], 0) + 1);
            }
        }
        int count = 0;
        for(int i = 0; i < num2.length; i++){
            for (int j = 0; j < num2[0].length; j++){
                int val = num2[i][j];
                int complementary = k - val;
                if(map.containsKey(complementary)){
                    count += map.get(complementary);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] mat1 = new int[N][N];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                mat1[i][j] = sc.nextInt();
            }
        }

        int[][] mat2 = new int[N][N];
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                mat2[i][j] = sc.nextInt();
            }
        }
        int K = sc.nextInt();
        System.out.println(solve(mat1, mat2, K));

    }
}

/*
Input:
3
1 5 6
8 10 11
15 16 18
2 4 7
9 10 12
13 16 20
21

Output:
4

 */


3.

/*
Link: https://www.youtube.com/watch?v=_yGf2rxwZlA&list=PL-Jc9J83PIiEp9DKNiaQyjuDeg3XSoVMR&index=5
Question: Largest Subarray With Zero Sum
1. You are given an array(arr) of integers.
2. You have to find the length of the largest subarray with sum 0.
 */

import java.util.HashMap;

public class _2_PairSum {

    public static int lengthOfLongestSubsetWithZeroSum(int arr[]){

        // HashMap contains sum and index of array
        HashMap<Integer, Integer> map = new HashMap<>();

        int maxLength = 0;
        int sum = 0;
        int i = -1;

        // Put zero-sum at -1 index i.e before arrayIndex
        map.put(sum, i);

        while (i < arr.length - 1){
            i++;
            sum += arr[i];

            // false means first time occurrence of sum at index
            if (map.containsKey(sum) == false){
                map.put(sum, i);
            }else{
                // If again same sum is found, we have to calculate length between them for knowing zero sum length
                // length = present index of sum - previous same index of sum
                int length = i - map.get(sum);

                if (length > maxLength){
                    maxLength = length;
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {2,8,-3,-5,2,-4,6,1,2,1,-3,4};
        System.out.println(lengthOfLongestSubsetWithZeroSum(arr));
    }
}

/*
output:
8
 */

4.
/*
Link:  https://www.youtube.com/watch?v=C9-n_H7dsvU&list=PL-Jc9J83PIiEp9DKNiaQyjuDeg3XSoVMR&index=6
Question: Count Of All Subarrays With Zero Sum
1. You are given an array(arr) of integers.
2. You have to find the count of all subarrays with sum 0.
 */

        import java.util.HashMap;

public class _6_CountOfZeroSumSubarray {

    public static int countOfZeroSumSubArray(int[] arr){
        int count = 0;

        // HashMap contains sum and frequency of that sum
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int i = -1;

        // Put frequency 1 at sum 0
        map.put(0, 1);

        while (i < arr.length - 1){
            i++;
            sum += arr[i];

            if (map.containsKey(sum)){
                count += map.get(sum);
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {2,8,-3,-5,2,-4,6,1,2,1,-3,4,-1,-3};
        System.out.println(countOfZeroSumSubArray(arr));
    }
}

/*
output:
11
 */

5.
        Q.  Pair Sum Binary Tree
        ----------------------------------------------
        Given a binary tree and an integer S, print all the pair of nodes whose sum equals S.
        ----------------------------------------------
        Note:
        1. Assume the given binary tree contains all unique elements.
        2. In a pair, print the smaller element first. Order of different pairs doesn't matter.
        ----------------------------------------------
        Create Project: BinaryTrees

        Create Package: default

Create class: BinaryTreeNode
        ----------------------------------------------
public class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public BinaryTreeNode(T data){
        this.data = data;
    }
}
----------------------------------------------
        Create class: Node
        --------------------
public class Node<T> {

    T data;
    Node<T> next;

    Node(T data){
        this.data = data;
        next = null;
    }
}
----------------------------------------------

        Create class: QueueUsingLL
        ----------------------------------------------
public class QueueUsingLL<T> {

    private Node<T> front;
    private Node<T> rear;
    int size;

    public QueueUsingLL(){   // Constructor
        front = null;
        rear = null;
        size = 0;
    }

    int size(){
        return size;
    }

    boolean isEmpty(){
        return size == 0;
    }

    T front() throws QueueEmptyException {
        if (size == 0){
            throw new QueueEmptyException();
        }
        return front.data;
    }

    void enqueue(T element){
        Node<T> newNode = new Node<>(element);
        if (front == null){
            front = newNode;
            rear = newNode;
        }else{
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    T dequeue() throws QueueEmptyException {
        if (size == 0){
            throw new QueueEmptyException();
        }

        T temp = front.data;
        front = front.next;

        if (size == 1){
            rear =null;
        }
        size--;

        return temp;
    }
}
-----------------------------------------------
        Create other class: QueueEmptyException
        ----------------------------------------------
public class QueueEmptyException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 7710094946748781333L;
}
----------------------------------------------
        Create class: BinaryTreeUse
        ----------------------------------------------
        import java.util.Scanner;
        import java.util.ArrayList;
        import java.util.Collections;

public class BinaryTreeUse {

    public static BinaryTreeNode<Integer> takeInputLevelWise(){
        Scanner sc = new Scanner(System.in);

        // Creating Queue in which we store pendingNodes whose children haven't asked yet.
        QueueUsingLL<BinaryTreeNode<Integer>> pendingNodes = new QueueUsingLL<>();

        //  Firstly taking rootData
        System.out.println("Enter root data");
        int rootData =  sc.nextInt();
        if (rootData == -1){   // Base Case
            return null;
        }

        // Creating Node of the BinaryTree as root using rootData
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);

        // Adding root into Queue as children of node hasn't asked yet
        pendingNodes.enqueue(root);

        // Work : As long as Queue is not empty
        while (!pendingNodes.isEmpty()){
            BinaryTreeNode<Integer> front;

            //  Handle the queueEmptyException of Queue by Try and Catch option
            try {
                // Work 1 : Removing frontNode from the Queue
                front = pendingNodes.dequeue();
            } catch (QueueEmptyException e) {
                return null;
            }

            // Work 2 : LeftChild

            // 2.1  Taking LeftChild
            System.out.println("Enter left child of " + front.data);
            int leftChild = sc.nextInt();

            if (leftChild != -1){
                // 2.2  Creating node for the LeftChild
                BinaryTreeNode<Integer> child = new BinaryTreeNode<>(leftChild);

                // 2.3  Linking leftChild with the front
                front.left = child;

                // 2.4  Adding leftChild also into Queue as children of leftChild hasn't asked yet
                pendingNodes.enqueue(child);

            }

            // Work 3 : RightChild

            // 3.1  Taking RightChild
            System.out.println("Enter right child of " + front.data);
            int rightChild = sc.nextInt();

            if (rightChild != -1){
                // 3.2  Creating node for the RightChild
                BinaryTreeNode<Integer> child = new BinaryTreeNode<>(rightChild);

                // 3.3  Linking RightChild with the front
                front.right = child;

                // 3.4  Adding RightChild also into Queue as children of RightChild hasn't asked yet
                pendingNodes.enqueue(child);

            }
        }
        return root;
    }

    public static void printLevelWise(BinaryTreeNode<Integer> root){
        QueueUsingLL<BinaryTreeNode<Integer>> mainQueue = new QueueUsingLL<>();
        mainQueue.enqueue(root);

        // Work : As long as Queue is not empty
        while (!mainQueue.isEmpty()){

            // Steps: 1. dequeue  2. print  3. enqueue
            try {
                BinaryTreeNode<Integer> front = mainQueue.dequeue();    // 1. dequeue
                System.out.print(front.data + ":");          // 2. print

                if (front.left != null){
                    mainQueue.enqueue(front.left);      // 3. enqueue
                    System.out.print("L:" + front.left.data);     // 4. print
                }else {
                    System.out.print("L:-1");
                }
                if (front.right != null){
                    mainQueue.enqueue(front.right);     // 3. enqueue
                    System.out.print(",R:" + front.right.data);    // 4. print
                }else {
                    System.out.print(",R:-1");
                }
                System.out.println();
            } catch (QueueEmptyException e) {
                e.printStackTrace();
            }
        }
    }

    public static void nodesSumToS(BinaryTreeNode<Integer> root, int sum) {
        if(root==null) {
            return ;
        }
        ArrayList<Integer> arr = convertTreeToArray(root);

        Collections.sort(arr);

        int i=0,j=arr.size()-1;
        while(i<j) {
            if(arr.get(i)+ arr.get(j)==sum) {
                System.out.println(arr.get(i) +" " + arr.get(j));
                i++;
                j--;
            }
            else if(arr.get(i) + arr.get(j) < sum ) {
                i++;
            }
            else {
                j--;
            }
        }
    }

    private static ArrayList<Integer> convertTreeToArray(BinaryTreeNode<Integer> root) {
        if(root==null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> leftArrayList = convertTreeToArray(root.left);
        ArrayList<Integer> rightArrayList = convertTreeToArray(root.right);
        ArrayList<Integer> ansArrayList = new ArrayList<>();
        for(int i : leftArrayList) {
            ansArrayList.add(i);
        }
        for(int i : rightArrayList) {
            ansArrayList.add(i);
        }
        ansArrayList.add(root.data);
        return ansArrayList;
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = takeInputLevelWise();
        printLevelWise(root);
        System.out.println();
        nodesSumToS(root, 15);
    }
}
---------------------------------------------
        Input:
        Enter root data
        5
        Enter left child of 5
        10
        Enter right child of 5
        6
        Enter left child of 10
        2
        Enter right child of 10
        3
        Enter left child of 6
        -1
        Enter right child of 6
        -1
        Enter left child of 2
        -1
        Enter right child of 2
        -1
        Enter left child of 3
        -1
        Enter right child of 3
        9
        Enter left child of 9
        -1
        Enter right child of 9
        -1
        ------------------------
        Output:
        5:L:10,R:6
        10:L:2,R:3
        6:L:-1,R:-1
        2:L:-1,R:-1
        3:L:-1,R:9
        9:L:-1,R:-1

        5 10
        6 9
        *********************************

6.
        Q. Pair sum in a BST
        ----------------------------------------------
        Given a binary search tree and an integer S, find pair of nodes in the BST which sum to S. You can use extra space of the order of O(log n).
        ----------------------------------------------
        Time Complexity: O(n)
        ---------------------------------------------
        Create Project: BinaryTrees

        Create Package: default

Create class: BinaryTreeNode
        ----------------------------------------------
public class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public BinaryTreeNode(T data){
        this.data = data;
    }
}
----------------------------------------------
        Create class: Node
        --------------------
public class Node<T> {

    T data;
    Node<T> next;

    Node(T data){
        this.data = data;
        next = null;
    }
}
----------------------------------------------

        Create class: QueueUsingLL
        ----------------------------------------------
public class QueueUsingLL<T> {

    private Node<T> front;
    private Node<T> rear;
    int size;

    public QueueUsingLL(){   // Constructor
        front = null;
        rear = null;
        size = 0;
    }

    int size(){
        return size;
    }

    boolean isEmpty(){
        return size == 0;
    }

    T front() throws QueueEmptyException {
        if (size == 0){
            throw new QueueEmptyException();
        }
        return front.data;
    }

    void enqueue(T element){
        Node<T> newNode = new Node<>(element);
        if (front == null){
            front = newNode;
            rear = newNode;
        }else{
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    T dequeue() throws QueueEmptyException {
        if (size == 0){
            throw new QueueEmptyException();
        }

        T temp = front.data;
        front = front.next;

        if (size == 1){
            rear =null;
        }
        size--;

        return temp;
    }
}
-----------------------------------------------
        Create other class: QueueEmptyException
        ----------------------------------------------
public class QueueEmptyException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 7710094946748781333L;
}
----------------------------------------------
        Create class: BinaryTreeUse
        ----------------------------------------------
        import java.util.Scanner;
        import java.util.Stack;

public class BinaryTreeUse {

    public static BinaryTreeNode<Integer> takeInputLevelWise(){
        Scanner sc = new Scanner(System.in);

        // Creating Queue in which we store pendingNodes whose children haven't asked yet.
        QueueUsingLL<BinaryTreeNode<Integer>> pendingNodes = new QueueUsingLL<>();

        //  Firstly taking rootData
        System.out.println("Enter root data");
        int rootData =  sc.nextInt();
        if (rootData == -1){   // Base Case
            return null;
        }

        // Creating Node of the BinaryTree as root using rootData
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);

        // Adding root into Queue as children of node hasn't asked yet
        pendingNodes.enqueue(root);

        // Work : As long as Queue is not empty
        while (!pendingNodes.isEmpty()){
            BinaryTreeNode<Integer> front;

            //  Handle the queueEmptyException of Queue by Try and Catch option
            try {
                // Work 1 : Removing frontNode from the Queue
                front = pendingNodes.dequeue();
            } catch (QueueEmptyException e) {
                return null;
            }

            // Work 2 : LeftChild

            // 2.1  Taking LeftChild
            System.out.println("Enter left child of " + front.data);
            int leftChild = sc.nextInt();

            if (leftChild != -1){
                // 2.2  Creating node for the LeftChild
                BinaryTreeNode<Integer> child = new BinaryTreeNode<>(leftChild);

                // 2.3  Linking leftChild with the front
                front.left = child;

                // 2.4  Adding leftChild also into Queue as children of leftChild hasn't asked yet
                pendingNodes.enqueue(child);

            }

            // Work 3 : RightChild

            // 3.1  Taking RightChild
            System.out.println("Enter right child of " + front.data);
            int rightChild = sc.nextInt();

            if (rightChild != -1){
                // 3.2  Creating node for the RightChild
                BinaryTreeNode<Integer> child = new BinaryTreeNode<>(rightChild);

                // 3.3  Linking RightChild with the front
                front.right = child;

                // 3.4  Adding RightChild also into Queue as children of RightChild hasn't asked yet
                pendingNodes.enqueue(child);

            }
        }
        return root;
    }

    public static void printLevelWise(BinaryTreeNode<Integer> root){
        QueueUsingLL<BinaryTreeNode<Integer>> mainQueue = new QueueUsingLL<>();
        mainQueue.enqueue(root);

        // Work : As long as Queue is not empty
        while (!mainQueue.isEmpty()){

            // Steps: 1. dequeue  2. print  3. enqueue
            try {
                BinaryTreeNode<Integer> front = mainQueue.dequeue();    // 1. dequeue
                System.out.print(front.data + ":");          // 2. print

                if (front.left != null){
                    mainQueue.enqueue(front.left);      // 3. enqueue
                    System.out.print("L:" + front.left.data);     // 4. print
                }else {
                    System.out.print("L:-1");
                }
                if (front.right != null){
                    mainQueue.enqueue(front.right);     // 3. enqueue
                    System.out.print(",R:" + front.right.data);    // 4. print
                }else {
                    System.out.print(",R:-1");
                }
                System.out.println();
            } catch (QueueEmptyException e) {
                e.printStackTrace();
            }
        }
    }

    public static void travelAndFill(BinaryTreeNode<Integer> root, ArrayList<Integer> arr){
        if (root == null){
            return;
        }
        // Inorder: For filling in increasing order
        travelAndFill(root.left, arr);
        arr.add(root.data);
        travelAndFill(root.right, arr);
    }

    public static void printNodesSumToS(BinaryTreeNode<Integer> root, int sum){
        ArrayList<Integer> arr = new ArrayList<>();
        travelAndFill(root, arr);

        int i = 0,j = arr.size() - 1;
        while(i < j) {
            if(arr.get(i) + arr.get(j) == sum) {
                System.out.println(arr.get(i) +" " + arr.get(j));
                i++;
                j--;
            }
            else if(arr.get(i) + arr.get(j) < sum ) {
                i++;
            }
            else {
                j--;
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = takeInputLevelWise();
        printLevelWise(root);
        System.out.println();
        printNodesSumToS(root, 12);
    }
}
---------------------------------------------
        Input:
        Enter root data
        8
        Enter left child of 8
        5
        Enter right child of 8
        10
        Enter left child of 5
        2
        Enter right child of 5
        6
        Enter left child of 10
        -1
        Enter right child of 10
        -1
        Enter left child of 2
        -1
        Enter right child of 2
        -1
        Enter left child of 6
        -1
        Enter right child of 6
        7
        Enter left child of 7
        -1
        Enter right child of 7
        -1
        ------------------------
        Output:
        8:L:5,R:10
        5:L:2,R:6
        10:L:-1,R:-1
        2:L:-1,R:-1
        6:L:-1,R:7
        7:L:-1,R:-1

        2 10
        5 7

        *********************************