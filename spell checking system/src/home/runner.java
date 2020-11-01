/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author abdelrhman shoukry
 */
public class runner {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        Scanner in = new Scanner(System.in);//to read an input from the user
        
        File file = new File("dictionary.txt");

        Scanner s = new Scanner(file);//to read the file

        BST bst = new BST(s.nextLine());

        tree tree = new tree();
        while (s.hasNextLine()) {
            tree.insert(bst, s.nextLine());//inserting the file words to the tree
        }
        System.out.println("select an option :");
        System.out.println("1. Print tree using in order traversal");
        System.out.println("2. Print tree using post order traversal");
        System.out.println("3. Print tree using pre order traversal");
        System.out.println("4. Print tree size");
        System.out.println("5. Print tree using BFS Traversal");
        System.out.println("6. Print tree height");
        System.out.println("7. Search for a word");
        System.out.println("8. Insert new word in the tree");
        System.out.println("0. exit the program");
        s.close();//closing the file reader
        
        int choice;
        
        try {
            do {
                
                choice = in.nextInt();
                
                switch (choice) {
                    case 1:
                        tree.inOrder(bst);
                        break;
                    case 2:
                        tree.postOrder(bst);
                        break;
                    case 3:
                        tree.preOrder(bst);
                        break;
                    case 4:
                        System.out.println("the size of the tree = " + tree.getSize(bst));
                        break;
                    case 5:
                        tree.BFS(bst);
                        break;
                    case 6:
                        System.out.println("the height of the tree = " + tree.getHeight(bst));
                        break;
                    case 7:
                        System.out.println("enter a word");
                        in.nextLine();//without it the program won't read an input from the user
                        String key = in.nextLine();
                        search(bst, key);
                        System.out.println("search finished");
                        break;
                    case 8:
                        System.out.println("enter a word");
                        in.nextLine();
                        String str = in.nextLine();
                        tree.insert(bst, str);
                        BufferedWriter out = new BufferedWriter(new FileWriter(file, true));//to write to the file
                        out.newLine();//to enter a new line in the file
                        out.write(str);//to write the string in the file
                        out.close();//closing the reader
                        System.out.println("word inserted");
                        break;
                    case 0:
                        System.out.println("exiting the program");
                        break;
                    default:
                        System.out.println("bad entry try again");
                }
            } while (choice != 0);
        } catch (InputMismatchException e) {
            System.out.println("choose a number from the menu don\'t enter a string");

        }

    }

    static void search(BST bst, String key) {
        if (tree.search(bst, key)) {
            System.out.println("the word is correct");
        } else {
            System.out.println("1. The word in the leaf node you reached before declaring that the word does not exist.");
            System.out.println("2. The word in the inorder predecessor of that leaf node.");
            System.out.println("3. The word in the inorder successor of that leaf node");
        }
    }

}
