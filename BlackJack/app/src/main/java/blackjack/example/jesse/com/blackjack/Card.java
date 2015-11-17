package blackjack.example.jesse.com.blackjack;

import java.util.Random;

/**
 * Created by jesse on 10/7/2015.
 */
public class Card {

    private int cardValue = 0;

    private String cardSuite = "";

    private String[] suites = {"c", "d", "h", "s"};

    public String aces = "c1d1h1s1";

    Random number = new Random();
    Random suite = new Random();

    public int getCardValue(){ return cardValue; }

    public String getCardSuite(){
        return cardSuite;
    }

    public void createCard(){
        cardValue = number.nextInt(13) + 1;
        cardSuite = suites[suite.nextInt(4)];
    }
}
