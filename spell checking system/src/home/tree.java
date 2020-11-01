/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.util.Queue;
import java.util.LinkedList;

public class tree {

    public static BST insert(BST root, String data) {
        //if the root = null then we reached the leaf node so we will insert and stop
        if (root == null) {
            return new BST(data);
        }
        //if the data is smaller than the root data we will take the left subtree
        if (data.compareTo(root.data) < 0) {
            root.left = insert(root.left, data);
            //if the data is bigger than the root data we will take the right subtree
        } else if (data.compareTo(root.data) > 0) {
            root.right = insert(root.right, data);
        }
        //if the data was equal to the root(after or before recursion)
        //we won't insert it because it is an existing element and if the 
        //data wasn't existing we added it and will return
        return root;

    }

    public static void preOrder(BST root) {
        //we will print the root then go left or right and print them
        //and stop if the root is null
        //it's implementaions is simlilar to DFS 
        if (root == null) {
            return;
        }

        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);

    }

    public static void postOrder(BST root) {
        //we will go left or right then we will print
        if (root == null) {
            return;
        }

        preOrder(root.left);
        preOrder(root.right);
        System.out.println(root.data);

    }

    public static void inOrder(BST root) {
// we will go left then  print then go right
        if (root == null) {
            return;
        }

        preOrder(root.left);
        System.out.println(root.data);
        preOrder(root.right);

    }

    public static void BFS(BST root) {
        //we will print the nodes level by level
        Queue<BST> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BST visit = queue.poll();
            if (visit == null) {
                continue;
            }
            System.out.println(visit.data);
            queue.add(visit.left);
            queue.add(visit.right);
        }

    }

    private static int size = 0;

    public static int getSize(BST root) {
        if (root == null) {
            return size;//stop when we reach a leaf node
        }
        size++;//increasing the size by 1 and it was declared outside the method to save the increased size
        getSize(root.left);//go left until reaching the leaf node
        getSize(root.right);// go right until reaching th leaf node
        return size;
    }

    public static int getHeight(BST root) {
        if (root == null) {
            //stop if we reached the leaf node
            return 0;
        } else {
            //here we will save the height of the left subtree 
            int lHeight = getHeight(root.left);
            //here we will save the height of the right subtree
            int rHeight = getHeight(root.right);
            if (lHeight > rHeight) {
                //here we are counting the height of the left sub tree
                //and return the result if the height of the left subtree 
                //is bigger than the height of the right subtree
                return lHeight + 1;
            } else {
                //here we are counting the height of the right subtree 
                //and return the result if the height of the right subtree
                //is bigger than the height of the left subtree
                return rHeight + 1;
            }

        }

    }

    public static boolean search(BST root, String key) {
        boolean isExist = false;
        if (root == null) {
            //reaching the end of the tree mean that the key doesn't exist
            return false;
        }

        if (root.data.equals(key)) {
            //if the element is found return true
            return true;
        }

        if (key.compareTo(root.data) < 0) {
            //if the key is smaller than the data 
            //we will go to the left subtrre
            //and save the result
            isExist = search(root.left, key);
        } else if (key.compareTo(root.data) > 0) {
            //if the key is bigger than the data we will
            //go to the right subtree and save the result
            isExist = search(root.right, key);
        }

        return isExist;
    }

}
