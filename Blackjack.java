import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {

    private Scanner playersCount= new Scanner(System.in);
    private ArrayList<Card> deck;

    private int players;
public void initialize() {
    System.out.print("Hello");

//do {
//        System.out.print("How many people are playing (1-3)? ");
//        players = playersCount.nextInt();
//
//
//    } while (players > 3 || players <=0);
//
//    System.out.print("./blackjack: "+players);
//    System.out.println("\n Starting game with " + players +" players");
}
    Blackjack()
    {
         deck = new ArrayList<Card>();
        for(int i=0; i<4; i++)
        {
            for(int j=1; j<=13; j++)
            {
                deck.add(new Card(i,j));
            }
        }
    }
public void shuffle(){

    Random random = new Random();
    Card temp;
    for(int i=0; i<200; i++)
    {
        int index1 = random.nextInt(deck.size()-1);
        int index2 = random.nextInt(deck.size()-1);
        temp = deck.get(index2);
        deck.set(index2, deck.get(index1));
        deck.set(index1, temp);
    }

}
    public Card drawCard()
    {

        return deck.remove(0);

    }

}
