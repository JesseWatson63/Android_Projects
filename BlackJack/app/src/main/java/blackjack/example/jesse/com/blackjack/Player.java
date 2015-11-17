package blackjack.example.jesse.com.blackjack;

import java.util.ArrayList;

/**
 * Created by jesse on 10/10/2015.
 */
public class Player {

    private String playerName = "";

    private int playerScore = 0;

    private boolean playerBust = false;

    private boolean playerWins = false;

    private int playerChips = 1000;

    ArrayList<String> playerHand = new ArrayList<>();

    public void resetPlayer(){
        playerScore = 0;
        playerBust = false;
        playerWins = false;
        playerHand.clear();
    }

    public int getPlayerChips(){ return playerChips; }

    public void setPlayerChips(int chips){ playerChips = chips; }

    public int getPlayerScore(){ return playerScore; }

    public void setPlayerScore(int score){ playerScore = score; }

    public String getPlayerName(){ return playerName; }

    public void setPlayerName(String name){ playerName = name; }

    public boolean getPlayerBust(){ return playerBust; }

    public void setPlayerBust(boolean bust) { playerBust = bust; }

    public void setPlayerWins(boolean win) { playerWins = win; }

    public boolean getPlayerWins(){return playerWins; }
}
