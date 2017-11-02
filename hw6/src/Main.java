import java.util.*;

/**
 * Created by Yang Chi-Chang on 2016/4/26.
 */
public class Main {

    public static void main(String[] args) {

        int randomTimes = 20;
        boolean printSortedCase = false;
        boolean printReverseCase = false;


        CardType[] types = {CardType.full_house , CardType.flush , CardType.straight , CardType.two_pair , CardType.one_pair , CardType.high_card};
        Card[] cards1;
        Card[] cards2;
        Player player1;
        Player player2;

        for (int i = 0; i < randomTimes; i++) {
            cards1 = deal();
            cards2 = deal();
            player1 = new Player("player1");
            player2 = new Player("player2");
            player1.setCards(cards1);
            player2.setCards(cards2);
            printResult(player1 , player2 , cards1 , cards2);
        }

        if(printSortedCase){
            for (int i = 0 ; i < types.length-1 ; i++){
                cards1 = deal(types[i]);
                cards2 = deal(types[i+1]);
                player1 = new Player("player1");
                player2 = new Player("player2");
                player1.setCards(cards1);
                player2.setCards(cards2);
                printResult(player1 , player2 , cards1 , cards2);
            }
        }

        if (printReverseCase){
            for (int i = types.length-1 ; i > 0 ; i--){
                cards1 = deal(types[i]);
                cards2 = deal(types[i-1]);
                player1 = new Player("player1");
                player2 = new Player("player2");
                player1.setCards(cards1);
                player2.setCards(cards2);
                printResult(player1 , player2 , cards1 , cards2);
            }
        }

    }

    enum CardType {
        full_house , flush , straight , two_pair , one_pair , high_card
    }


    public static Card[] deal(CardType cardType){
        Card[] cards = deal();
        while (new CardQuery(cards).getCardType() != cardType){
            cards = deal();
        }
        return cards;
    }

    public static Card[] deal(){
        String[] faces = {"A","K","Q","J","10","9","8","7","6","5","4","3","2"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (list.size() < 10){
            int i = (int)(Math.random()*52);
            if(!list.contains(i)) list.add(i);
        }
        Card[] cards = new Card[5];
        for(int i = 0 ; i < 5 ; i ++){
            int n = list.get(i);
            int suit = n/13;
            int face = n - (n/13)*13;
            Card c = new Card(faces[face] , suits[suit]);
            cards[i] = c;
        }
        return cards;
    }

    public static String toString(Card[] cards){
        String temp = "";
        String sp = "";
        for (Card c:cards){
            temp += sp + c.getFace()+"("+c.getSuit().substring(0,1) +")";
            sp = ",";
        }
        return temp;
    }

    public static void printResult(Player player1 , Player player2 , Card[] cards1 , Card[] cards2){
        System.out.println("S = Spades ,  H = Hearts , D = Diamonds , C = Clubs");
        Arrays.sort(cards1);
        Arrays.sort(cards2);
        System.out.println(player1.getName() + " : " + toString(cards1) + "\t" + new CardQuery(cards1).getCardType().toString());
        System.out.println(player2.getName() + " : " + toString(cards2) + "\t" + new CardQuery(cards2).getCardType().toString());
        System.out.println(player1.getName() + ".compareTo(" + player2.getName()+") = " + player1.compareTo(player2));
        System.out.println("========================");
    }

    private static class CardQuery {

        Card[] cards;

        CardQuery (Card[] cards){
            this.cards = cards;
        }

        public CardType getCardType(){
            Map<String,Integer> faces = getFaceCount();
            if (faces.size() == 2) {
                // (4,1) or (3,2)
                if (getThrees().size() != 0) {
                    return CardType.full_house;
                }
                return CardType.two_pair;
            } else if (faces.size() == 3){
                // (3,1,1) or (2,2,1)
                if (getPairs().size() != 0) {
                    return CardType.two_pair;
                }
                return CardType.one_pair;
            } else if (faces.size() == 4){
                // (2,1,1,1)
                return CardType.one_pair;
            } else if (faces.size() == 5){
                // check for flush
                if (getSuitSet().size() == 1) {
                    return CardType.flush;
                }
                // check for straight
                String[] straights = {"A","K","Q","J","10","9","8","7","6","5","4","3","2","A"};
                Set<String> faceSet = faces.keySet();
                for(int i = 0; i < straights.length - 5 ; i++){
                    Set<String> straightOne = new HashSet<String>();
                    for(int j = i ; j < i + 5 ; j++){
                        straightOne.add(straights[j]);
                    }
                    if (faceSet.containsAll(straightOne)) {
                        return CardType.straight;
                    }
                }
                // high card otherwise
                return CardType.high_card;
            }
            return CardType.high_card;
        }

        private Map<String , Integer> getFaceCount(){
            Map<String,Integer> faces = new HashMap<String,Integer>();
            for(Card c:cards){
                if (faces.containsKey(c.getFace()))
                    faces.put(c.getFace() , faces.get(c.getFace())+1);
                else
                    faces.put(c.getFace(), 1);
            }
            return faces;
        }

        private Set<String> getSuitSet(){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        private Set<Card> getPairs(){
            Set<Card> pairs = new HashSet<Card>();
            Map<String,Integer> faces = getFaceCount();
            for (Card c:cards){
                if (faces.get(c.getFace()) == 2)
                    pairs.add(c);
            }
            return pairs;
        }

        private Set<Card> getThrees(){
            Set<Card> threes = new HashSet<Card>();
            Map<String,Integer> faces = getFaceCount();
            for (Card c:cards){
                if (faces.get(c.getFace()) == 3)
                    threes.add(c);
            }
            return threes;
        }
    }


}
