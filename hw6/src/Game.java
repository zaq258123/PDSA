import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;

public class Game{
    
    // Judge System will Execute The Program Here
    public static void main(String[] args) throws Exception{

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split(",");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split("_");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                player.setCards(cardsArray);
            }
//                for (int k = 0; k < playerCount; k++) {
//                    int[] a = playerArray[k]
//                    int[] b = playerArray[k]
//                    for (int i = 0; i < 5; i++) {
//                        System.out.println("suit : " + a[i] + "/ face : " + b[i]);
//                    }
//                    System.out.println(playerArray[k].isOnePair());
//                }
//            System.out.println(playerArray[1].getTwoPair().getFace() + "/" + playerArray[1].getTwoPair().getSuit());

//            Card[] cardCopy = new Card[5];
//            for (int i = 0; i < 5; i++)
//                cardCopy[i] = playerArray[4].cards[i];

//            Arrays.sort(cardCopy);
//            for (int i = 0; i < 5; i++) {
//                System.out.println(playerArray[4].cards[i].getFace() + "/" +cardCopy[i].getFace());
//            }
//            System.out.println(playerArray[playerCount - 1].getName());
        }
    }
}
