package com.example.piggame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity
{
    int playerIndex;
    Player[] players;

    public static Player winner;
    int[] dice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.game_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.game), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int numPlayers = PlayerSelect.getPlayercount();

        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i ++)
        {
            players[i] = new Player("Player" + (i+1));
        }

        playerIndex = 0;

        dice = new int[2];

        updateUI();
    }

    public void roll(View view)
    {
        int[] dice = generateDice(2, 6);

        updateUI();

        boolean gotOne = players[playerIndex].processRoll(dice);

        if (gotOne)
        {
            endTurn(null);
        }

        updateUI();
    }

    public void endTurn(View view)
    {
        players[playerIndex].finishTurn();

        playerIndex ++;

        if (playerIndex >= players.length)
        {
            playerIndex = 0;
        }

        if (players[playerIndex].totalScore  >= 100){
            winner = getWinner();

            Toast.makeText(this, "Game over! " + winner.getName(),
                    Toast.LENGTH_SHORT).show();

           startActivity(new Intent(GameActivity.this,EndActivity.class));
        }

        updateUI();
    }

    public Player getWinner()
    {
        int max = players[0].totalScore;
        Player maxPlayer = players[0];

        for( int i=1; i<players.length; i++)
        {
            if( players[i].totalScore > max)
            {
                max= players[i].totalScore;
                maxPlayer = players[i];
            }
        }

        return maxPlayer;
    }

    public int[] generateDice(int numDice, int numFaces)
    {
        dice = new int[numDice];

        for (int i = 0; i < numDice; i ++)
        {
            dice[i] = (int)(Math.random() * numFaces) + 1;
        }

        return dice;
    }

    public void updateUI()
    {
        TextView totalMoneyTV = (TextView) findViewById(R.id.totalMoneyTV);
        TextView currentMoneyTV = (TextView) findViewById(R.id.currentMoneyTV);
        TextView diceTV = (TextView) findViewById(R.id.diceTV);
        TextView currentPlayerTV = (TextView) findViewById(R.id.currentPlayerTV);

        Player currentPlayer = players[playerIndex];

        String diceOutput = "[";
        for (int i = 0; i < dice.length; i ++)
        {
            diceOutput += dice[i] + (i == dice.length-1 ? "]":", ");
        }

        totalMoneyTV.setText("Total Money: " + currentPlayer.totalScore);
        currentMoneyTV.setText("Current Money: " + currentPlayer.currentScore);
        diceTV.setText("Dice: " + diceOutput);
        currentPlayerTV.setText("Current Player: " + currentPlayer.getName());

        for (int i = 0; i < players.length; i ++)
        {
            TextView currentTV = null;

            switch (i)
            {
                case 0:
                    currentTV = (TextView) findViewById(R.id.playerTV1);
                    break;
                case 1:
                    currentTV = (TextView) findViewById(R.id.playerTV2);
                    break;
                case 2:
                    currentTV = (TextView) findViewById(R.id.playerTV3);
                    break;
                case 3:
                    currentTV = (TextView) findViewById(R.id.playerTV4);
                    break;
                case 4:
                    currentTV = (TextView) findViewById(R.id.playerTV5);
                    break;
            }


            Log.i("DEBUG", i + "");
            currentTV.setText(players[i].getName() + " - Money: "
                    + (players[i].totalScore + players[i].currentScore));
        }

    }
}
