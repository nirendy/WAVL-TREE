/**
 * WAVLTree
 * <p>
 * An implementation of a WAVL Tree. (Haupler, Sen & Tarajan ‘15)
 */

public class WAVLTree {
    public enum NodeDirection {
        Right, Left
    }
    
    // public enum BalanceCase {
    //     A, B, C
    // }
    
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
            this.setRoot(new WAVLNode(k, i));
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
        return this.root.delete(k);
    }
    
    /**
     * public String min()
     * <p>
     * Returns the info of the item with the smallest key in the tree, or null if
     * the tree is empty
     */
    public String min() {
        return this.getMinNode().getValue();
    }
    
    private WAVLNode getMinNode() {
        if (empty()) {
            return this.externalLeaf;
        }
        
        WAVLNode cur = root;
        while (cur.left.isInnerNode()) {
            cur = cur.left;
        }
        return cur;
    }
    
    /**
     * public String max()
     * <p>
     * Returns the info of the item with the largest key in the tree, or null if the
     * tree is empty
     */
    public String max() {
        return this.getMaxNode().getValue();
    }
    
    
    private WAVLNode getMaxNode() {
        if (empty()) {
            return this.externalLeaf;
        }
        
        WAVLNode cur = root;
        while (cur.right.isInnerNode()) {
            cur = cur.right;
        }
        return cur;
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
    
    private void setRoot(WAVLNode newRoot) {
        this.root = newRoot;
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
        private WAVLNode parent;
        // private int      size; // TODO: need that?
        // private int      height; // TODO: need that?
        private Integer  rank;
        
        public WAVLNode(Integer key, String value) {
            this.key = key;
            this.value = value;
            this.rank = (key == null) ? -1 : 0;
            
            // TODO: this is recursive definition
            this.left = externalLeaf;
            this.right = externalLeaf;
        }
        
        private boolean isRoot() {
            return this == getRoot();
        }
        
        private void setParent(WAVLNode parent) {
            if (this.isExternalNode()) {
                System.err.println("warning: set parent of external");
                return;
            }
            
            this.parent = parent;
        }
        
        public int getKey() {
            return this.key;
        }
        
        public int getRank() {
            // TODO: remove
            return this.rank;
        }
        
        public void promotion() {
            this.rank++;
        }
        
        public void demotion() {
            this.rank--;
        }
        
        public String getValue() {
            return this.value;
        }
        
        public WAVLNode getLeft() {
            return this.getChild(NodeDirection.Left);
        }
        
        public WAVLNode getRight() {
            return this.getChild(NodeDirection.Right);
        }
        
        public boolean isInnerNode() {
            return this.key != null;
        }
        
        public boolean isExternalNode() {
            return !this.isInnerNode();
        }
        
        public int getSubtreeSize() {
            // TODO: make it O(1)
            
            if (!isInnerNode()) {
                return 0;
            }
            
            return this.left.getSubtreeSize() + this.right.getSubtreeSize() + 1;
        }
        
        private WAVLNode searchNode(int k) {
            if (isExternalNode()) {
                return this;
            } else {
                if (this.key == k) {
                    return this;
                } else if (k < this.key) {
                    return this.left.searchNode(k);
                } else {
                    return this.right.searchNode(k);
                }
            }
        }
        
        private int getHeight() {
            // TODO: make it O(1)
            
            if (!isInnerNode()) {
                return -1;
            }
            
            return Math.max(this.left.getSubtreeSize(), this.right.getSubtreeSize()) + 1;
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
        
        private WAVLNode successor() {
            if (this == getMaxNode()) {
                return null;
            }
            
            if (this.getRight().isInnerNode()) {
                WAVLNode candidate = this.getRight();
                while (candidate.getLeft().isInnerNode()) {
                    candidate = candidate.getLeft();
                }
                return candidate;
            } else {
                
                WAVLNode candidate = this.getParent();
                while (candidate.getParentDirection() == NodeDirection.Right) {
                    candidate = candidate.getLeft();
                }
                return candidate;
            }
        }
        
        private WAVLNode getParent() {
            return this.parent;
        }
        
        private void setChild(NodeDirection d, WAVLNode child) {
            if (this.isExternalNode()) {
                System.err.println("warning: set child of external");
                return;
            }
            
            child.setParent(this);
            if (d == NodeDirection.Left) {
                this.left = child;
            } else {
                this.right = child;
            }
        }
        
        private WAVLNode getChild(NodeDirection d) {
            if (d == NodeDirection.Left) {
                return this.left;
            } else {
                return this.right;
            }
        }
        
        
        private int insert(WAVLNode node) {
            
            NodeDirection direction;
            if (this.key.equals(node.key)) {
                // key already exist
                return -1;
            } else if (node.key < this.key) {
                direction = NodeDirection.Left;
            } else {
                direction = NodeDirection.Right;
            }
            
            if (this.getChild(direction).isExternalNode()) {
                // found place to insert
                this.setChild(direction, node);
                return this.insertionBalance();
            } else {
                // recursive insert
                return this.getChild(direction).insert(node);
            }
            
        }
        
        private int delete(int key) {
            
            NodeDirection direction;
            if (this.key.equals(key)) {
                
                return 0;
            } else if (key < this.key) {
                direction = NodeDirection.Left;
            } else {
                direction = NodeDirection.Right;
            }
            
            if (this.getChild(direction).isExternalNode()) {
                return -1;
            } else {
                // recursive insert
                return this.getChild(direction).delete(key);
            }
            
        }
        
        private int getRankDiff(NodeDirection d) {
            return this.rank - this.getChild(d).rank;
        }
        
        private NodeDirection getOppositeDirection(NodeDirection d) {
            return d == NodeDirection.Left ? NodeDirection.Right : NodeDirection.Left;
        }
        
        private NodeDirection getParentDirection() {
            
            // return D if the node is a D child of his parent
            if (this.getParent().getChild(NodeDirection.Left) == this) {
                return NodeDirection.Left;
            } else {
                return NodeDirection.Right;
            }
        }
        
        private void rotate(NodeDirection d) {
            /*
            
            Example of Right rotation
            
            we can think of d=Right and oppD=Left
            
                    z                   x
                 __/ \               __/ \__
                x     Y    --->     A       z
             __/ \                 / \
            A     B               B   Y

             */
            
            
            NodeDirection oppD = getOppositeDirection(d);
            
            WAVLNode z = this;
            WAVLNode x = z.getChild(oppD);
            WAVLNode b = x.getChild(d);
            
            // making sure the x has the correct parent
            if (z.isRoot()) {
                setRoot(x);
                x.setParent(null);
            } else {
                z.getParent().setChild(this.getParentDirection(), x);
            }
            
            z.setChild(oppD, b);
            x.setChild(d, z);
            
        }
        
        private int insertionBalance() {
            int lDiff = this.getRankDiff(NodeDirection.Left);
            int rDiff = this.getRankDiff(NodeDirection.Right);
            // System.out.println("key=" + this.key + ", Ldiff=" + lDiff + ", rDiff=" + rDiff);
            
            if ((lDiff == 1 && rDiff == 1) //
                || (lDiff == 1 && rDiff == 2) //
                || (lDiff == 2 && rDiff == 1)) {
                
                // stopping condition - after the rank diff is ok
                return 0;
            } else {
                if ((lDiff == 0 && rDiff == 1) //
                    || (lDiff == 1 && rDiff == 0)) {
                    // child nodes diff is ok
                    // but this node need promote
                    this.promotion();
                    
                    if (this.isRoot()) {
                        // we got to the root
                        return 1;
                    } else {
                        // move problem to my parent
                        return 1 + this.getParent().insertionBalance();
                    }
                } else {
                    // child nodes diff is not ok
                    // and this node need promote
                    
                    /*
                        notice: the case that child diff is
                        not ok and the parent don't need promote can't happen
                    */
                    
                    NodeDirection d    = lDiff == 0 ? NodeDirection.Left : NodeDirection.Right;
                    NodeDirection oppD = getOppositeDirection(d);
                    
                    
                    WAVLNode probChild = this.getChild(d);
                    
                    int lProbDiff = probChild.getRankDiff(d);
                    int rProbDiff = probChild.getRankDiff(oppD);
                    
                    if (lProbDiff == 1 && rProbDiff == 2) {
                        this.demotion();
                        this.rotate(oppD);
                        return 2;
                    } else {
                        this.demotion();
                        probChild.demotion();
                        probChild.getChild(oppD).promotion();
                        probChild.rotate(d);
                        this.rotate(oppD);
                        return 5;
                    }
                    
                }
            }
        }
        
        private int deletionBalance() {
            int lDiff = this.getRankDiff(NodeDirection.Left);
            int rDiff = this.getRankDiff(NodeDirection.Right);
            System.out.println("key=" + this.key + ", Ldiff=" + lDiff + ", rDiff=" + rDiff);
            
            
            return 0;
        }
        
        private WAVLNode selectNode(int i) {
            int leftSize = this.left.getSubtreeSize();
            if (i <= leftSize) {
                return this.left.selectNode(i);
            } else if (i == leftSize + 1) {
                return this;
            } else {
                return this.right.selectNode(i - (leftSize + 1));
            }
        }
    }
    
}
