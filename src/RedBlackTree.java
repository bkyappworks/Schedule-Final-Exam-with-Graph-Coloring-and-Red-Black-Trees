public class RedBlackTree {
    RedBlackNode root;
    private RedBlackNode nil;
    private int size;
    private int compareCount;
    private int id;
    /**
     *  It creates a RedBlackNode that represents null.
     *  It sets the internal variable tree to point to this
     *  node. This node that an empty tree points to will be
     *  used as a sentinel node. That is, all pointers in the
     *  tree that would normally contain the value null, will
     *  instead point to this sentinel node.
     */
    public RedBlackTree() {
        nil = new RedBlackNode(-1,"nil",0, nil,nil,nil);
//        nil.setColor(0);
        root = nil;
//        root.setP(nil);
        id = 0;
    }
    /**
     * Pre-condition: memory is available for insertion
     * The insert() method places a data item into the tree.
     * @param value
     */
    public void insert(java.lang.String value) {
        RedBlackNode y = nil;
        RedBlackNode x = root;
        RedBlackNode z = new RedBlackNode(id,value,1, nil,nil,nil); // color is default red
//        System.out.println("x: "+x);
//        System.out.println("y.getColor(): "+y.getColor());
        while (x != nil) {
//            System.out.println("in while loop");
            y = x;
            if (value.compareTo(x.getData()) < 0) {
                x = x.getLc();
            } else {
                x = x.getRc();
            }
        }
        z.setP(y);
//        System.out.println("y: "+y);
        if (y == nil) {
            root = z;
//            System.out.println("root: "+root);
        } else {
            if (value.compareTo(y.getData()) < 0) {
                y.setLc(z);
            } else {
                y.setRc(z);
            }
        }
        id++;
        z.setLc(nil);
        z.setRc(nil);
        z.setColor(1);
//        System.out.println("z.getP(): "+z.getP());
//        System.out.println("y.getColor(): "+y.getColor());
//        System.out.println(z.getP().getColor());
        RBInsertFixup(z);
//        System.out.println("DONE RBInsertFixup");
        size++;
    }
    /**
     * Pre-condition: right[x] != nil[T]
     * Pre-condition: root's parent is nill[T]
     * leftRotate() performs a single left rotation.
     * This would normally be a private method.
     * It executes the following algorithm from CLR.
     * @param x
     */
    public void leftRotate(RedBlackNode x) {
        RedBlackNode y = x.getRc();
        x.setRc(y.getLc());
        y.getLc().setP(x);
        y.setP(x.getP());
        if (x.getP() == nil) {
            root = y;
        } else {
            if (x ==x.getP().getLc()) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setLc(x);
        x.setP(y);

    }
    /**
     * Pre-condition: left[x] != nil[T]
     * Pre-condition: root's parent is nill[T]
     * rightRotate() performs a single right rotation This would normally be a private method.
     * It executes the following algorithm from CLR.
     * @param x
     */
    public void rightRotate(RedBlackNode x) {
        RedBlackNode y = x.getLc(); // y now points to node to left of x
        x.setLc(y.getRc()); // y's right subtree becomes x's left subtree
        y.getRc().setP(x); // right subtree of y gets a new parent
        y.setP(x.getP()); // y's parent is now x's parent
        // if x is at root then y becomes new root
        if (x.getP() == nil) {
            root = y;
        } else {
            // if x is a left child then adjust x's parent's left child or...
            if (x == x.getP().getLc()) {
                x.getP().setLc(y);
            } else {
                // adjust x's parent's right child
                x.getP().setRc(y);
            }

        }
        // the right child of y is now x
        y.setRc(x);
        // the parent of x is now y
        x.setP(y);
    }
    /**
     * Fixing up the tree so that Red Black Properties are preserved. This would normally be a private method.
     * @param z - is the new node.
     */
    public void RBInsertFixup(RedBlackNode z) {
        RedBlackNode y;

        while (z.getP().getColor() == 1) {
//            System.out.println("z.getP(): "+z.getP());
//            System.out.println("Enter while (z.getP().getColor() == 1)");
            if (z.getP() == z.getP().getP().getLc()) { // if z.p is a left node, then uncle is the right node
                y = z.getP().getP().getRc();
//                System.out.println("y.getColor(): "+y.getColor());
                if (y.getColor() == 1) {
                    z.getP().setColor(0);
                    y.setColor(0);
//                    System.out.println("y.setColor(0): "+y.getColor());
                    z.getP().getP().setColor(1);
                    z = z.getP().getP();
//                    System.out.println("z = z.getP().getP()");
                } else {
//                    System.out.println("y.getColor() != 1");
                    if (z == z.getP().getRc()) {
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(0);
                    z.getP().getP().setColor(1);
//                    System.out.println("DONE before z.getP().getP()");
                    rightRotate(z.getP().getP());
//                    System.out.println("DONE rightRotate");
                }
            } else {
                y = z.getP().getP().getLc();
                if (y.getColor() == 1) {
                    z.getP().setColor(0);
                    y.setColor(0);
                    z.getP().getP().setColor(1);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getLc()) {
                    z = z.getP();
                    rightRotate(z);
                }
                    z.getP().setColor(0);
                    z.getP().getP().setColor(1);
//                    System.out.println("DONE before z.getP().getP()");
                    leftRotate(z.getP().getP());
//                    System.out.println("leftRotate");
                } // end else
            } // end else

        } // end while
        root.setColor(0);
    }
    /**
     *
     * @return number of values inserted into the tree.
     */
    public int getSize() {
        return size;
    }
    /**
     * Perfrom an inorder traversal of the tree. The inOrderTraversal(RedBlackNode) method is recursive and displays the content of the tree. It makes calls on System.out.println(). This method would normally be private.
     * @param t - the root of the tree on the first call.
     */
    public void inOrderTraversal(RedBlackNode t) {
        if (t != null) {
            inOrderTraversal(t.getLc());
            System.out.println(t);
            inOrderTraversal(t.getRc());
        }
    }
    /**
     * The no argument inOrderTraversal() method calls the recursive inOrderTraversal(RedBlackNode) - passing the root.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }
    /**
     * reference: https://www.geeksforgeeks.org/reverse-level-order-traversal/
     * Perform a reverseOrder traversal of the tree.
     * The reverseOrderTraversal(RedBlackNode) method is recursive and displays the content of the tree in reverse order.
     * This method would normally be private.
     * @param t
     */
    public void reverseOrderTraversal(RedBlackNode t,int level) {
        if (t == null)
            return;
        if (level == 1)
            System.out.print(t.getData() + " ");
        else if (level > 1)
        {
            reverseOrderTraversal(t.getLc(), level - 1);
            reverseOrderTraversal(t.getRc(), level - 1);
        }
    }
    /**
     * The no argument reverseOrderTraversal() method calls the recursive reverseOrderTraversal(RedBlackNode) - passing the root.
     */
    public void reverseOrderTraversal() {
        int h = height(root);
        int i;
        for (i = h; i >= 1; i--)
        //THE ONLY LINE DIFFERENT FROM NORMAL LEVEL ORDER
        {
            reverseOrderTraversal(root, i);
        }
    }
    /**
     * The boolean contains() returns true if the String v is in the RedBlackTree and false otherwise.
     * It counts each comparison it makes in the variable recentCompares.
     * @param value - the value to search for.
     * @return true if v is in the tree, false otherwise;
     */
    public boolean contains(java.lang.String value) {
        RedBlackNode y = nil;
        RedBlackNode x = root;
        if (x == nil) {
            return false;
        }
        while (x != nil && !x.getData().equals(value)) {
            y = x;
            if (value.compareTo(x.getData()) < 0) {
                x = x.getLc();
            } else {
                x = x.getRc();
            }
            if (x == nil) { // not found
                return false;
            }
        }
        return true;
    }
    /**
     * @return number of comparisons made in last call on the contains method.
     */
    public int getRecentCompares() {
        return compareCount;
    }
    /**
     * This method calls the recursive method.
     * @return the height of the red black tree.
     */
    public int height() {
        return height(root);
    }
    /**
     * reference: https://stackoverflow.com/questions/2597637/finding-height-in-binary-search-tree
     * This a recursive routine that is used to compute the height of the red black tree.
     * It is called by the height() method.
     * The height() method passes the root of the tree to this method. This method would normally be private.
     * @param t - a pointer to a node in the tree.
     * @return the height of node t.
     */
    public int height(RedBlackNode t) {
        if (t == null) {
            return -1;
        }

        int lefth = height(t.getLc());
        int righth = height(t.getRc());

        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }
    public int getID(String courseName) {
        RedBlackNode y = nil;
        RedBlackNode x = root;
        if (x == nil) {
            return -1;
        }
        while (x != nil && !x.getData().equals(courseName)) {
            y = x;
            if (courseName.compareTo(x.getData()) < 0) {

                x = x.getLc();
            } else {
                compareCount++;
                x = x.getRc();
            }
            if (x == nil) { // not found
                return -1;
            }
        }
        return x.getCourseID();
    }
//    public String getName(int id) {
//
//    }
    @Override
    public String toString() {
        return toString (root);
    }
    private String toString(RedBlackNode root) {
        /*return "RedBlackTree{" +
                "root=" + root +
                ", nil=" + nil +
                ", size=" + size +
                ", compareCount=" + compareCount +
                ", id=" + id +
                '}';*/
        StringBuilder builder = new StringBuilder();
        if (root == null)
            return "";
        builder.append(toString(root.getLc()));
        builder.append(toString(root.getRc()));
        return builder.append(root.getData()).append(",").append(root.getCourseID()).append(" ").toString();
    }
}
