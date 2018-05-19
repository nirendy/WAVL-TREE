import java.util.Arrays;
import java.util.Scanner;

public class Tester {
    
    public static void main(String[] args) {
//        test3();
//         test6();
        test7();
    }
    
    public static void test1() {
        WAVLTree kak = new WAVLTree();
        
        System.out.println("max: " + kak.max());
        System.out.println("min44: " + kak.min());
        System.out.print("************\n");
        
        kak.insert(3, "_3");
        kak.insert(3, "_3");
        kak.insert(3, "_3");
        kak.insert(3, "_3");
        kak.insert(6, "_6");
        kak.insert(2, "_2");
        kak.insert(4, "_4");
        kak.insert(1, "_1");
        kak.insert(5, "_5");
        kak.insert(7, "_7");
        System.out.println(WAVLTreePrinter.toString(kak));
        
        System.out.print("************\n");
        System.out.println(Arrays.toString(kak.keysToArray()));
        
        System.out.print("************\n");
        System.out.println(Arrays.toString(kak.infoToArray()));
        
        System.out.print("************\n");
        System.out.println("min: " + kak.min());
        
        System.out.print("************\n");
        System.out.println("max: " + kak.max());
        
        System.out.print("************\n");
        System.out.println("size: " + kak.size());
        
        System.out.print("************\n");
        System.out.println("search 6: " + kak.search(6));
        
        System.out.print("************\n");
        System.out.println("search 1000: " + kak.search(1000));
        
        System.out.print("************\n");
        System.out.println("select 6: " + kak.select(6));
        
        System.out.print("************\n");
        System.out.println("select 1000: " + kak.select(1000));
        
        
        kak.delete(3);
        kak.delete(6);
        System.out.println(WAVLTreePrinter.toString(kak));
    }
    
    public static void test2() {
        WAVLTree kak = new WAVLTree();
        
        for (int i = 1; i <= 10; i++) {
            System.out.print("************\n");
            kak.insert(i, "_" + i);
            System.out.println(WAVLTreePrinter.toString(kak));
            
        }
        
        
        for (int i = 1; i <= 10; i++) {
            System.out.print("************\nDelete:" + i + "\n");
            kak.delete(i);
            System.out.println(WAVLTreePrinter.toString(kak));
            
        }
    }
    
    public static void test5() {
        WAVLTree kak = new WAVLTree();
        
        for (int i = 9; i >= 1; i--) {
            System.out.print("************\n");
            kak.insert(i, "_" + i);
            System.out.println(WAVLTreePrinter.toString(kak));
            
        }
        
        for (int i = 9; i >= 1; i--) {
            System.out.println("************\n delete: " + i);
            kak.delete(i);
            System.out.println(WAVLTreePrinter.toString(kak));
            
        }
        
        
    }
    
    public static void test4() {
        WAVLTree kak   = new WAVLTree();
        int[]    order = {1, 4, 7, 8, 1, 8, 4, 543, 63, 2, 3, 5, 6, 25, 131, 233, 32, 3425, 3, 32, 22, 3, 567, 2};
        for (int i : order) {
            System.out.print("************\n");
            System.out.println(kak.insert(i, "_" + i));
            System.out.println(WAVLTreePrinter.toString(kak));
        }
        
        int[] delorder = {7, 1, 5, 7, 3, 3, 43, 543, 6, 4, 5, 6, 7, 25, 131, 233, 3425};
        
        for (int i : delorder) {
            System.out.print("************ delete : " + i + "\n");
            System.out.println(kak.delete(i));
            System.out.println(WAVLTreePrinter.toString(kak));
        }
    }
    
    
    public static void test6() {
        WAVLTree kak = new WAVLTree();
        
        kak.insert(4293, "_4293");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.insert(4293, "_4293");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.insert(1525, "_1525");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.insert(1525, "_1525");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.insert(969, "_969");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.delete(4332);
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.insert(1417, "_1417");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.insert(2106, "_2106");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.insert(1592, "_1592");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.insert(4704, "_4704");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.delete(1417);
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.insert(1858, "_1858");
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.delete(969);
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.delete(4293);
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.delete(1592);
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        kak.delete(1525);
        System.out.print("************\n");
        System.out.println(WAVLTreePrinter.toString(kak));
        
        
    }

    public static void test3() {
        WAVLTester.main(null);
    }


    public static void addRemove(WAVLTree kak, int k, int j) { // print tree
        System.out.println("****************");
        int balanceOpeartions;
        if (j == 1) {
            System.out.println("inserted:" + k + "\n");
            balanceOpeartions = kak.insert(k, "_" + k + "_");
        } else {
            System.out.println("deleted:" + k + "\n");
            balanceOpeartions = kak.delete(k);
        }
        System.out.println("tree: ");
        System.out.println(WAVLTreePrinter.toString(kak)+"\n");

        System.out.println("# of balance operations: " + balanceOpeartions);
        System.out.println("min: " + kak.min());
        System.out.println("max: " + kak.max());
        System.out.println("size: " + kak.size());
        System.out.println("select 1: " + kak.select(1));
        System.out.println("select 2: " + kak.select(3));
        System.out.println("select 5: " + kak.select(5));
        System.out.println("keys inorder scan : " + Arrays.toString(kak.keysToArray()));
        System.out.println("values inorder scan : " + Arrays.toString(kak.infoToArray()));
        System.out.println("root: " + kak.getRoot().getValue());
        System.out.println("search 5: " + kak.search(5));
        System.out.println("root is empty: " + kak.empty());



        System.out.println("****************\n");
    }

    public static void add(WAVLTree kak, int k){ // print tree
        addRemove(kak, k, 1);
    }

    public static void remove(WAVLTree kak, int k) { // print tree
        addRemove(kak, k, 2);
    }

    public static void test7() {
        WAVLTree kak = new WAVLTree();
//        int[] order = {1,2,3,4,5,6,7,8,9};
//        for (int i : order)
//        {
//            add(kak,i);
//        }
//        for (int i : order)
//        {
//            remove(kak,i);
//        }
        Scanner y = new Scanner(System.in);
        int i;
        String op = " ";
        while (op.compareTo("stop") != 0) {
            i = y.nextInt();
            op = y.next();
            if (op.compareTo("add") == 0 || op.compareTo("a") == 0) {
                add(kak, i);
                continue;
            }
            if (op.compareTo("remove") == 0 || op.compareTo("r") == 0) {
                remove(kak, i);
                continue;
            }
            System.out.println("wrong input");
        }

        y.close();
    }
}
