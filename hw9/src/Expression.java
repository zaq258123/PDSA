import java.util.ArrayList;
import java.util.List;

public class Expression{

    private Node root;
    public List<String> infixArray = new ArrayList<>();

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        Stack<Node> operator = new Stack<>();
        Stack<Node> value = new Stack<>();
        Character a;
        String num = "", c;
        for (int i = 0; i < infix.length(); i++) {
            a = infix.charAt(i);
            c = a.toString();

            if (c.equals("(")) {
                num = "";
                infixArray.add(c);
            } else if (c.equals(")")) {
                if (!num.equals("")) { value.push(new Node(null,null,num)); infixArray.add(num); }
                infixArray.add(c);
                num = "";
                Node n = operator.pop();
                n.setRight(value.pop());
                n.setLeft(value.pop());
                root = n;
                value.push(n);
            } else if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") ) {
                if (!num.equals("")) { value.push(new Node(null,null,num)); infixArray.add(num); }
                infixArray.add(c);
                num = "";
                operator.push(new Node(null,null,c));
            } else num = num + c;
        } return root;
    }

    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<Node>();
        list.add(root);
        boolean isPrefix = true;
        traversalTree(list,root.getLeft(),isPrefix);
        traversalTree(list,root.getRight(),isPrefix);
        return list.toArray(new Node[list.size()]);
    }

    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();
        List<Node> list = new ArrayList<>();
        boolean isPrefix = false;
        traversalTree(list,root.getLeft(),isPrefix);
        traversalTree(list,root.getRight(),isPrefix);
        list.add(root);
        return list.toArray(new Node[list.size()]);
    }

    private void traversalTree(List<Node> list, Node root,boolean isPrefix) {
        if (root == null) return;
        if (isPrefix) list.add(root);
        traversalTree(list,root.getLeft(),isPrefix);
        traversalTree(list,root.getRight(),isPrefix);
        if (!isPrefix) list.add(root);
    }

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        String[] e = infixArray.toArray(new String[infixArray.size()]);
        Stack<String> operator = new Stack<>();
        Stack<Double> value = new Stack<>();
        for (int i = 0; i < e.length; i++) {
            String s = e[i];
            if (s.equals("(")) ;
            else if (s.equals("+")) operator.push(s);
            else if (s.equals("-")) operator.push(s);
            else if (s.equals("*")) operator.push(s);
            else if (s.equals("/")) operator.push(s);
            else if (s.equals(")"))
            {
                String op = operator.pop();
                if (op.equals("+")) value.push(value.pop() + value.pop());
                else if (op.equals("-")) value.push(- value.pop() + value.pop());
                else if (op.equals("*")) value.push(value.pop() * value.pop());
                else if (op.equals("/")) {
                    Double a = value.pop();
                    Double b = value.pop();
                    value.push(b/a);
                }
            }
            else value.push(Double.parseDouble(s));
        }
        return value.pop();
    }
}