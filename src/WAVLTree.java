/**
 * WAVLTree
 * <p>
 * An implementation of a WAVL Tree. (Haupler, Sen & Tarajan ‘15)
 * made by: Yahav Ben Yaakov, 305170987
 *          Nir Endy,
 */

public class WAVLTree {
    public enum NodeDirection {
        Right, Left
    }
    
    private       WAVLNode root;
    private final WAVLNode externalLeaf;
    private       WAVLNode min;
    private       WAVLNode max;
    
    public WAVLTree() {
        externalLeaf = new WAVLNode(null, null);
        root = externalLeaf;
        min = root;
        max = root;
    }
    
    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() {
        // todo: root.isExternalNode()
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
        WAVLNode newNode = new WAVLNode(k, i);
        if (empty()) {
            // if the tree is empty
            this.setRoot(newNode);
            this.min = newNode;
            this.max = newNode;
            return 0;
        } else {
            if (min.getKey() > k) {
                this.min = newNode;
            }
            
            if (max.getKey() < k) {
                this.max = newNode;
            }
            
            return this.root.insert(newNode);
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
        if (this.empty()) {
            return -1;
        }
        if (this.size() == 1 && this.getRoot().getKey() == k) {
            // deleting the root
            min = externalLeaf;
            max = externalLeaf;
        } else if (k == min.getKey()) {
            // deleting the min (will always have successor because of the last if)
            min = min.successor();
        } else if (k == max.getKey()) {
            // deleting the min (same as above)
            max = max.predecessor();
        }
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

    // returns the min node
    private WAVLNode getMinNode() {
        return min;
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

    // returns the max node
    private WAVLNode getMaxNode() {
        return max;
    }

    // returns an array of all nodes, sorted by keys
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

    // update the root
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
        if (i <= 0 || i > size()) {
            return null;
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
        private int      size;
        private Integer  rank;
        
        public WAVLNode(Integer key, String value) {
            this.key = key;
            this.value = value;
            this.rank = (key == null) ? -1 : 0;
            this.size = (key == null) ? 0 : 1;

            this.left = externalLeaf;
            this.right = externalLeaf;
        }

        // check if the node is root of his tree
        private boolean isRoot() {
            return this == getRoot();
        }

        // set parent of the node
        private void setParent(WAVLNode parent) {
            if (this.isExternalNode()) {
                return;
            }
            
            this.parent = parent;
        }

        // get key of the node
        public int getKey() {
            return this.key == null ? -1 : this.key;
        }

        // get rank of the node
        public int getRank() {
            return this.rank;
        }

        // promote rank of the node by 1
        public void promotion() {
            this.rank++;
        }

        // demote rank of the node by 1
        public void demotion() {
            this.rank--;
        }

        // get value of the node
        public String getValue() {
            return this.value;
        }

        // get the left child of a node
        public WAVLNode getLeft() {
            return this.getChild(NodeDirection.Left);
        }

        // get the right child of a node
        public WAVLNode getRight() {
            return this.getChild(NodeDirection.Right);
        }

        // return true if the node is an inner node
        public boolean isInnerNode() {
            return this.key != null;
        }

        // return true if the node is an external node
        public boolean isExternalNode() {
            return !this.isInnerNode();
        }

        // return size of the tree which the node is the root of
        public int getSubtreeSize() {
            return this.size;
        }

        // update subtree size of the node
        public void setSubtreeSize(int newSize) {
            this.size = newSize;
        }

        // increase subtree size of the node by 1
        public void incSubtreeSize() {
            this.setSubtreeSize(this.size + 1);
        }

        // decrease subtree size of the node by 1
        public void decSubtreeSize() {
            this.setSubtreeSize(this.size - 1);
        }

        // update subtree size of the node following the subtree sizes of his children
        private void updateSubtreeSize() {
            this.size = 1 + this.getLeft().getSubtreeSize() + this.getRight().getSubtreeSize();
        }
        
        // search a node in the tree, implements WAVLTree search
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

        // return a sorted array of all tree nodes, sorted by keys
        // implements WAVLTree inOrderWalk
        private int fillInOrderArray(WAVLNode[] inOrderArray, int pos) {
            if (isInnerNode()) {
                pos = this.left.fillInOrderArray(inOrderArray, pos);
                inOrderArray[pos] = this;
                pos += 1;
                pos = this.right.fillInOrderArray(inOrderArray, pos);
            }
            return pos;
        }
        
        // return the successor of a node, null if there is no successor
        private WAVLNode successor() {
            return this.dCessor(NodeDirection.Right);
        }

        // return the predecessor of a node, null if there is no successor
        private WAVLNode predecessor() {
            return this.dCessor(NodeDirection.Left);
        }

        // return the successor / predecessor according to direction received
        // implements functions: successor, predecessor
        private WAVLNode dCessor(NodeDirection d) {
            // d=right => return the successor
            // d=left => return the predecessor
            
            NodeDirection oppD = getOppositeDirection(d);
            if ((d == NodeDirection.Right && this == getMaxNode()) //
                || (d == NodeDirection.Left && this == getMinNode())) {
                return null;
            }
            
            if (this.getChild(d).isInnerNode()) {
                // find minimum in the right/left subtree
                WAVLNode candidate = this.getChild(d);
                while (candidate.getChild(oppD).isInnerNode()) {
                    candidate = candidate.getChild(oppD);
                }
                return candidate;
            } else {
                // get the first node which is a right/left child
                WAVLNode candidate = this;
                while (candidate.getParentDirection() == d) {
                    candidate = candidate.parent;
                }
                return candidate.parent;
            }
        }

        // returns the parent of the node, null if node is the root
        private WAVLNode getParent() {
            return this.parent;
        }

        // receives a direction 'd' and a new node, and sets the new node
        // as a 'd' child of the current node
        private void setChild(NodeDirection d, WAVLNode child) {
            if (this.isExternalNode()) {
                return;
            }
            
            child.setParent(this);
            if (d == NodeDirection.Left) {
                this.left = child;
            } else {
                this.right = child;
            }
        }

        // returns left/right child of the node
        private WAVLNode getChild(NodeDirection d) {
            if (d == NodeDirection.Left) {
                return this.left;
            } else {
                return this.right;
            }
        }
        
        // insert a node to the tree, implements WAVLTree insert
        private int insert(WAVLNode node) {
            NodeDirection direction;
            if (this.key.equals(node.key)) {
                // key already exist
                
                // updating size of parents all the way up
                WAVLNode curParent = this;
                while (!curParent.isRoot()) {
                    curParent = curParent.getParent();
                    curParent.decSubtreeSize();
                }
                return -1;
            } else if (node.key < this.key) {
                direction = NodeDirection.Left;
            } else {
                direction = NodeDirection.Right;
            }
            
            this.incSubtreeSize();
            if (this.getChild(direction).isExternalNode()) {
                // found place to insert
                this.setChild(direction, node);
                return this.insertionBalance();
            } else {
                // recursive insert
                return this.getChild(direction).insert(node);
            }
        }
        
        // returns true if the node is a leaf
        private boolean isLeaf() {
            return this.getLeft().isExternalNode()  //
                   && this.getRight().isExternalNode();
        }

        // perform a switch between two nodes (middle stage in deletion)
        private void switchWith(WAVLNode node) {
            WAVLNode z       = this;
            WAVLNode x       = node;
            WAVLNode zParent = z.getParent();
            WAVLNode xParent = node.getParent();
            x.rank = z.getRank();
            NodeDirection zParentDirection = z.isRoot() ? null : z.getParentDirection();


            if (z == xParent) { // switch between a child and parent
                WAVLNode xRight = x.getRight();
                z.setChild(NodeDirection.Right, externalLeaf);
                x.setChild(NodeDirection.Right, z);
                x.setChild(NodeDirection.Left, z.getLeft());
                z.setChild(NodeDirection.Right, xRight);
                
            } else {
                WAVLNode rightChildx = x.getRight();
                x.setChild(NodeDirection.Right, z.getRight());
                x.setChild(NodeDirection.Left, z.getLeft());
                z.setChild(NodeDirection.Right, rightChildx);
                z.setChild(NodeDirection.Left, externalLeaf);
                xParent.setChild(NodeDirection.Left, z);
                
            }
            

            // update root if necessary
            if (this.isRoot()) {
                x.setParent(null);
                setRoot(x);
            } else {
                zParent.setChild(zParentDirection, x);
            }

            // fix subtree sizes of nodes affected from the deletion
            while (z.parent != x) {
                z = z.getParent();
                z.decSubtreeSize();
            }
        }

        // delete a node from the tree, implements WAVLTree delete
        private int delete(int key) {
            NodeDirection direction;
            if (this.key.equals(key)) { // found node to delete
                this.decSubtreeSize();
                
                if (this.isLeaf()) { // node is a leaf
                    if (this.isRoot()) {
                        setRoot(externalLeaf);
                        return 0;
                    } else {
                        WAVLNode RB = this.getParent();
                        RB.setChild(this.getParentDirection(), externalLeaf);
                        return RB.deletionBalance();
                    }
                } else if (this.getLeft().isExternalNode() || this.getRight().isExternalNode()) { // node has only 1 child
                    WAVLNode onlyChild = this.getLeft().isExternalNode() ? this.getRight() : this.getLeft();
                    if (this.isRoot()) {
                        setRoot(onlyChild);
                        onlyChild.parent = externalLeaf;
                        return 0;
                    } else {
                        this.getParent().setChild(this.getParentDirection(), onlyChild);
                        return onlyChild.getParent().deletionBalance();
                    }
                } else { // node has 2 children
                    WAVLNode successor = this.successor();
                    WAVLNode RB        = successor.getParent();
                    this.switchWith(successor);
                    if (RB != this) {
                        RB.setChild(NodeDirection.Left, this.getRight());
                        successor.updateSubtreeSize();
                        return RB.deletionBalance();
                    } else {
                        successor.setChild(NodeDirection.Right, this.getRight());
                        successor.updateSubtreeSize();
                        return successor.deletionBalance();
                    }
                }
            } else if (key < this.key) {
                direction = NodeDirection.Left;
            } else {
                direction = NodeDirection.Right;
            }
            
            if (this.getChild(direction).isExternalNode()) {
                
                // node to delete not found, increase size back up
                WAVLNode curParent = this;
                while (!curParent.isRoot()) {
                    curParent = curParent.getParent();
                    curParent.incSubtreeSize();
                }
                
                return -1;
            } else {
                // recursive insert
                this.decSubtreeSize();
                return this.getChild(direction).delete(key);
            }
            
        }

        // returns the rank difference between a node to his 'd' child
        public int getRankDiff(NodeDirection d) {
            return this.rank - this.getChild(d).rank;
        }

        // returns the opposite direction of 'd'
        private NodeDirection getOppositeDirection(NodeDirection d) {
            return d == NodeDirection.Left ? NodeDirection.Right : NodeDirection.Left;
        }

        // return the direction of the node in relate to his parent
        // e.g left if he is a left child of his parent
        private NodeDirection getParentDirection() {
            

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
             __/ \                         / \
            A     B                       B   Y

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
            
            z.updateSubtreeSize();
            x.updateSubtreeSize();
            
        }

        // balance the tree after insertion
        // returns the number of balancing operations needed
        private int insertionBalance() {
            int lDiff = this.getRankDiff(NodeDirection.Left);
            int rDiff = this.getRankDiff(NodeDirection.Right);
            
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

        // balance the tree after deletion
        // returns the number of balancing operations needed
        private int deletionBalance() {
            int lDiff = this.getRankDiff(NodeDirection.Left);
            int rDiff = this.getRankDiff(NodeDirection.Right);
            
            if ((lDiff == 1 && rDiff == 1) //
                || (lDiff == 1 && rDiff == 2) //
                || ((lDiff == 2 && rDiff == 2) && (!this.isLeaf())) //
                || (lDiff == 2 && rDiff == 1)) {
                // stopping condition - after the rank diff is ok
                return 0;
            } else if (lDiff == 2 && rDiff == 2) {
                // a leaf with rank 1, demote and move the problem up
                this.demotion();
                return 1 + (this.isRoot() ? 0 : this.getParent().deletionBalance());
            } else {
                if ((lDiff == 3 && rDiff == 2) //
                    || (lDiff == 2 && rDiff == 3)) {
                    // child nodes diff is ok
                    // but this node need demotion
                    this.demotion();
                    
                    if (this.isRoot()) {
                        // we got to the root
                        return 1;
                    } else {
                        // move problem to my parent
                        return 1 + this.getParent().deletionBalance();
                    }
                } else {
                    
                    NodeDirection d    = rDiff == 1 ? NodeDirection.Right : NodeDirection.Left;
                    NodeDirection oppD = getOppositeDirection(d);
                    
                    WAVLNode probChild = this.getChild(d);
                    
                    int rProbDiff = probChild.getRankDiff(d);
                    int lProbDiff = probChild.getRankDiff(oppD);
                    
                    if (lProbDiff == 2 && rProbDiff == 2) {
                        this.demotion();
                        probChild.demotion();
                        

                        if (this.isRoot()) {
                            // we got to the root
                            return 2;
                        } else {
                            return 2 + this.parent.deletionBalance();
                        }
                        
                    } else if ((lProbDiff == 1 || lProbDiff == 2) && rProbDiff == 1) {

                        this.rotate(oppD);
                        probChild.promotion();
                        this.demotion();
                        if (this.isLeaf() && this.getRankDiff(d) == 2 && this.getRankDiff(oppD) == 2) {
                            this.demotion();
                        }
                        return 3;
                    } else {

                        
                        this.demotion();
                        this.demotion();
                        probChild.demotion();
                        probChild.getChild(oppD).promotion();
                        probChild.getChild(oppD).promotion();
                        probChild.rotate(d);
                        this.rotate(oppD);
                        
                        return 5;
                    }
                }
            }
        }

        // returns the i-th's node in the tree by key size
        // implements WAVLTree select
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
