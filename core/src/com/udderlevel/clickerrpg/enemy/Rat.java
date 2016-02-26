package com.udderlevel.clickerrpg.enemy;

/**
 * A basic enemy.
 * It a rat that is very weak
 * Created by Edwin on 2/26/2016.
 */
public class Rat extends Enemy
{
    private final static int BASE_ATK = 5;
    private final static int BASE_DEF = 5;
    private final static int BASE_HP = 15;
    private final static int BASE_XP = 5;

    public Rat(int playerLevel)
    {
        setLevel(playerLevel);
        setAttack(BASE_ATK + getLevel());
        setDefense(BASE_DEF+getLevel());
        setHealth(BASE_HP+getLevel());
        setXpDrop(BASE_XP+getLevel());
    }
}
