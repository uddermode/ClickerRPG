package com.udderlevel.clickerrpg.enemy;

/**
 * A basic enemy
 * A weak bat that is more of an annoyance
 * Created by Edwin on 2/26/2016.
 */
public class Bat extends Enemy
{
    private final static int BASE_ATK = 3;
    private final static int BASE_DEF = 3;
    private final static int BASE_HP = 10;
    private final static int BASE_XP = 3;
    private final static String NAME = "Bat";

    public Bat(int playerLevel) {
        super(NAME);
        setLevel(playerLevel);
        setAttack(BASE_ATK + getLevel() - 1);
        setDefense(BASE_DEF + getLevel() - 1);
        setHealth(BASE_HP + getLevel() - 1);
        setXpDrop(BASE_XP * getLevel() - 1);
    }
}
