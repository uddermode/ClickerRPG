package com.udderlevel.clickerrpg;

import com.udderlevel.clickerrpg.loot.Loot;
import java.util.ArrayList;

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
    private int clickDMG;

    private ArrayList<Loot> bag;
    private ArrayList<Loot> equip;

    //Constructors
    public Player()
    {
        level = 1;
        health = 20;
        attack = 5;
        defense = 5;
        speed = 5;
        xpNeeded = 10;
        xpCurrent = 0;
        clickDMG = 1;
        setState(State.STATE_FIGHTING);

        bag = new ArrayList<Loot>();
        equip = new ArrayList<Loot>();
    }

    //Methods
    public void gainXP(int xp)
    {
        xpCurrent += xp;

        //check if level up has occured
        if(xpCurrent >= xpNeeded)
        {
            //update stats
            level++;
            health += 5;
            attack += 1;
            defense += 1;
            speed += 1;
            clickDMG += level;

            //reset xp and xpNeeded
            xpCurrent = 0;
            xpNeeded = xpNeeded + (20 * level);
        }
    }

    public void getLoot(Loot loot)
    {
        //Make sure loot is not null
        if(loot != null)
        {
            //add loot to bag
            bag.add(loot);
        }
    }

    public void equipLoot(Loot loot)
    {
        switch(loot.getType())
        {
            case HEAD:
                equip.set(2, loot);
                break;
            case BODY:
                equip.set(1, loot);
                break;
            case ARMS:
                equip.set(0, loot);
                break;
            case LEGS:
                equip.set(3, loot);
                break;
            case WEAPON:
                equip.set(6, loot);
                break;
            case SHIELD:
                equip.set(5, loot);
                break;
            case OTHER:
                equip.set(4, loot);
                break;
        }
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

    public int getClickDMG() { return clickDMG; }


    public ArrayList<Loot> getEquip()
    {
        return equip;
    }

    public ArrayList<Loot> getBag()
    {
        return bag;
    }

    public Loot getFromBag(int index)
    {
        return bag.get(index);
    }

    public Loot removeFromBag(int index)
    {
        return bag.remove(index);
    }

    //Setters
    public void setState(State state)
    {
        this.state = state;
    }

    public void addClickDMG(int increase) { this.clickDMG += increase; }

}
