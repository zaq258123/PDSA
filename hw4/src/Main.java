import java.util.Iterator;

/**
 * Created by Cavitation on 2016/3/26.
 * Created by Cavitation on 2016/3/26.
 */
public class Main {
    public static void main(String[] args) {
        Deque<String> test = new Deque<>();
//        for (int i = 0; i < 8; i++) {
//            test.addFirst(i+"");
//        }

        for (int i = 0; i < 8; i++) {
            test.addLast(i+"");
        }


        Iterator<String> printItem = test.iterator();
        while(printItem.hasNext())
            System.out.println(printItem.next());
    }
}
