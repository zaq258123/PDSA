import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {
    int N;
    Hand[] pq;

    HandPQ (){
        N = 0;
        pq = new Hand[10];
    }

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split(",");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> pq = new MinPQ<Hand>();
            for (int line = 0 ; line < count ; line++ ){
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split(",");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split("_");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand = new Hand(cardsArray);
                pq.insert(hand);
                if (pq.size() > target){
                    pq.delMin();
                }
            }
            Card[] targetCard = pq.delMin().getCards();
            Arrays.sort(targetCard);
            String ans = "";
            for (int i = 0; i < 4; i++) {
                ans += targetCard[i].getSuit() + "_" + targetCard[i].getFace() + ",";
            }
            ans += targetCard[4].getSuit() + "_" + targetCard[4].getFace();
            System.out.println(ans);
        }
    }
}
