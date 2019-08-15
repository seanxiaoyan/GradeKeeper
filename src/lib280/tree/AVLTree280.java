package lib280.tree;

import lib280.exception.ContainerEmpty280Exception;
import lib280.exception.DuplicateItems280Exception;
import lib280.exception.ItemNotFound280Exception;


public class AVLTree280<I extends Comparable<? super I>> implements SimpleTree280<I>{

    protected AVLNode280<I> rootNode;
    public AVLTree280()
    {
        this.rootNode=null;
    }

    protected AVLNode280<I> createNewNode(I item)
    {
        return new AVLNode280<I>(item);
    }
    @Override
    public I rootItem() throws ContainerEmpty280Exception {
        return rootNode.item();
    }

    @Override
    public AVLTree280<I> rootRightSubtree() throws ContainerEmpty280Exception {
        AVLTree280<I> newT=new AVLTree280<>();
        newT.rootNode=this.rootNode.getRightNode();
        return newT;
    }

    @Override
    public AVLTree280<I> rootLeftSubtree() throws ContainerEmpty280Exception {
        AVLTree280<I> newT=new AVLTree280<>();
        newT.rootNode=this.rootNode.getLeftNode();
        return newT;
    }

    @Override
    public boolean isEmpty() {
        return rootNode==null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void clear() {

    }
    /**
     * find max of two integers
     * @param n1
     * @param n2
     * @return the max of two integers
     */
    protected int max(int n1, int n2){
        if(n1>n2){
            return n1;
        }
        else{
            return n2;
        }
    }
    /**
     * get height of the N's subtree
     * @param N
     * @return
     */
    private int getHeight(AVLNode280<I>N){
        if (N==null){
            return 0;
        }
        else{
            return N.height;
        }
    }
    public int getHeight(){
        return getHeight(rootNode);
    }

    /**
     * check the left subtree height with right subtree height
     * @param R the root node of the tree
     * @return the difference value
     */
    public int checkHeightDiff(AVLNode280<I>R){
        int leftH=getHeight(R.getLeftNode());
        int rightH=getHeight(R.getRightNode());
        return leftH - rightH;
    }
    /**
     * determine if item is in the tree
     * @param node the node where to start search
     * @param data the data which is looking for
     * @return true if data is in the node and or its subnode
     */
    private boolean has(AVLNode280<I>node,I data){
        if(node == null){
            return false;
        }
        else if (node.item()==data){
            return true;
        }
        else{
            if(data.compareTo(node.item())<0){
                return has(node.leftNode,data);
            }
            else{
                return has(node.rightNode,data);
            }

        }
    }
    /**
     * determine if item is in the tree
     * @param data the data which is looking for
     * @return true if data is in the node and or its subnode
     */
    public boolean has(I data){
        return has(rootNode,data);
    }

    private AVLNode280<I> rightRotation(AVLNode280<I> node){
        AVLNode280<I> node2 = node.leftNode;
        node.leftNode=node2.rightNode;
        node2.rightNode=node;
        node.height=max(getHeight(node.leftNode),getHeight(node.rightNode))+1;
        node2.height=max(getHeight(node2.leftNode),node.height)+1;
        return node2;
    }
    private AVLNode280<I> leftRotation(AVLNode280<I> node){
        AVLNode280<I> node2 = node.rightNode;
        node.rightNode=node2.leftNode;
        node2.leftNode=node;
        node.height=max(getHeight(node.leftNode),getHeight(node.rightNode))+1;
        node2.height=max(node.height,getHeight(node2.rightNode))+1;
        return node2;
    }

    /**
     *
     * @param data item to be inserted
     * @param R rootNode
     */
    private AVLNode280 insert(I data, AVLNode280<I>R) throws DuplicateItems280Exception {
        AVLNode280<I> newN=new AVLNode280<>(data);
        if (R==null){
            R=newN;
        }
        else {
            if (newN.compareTo(R) < 0) {
                R.setLeftNode(insert(data, R.getLeftNode()));
                if (getHeight(R.getLeftNode()) - getHeight(R.getRightNode()) == 2) {
                    if (data.compareTo(R.getLeftNode().item()) < 0) {
                        //left-left imbalance
                        R = rightRotation(R);
                    } else {
                        //left-right imbalance
                        R.setLeftNode(leftRotation(R.getLeftNode()));
                        R = rightRotation(R);
                    }
                }
            } else if (newN.compareTo(R) > 0) {
                R.setRightNode(insert(data, R.getRightNode()));
                if (getHeight(R.getRightNode()) - getHeight(R.getLeftNode()) == 2) {
                    if (data.compareTo(R.getRightNode().item()) > 0) {
                        //right-right imbalance
                        R = leftRotation(R);
                    } else {
                        //right-left imbalance
                        R.setRightNode(rightRotation(R.getRightNode()));
                        R = leftRotation(R);
                    }
                }}

                else
                    {
                    throw new DuplicateItems280Exception();
                }

            }
        R.height = max(getHeight(R.getLeftNode()), getHeight(R.getRightNode())) + 1;
        return R;
    }

    /**
     * insert item into tree
     * @param data item
     */
    public void insert(I data){
        if(isEmpty()){
            rootNode=new AVLNode280<>(data);
            rootNode.height=1;
        }
        else{
            rootNode=insert(data,rootNode);
        }
    }

    /**
     *
     * @param targetNode the node need to be deleted
     * @param R the rootNode of the tree
     * @return the rootNode after deletion
     */

    private AVLNode280 delete(AVLNode280<I>targetNode, AVLNode280<I>R) throws ItemNotFound280Exception {
        if(R.item()==null){
            return null;
        }
        if(!has(targetNode.item())){
            throw new ItemNotFound280Exception();
        }
        if(targetNode.item().compareTo(R.item())<0){
            R.setLeftNode(delete(targetNode,R.getLeftNode()));
            //delete one node from LEFT subtree may cause RIGHT - IMBALANCE
            if(getHeight(R.getRightNode())-getHeight(R.getLeftNode())==2){
                // now check RIGHT subtree
                AVLNode280<I>rSub=R.getRightNode();
                // rSub is right subtree now.
                // for the tree rSub, if the left subtree's height > the right subtree's height
                if(getHeight(rSub.getLeftNode())>getHeight(rSub.getRightNode())){
                    // RIGHT - LEFT imbalance!
                    R.setRightNode(rightRotation(R.getRightNode()));
                    rootNode = leftRotation(R);
                }
                else{
                    // RIGHT - RIGHT imbalance!
                    rootNode = leftRotation(R);
                }
            }
        }
        else if(targetNode.item().compareTo(R.item())>0){
            R.setRightNode(delete(targetNode,R.getRightNode()));
            //delete one node from RIGHT subtree may cause LEFT - IMBALANCE
            if(getHeight(R.getLeftNode())-getHeight(R.getRightNode())==2){
                // now check LEFT subtree
                AVLNode280<I>lSub=R.getLeftNode();
                //if lSub.left's height > lSub.right's height
                if(getHeight(lSub.getLeftNode())>getHeight(lSub.getRightNode())){
                    // LEFT - LEFT imbalance
                    rootNode=rightRotation(R);
                }
                else{
                    //LEFT - RIGHT imbalance
                    R.setLeftNode(leftRotation(R.getLeftNode()));
                    rootNode= rightRotation(R);
                }
            }
        }
        else {
            //if has two child
            if(R.getLeftNode()!=null&&R.getRightNode()!=null){
                if(getHeight(R.getLeftNode())>getHeight(R.getRightNode())){
                    AVLNode280<I>leftT=R.getLeftNode();
                    while(leftT.getRightNode()!=null){
                        leftT=leftT.getRightNode();
                    }
                    R.setItem(leftT.item());
                    R.setLeftNode(delete(leftT,R.getLeftNode()));
                }
                else{
                    AVLNode280<I>rightT=R.getRightNode();
                    while(rightT.getLeftNode()!=null){
                        rightT=rightT.getLeftNode();
                    }
                    R.setItem(rightT.item());
                    R.setRightNode(delete(rightT,R.getRightNode()));
                }
            }
            else{
                //if only has one child
                if(R.getLeftNode()!=null){
                    //just update rootNode reference
                    R=R.getLeftNode();
                }
                else{
                    ////just update rootNode reference
                    R=R.getRightNode();
                }

            }
        }
        return R;
    }
    public void delete(I data){
        AVLNode280<I>targetNode=new AVLNode280<>(data);
        delete(targetNode,rootNode);
    }

    /**	Form a string representation that includes level numbers.
     Analysis: Time = O(n), where n = number of items in the (sub)lib280.tree
     @param i the level for the root of this (sub)lib280.tree
     */
     protected String toStringByLevel(int i)
     {
     StringBuffer blanks = new StringBuffer((i - 1) * 5);
     for (int j = 0; j < i - 1; j++)
     blanks.append("     ");

     String result = new String();
     if (!isEmpty() && (!rootLeftSubtree().isEmpty() || !rootRightSubtree().isEmpty()))
     result += rootRightSubtree().toStringByLevel(i+1);

     result += "\n" + blanks + i + ": " ;
     if (isEmpty())
     result += "-";
     else
     {
     result += rootItem();
     if (!rootLeftSubtree().isEmpty() || !rootRightSubtree().isEmpty())
     result += rootLeftSubtree().toStringByLevel(i+1);
     }
     return result;
     }

     /**	String representation of the lib280.tree, level by level. <br>
     Analysis: Time = O(n), where n = number of items in the lib280.tree
     */
    public String toStringByLevel()
    {
        return toStringByLevel(1);
    }


    public static void main(String args[]) {
        AVLTree280<Integer> T = new AVLTree280<>();
        System.out.println("****** test insert() ******");
        System.out.println("Insert in order: 2, 1, 4, 5, 6, 7");
        T.insert(2);
        T.insert(1);
        T.insert(4);
        T.insert(5);
        T.insert(6);
        T.insert(7);

        System.out.println(T.toStringByLevel());
        System.out.println("left subtree height is: "+T.getHeight(T.rootNode.getLeftNode()));
        System.out.println("right subtree height is: "+T.getHeight(T.rootNode.getRightNode()));

        System.out.println("****** test delete() ******");
        T.delete(2);
        System.out.println("After delete 2");
        System.out.println(T.toStringByLevel());
        System.out.println("left subtree height is: "+T.getHeight(T.rootNode.getLeftNode()));
        System.out.println("right subtree height is: "+T.getHeight(T.rootNode.getRightNode()));
        T.delete(5);
        System.out.println("After delete 5");
        System.out.println(T.toStringByLevel());
        System.out.println("left subtree height is: "+T.getHeight(T.rootNode.getLeftNode()));
        System.out.println("right subtree height is: "+T.getHeight(T.rootNode.getRightNode()));
        T.delete(7);
        System.out.println("After delete 7");
        System.out.println(T.toStringByLevel());
        System.out.println("left subtree height is: "+T.getHeight(T.rootNode.getLeftNode()));
        System.out.println("right subtree height is: "+T.getHeight(T.rootNode.getRightNode()));

        System.out.println("******* test has() *******");
        System.out.println("Is 4 in the tree?  "+T.has(4));
        System.out.println("Is 44 in the tree?  "+T.has(44));


        System.out.println("****** insert bunch of items, see if property holds *******");
        T.insert(16);
        T.insert(15);
        T.insert(14);
        T.insert(13);
        T.insert(12);
        T.insert(11);
        T.insert(10);
        T.insert(8);
        T.insert(9);
        System.out.println(T.toStringByLevel());
        System.out.println("left subtree height is: "+T.getHeight(T.rootNode.getLeftNode()));
        System.out.println("right subtree height is: "+T.getHeight(T.rootNode.getRightNode()));


    }
}
