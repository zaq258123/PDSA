/**
 * Created by ASUS on 2016/5/16.
 */
public class Main {
    public static void main(String[] args) {
        String infix1 = "((441*3)+((((4*2)/2)/3)+((2+1)*3)))";
        String infix2 = "(4+(((4*2)/2)/3))";
        String infix3 = "((4.4-3)+((((4*2)/2)/3)+((2+1)*3)))";
        Expression e = new Expression();
        Node n = e.Infix2BT(infix2);
        for (int i = 0; i < e.infixArray.size(); i++) System.out.println(e.infixArray.get(i));
        Node[] nArray = e.PrintPostfix();
        for (int i = 0; i < nArray.length; i++) System.out.println(nArray[i].getValue());
        System.out.println(e.Evaluation());
    }
}
