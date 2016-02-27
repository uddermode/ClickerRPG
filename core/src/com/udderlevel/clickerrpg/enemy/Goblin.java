package com.udderlevel.clickerrpg.enemy;

/**
 * This is a normal enemy
 * A goblin with some punch but not much defense
 * Created by Edwin on 2/26/2016.
 */
public class Goblin extends Enemy{
    private final static int BASE_ATK = 7;
    private final static int BASE_DEF = 3;
    private final static int BASE_HP = 20;
    private final static int BASE_XP = 10;
    private final static String NAME = "Goblin";

    public Goblin(int playerLevel) {
        super(NAME);
        setLevel(playerLevel);
        setAttack(BASE_ATK * getLevel() - 1);
        setDefense(BASE_DEF * getLevel() - 1);
        setHealth(BASE_HP * getLevel() - 1);
        setXpDrop(BASE_XP * getLevel() - 1);
    }
}
