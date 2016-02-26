package com.udderlevel.clickerrpg;

/**
 * An object of this class stores all the information of the player
 * Created by Edwin on 2/25/2016.
 */
public class Player
{
    //Player states
    public enum State
    {
        STATE_MOVING, STATE_FIGHTING, STATE_WON, STATE_DEAD
    }

    //Current state of player
    private State state;

    //Player stats
    private int level;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int xpNeeded;
    private int xpCurrent;

    //Constructors
    public Player()
    {
        level = 1;
        health = 20;
        attack = 5;
        attack = 5;
        defense = 5;
        speed = 5;
        xpNeeded = 10;
        xpCurrent = 0;
        state = State.STATE_MOVING;
    }

    //Getters
    public State getState()
    {
        return state;
    }

    public int getLevel()
    {
        return level;
    }

    public int getHealth()
    {
        return health;
    }

    public int getAttack()
    {
        return attack;
    }

    public int getDefense()
    {
        return defense;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getXpNeeded()
    {
        return xpNeeded;
    }

    public int getXpCurrent()
    {
        return xpCurrent;
    }

    //Setters
    public void setState(State state)
    {
        this.state = state;
    }
}
