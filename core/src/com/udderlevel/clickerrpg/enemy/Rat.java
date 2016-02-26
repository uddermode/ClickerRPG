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
    private final static String NAME = "Rat";

    public Rat(int playerLevel)
    {
        super(NAME);
        setLevel(playerLevel);
        setAttack(BASE_ATK + getLevel() - 1);
        setDefense(BASE_DEF+getLevel() - 1);
        setHealth(BASE_HP+getLevel() - 1);
        setXpDrop(BASE_XP+getLevel() - 1);
    }
}
