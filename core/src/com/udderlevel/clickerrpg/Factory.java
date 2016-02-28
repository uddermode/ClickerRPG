package com.udderlevel.clickerrpg;

import com.udderlevel.clickerrpg.enemy.Bat;
import com.udderlevel.clickerrpg.enemy.Enemy;
import com.udderlevel.clickerrpg.enemy.Goblin;
import com.udderlevel.clickerrpg.enemy.Rat;
import com.udderlevel.clickerrpg.loot.Loot;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Enemy Factory creates a random enemy
 * Created by Edwin on 2/26/2016.
 */
public enum Factory
{
    FACTORY;

    private Random rand;
    Factory()
    {
        rand = new Random();
    }

    public Enemy generateEnemy(int worldLevel)
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

    public Loot generateLoot(ArrayList<Loot> playerLoot)
    {
        int type = rand.nextInt(7);
        Loot loot = playerLoot.get(type);

        if(type == 0)
        {
            return new Loot(loot, rand, Loot.Type.ARMS);
        }
        else if(type == 1)
        {
            return new Loot(loot, rand, Loot.Type.BODY);
        }
        else if(type == 2)
        {
            return new Loot(loot, rand, Loot.Type.HEAD);
        }
        else if(type == 3)
        {
            return new Loot(loot, rand, Loot.Type.LEGS);
        }
        else if(type == 4)
        {
            return new Loot(loot, rand, Loot.Type.OTHER);
        }
        else if(type == 5)
        {
            return new Loot(loot, rand, Loot.Type.SHIELD);
        }
        else
        {
            return new Loot(loot, rand, Loot.Type.WEAPON);
        }
    }
}
