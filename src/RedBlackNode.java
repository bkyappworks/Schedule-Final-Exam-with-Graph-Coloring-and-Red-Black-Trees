public class RedBlackNode {
    public static final int BLACK = 0; // https://www.andrew.cmu.edu/user/mm6/95-771/examples/DSARedBlackTreeProject/dist/javadoc/constant-values.html#redblacktreetesterproject.RedBlackNode.RED
    public static final int RED = 1;
//    private static java.lang.String data;
    private  int courseID;
    private  java.lang.String courseName;
    private  int color;
    private  RedBlackNode p, lc, rc;

    /**
     *
     * @param data a simple value held in the tree
     * @param color either RED or BLACK
     * @param p the parent pointer
     * @param lc the pointer to the left child (will be null only for the node that represents all external nulls.
     * @param rc the pointer to the right child (will be null only for the node that represents all external nulls.
     */
    public RedBlackNode(int id,java.lang.String data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
        this.courseID = id;
        this.courseName = data;
        this.color = color;
        this.p = p;
        this.lc = lc;
        this.rc = rc;
    }

    public  String getData() {
        return this.courseName;
    }

    public  void setData(String data) {
        this.courseName = data;
    }

    public  int getColor() {
        return color;
    }

    public  void setColor(int color) {
        this.color = color;
    }

    public  RedBlackNode getP() {
        return p;
    }

    public  void setP(RedBlackNode p) {
        this.p = p;
    }

    public  RedBlackNode getLc() {
        return lc;
    }

    public  void setLc(RedBlackNode lc) {
        this.lc = lc;
    }

    public  RedBlackNode getRc() {
        return rc;
    }

    public  void setRc(RedBlackNode rc) {
        this.rc = rc;
    }

    public  int getCourseID() {
        return courseID;
    }

    @Override
    public String toString() {
        return "RedBlackNode{"+courseName+","+courseID+"}";
    }
}
