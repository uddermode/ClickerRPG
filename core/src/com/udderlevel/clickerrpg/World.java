package com.udderlevel.clickerrpg;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.udderlevel.clickerrpg.enemy.Enemy;
import com.udderlevel.clickerrpg.enemy.EnemyFactory;

/**
 * The world class holds all the game logic
 * Created by Edwin on 2/26/2016.
 */
public class World
{
    private Player player;
    private Enemy enemy;
    private float lastUpdate; //Time since last time update was called. Aiming for 1 second

    //Constructors

    //Default new game
    public World()
    {
        player = new Player();
        this.enemy = EnemyFactory.EFACTORY.generate(player.getLevel());
    }

    //loading player
    public World(Player player)
    {
        this.player = player;
        this.enemy = EnemyFactory.EFACTORY.generate(player.getLevel());
    }

    //Methods
    public void update(float deltaTime)
    {
        lastUpdate += deltaTime;
        if(lastUpdate >= 1)
        {
            applyDPS();
            lastUpdate = 0;
        }
    }

    public void render(SpriteBatch batch, BitmapFont font)
    {
        //Player Stats
        font.draw(batch, "Level: " + player.getLevel(), 0, 450);
        font.draw(batch, "Health: " + player.getHealth(), 0, 400);
        font.draw(batch, "Attack: " + player.getAttack(), 0, 350);
        font.draw(batch, "Defense: " + player.getDefense(), 0, 300);
        font.draw(batch, "Speed: " + player.getSpeed(), 0, 250);
        font.draw(batch, "Xp: " + player.getXpCurrent(), 0, 200);
        font.draw(batch, "Next Level: " + player.getXpNeeded(), 0, 150);
        font.draw(batch, "DPS: " + getDPS(), 0, 100);

        //Enemy Stats
        font.draw(batch, "Level: " + enemy.getLevel(), 200, 450);
        font.draw(batch, "Health: " + enemy.getHealth(), 200, 400);
        font.draw(batch, "Attack: " + enemy.getAttack(), 200, 350);
        font.draw(batch, "Defense: " + enemy.getDefense(), 200, 300);
    }

    public int getDPS()
    {
        return Math.min(player.getAttack() * (player.getAttack()/enemy.getDefense()), player.getAttack()/2);
    }

    public void applyDPS()
    {
        enemy.setHealth(enemy.getHealth()-getDPS());
    }

}
