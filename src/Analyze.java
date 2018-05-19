import java.util.Arrays;
import java.util.Random;

public class Analyze {
    
    static final int TIMES = 50;
    
    public static void main(String[] args) {
        String[] resultsArr = new String[TIMES];
        
        for (int i = 1; i <= TIMES; i++) {
            resultsArr[i - 1] = i + "," + test1(i * 10000);
            System.out.println(resultsArr[i - 1]);
            
        }
        
        for (String s : resultsArr) {
            System.out.println(s);
        }
    }
    
    private static int[] generateRandomArr(int i) {
        Random r = new Random();
        System.out.println("strart " + i);
        return r.ints(i).toArray();
    }
    
    public static String test1(int i) {
        double avgInsert = 0;
        int    maxInsert = 0;
        double avgDelete = 0;
        int    maxDelete = 0;
        
        
        WAVLTree kak         = new WAVLTree();
        int[]    genratedArr = generateRandomArr(i);
        int[]    sortedArr   = genratedArr.clone();
        Arrays.sort(sortedArr);
        
        int val;
        
        for (int j = 0; j < genratedArr.length; j++) {
            val = kak.insert(genratedArr[j], "");
            avgInsert += val;
            maxInsert = Math.max(maxInsert, val);
        }
        
        for (int j = 0; j < genratedArr.length; j++) {
            val = kak.delete(sortedArr[j]);
            
            avgDelete += val;
            maxDelete = Math.max(maxDelete, val);
        }
        
        return i + "," + (avgInsert / i) + "," + (maxInsert) + "," + (avgDelete / i) + "," + (maxDelete);
    }
}
