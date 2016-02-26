package com.udderlevel.clickerrpg.enemy;

/**
 * Created by Edwin on 2/26/2016.
 */
public enum EnemyFactory
{
    EFACTORY;

    public Enemy generate(int playerLevel)
    {
        Enemy e = new Rat(playerLevel);
        return e;
    }

}
