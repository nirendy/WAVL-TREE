import com.sun.org.apache.regexp.internal.REUtil;

/**
 * WAVLTree
 * <p>
 * An implementation of a WAVL Tree. (Haupler, Sen & Tarajan ‘15)
 */

public class WAVLTree {
    private       WAVLNode root;
    private final WAVLNode externalLeaf;
    
    public WAVLTree() {
        externalLeaf = new WAVLNode(null, null);
        root = externalLeaf;
    }
    
    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() {
        return root == externalLeaf;
    }
    
    /**
     * public String search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree otherwise,
     * returns null
     */
    public String search(int k) {
        return this.root.searchNode(k).getValue();
    }
    
    /**
     * public int insert(int k, String i)
     * <p>
     * inserts an item with key k and info i to the WAVL tree. the tree must remain
     * valid (keep its invariants). returns the number of rebalancing operations, or
     * 0 if no rebalancing operations were necessary. returns -1 if an item with key
     * k already exists in the tree.
     */
    public int insert(int k, String i) {
        // if the tree is empty
        if (empty()) {
            this.root = new WAVLNode(k, i);
            return 0;
        } else {
            return this.root.insert(new WAVLNode(k, i));
        }
    }
    
    /**
     * public int delete(int k)
     * <p>
     * deletes an item with key k from the binary tree, if it is there; the tree
     * must remain valid (keep its invariants). returns the number of rebalancing
     * operations, or 0 if no rebalancing operations were needed. returns -1 if an
     * item with key k was not found in the tree.
     */
    public int delete(int k) {
        return 42; // to be replaced by student code
    }
    
    /**
     * public String min()
     * <p>
     * Returns the info of the item with the smallest key in the tree, or null if
     * the tree is empty
     */
    public String min() {
        if (empty()) {
            return null;
        }
        
        WAVLNode cur = root;
        while (cur.left.isInnerNode()) {
            cur = cur.left;
        }
        
        return cur.getValue();
    }
    
    /**
     * public String max()
     * <p>
     * Returns the info of the item with the largest key in the tree, or null if the
     * tree is empty
     */
    public String max() {
        if (empty()) {
            return null;
        }
        
        WAVLNode cur = root;
        while (cur.right.isInnerNode()) {
            cur = cur.right;
        }
        return cur.getValue();
        
    }
    
    private WAVLNode[] inOrderWalk() {
        WAVLNode[] inOrderArray = new WAVLNode[size()];
        this.root.fillInOrderArray(inOrderArray, 0);
        return inOrderArray;
    }
    
    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree, or an empty array
     * if the tree is empty.
     */
    public int[] keysToArray() {
        WAVLNode[] inOrderArray = inOrderWalk();
        int[]      arr          = new int[inOrderArray.length];
        for (int i = 0; i < inOrderArray.length; i++) {
            arr[i] = inOrderArray[i].getKey();
        }
        return arr;
    }
    
    /**
     * public String[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree, sorted by their
     * respective keys, or an empty array if the tree is empty.
     */
    public String[] infoToArray() {
        WAVLNode[] inOrderArray = inOrderWalk();
        String[]   arr          = new String[inOrderArray.length];
        for (int i = 0; i < inOrderArray.length; i++) {
            arr[i] = inOrderArray[i].getValue();
        }
        return arr;
    }
    
    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     */
    public int size() {
        return root.getSubtreeSize(); // to be replaced by student code
    }
    
    /**
     * public WAVLNode getRoot()
     * <p>
     * Returns the root WAVL node, or null if the tree is empty
     */
    public WAVLNode getRoot() {
        return root;
    }
    
    /**
     * public int select(int i)
     * <p>
     * Returns the value of the i'th smallest key (return -1 if tree is empty)
     * Example 1: select(1) returns the value of the node with minimal key Example
     * 2: select(size()) returns the value of the node with maximal key Example 3:
     * select(2) returns the value 2nd smallest minimal node, i.e the value of the
     * node minimal node's successor
     */
    public String select(int i) {
        if (i > size()) {
            return "-1";
        }
        
        return this.root.selectNode(i).getValue();
    }
    
    /**
     * public class WAVLNode
     */
    public class WAVLNode {
        private Integer  key;
        private String   value;
        private WAVLNode left;
        private WAVLNode right;
        // private WAVLNode parent; // TODO: need that?
        // private int      size; // TODO: need that?
        // private int      height; // TODO: need that?
        private Integer  rank;
        
        public WAVLNode(Integer key, String value) {
            this.key = key;
            this.value = value;
            this.rank = 0;
            
            // TODO: this is recursive definition
            left = externalLeaf;
            right = externalLeaf;
        }
        
        public int getKey() {
            return key.intValue();
        }
        
        public String getValue() {
            return value;
        }
        
        public WAVLNode getLeft() {
            return left;
        }
        
        public WAVLNode getRight() {
            return right;
        }
        
        public boolean isInnerNode() {
            return key != null;
        }
        
        public boolean isExternalNode() {
            return !isInnerNode();
        }
        
        public int getSubtreeSize() {
            // TODO: make it O(1)
            
            if (!isInnerNode()) {
                return 0;
            }
            
            return left.getSubtreeSize() + right.getSubtreeSize() + 1;
        }
        
        private WAVLNode searchNode(int k) {
            if (isExternalNode()) {
                return this;
            } else {
                if (key == k) {
                    return this;
                } else if (k < this.key) {
                    return left.searchNode(k);
                } else {
                    return right.searchNode(k);
                }
            }
        }
        
        private int getHeight() {
            // TODO: make it O(1)
            
            if (!isInnerNode()) {
                return -1;
            }
            
            return Math.max(left.getSubtreeSize(), right.getSubtreeSize()) + 1;
        }
        
        private int fillInOrderArray(WAVLNode[] inOrderArray, int pos) {
            if (isInnerNode()) {
                pos = this.left.fillInOrderArray(inOrderArray, pos);
                inOrderArray[pos] = this;
                pos += 1;
                pos = this.right.fillInOrderArray(inOrderArray, pos);
            }
            return pos;
        }
        
        private int insert(WAVLNode node) {
            if (this.key.equals(node.key)) {
                return -1;
            } else if (node.key < this.key) {
                if (this.left.isExternalNode()) {
                    left = node;
                    return 1;
                } else {
                    return this.left.insert(node);
                }
            } else {
                if (this.right.isExternalNode()) {
                    right = node;
                    return 1;
                } else {
                    return this.right.insert(node);
                }
            }
        }
        
        private WAVLNode selectNode(int i) {
            int leftSize = left.getSubtreeSize();
            if (i <= leftSize) {
                return left.selectNode(i);
            } else if (i == leftSize + 1) {
                return this;
            } else {
                return right.selectNode(i - (leftSize + 1));
            }
        }
    }
    
}
