import java.util.ArrayList;
import java.util.Scanner;
public class BinarySearchTree {
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }
    Node root;
    BinarySearchTree(){
        root = null;
    }
    BinarySearchTree(int value){
        root = new Node(value);
    }



    void insert(int key){
        root = insertRec(root,key);
    }



    Node insertRec(Node root,int key){
        if(root == null){
            root = new Node(key);
            return root;
        } else if(key < root.key) {
            root.left = insertRec(root.left,key);
        } else if(key > root.key){
            root.right = insertRec(root.right,key);
        }
        return root;
    }



    public void remove(int key){
        removeHelper(root,key);
    }


     Node removeHelper(Node root, int key) {
        if(root == null) {
            return root;
        }
        if(root.key > key){
            root.left = removeHelper(root.left,key);
        } else if(root.key < key){
            root.right = removeHelper(root.right,key);
        } else {
            if(root.left == null && root.right == null) {
                root = null;

            } else  if(root.right != null){
                root.key = succesor(root);
                root.right = removeHelper(root.right,root.key);
            }
            else {
                root.key = predecessor(root);
                root.left = removeHelper(root.left,root.key);
            }

        }
        return root;
    }



    int succesor(Node root){
        root = root.right;
        while(root.left != null){
            root = root.left;
        }
        return root.key;
    }



    int predecessor(Node root){
        root = root.left;
        while(root.right != null){
            root = root.right;
        }
        return root.key;
    }


    boolean isBalanced(){
        return isBalancedHelper(root);
    }




    int height(Node root){
        if(root == null){
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(lh , rh) * 1;
    }




    boolean isBalancedHelper(Node root){
        if(root == null){
            return true;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        if(Math.abs(lh-rh) > 1) {
            return false;
        }
        return isBalancedHelper(root.left) && isBalancedHelper(root.right);
    }



    int GCD(int n, int m){
        if(m == 0){
            return n;
        }
        return GCD(m, n % m);
    }




    int lSubTree(){
        ArrayList<Integer> leafNodes = new ArrayList<>();
        findNodes(root.left,leafNodes);
        if(leafNodes.isEmpty()) {
            return 0;
        }
        int res = leafNodes.get(0);
        for(int i = 0; i < leafNodes.size();i++){
            res = GCD(res,leafNodes.get(i));
        }
        return res;
    }
    void findNodes(Node root, ArrayList<Integer> leafNodes) {
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            leafNodes.add(root.key);
        }
        findNodes(root.left,leafNodes);
        findNodes(root.right,leafNodes);
    }

    void inorder(){inorderR(root);}
    void inorderR(Node root){
        if(root != null){
            inorderR(root.left);
            System.out.print(root.key + " ");
            inorderR(root.right);
        }

    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        System.out.println("how many nodes in tree");
        Scanner scnr = new Scanner(System.in);
        int nodes = scnr.nextInt();
        System.out.println("enter the numbers for the nodes");
        for (int i = 0; i < nodes; i++) {
            int key = scnr.nextInt();
            tree.insert(key);
        }
        tree.inorder();
        tree.remove(20);
        System.out.println(" ");
        tree.inorder();
        tree.insert(20);
        tree.remove(70);
        System.out.println(" ");
        tree.inorder();
        tree.insert(70);
        tree.remove(50);
        System.out.println(" ");
        tree.inorder();
        tree.insert(50);
        System.out.println(" ");
        if(tree.isBalanced()) {
            System.out.println("binary tree is balanced");
        } else {
            System.out.println("binary tree is not balanced");
        }
        int GCDlSubTree = tree.lSubTree();
        if(GCDlSubTree != 0){
            System.out.println("Left tree leaf nodes GCD " + GCDlSubTree);
        } else {
            System.out.println("No left subtree nodes");
        }
    }
}