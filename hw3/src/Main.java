import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by ASUS on 2016/3/21.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            Calculator test = new Calculator();
            double d = test.ans(data);
            System.out.println(d);
        }
    }
}
