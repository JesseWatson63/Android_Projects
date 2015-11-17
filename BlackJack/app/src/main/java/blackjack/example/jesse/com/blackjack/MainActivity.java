package blackjack.example.jesse.com.blackjack;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ImageButton hitButton;
    ImageButton dealButton;
    ImageButton stayButton;
    ImageButton chip20;
    ImageButton chip10;
    ImageButton chip4;
    ImageButton chip1;
    ImageView resultView;
    LinearLayout queryHumanTable;
    LinearLayout queryCompTable;
    Card card = new Card();
    BlackJack game = new BlackJack();
    TextView playerChipAmount;
    TextView betChipAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        hitButton = (ImageButton) findViewById(R.id.gameHitMeButton);
        dealButton = (ImageButton) findViewById(R.id.gameDealButton);
        stayButton = (ImageButton) findViewById(R.id.stayButton);
        chip20 = (ImageButton) findViewById(R.id.gameChip20);
        chip10 = (ImageButton) findViewById(R.id.gameChip10);
        chip4 = (ImageButton) findViewById(R.id.gameChip4);
        chip1 = (ImageButton) findViewById(R.id.gameChip1);

        playerChipAmount = (TextView) findViewById(R.id.playerChipAmount);
        betChipAmount = (TextView) findViewById(R.id.playerBetView);
        queryHumanTable = (LinearLayout) findViewById(R.id.queryHumanTableLayout);
        queryCompTable = (LinearLayout) findViewById(R.id.queryCompTableLayout);
        resultView = (ImageView) findViewById(R.id.resultView);
        game.dealer.setPlayerName("dealer");
        game.player1.setPlayerName("player1");


        chip20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBet(20);
            }
        });
        chip10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBet(10);
            }
        });
        chip4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBet(4);
            }
        });
        chip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBet(1);
            }

            });

        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (game.player1.playerHand.size() != 0) {
                game.hit(game.player1);
                createHumanCard(game.player1.playerHand);
                if (game.player1.getPlayerBust()) {
                    resultView.setBackgroundResource(R.drawable.busted);
                }
            }
            }
        });
//-----------------------dealButotn
        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                queryHumanTable.removeAllViews();
                queryCompTable.removeAllViews();

                resultView.setBackground(null);
                game.deal();

                createHumanCard(game.player1.playerHand);
                createCompCard(game.dealer.playerHand);
            }
        });
//--------------------------stayButton
        stayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(game.player1.playerHand.size() != 0){
                    if(!game.player1.getPlayerBust()){
                        game.stay();
                        if(game.player1.getPlayerWins()){
                            resultView.setBackgroundResource(R.drawable.winner);
                        }else{
                            resultView.setBackgroundResource(R.drawable.tryagain);
                        }
                        queryCompTable.removeAllViews();
                        createCompCardFace(game.dealer.playerHand);
                    }else {
                        resultView.setBackgroundResource(R.drawable.busted);
                    }
                }
                betChipAmount.setText("");

            }

        });
    }

    private void makeBet(int betAmount){
        game.bet(betAmount);
        playerChipAmount.setText(String.valueOf(game.player1.getPlayerChips()).toString());
        betChipAmount.setText(String.valueOf(game.betAmount).toString());
    }
    private void createHumanCard(ArrayList playerHand) {
        /*For creating Human card and setting the image.
            this take in a string that is in 1letter adn 1number string format.*/

        card.createCard();

        queryHumanTable.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        int handSize = playerHand.size();

        for (int i = 0; i < handSize; i++) {

            View newCard = inflater.inflate(R.layout.card, null);

            ImageView card = (ImageView) newCard.findViewById(R.id.newCard);

            int id = getResources().getIdentifier(playerHand.get(i).toString(), "drawable", this.getPackageName());

            card.setImageResource(id);

            queryHumanTable.addView(card);

        }
    }

    private void createCompCard(ArrayList playerHand){
        /*For creating Human card and setting the image.
            this take in a string that is in 1letter adn 1number string format.*/

        card.createCard();

        queryCompTable.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        int handSize = playerHand.size();

        for(int i = 0; i < handSize; i++){

            View newCard = inflater.inflate(R.layout.card, null);

            ImageView card = (ImageView) newCard.findViewById(R.id.newCard);

            int id = getResources().getIdentifier(playerHand.get(i).toString(), "drawable", this.getPackageName());

            if(i == 0){
                card.setImageResource(id);
            }else{
                card.setBackgroundResource(R.drawable.cardback);
            }

            queryCompTable.addView(card);

        }

    }

    private void createCompCardFace(ArrayList playerHand) {
        /*For creating Human card and setting the image.
            this take in a string that is in 1letter adn 1number string format.*/

        card.createCard();

        queryCompTable.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        int handSize = playerHand.size();

        for (int i = 0; i < handSize; i++) {

            View newCard = inflater.inflate(R.layout.card, null);

            ImageView card = (ImageView) newCard.findViewById(R.id.newCard);

            int id = getResources().getIdentifier(playerHand.get(i).toString(), "drawable", this.getPackageName());

            card.setImageResource(id);

            queryCompTable.addView(card);

        } }



}
