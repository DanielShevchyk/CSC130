//Daniel Shevchyk
//CSC130 M-W
//Project 2 Tree Tester
//Sources (Lecture/Book Aswell):
//Fellow Friends and Collegues
//https://www.youtube.com/watch?v=M6lYob8STMI
//https://stackoverflow.com/questions/35560798/binary-tree-testing
//https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/trees.html
import java.util.*;
public class TreeTester
{
    public static void main(String[] args) throws Exception
    {
        if(args.length == 0)//input ""
        {
            //Bracketed command is only necessary if running from command line. Should run fine from source in eclipse or jgrasp.
            System.out.println("Command_1: [java TreeTester] part1 bst/avl");
            System.out.println("Command_2: [java TreeTester] part2 n k");
            System.out.println("n :: How many random numbers to generate for insertion");
            System.out.println("k :: How many random numbers to generate for searching");
            return;
        }

        switch(args[0])
        {
            case "part1": // if part1 input
                switch(args[1])//bst or avl input
                {
                    case "bst":
                        testBST();
                        break;
                    case "avl":
                        testAVL();
                        break;
                }
                break;
            case "part2":
                timeTrees(Integer.parseInt(args[1]),
                          Integer.parseInt(args[2]));
                break;
        }
    }

    private static void testBST()
    {
        Random randomNum = new Random();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int min = 10;
        int max = 100;

        while(bst.height() < 5)
        {
            bst.insert(randomNum.nextInt(max - min) + min);
            new TreePrinter(bst).print("BST");
        }

        while(!bst.isEmpty())
        {
            bst.remove(bst.getRoot().getData());
            new TreePrinter(bst).print("Removing Root BST");
        }
    }

    private static void testAVL() throws Exception
    {
        Random randomNum = new Random();
        AVLTree<Integer> avl = new AVLTree<>(true);

        int min = 10;
        int max = 100;

        for(int i = 0; i < 35; i++)
        {
            avl.insert(randomNum.nextInt(max - min) + min);
            if(!avl.isBalanced())
            {
                throw new Exception("Not Balanced");
            }
            new TreePrinter(avl).print("AVL Tree");
        }

        while(!avl.isEmpty())
        {
            avl.remove(avl.getRoot().getData());
            if(!avl.isBalanced())
            {
                throw new Exception("Not Balanced");
            }
            new TreePrinter(avl).print("Removing Root");
        }
    }

    private static void timeTrees(int n, int k)//Sourced from collegues
    {
        Random randomNum = new Random();
        int min = 0, max = 1000;

        int[] randomInts = new int[n];
        int[] randomIntsToSearch = new int[k];

        for(int i = 0; i < n; i++) 
        {
            randomInts[i] = randomNum.nextInt(max - min) + min;
            if(i < k) {
                randomIntsToSearch[i] = randomNum.nextInt(max - min) + min;
            }
        }

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        AVLTree<Integer> avl = new AVLTree<>();

        double result = timeRunnable(() -> {
            for(int val : randomInts) {
                bst.insert(val);
            }
        });

        System.out.println("BST Insertion Time(m/s): " + result);

        result = timeRunnable(() -> {
            for(int val : randomInts) {
                avl.insert(val);
            }
        });

        System.out.println("AVL Insertion Time(m/s): " + result);

        result = timeRunnable(() -> {
            for(int val : randomIntsToSearch) {
                bst.contains(val);
            }
        });

        System.out.println("BST Search Time(m/s): " + result);

        result = timeRunnable(() -> 
        {
            for(int val : randomIntsToSearch) {
                avl.contains(val);
            }
        });

        System.out.println("AVL Search Time(m/s): " + result);
    }

    private static double timeRunnable(Runnable runnable) 
    {
        long startTime = System.nanoTime();
        runnable.run();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000.0;
    }
}