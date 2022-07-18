import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static int AceCounter;
    private static int handvalue;
    private static Card h1;
    private static Card h2;
    private static boolean isDealer;

    public static void main(String[] args) {
        Scanner playersCount = new Scanner(System.in);
        int players;
        Blackjack res = new Blackjack();

        do {
            System.out.print("How many people are playing (1-3)? ");
            players = playersCount.nextInt();
        } while (players > 3 || players <= 0);

        System.out.print("./blackjack: " + players);
        System.out.println("\n Starting game with " + players + " players");
        res.shuffle();

        AceCounter = 0;
        Dealer dealer = new Dealer(res);
        List<Card> hand = new ArrayList<>();
        for(int i=1;i<=players;i++) {

            h1 = res.drawCard();
            h2 = res.drawCard();
            hand.add(h1);
            hand.add(h2);

            System.out.println("Here is the player "+i+"hand" + hand);


        }
            int handvalue = calcHandValue(hand);
            System.out.println("The dealer is showing: ");
            dealer.showFirstCard();

            if (hasBlackJack(handvalue) && dealer.hasBlackJack())//check if both the user and dealer have blackjack.
            {
                Push();
            } else if (hasBlackJack(handvalue))//check if the user has blackjack.
            {
                Win();
            } else if (dealer.hasBlackJack())//check if the dealer has blackjack.
            {
                System.out.println("Here is the dealer's hand:");
                dealer.showHand();
                Lose();
            } else {

                System.out.println("hit or stand?");//ask if the user will hit or stand
                Scanner hitorstand = new Scanner(System.in);
                String hitter = hitorstand.nextLine();
                while (!isHitorStand(hitter)) {
                    System.out.println("Please enter 'hit' or 'stand'.");
                    hitter = hitorstand.nextLine();
                }
                while (hitter.equals("hit"))//hits the user as many times as he or she pleases.
                {
                    Hit(res, hand);
                    System.out.println("Your hand is now:");
                    System.out.println(hand);
                    handvalue = calcHandValue(hand);
                    if (checkBust(handvalue))//checks if the user busted
                    {
                        Lose();
                        break;
                    }
                    if (handvalue <= 21 && hand.size() == 5)//checks for a five card trick.
                    {
                        fivecardtrick();
                        break;
                    }
                    System.out.println("Would you like to hit or stand?");
                    hitter = hitorstand.nextLine();
                }
                if (hitter.equals("stand"))//lets the user stand.
                {
                    int dealerhand = dealer.takeTurn(res);//takes the turn for the dealer.
                    System.out.println("");
                    System.out.println("Here is the dealer's hand:");
                    dealer.showHand();
                    if (dealerhand > 21)//if the dealer busted, user wins.
                    {
                        Win();
                    } else {
                        int you = 21 - handvalue;//check who is closer to 21 and determine winner
                        int deal = 21 - dealerhand;
                        if (you == deal) {
                            Push();
                        }
                        if (you < deal) {
                            Win();
                        }
                        if (deal < you) {
                            Lose();
                        }
                    }
                }
            }

        }

    /*
     * Checks if the user has blackjack.
     */
    public static boolean hasBlackJack(int handValue)
    {
        if(handValue==21)
        {
            return true;
        }
        return false;
    }
    /*
     * Calculates the value of a player's hand.
     */
    public static int calcHandValue(List<Card> hand)
    {
        Card[] aHand = new Card[]{};
        aHand = hand.toArray(aHand);
        int handvalue=0;
        for(int i=0; i<aHand.length; i++)
        {
            handvalue += aHand[i].getValue();
            if(aHand[i].getValue()==11)
            {
                AceCounter++;
            }
            while(AceCounter>0 && handvalue>21)
            {
                handvalue-=10;
                AceCounter--;
            }
        }
        return handvalue;
    }

    public static void Win()
    {
        System.out.println("Congratulations, you win!");
    }

    public static void Lose()
    {
        System.out.println("Sorry, you lose!");

    }
    /*
     * Called if the user pushes
     */
    public static void Push()
    {
        System.out.println("It's a push!");

    }
    /*
     * Adds a card to user's hand and calculates the value of that hand. Aces are taken into account.
     */
    public static void Hit(Blackjack deck, List<Card> hand)
    {
        hand.add(deck.drawCard());
        Card[] aHand = new Card[]{};
        aHand = hand.toArray(aHand);
        handvalue = 0;
        for(int i=0; i<aHand.length; i++)
        {
            handvalue += aHand[i].getValue();
            if(aHand[i].getValue()==11)
            {
                AceCounter++;
            }
            while(AceCounter>0 && handvalue>21)
            {
                handvalue-=10;
                AceCounter--;
            }
        }
    }
    /*
     * Determines if a user has input hit or stand.
     */
    public static boolean isHitorStand(String hitter)
    {
        if(hitter.equals("hit") || hitter.equals("stand"))
        {
            return true;
        }
        return false;
    }
    /*
     * Determines if a user has busted.
     */
    public static boolean checkBust(int handvalue)
    {
        if(handvalue>21)
        {
            System.out.println("You have busted!");
            return true;
        }
        return false;
    }
    /*
     * Determines if a user has input yes or no.
     */
    public static boolean isyesorno(String answer)
    {
        if(answer.equals("yes") || answer.equals("no"))
        {
            return true;
        }
        return false;
    }
    /*
     * Called if the user has a five card trick.
     */
    public static void fivecardtrick()
    {
        System.out.println("You have achieved a five card trick!");
        Win();
    }

    }



