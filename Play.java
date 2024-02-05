import java.util.Stack;
import java.util.Random;
import java.util.Scanner;

public class Play {
    private int i;
    private int playerSum;
    private int compSum;
    private int[] deck = new int[52];
    private int[] playerCards = new int[5];
    private int[] compCards = new int[5];
    private Stack<Integer> stackOfCards = new Stack<>();
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public Play() {
        playerSum = 0;
        compSum = 0;
    }

    public void closeScanner() {
        scanner.close();
    }

    private void shuffleCards() {
        int temp;

        for (int i = 0; i < 52; i++)
            deck[i] = (((i / 13 + 3) * 100) + (i % 13) + 1);
        
        // for(int i = 0; i < 52; i++)
        //     System.out.println(tempDeck[i]);
         
        for (int i = 0; i < 52; i++)
        {
            int r = i + random.nextInt(52 - i);
             
            temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp; 
        }
    }

    private int convertFaceCards(int val) {
        if ((val % 100 == 11) || (val % 100 == 12) || (val % 100 == 13))
            return ((val / 100) * 100 + 10);
        else
            return val;
    }

    private void printCard(int card) {
        char suit;
        int cardValue = card % 100;
        int cardSuit = card / 100;

        switch (cardSuit) {
            case 3: suit = 'H'; break; // Hearts
            case 4: suit = 'D'; break; // Diamonds
            case 5: suit = 'C'; break; // Clubs
            case 6: suit = 'S'; break; // Spades
            default: suit = '?';
        }

        String cardPattern1 = "*************\n";
        String cardPattern2 = "*           *\n";

        switch (cardValue) {
            case 1: {
                System.out.format("%s", cardPattern1);
                System.out.format("* A         *\n");
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         A *\n");
                System.out.format("%s", cardPattern1);
                break;
            }
            case 11: {
                System.out.format("%s", cardPattern1);
                System.out.format("* J         *\n");
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         J *\n");
                System.out.format("%s", cardPattern1);
                break;
            }
            case 12: {
                System.out.format("%s", cardPattern1);
                System.out.format("* Q         *\n");
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         Q *\n");
                System.out.format("%s", cardPattern1);
                break;
            }
            case 13: {
                System.out.format("%s", cardPattern1);
                System.out.format("* K         *\n");
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         K *\n");
                System.out.format("%s", cardPattern1);
                break;
            }
            default: {
                System.out.format("%s", cardPattern1);
                System.out.format("*%2d         *\n", cardValue);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*     %c     *\n", suit);
                System.out.format("%s", cardPattern2);
                System.out.format("%s", cardPattern2);
                System.out.format("*         %2d*\n", cardValue);
                System.out.format("%s", cardPattern1);
                break;
            }
        }
        System.out.println();
    }

