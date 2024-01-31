import java.util.Stack;
import java.util.Random;
// import java.util.concurrent.TimeUnit;

public class Play {
    private int i;
    private int playerSum = 0;
    private int compSum = 0;
    private int totalCards = 52;
    private int top = -1;
    private int[] deck = new int[52];
    private int[] playerCards = new int[5];
    private int[] compCards = new int[5];
    private Stack<Integer> stackOfCards = new Stack<>();

    private void shuffleCards() {
        int temp;
        int[] tempDeck = new int[52];

        for(int i = 0; i < 52; i++) 
            tempDeck[i] = (((i / 13 + 3) * 100) + (i % 13) + 1);

        Random random = new Random();
        for(int i = 0; i < 52; i++) {
            do {
                temp = random.nextInt(52) + 1;
            } while(tempDeck[temp] == 0);

            deck[i] = tempDeck[temp];   
            tempDeck[temp] = 0;
        }
    }

    private int convertFaceCards(int val) {
        if((val % 100 == 11) || (val % 100 == 12) || (val % 100 == 13))
            return ((val / 100) * 100 + 10);
        else
            return val;
    }

    private void printCard(int card) {
        char suit = Character.forDigit(card / 100, 10);
        int value = card % 100;

        String cardPattern1 = "*************\n";
        String cardPattern2 = "*           *\n";
        
        switch (value) {
            case 1:
                System.out.format("%s", cardPattern1);
                System.out.format("* A         *\n");;
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         A *\n");
                System.out.format("%s", cardPattern1);
                break;
                
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.format("%s", cardPattern1);
                System.out.format("*%2d         *\n", value);;
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         %2d*\n", value);
                System.out.format("%s", cardPattern1);
                break;
            case 11:
                System.out.format("%s", cardPattern1);
                System.out.format("* J         *\n");;
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         J *\n");
                System.out.format("%s", cardPattern1);
                break;
            case 12:
                System.out.format("%s", cardPattern1);
                System.out.format("* Q         *\n");;
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         Q *\n");
                System.out.format("%s", cardPattern1);
                break;
            case 13:
                System.out.format("%s", cardPattern1);
                System.out.format("* K         *\n");;
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         K *\n");
                System.out.format("%s", cardPattern1);
                break;
        }
        System.out.println();
    }

    public void game() {
        shuffleCards();

        for(int i = 0; i < 52; i++) 
            stackOfCards.push(deck[i]);

        playerCards[0] = stackOfCards.pop();
        playerCards[1] = stackOfCards.pop();
        compCards[0] = stackOfCards.pop();
        compCards[1] = stackOfCards.pop();

        printCard(playerCards[0]);
        printCard(playerCards[1]);
        printCard(compCards[0]);
        printCard(compCards[1]);
    }
}