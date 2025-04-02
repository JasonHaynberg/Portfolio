import java.util.*;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void clear() {
        hand.clear();
    }

    public void showHand() {
        for (Card card : hand) {
            System.out.println(card);
        }
    }

    public List<Integer> highestRanks(){
        List<Integer> ranks = new ArrayList<>();
         for (Card card : hand) 
            ranks.add(rankValue(card.getRank()));
        Collections.sort(ranks, Comparator.reverseOrder());
        return ranks;
    }

    // Method to evaluate the hand and return its ranking score
    public String evaluateHand() {
        List<String> ranks = new ArrayList<>();
        List<String> suits = new ArrayList<>();
        for (Card card : hand) {
            ranks.add(card.getRank());
            suits.add(card.getSuit());
        }

        // Count the frequency of each rank
        Map<String, Integer> rankCount = new HashMap<>();
        for (String rank : ranks) {
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }

        // Check for flush (same suit)
        boolean isFlush = new HashSet<>(suits).size() == 1;

        // Check for straight (consecutive ranks)
        Collections.sort(ranks, (r1, r2) -> rankValue(r1) - rankValue(r2));
        boolean isStraight = true;
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (rankValue(ranks.get(i)) != rankValue(ranks.get(i + 1)) - 1) {
                isStraight = false;
                break;
            }
        }

        // Check for each hand type
        if (isFlush && isStraight) {
            if (ranks.contains("ace") && ranks.contains("king")) {
                return "Royal Flush";
            }
            return "Straight Flush";
        }

        // Four of a Kind
        if (rankCount.containsValue(4)) return "Four of a Kind";

        // Full House
        if (rankCount.containsValue(3) && rankCount.containsValue(2)) return "Full House";

        // Flush
        if (isFlush) return "Flush";

        // Straight
        if (isStraight) return "Straight";

        // Three of a Kind
        if (rankCount.containsValue(3)) return "Three of a Kind";

        // Two Pair
        if (rankCount.containsValue(2) && rankCount.size() == 3) return "Two Pair";

        // One Pair
        if (rankCount.containsValue(2)) return "One Pair";

        // High Card
        return "High Card";
    }

    // Convert rank to a numerical value for comparison
    private int rankValue(String rank) {
        switch (rank) {
            case "ace": return 14;
            case "king": return 13;
            case "queen": return 12;
            case "jack": return 11;
            case "10": return 10;
            case "9": return 9;
            case "8": return 8;
            case "7": return 7;
            case "6": return 6;
            case "5": return 5;
            case "4": return 4;
            case "3": return 3;
            case "2": return 2;
            default: return 0;
        }
    }

    // Compare two hands based on their hand types
    public int compareHands(Hand otherHand) {
        // First, get hand types
        String thisHandType = this.evaluateHand();
        String otherHandType = otherHand.evaluateHand();

        // Compare based on hand type hierarchy
        List<String> handRanking = Arrays.asList(
                "High Card", "One Pair", "Two Pair", "Three of a Kind", "Straight", "Flush",
                "Full House", "Four of a Kind", "Straight Flush", "Royal Flush");

        int thisRank = handRanking.indexOf(thisHandType);
        int otherRank = handRanking.indexOf(otherHandType);

        if (thisRank != otherRank) {
            int res= Integer.compare(thisRank, otherRank);
            if(res!=0)
                return res;
            else{
                List<Integer> highest1 = new ArrayList<>();
                List<Integer> highest2 = new ArrayList<>();
                highest1=this.highestRanks();
                highest2=otherHand.highestRanks();

                for(int i=0; i<highest1.size(); i++){
                    res = Integer.compare(highest1.get(i), highest2.get(i));    
                    if(res!=0)
                        return res;
                    
                }
                return 0;

            }
        }

        // If hands are of the same type, compare high card or other tie-breaking criteria
        // Here you could implement more detailed tie-breaking logic, like comparing high cards.
        return 0;
    }
}
