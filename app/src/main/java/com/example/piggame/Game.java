package com.example.piggame;

public class Game
{
    // VARIABLES
    private Player[] players;

    private int turnIndex;
    private int[] dice;
    private int winningScore;

    // CONSTRUCTORS
    public Game(Player[] players, int winningScore, int numDice)
    {
        this.players = players;

        turnIndex = 0;
        dice = new int[numDice];
        winningScore = winningScore;
    }

    public Game(Player[] players)
    {
        this.players = players;

        turnIndex = 0;
        dice = new int[2];
        winningScore = 100;
    }

    // GETTERS
    public int getTurn() {return turnIndex;}
    public int getWinningScore() {return winningScore;}
    public int[] getDice() {return dice;}

    // BRAIN METHODS
    public void roll()
    {
        for (int i = 0; i < dice.length; i ++)
            dice[i] = (int) (Math.random() * 6) + 1;

        boolean gotOne = players[turnIndex].processRoll(dice);

        if (gotOne)
            endTurn();

        updateUI();
    }

    public void endTurn()
    {
        turnIndex ++;

        if (turnIndex >= players.length)
            turnIndex = 0;
    }

    public void updateUI()
    {
        // TODO: write this function
    }
}
