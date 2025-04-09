package com.example.piggame;

import androidx.annotation.NonNull;

public class Player
{
    // Variables
    private final String name;

    public int totalScore;
    public int currentScore;

    // Constructor
    public Player(String name)
    {
        this.name = name;

        totalScore = 0;
        currentScore = 0;
    }

    // Getters
    public String getName() {return name;}

    // Brain Methods
    public boolean processRoll(int[] dice)
    {
        int numOnes = 0;
        int sum = 0;

        for (int i : dice)
        {
            if (i == 1) numOnes ++;
            sum += i;
        }

        if (numOnes == dice.length)
        {
            totalScore = 0;
            currentScore = 0;
            return true;
        }
        else if (numOnes > 0)
        {
            currentScore = 0;
            return true;
        }
        else
        {
            currentScore += sum;
            return false;
        }
    }

    public void finishTurn()
    {
        totalScore += currentScore;
        currentScore = 0;
    }

    @NonNull
    @Override
    public String toString()
    {
        return name +
                ":\nTotal: " + totalScore +
                "\nCurrent: " + currentScore;
    }
}