package example.jesse.com.rockpaperscissors;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    FrameLayout cmpMainContainer;
    FrameLayout cmpImageContainer;
    FrameLayout userMainContainer;
    FrameLayout userImageContainer;
    ImageButton userButtonRock;
    ImageButton userButtonPaper;
    ImageButton userButtonScissors;
    ImageButton resetButton;
    TextView userScore;
    TextView compScore;
    TableLayout queryTableLayout;

    Game thisGame = new Game();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmpMainContainer    = (FrameLayout)findViewById(R.id.cmp_main_container);
        cmpImageContainer   = (FrameLayout) findViewById(R.id.cmp_image);
        userMainContainer   = (FrameLayout) findViewById(R.id.user_main_container);
        userImageContainer  = (FrameLayout) findViewById(R.id.user_image);
        userButtonRock      = (ImageButton) findViewById(R.id.user_button_rock);
        userButtonPaper     = (ImageButton) findViewById(R.id.user_button_paper);
        userButtonScissors  = (ImageButton) findViewById(R.id.user_button_scissors);
        resetButton         = (ImageButton) findViewById(R.id.resetButton);
        userScore           = (TextView) findViewById(R.id.userscore);
        compScore           = (TextView) findViewById(R.id.cmpscore);
        queryTableLayout    = (TableLayout) findViewById(R.id.queryTableLayout);

        userButtonRock.setOnClickListener(customOnClickRock);
        userButtonPaper.setOnClickListener(customOnClickPaper);
        userButtonScissors.setOnClickListener(customOnClickScissors);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });
    }


    private View.OnClickListener customOnClickRock = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int gameReturnedResult = thisGame.shoot("rock");
            if(gameReturnedResult == 1){
                cmpMainContainer.setBackgroundResource(R.drawable.redring);
                cmpImageContainer.setBackgroundResource(R.drawable.enemyscissors);
                userMainContainer.setBackgroundResource((R.drawable.bluering));
                userImageContainer.setBackgroundResource(R.drawable.rock);
                makeTagGui("WIN", "ROCK", "SCISSORS");
            }else if(gameReturnedResult == 2){
                cmpMainContainer.setBackgroundResource(R.drawable.bluering);
                cmpImageContainer.setBackgroundResource(R.drawable.enemypaper);
                userMainContainer.setBackgroundResource((R.drawable.redring));
                userImageContainer.setBackgroundResource(R.drawable.rock);
                makeTagGui("LOO", "ROCK", "PAPER");
            }
            else{
                cmpImageContainer.setBackgroundResource(R.drawable.enemyrock);
                userImageContainer.setBackgroundResource(R.drawable.rock);
                cmpMainContainer.setBackgroundResource(R.drawable.bluering);
                userMainContainer.setBackgroundResource((R.drawable.bluering));
                makeTagGui("TIE", "ROCK", "ROCK");
            }

            compScore.setText(String.valueOf(thisGame.compScore()));
            userScore.setText(String.valueOf(thisGame.userScore()));
        }
    };
    private View.OnClickListener customOnClickPaper = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int gameReturnedResult = thisGame.shoot("paper");
            if(gameReturnedResult == 1){
                cmpMainContainer.setBackgroundResource(R.drawable.redring);
                cmpImageContainer.setBackgroundResource(R.drawable.enemyrock);
                userMainContainer.setBackgroundResource((R.drawable.bluering));
                userImageContainer.setBackgroundResource(R.drawable.paper);
                makeTagGui("WIN", "PAPER", "ROCK");
            }else if(gameReturnedResult == 2){
                cmpMainContainer.setBackgroundResource(R.drawable.bluering);
                cmpImageContainer.setBackgroundResource(R.drawable.enemyscissors);
                userMainContainer.setBackgroundResource((R.drawable.redring));
                userImageContainer.setBackgroundResource(R.drawable.paper);
                makeTagGui("LOO", "PAPER", "SCISSORS");
            }
            else{
                userImageContainer.setBackgroundResource(R.drawable.paper);
                cmpImageContainer.setBackgroundResource(R.drawable.enemypaper);
                cmpMainContainer.setBackgroundResource(R.drawable.bluering);
                userMainContainer.setBackgroundResource((R.drawable.bluering));
                makeTagGui("TIE", "PAPER", "SCISSORS");
            }

            compScore.setText(String.valueOf(thisGame.compScore()));
            userScore.setText(String.valueOf(thisGame.userScore()));
        }
    };
    private View.OnClickListener customOnClickScissors = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int gameReturnedResult = thisGame.shoot("scissor");
            if(gameReturnedResult == 1){
                cmpMainContainer.setBackgroundResource(R.drawable.redring);
                cmpImageContainer.setBackgroundResource(R.drawable.enemypaper);
                userMainContainer.setBackgroundResource((R.drawable.bluering));
                userImageContainer.setBackgroundResource(R.drawable.scissors);
                makeTagGui("WIN", "SCISSORS", "PAPER");
            }else if(gameReturnedResult == 2){
                cmpMainContainer.setBackgroundResource(R.drawable.bluering);
                cmpImageContainer.setBackgroundResource(R.drawable.enemyrock);
                userMainContainer.setBackgroundResource((R.drawable.redring));
                userImageContainer.setBackgroundResource(R.drawable.scissors);
                makeTagGui("LOO", "SCISSORS", "ROCK");
            }
            else{
                cmpImageContainer.setBackgroundResource(R.drawable.enemyscissors);
                userImageContainer.setBackgroundResource(R.drawable.scissors);
                cmpMainContainer.setBackgroundResource(R.drawable.bluering);
                userMainContainer.setBackgroundResource((R.drawable.bluering));
                makeTagGui("TIE", "SCISSORS", "SCISSORS");
            }

            compScore.setText(String.valueOf(thisGame.compScore()));
            userScore.setText(String.valueOf(thisGame.userScore()));
        }
    };

    private void makeTagGui(String result, String userGuess, String compGuess){
        LayoutInflater inflater = (LayoutInflater) getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View newTagView = inflater.inflate(R.layout.layout, null);

        TextView newNewTextView = (TextView) newTagView.findViewById(R.id.newTextView);

        newNewTextView.setText(result + " User: " + userGuess + "   Comp: " + compGuess);

        queryTableLayout.addView(newTagView);
    }

    private void resetAll( ){
        queryTableLayout.removeAllViews();
        thisGame.resetUserScore();
        thisGame.resetCompScore();
        compScore.setText(String.valueOf(thisGame.compScore()));
        userScore.setText(String.valueOf(thisGame.userScore()));
    }

}
