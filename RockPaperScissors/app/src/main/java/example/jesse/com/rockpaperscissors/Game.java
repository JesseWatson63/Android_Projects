package example.jesse.com.rockpaperscissors;

import java.util.Random;

/**
 * Created by jesse on 9/25/2015.
 */
public class Game {
    private int compScore = 0;
    private int userScore = 0;

    private Random randomNumber = new Random();

    public int shoot(String userThrow){
        int compRand = randomNumber.nextInt(3);
        /*
        0 = rock
        1 = paper
        2 = scissors
         */
        userThrow = userThrow.toLowerCase();
        if (userThrow.equals("rock") && compRand == 2) {
            userScore += 1;
            return 1;
        }else if (userThrow.equals("paper") && compRand == 0){
            userScore += 1;
            return 1;
        }else if (userThrow.equals("scissor")&& compRand == 2) {
            userScore += 1;
            return 1;
        }else if (userThrow.equals("rock") && compRand == 1) {
            compScore += 1;
            return 2;
        }else if (userThrow.equals("paper") && compRand == 2){
            compScore += 1;
            return 2;
        }else if (userThrow.equals("scissor")&& compRand == 0) {
            compScore += 1;
            return 2;
        }else{
            return 0;
        }

    }

    public void resetUserScore(){ userScore = 0;}

    public void resetCompScore(){compScore = 0;}

    public int userScore(){ return userScore;}

    public int compScore(){return compScore;}
}
