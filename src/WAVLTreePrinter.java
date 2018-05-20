import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Credit to Amitai Cohen, the creator of the original code in python.
 * Ported by Yoav Sternberg
 */
class WAVLTreePrinter {
    private WAVLTreePrinter() {
    }
    
    public static String toString(WAVLTree tree) {
        return toString(tree, true);
    }
    
    public static String toString(WAVLTree tree, boolean byKey) {
        return String.join(System.lineSeparator(), repr(tree.getRoot(), byKey));
    }
    
    @SuppressWarnings("ConstantConditions")
    private static List<String> repr(WAVLTree.WAVLNode node, boolean byKey) {
        if (!node.isInnerNode()) {
            return Collections.singletonList("#");
        }
        
        String thisString = byKey ? String.valueOf(node.getKey()) : node.getValue();
        
         // thisString = thisString + "(" + node.getSubtreeSize() + ")";
       thisString = thisString + "(" + node.getRank() + "-[" + node.getRankDiff(WAVLTree.NodeDirection.Left) + "," + node.getRankDiff(WAVLTree.NodeDirection.Right) + "])";
        
        return concatenation(repr(node.getLeft(), byKey), thisString, repr(node.getRight(), byKey));
    }
    
    private static List<String> concatenation(List<String> left, String root, List<String> right) {
        int lwid    = left.get(left.size() - 1).length();
        int rwid    = right.get(right.size() - 1).length();
        int rootwid = root.length();
        
        ArrayList<String> result = new ArrayList<>();
        result.add(repeat(lwid + 1, " ") + root + repeat(rwid + 1, " "));
        
        int ls = leftSpace(left.get(0));
        int rs = rightSpace(right.get(0));
        result.add(repeat(ls, " ") + repeat(lwid - ls, "_") + "/" + repeat(rootwid, " ") + "\\" + repeat(rs, "_") +
                   repeat(rwid - rs, " "));
        
        for (int i = 0; i < Math.max(left.size(), right.size()); i++) {
            String row = "";
            if (i < left.size()) {
                row += left.get(i);
            } else {
                row += repeat(lwid, " ");
            }
            row += repeat(rootwid + 2, " ");
            if (i < right.size()) {
                row += right.get(i);
            } else {
                row += repeat(rwid, " ");
            }
            result.add(row);
        }
        return result;
    }
    
    private static String repeat(int n, String s) {
        if (n <= 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(s.length() * n);
        for (int i = 0; i < n; i++) {
            builder.append(s);
        }
        return builder.toString();
    }
    
    private static int leftSpace(String row) {
        int i = row.length() - 1;
        while (row.charAt(i) == ' ') {
            i -= 1;
        }
        return i + 1;
    }
    
    private static int rightSpace(String row) {
        int i = 0;
        while (row.charAt(i) == ' ') {
            i += 1;
        }
        return i;
    }
}