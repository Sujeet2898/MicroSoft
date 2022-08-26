1. LCA of three Nodes
/**********************************************************
 Following is the Binary Tree Node class.

 class BinaryTreeNode<T> {
 T data;
 BinaryTreeNode<T> left;
 BinaryTreeNode<T> right;

 public BinaryTreeNode(T data) {
 this.data = data;
 }
 }

 ***********************************************************/

public class Solution {
    public static BinaryTreeNode<Integer> lcaOfThreeNodes(BinaryTreeNode<Integer> root, int node1, int node2,
                                                          int node3) {
        // Write your code here.
        // Case 1: Root is null
        if(root==null) {
            return null;
        }

        // Case 2: Either nodes a or b is equal to Root
        if(root.data==node1 || root.data==node2 || root.data==node3) {
            return root;
        }

        // Call left & right subtree
        BinaryTreeNode<Integer> leftLca = lcaOfThreeNodes(root.left, node1, node2, node3);
        BinaryTreeNode<Integer> rightLca = lcaOfThreeNodes(root.right, node1, node2, node3);

        // Case 3: Both nodes a & b is null
        if(leftLca==null && rightLca ==null) {
            return null;
        }

        // Case 4: Node a is null & b is not null
        else if(leftLca==null) {
            return rightLca;
        }

        // Case 5: Node a is not null & b is null
        else if(rightLca==null) {
            return leftLca;
        }

        // Case 6: Both nodes a & b is not null
        else {
            return root;
        }
    }
}

2. LCA of Binary Tree
        ----------------------------------------------
        Given a binary tree and data of two nodes, find 'LCA' (Lowest Common Ancestor) of the given two nodes in the binary tree.
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

    public static int lcaBinaryTree(BinaryTreeNode <Integer> root , int a, int b){

        // Case 1: Root is null
        if(root==null) {
            return -1;
        }

        // Case 2: Either nodes a or b is equal to Root
        if(root.data==a || root.data==b) {
            return root.data;
        }

        // Call left & right subtree
        int leftLca = lcaBinaryTree(root.left, a, b);
        int rightLca = lcaBinaryTree(root.right, a, b);

        // Case 3: Both nodes a & b is null
        if(leftLca==-1 && rightLca ==-1) {
            return -1;
        }

        // Case 4: Node a is null & b is not null
        else if(leftLca==-1) {
            return rightLca;
        }

        // Case 5: Node a is not null & b is null
        else if(rightLca==-1) {
            return leftLca;
        }

        // Case 6: Both nodes a & b is not null
        else {
            return root.data;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = takeInputLevelWise();
        printLevelWise(root);
        System.out.println();
        System.out.println(lcaBinaryTree(root, 2, 6));
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

        5
        *********************************

3.  LCA of BST
        ----------------------------------------------
        Given a binary search tree and data of two nodes, find 'LCA' (Lowest Common Ancestor) of the given two nodes in the BST.
        ----------------------------------------------
        LCA: LCA of two nodes A and B is the lowest or deepest node which has both A and B as its descendants.
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

    public static int lcaInBST(BinaryTreeNode <Integer> root , int a, int b){

        // Case 1: Root is null
        if(root==null) {
            return -1;
        }

        // Additional case 1: Both nodes a & b is less than Root
        if(a < root.data && b < root.data ) {
            return lcaInBST(root.left, a, b);
        }

        // Additional case 2: Both nodes a & b is greater than Root
        if(a > root.data && b > root.data) {
            return lcaInBST(root.right, a, b);
        }

        // Case 2: Either nodes a or b is equal to Root
        if(root.data==a || root.data==b) {
            return root.data;
        }

        // Call left & right subtree
        int leftLca = lcaInBST(root.left, a, b);
        int rightLca = lcaInBST(root.right, a, b);

        // Case 3: Both nodes a & b is null
        if(leftLca==-1 && rightLca ==-1) {
            return -1;
        }

        // Case 4: Node a is null & b is not null
        else if(leftLca==-1) {
            return rightLca;
        }

        // Case 5: Node a is not null & b is null
        else if(rightLca==-1) {
            return leftLca;
        }

        // Case 6: Both nodes a & b is not null
        else {
            return root.data;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = takeInputLevelWise();
        printLevelWise(root);
        System.out.println();
        System.out.println(lcaInBST(root, 2, 6));
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

        5
        *********************************