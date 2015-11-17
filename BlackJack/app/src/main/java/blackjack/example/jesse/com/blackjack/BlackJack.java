package blackjack.example.jesse.com.blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by jesse on 10/8/2015.
 */
public class BlackJack {
    Card card = new Card();

    Player dealer = new Player();

    Player player1 = new Player();

    ArrayList<String> deck = new ArrayList<>();

    boolean delt = false;
    int betAmount = 0;

    public void deal(){
        //Reset player and dealer.  Deal is new game every time.
        player1.resetPlayer();
        dealer.resetPlayer();
        //Deal two cards each.
        delt = true;
        for(int i = 2; i > 0; i--){
            hit(player1);
            hit(dealer);
        }

    }

    public void hit(Player player){
        //do nothing if game is over.
        if(player.getPlayerBust() == false && player.getPlayerWins() == false){
            card.createCard();
            //convert the card value to string for use with getting image names.
            String thisCard = card.getCardSuite() + String.valueOf(card.getCardValue());
            //Checks if card exists in deck and uses recursion if it is not in deck to call function again
            if(deck.contains(thisCard)){
                if(deck.size() >= 52){
                    deck.clear();
                }
                hit(player);
            }else//Check if the dealer is hitting.
            if(player.getPlayerName() == "dealer"){
                player.playerHand.add(thisCard);
            }
            else//else its the player hitting.
            {
                player.playerHand.add(thisCard);
            }
            //add the card to the deck.
            deck.add(thisCard);
            setPlayerScore(player);
        }
    }

    private void setPlayerScore(Player player){
            player.setPlayerScore(0);
            for(int i = 0; i < player.playerHand.size(); i++){
                if(player.getPlayerScore() == 21){
                    player.setPlayerWins(true);
                    return;
                }
                int cardValue =
                        Integer.parseInt(player.playerHand.get(i).substring(1));
                //If the card has a value in between this it gets 10
                if(cardValue <= 13 && cardValue >= 11){
                    player.setPlayerScore(player.getPlayerScore() + 10);
                }
                //Check if its not an ace and add the card value.
                else if (cardValue != 1){
                    player.setPlayerScore(player.getPlayerScore() + cardValue);
                }
                //Check if its an ace.
                else if(cardValue == 1){
                    //add 11 to the score if it is an ace.
                    player.setPlayerScore(player.getPlayerScore() + 11);
                    //if score is over 21 with ace, subtract 10.
                    if(player.getPlayerScore() > 21){
                        player.setPlayerScore(player.getPlayerScore() - 10);
                    }
                }
                checkForBust(player);
            }

    }

    //if player score is over 21 they bust.  Set player bust to true.
    public void checkForBust(Player player){
        if(player.getPlayerScore() > 21 ) {
            player.setPlayerBust(true);
            delt = false;
        }
    }

    public void stay(){
        if(player1.getPlayerBust() == false){
            //set dealer score so far.
            setPlayerScore(dealer);
            //setting them to ints because it will cut down on computations.
            int dealerScore =  dealer.getPlayerScore();
            int playerScore =  player1.getPlayerScore();

            if (dealerScore <= playerScore) {
                hit(dealer);
                stay();
            } else if (dealer.getPlayerScore() > 21) {
                player1.setPlayerWins(true);
                player1.setPlayerChips(player1.getPlayerChips() + (betAmount * 3));
            } else if (dealerScore > playerScore && playerScore < 21) {
                dealer.setPlayerWins(true);
            }
            betAmount = 0;
        }
        delt = false;
    }

    public void bet(int chips){
        if(delt == false){
            player1.setPlayerChips(player1.getPlayerChips() - chips);
            betAmount += chips;
        }
    }
}
