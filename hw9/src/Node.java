public class Node{
    private Node left;
    private Node right;
    private String value;

    public Node(Node left, Node right, String value){
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node getLeft(){
        return this.left;
    }

    public Node getRight(){
        return this.right;
    }

    public String getValue(){
        return this.value;
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public void setValue(String value){
        this.value = value;
    }

}