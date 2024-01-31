import java.util.Scanner;

public class Main {
    public static void printMessage() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Welcome to Mr. Jackie's Blackjack!!");
        System.out.println();
        System.out.println("The rules are simple,\n  ->the one with the sum of their cards closest to 21 without going over it wins!");
        System.out.println("  ->If player card's sum goes over 21, he/she losses irrespective of the hand of Mr. Jackie and so is the same for Mr. Jackie!");
        System.out.println("  ->Mr. Jackie will continue to draw cards until his cards add upto 17.");
        System.out.println("  ->hit = get another card, stand = hold your hand and reveal Mr. Jackie's cards.");
        System.out.println("  ->If a player gets an Ace, he/she will have an option to set the value of that card to 11 or 1, if");
        System.out.println("    the sum of your cards exceed with the value of ace as 11 then choose 1 and try your luck:)");
        System.out.println("Good Luck!");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Play play = new Play();
        String choice = "";
        
        printMessage();
        do {
            if (choice.equals("yes"))
                System.out.println("\nOk, Good luck again!");

            play.game();

            do {
                System.out.print("Do you want to play another hand[Yes/No]: ");
                choice = scanner.nextLine();
                choice = choice.toLowerCase();
            } while (!(choice.equals("yes")) && !(choice.equals("no")));
        } while (choice.equals("yes"));
        
        System.out.println("Thank you for playing Blackjack with Mr. Jackie!");
        scanner.close();
    }
}