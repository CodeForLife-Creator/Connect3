package com.glowedupgiftinc.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //o - yellow , 1 - red, 2 - empty
    int activePlayer =0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedCounter] == 2 && gameActive == true) {
            gamestate[tappedCounter] = activePlayer;

            counter.setTranslationY(-50);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationY(50).setDuration(300);

            for (int[] winningPositions : winningPositions) {
                if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] && gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                        gamestate[winningPositions[0]] != 2) {
                    //Someone has won
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button button = (Button) findViewById(R.id.button);
                    TextView tvWinner = (TextView) findViewById(R.id.tvWinner);
                    tvWinner.setText(winner + " has won!!");
                    button.setVisibility(View.VISIBLE);
                    tvWinner.setVisibility(View.VISIBLE);

                }
            }
        }
    }
    public void playAgain(View view){
        Button button = (Button) findViewById(R.id.button);
        TextView tvWinner = (TextView) findViewById(R.id.tvWinner);
        button.setVisibility(View.INVISIBLE);
        tvWinner.setVisibility(View.INVISIBLE);

        GridLayout board = (GridLayout) findViewById(R.id.board);

        for(int i = 0; i < board.getChildCount(); i++){
            ImageView counters = (ImageView) board.getChildAt(i);
            counters.setImageDrawable(null);
        }
        activePlayer =0;
        for (int i = 0; i < gamestate.length; i++){
            gamestate[i] = 2;
        }
        gameActive = true;
    }

}