    public char chooseAceValue(int j) {
        char n;
        System.out.print("->Choose the value of Ace for card " + (j + 1) + ", (Enter a for 11 and b for 1): ");
        do {
            n = scanner.next().charAt(0);
        } while (n != 'a' && n != 'b');
        return n;
    }
    public void game() {
        // String choice = "";
        char n;
        shuffleCards();

        // pushing the cards from an array to a stack
        for(int i = 0; i < 52; i++) {
            stackOfCards.push(deck[i]);
        }

        playerCards[0] = stackOfCards.pop();
        playerCards[1] = stackOfCards.pop();
        compCards[0] = stackOfCards.pop();
        compCards[1] = stackOfCards.pop();

        System.out.println();
        System.out.println("~Mr. Jackie's 1st card:");
        printCard(compCards[0]);
        System.out.println();
        System.out.println("~Your cards:");
        printCard(playerCards[0]);
        printCard(playerCards[1]);
        
        // playerCards[0] = 301;
        
        for(int i = 0; i < 2; i++) {
            if(playerCards[i] % 100 == 1) {
                n = chooseAceValue(i);
                // System.out.println("success");

                if(n == 'a') {
                    System.out.println(" >-> You've chosen the value of Ace as 11.");
                    playerSum += 11;
                } else  {
                    System.out.println(" >-> You've chosen the value of Ace as 1.");
                    playerSum += 1;
                }
            } else if (convertFaceCards(playerCards[i]) % 100 == 10) {
                playerSum += 10;
            } else {
                playerSum += playerCards[i] % 100;
            }
            
            if(playerSum > 21) {
                System.out.println("~Mr. Jackie's cards:");
                printCard(compCards[0]);
                printCard(compCards[1]);
                System.out.println(" >-> Mr. Jackie WINS!! because the sum of your cards is over 21!");
                return;
            }
        }

        System.out.println(" >-> SUM OF YOUR CARDS CURRENTLY is " + playerSum);

        if(playerSum == 21) {
            System.out.println("~Mr. Jackie's cards:");
            printCard(compCards[0]);
            printCard(compCards[1]);

            if((((convertFaceCards(compCards[0])) % 100 == 1) && ((convertFaceCards(compCards[1])) % 100 == 10)) || (((convertFaceCards(compCards[0])) % 100 == 10) && ((convertFaceCards(compCards[1])) % 100 == 1))) {
                System.out.println(" >-> Push! its a draw, you and Mr. Jackie both have a Blackjack");
                return;
            } else {
                System.out.println(" >-> You WIN!! Its a blackjack!");
                return;
            }
        }

        for(int i = 0; i < 3; i++) {
            System.out.print("->Do you want to HIT or STAND?(Enter h to hit and s to stand): ");
            do {
                n = scanner.next().charAt(0);
            } while (n != 'h' && n != 's');

            if(n == 'h') {
                playerCards[i + 2] = stackOfCards.pop();
                System.out.println("Your card " + (i + 3) + " is:");
                printCard(playerCards[i + 2]);
                // System.out.println(playerCards[i + 2]);
                if(playerCards[i + 2] % 100 == 1) {
                    n = chooseAceValue(i + 2);
    
                    if(n == 'a') {
                        System.out.println(" >-> You've chosen the value of Ace as 11.");
                        playerSum += 11;
                    } else  {
                        System.out.println(" >-> You've chosen the value of Ace as 1.");
                        playerSum += 1;
                    }
                } else if (convertFaceCards(playerCards[i + 2]) % 100 == 10) {
                    playerSum += 10;
                } else {
                    playerSum += playerCards[i + 2] % 100;
                }
                System.out.println(" >-> SUM OF YOUR CARDS CURRENTLY is " + playerSum);
                
                if(playerSum > 21) {
                    System.out.println("~Mr. Jackie's cards:");
                    printCard(compCards[0]);
                    printCard(compCards[1]);
                    System.out.println(" >-> Mr. Jackie WINS!! because the sum of your cards is over 21!");
                    return;
                }
                
                if(playerSum == 21) {
                    break;
                }
            } else {
                System.out.println(" >-> FINAL SUM OF YOUR CARDS is " + playerSum);
                break;
            }
        }

        if(i == 3 && playerSum <= 21) {
            System.out.println(" >-> You WIN!! because the sum of your 5 cards is no larger than 21! you're quite lucky!!");
            return;
        }

        System.out.println("~Mr. Jackie's first 2 cards:");
        printCard(compCards[0]);
        printCard(compCards[1]);
    
        if(compCards[0] % 100 + compCards[1] % 100 == 2) {
            compSum = 12;
            System.out.println(" >-> Sum of Mr. Jackie's cards currently is " + compSum);
        } else if((((convertFaceCards(compCards[0])) % 100 == 1) && ((convertFaceCards(compCards[1])) % 100 == 10)) || (((convertFaceCards(compCards[0])) % 100 == 10) && ((convertFaceCards(compCards[1])) % 100 == 1))) {
            compSum = 21;
            System.out.println(" >-> Sum of Mr. Jackie's cards currently is " + compSum);
            System.out.println(" >-> Mr. Jackie WINS as he has a BLACKJACK! better luck next time!");
            return;
        } else if(compCards[0] % 100 == 1 || compCards[0] % 100 == 1) {
            compSum = (compCards[0] % 100) + (compCards[1] % 100) + (random.nextInt(2) * 10);
        } else {
            compSum = (convertFaceCards(compCards[0]) % 100) + convertFaceCards(compCards[1] % 100);
        }
        System.out.println(" >-> Sum of Mr. Jackie's cards currently is " + compSum);
        
        for(int i = 0; i < 3 && compSum < 17; i++) {
            compCards[i + 2] = stackOfCards.pop();
            System.out.println(" >-> Mr. Jackie's card " + (i + 3) + " is:");
            printCard(compCards[i + 2]);
            
            if(compCards[i + 2] % 100 == 1) {
                if((compSum + 11) <= 21) {
                    compSum += 11;
                    System.out.println(" >-> Mr. Jackie chosen the value of Ace as 11.");
                    System.out.println(" >-> Sum of Mr. Jackie's cards currently is " + compSum);
                } else {
                    compSum += 1;
                    System.out.println(" >-> Mr. Jackie chosen the value of Ace as 1.");
                    System.out.println(" >-> Sum of Mr. Jackie's cards currently is " + compSum);
                }
            } else {
                compSum += convertFaceCards(compCards[i + 2]) % 100;
                System.out.println(" >-> Sum of Mr. Jackie's cards currently is " + compSum);
            }

            if(i == 3 && compSum <= 21) {
                System.out.println(" >-> Mr. Jackie WINS because the sum of his 5 cards is no larger than 21! He is quite lucky!!");
                return;
            }

        }
        if(compSum > 21 || playerSum > compSum) {
            System.out.println(" >-> You WIN!!");
            return;
        } else if(playerSum == compSum) {
            System.out.println(" >-> Push! its a draw, yours and Mr. Jackie's card add up to the same!");
            return;
        } else {
            System.out.println(" >-> Mr. Jackie WINS!! better luck next time!");
            return;
        }
    }
}
