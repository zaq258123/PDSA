import java.util.Comparator;

public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    public static final Comparator<Card> FACE_ORDER = new FaceOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
    }
     
    // DO NOT MODIFY THIS   
    public String getFace(){
        return this.face;
    }
    
    // DO NOT MODIFY THIS    
    public String getSuit(){
        return this.suit;
    }   
    
    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        int ans = FACE_ORDER.compare(this,that);
        if (ans == 1 || ans ==-1) return ans;
        else return  SUIT_ORDER.compare(this,that);
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            String[] suitArray = {"Spades", "Hearts", "Diamonds", "Clubs"};
            int suitC1 = 0;
            int suitC2 = 0;
            for (int i = 0; i < 4; i++) {
                if (c1.getSuit().equals(suitArray[i])) suitC1 = i;
                if (c2.getSuit().equals(suitArray[i])) suitC2 = i;
            }
            if (suitC1 > suitC2) return -1; // c1 < c2
            else if (suitC1 < suitC2) return 1; // c1 > c2
            else return 0;
        }
    }

    private static class FaceOrder implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the face
            String[] faceArray = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
            int faceC1 = 0;
            int faceC2 = 0;
            for (int i = 0; i < 13; i++) {
                if (c1.getFace().equals(faceArray[i])) faceC1 = i+1;
                if (c2.getFace().equals(faceArray[i])) faceC2 = i+1;
            }
            if (faceC1 > faceC2) return -1; // c1 < c2
            else if (faceC1 < faceC2) return 1; // c1 > c2
            else return 0;
        }
    }
}

