package com.udderlevel.clickerrpg.enemy;

/**
 * The base class all enemies all derived from
 * Created by Edwin on 2/26/2016.
 */
public abstract class Enemy
{
    //Enemy Stats
    private int level;
    private int health;
    private int attack;
    private int defense;
    private int xpDrop;

    private String name;

    //Constructors
    public Enemy(String name)
    {
        this.name = name;
    }

    //Getters
    public String getName()
    {
        return name;
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

    public int getXpDrop()
    {
        return xpDrop;
    }

    //Setters
    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public void setDefense(int defense)
    {
        this.defense = defense;
    }

    public void setXpDrop(int xpDrop)
    {
        this.xpDrop = xpDrop;
    }
}
