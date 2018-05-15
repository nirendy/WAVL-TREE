import java.util.Arrays;

public class Tester {
    
    public static void main(String[] args) {
        test2();
    }
    
    public static void test1() {
        WAVLTree kak = new WAVLTree();
        
        System.out.println("min55: " + kak.min());
        System.out.println("max: " + kak.max());
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
    
        System.out.println("min: " + kak.min());
        System.out.println("max: " + kak.max());
        System.out.print("************\n");
    
        kak.insert(1, "_1");
        kak.insert(3, "_3");
        kak.insert(2, "_2");
        kak.insert(3, "_3");
        kak.insert(6, "_6");
        kak.insert(4, "_4");
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
    
    public static void test3() {
        WAVLTester.main(null);
        
    }
}
