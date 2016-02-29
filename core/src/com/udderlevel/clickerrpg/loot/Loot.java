package com.udderlevel.clickerrpg.loot;

import java.util.Random;

/**
 * The base class all loot dervies from
 * Created by Edwin on 2/28/2016.
 */
public class Loot
{
    public enum Type
    {
        HEAD, BODY, ARMS, LEGS, WEAPON, SHIELD, OTHER
    }

    //loot info
    private String name;
    private Type type;

    //bonus
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int xpGain;
    private int clickDMG;
    private int DPS;

    //constuctors

    //This constructor is for when player has no loot of type
    private Loot()
    {
        health = 10;
        attack = 5;
        defense = 5;
        speed = 5;
        xpGain = 5;
        clickDMG = 5;
        DPS = 5;
    }

    //COnstructor for all loot after user has one of type
    public Loot(Loot current, Random rand, Type type)
    {
        if(current == null)
        {
            current = new Loot();
        }

        name = "loot #" + rand.nextInt();
        this.type = type;

        switch(getType())
        {
            case HEAD:
                health = current.getHealth() + rand.nextInt(10);
                defense = current.getDefense() + rand.nextInt(5);
                break;
            case BODY:
                health = current.getHealth() + rand.nextInt(10);
                defense = current.getDefense() + rand.nextInt(5);
                speed = current.getSpeed() + rand.nextInt(5);
                break;
            case ARMS:
                attack = current.getAttack() + rand.nextInt(5);
                defense = current.getDefense() + rand.nextInt(5);
                break;
            case LEGS:
                speed = current.getSpeed() + rand.nextInt(5);
                defense = current.getDefense() + rand.nextInt(5);
                break;
            case WEAPON:
                attack = current.getAttack() + rand.nextInt(10);
                clickDMG = current.getClickDMG() + rand.nextInt(5);
                DPS = current.getDPS() + rand.nextInt(5);
                break;
            case SHIELD:
                defense = current.getDefense() + rand.nextInt(20);
                break;
            case OTHER:
                clickDMG = current.getClickDMG() + rand.nextInt(10);
                DPS = current.getDPS() + rand.nextInt(10);
                xpGain = current.getXpGain() + rand.nextInt(25);
                break;
        }
    }

    //getters
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getXpGain() {
        return xpGain;
    }

    public int getClickDMG() {
        return clickDMG;
    }

    public int getDPS() {
        return DPS;
    }
}
