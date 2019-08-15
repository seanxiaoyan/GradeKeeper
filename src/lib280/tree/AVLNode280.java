package lib280.tree;

public class AVLNode280<I extends Comparable<? super I>> implements Comparable<AVLNode280<I>>
{
    /** Contents of the node. */
    protected I item;

    /** The left node. */
    protected AVLNode280<I> leftNode;

    /** The right node. */
    protected AVLNode280<I> rightNode;
    /** The height */
    protected int height;


    @Override
    public int compareTo(AVLNode280<I> x) {
        return this.item.compareTo(x.item);
    }

    /** Construct a new node with item x
     *  @timing Time = O(1)
     *  @param x the item placed in the new node
     *  */
    public AVLNode280(I x)
    {
        this.item = x;
        this.height=0;

    }


    /** Contents of the node.
     *  @timing Time = O(1) */
    public I item()
    {
        return item;
    }

    /** The left node.
     *  @timing Time = O(1) */
    public AVLNode280<I> getLeftNode()
    {
        return leftNode;
    }

    /** The left node.
     *  @timing Time = O(1) */
    public AVLNode280<I> getRightNode()
    {
        return rightNode;
    }

    /**
     * set left node as n
     * @param n node
     */
    public void  setLeftNode(AVLNode280<I> n){
        this.leftNode=n;
    }

    /**
     * set right node as n
     * @param n node
     */
    public void  setRightNode(AVLNode280<I> n){
        this.rightNode=n;
    }
    /**
     * Set the item contained in the node.
     * @param x The new item to store in the node.
     * @timing Time = O(1)
     * */
    public void setItem(I x) {
        this.item = x;
    }


    /**
     * Returns a string representation of the item contained within the node.
     */
    public String toString() {
        return this.item.toString();
    }

}