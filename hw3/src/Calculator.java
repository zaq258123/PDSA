/**
 * Created by ASUS on 2016/3/21.
 */
public class Calculator {
    public Double ans(String e) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        String[] input = e.split(" ");
        for (int i = 0; i <input.length; i++) {
            String s = input[i];
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals(")"))
            {
                String op = ops.pop();
                if (op.equals("+")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals("-")) {
                    vals.push(- vals.pop() + vals.pop());
                } else if (op.equals("*")) {
                    vals.push(vals.pop() * vals.pop());
                } else if (op.equals("/")) {
                    Double a = vals.pop();
                    Double b = vals.pop();
                    vals.push(b/a);
                }
            }
            else vals.push(Double.parseDouble(s));
        }
        return vals.pop();
    }
}
