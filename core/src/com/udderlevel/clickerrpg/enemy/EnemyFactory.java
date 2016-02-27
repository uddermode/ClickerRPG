package com.udderlevel.clickerrpg.enemy;

import java.util.Random;

/**
 * The Enemy Factory creates a random enemy
 * Created by Edwin on 2/26/2016.
 */
public enum EnemyFactory
{
    EFACTORY;

    private Random rand;
    EnemyFactory()
    {
        rand = new Random();
    }

    public Enemy generate(int worldLevel)
    {
        int enemy = rand.nextInt(5);
        if(enemy == 0)
        {
            return new Goblin(worldLevel);
        }
        else if(enemy == 1)
        {
            return new Bat(worldLevel);
        }
        else
        {
            return new Rat(worldLevel);
        }
    }

}
