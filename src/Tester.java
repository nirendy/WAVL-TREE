import java.util.Arrays;

public class Tester {
    
    public static void main(String[] args) {
        test4();
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
        
        for (int i = 3; i >= 1; i--) {
            System.out.print("************\n");
            kak.insert(i, "_" + i);
            System.out.println(WAVLTreePrinter.toString(kak));
            
        }
        
        
    }
    
    public static void test4() {
        WAVLTree kak   = new WAVLTree();
        int[]    order = {1,1,4,53,12,5,35,12,53,12,24,462,62,1,3,1,13,6,2,7,2,32,3,23,73,2,2};
        for (int i : order) {
            System.out.print("************\n");
            System.out.println(kak.insert(i, "_" + i));
            System.out.println(WAVLTreePrinter.toString(kak));
        }
    }
    
    public static void test3() {
        WAVLTester.main(null);
        
    }
}
