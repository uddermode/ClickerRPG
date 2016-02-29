package com.udderlevel.clickerrpg;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.udderlevel.clickerrpg.enemy.Enemy;
import com.udderlevel.clickerrpg.loot.Loot;

import java.util.ArrayList;

/**
 * The world class holds all the game logic
 * Created by Edwin on 2/26/2016.
 */
public class World
{
    public enum State
    {
            STATE_STATS, STATE_BAG, STATE_GEAR
    }

    private Player player;
    private Enemy enemy;
    private float lastUpdate; //Time since last time update was called. Aiming for 1 second
    private boolean enemyKilled;
    private int distanceFromEnemy;
    private int worldLevel;

    //current position in the bag
    private int bagPos;
    private int selectedPos;

    private State state;

    //Constructors

    //Default new game
    public World()
    {
        player = new Player();
        player.setState(Player.State.STATE_MOVING);
        enemy = Factory.FACTORY.generateEnemy(worldLevel);
        distanceFromEnemy = 10;
        worldLevel = 1;
        bagPos = 0;
        selectedPos = 0;
        state = State.STATE_STATS;
    }

    //loading player
    public World(Player player, int worldLevel)
    {
        this.player = player;
        player.setState(Player.State.STATE_MOVING);
        enemy = Factory.FACTORY.generateEnemy(worldLevel);
        this.worldLevel = worldLevel;
        distanceFromEnemy = 10 * worldLevel;
        state = State.STATE_STATS;
    }

    //Methods
    public void update(float deltaTime)
    {
        lastUpdate += deltaTime;

        //check if a second has passed
        if(lastUpdate >= 1) {
            lastUpdate = 0;
            //checks player state for what to do
            switch (player.getState()) {
                //Player is travelling to enemy
                case STATE_MOVING:
                    //check if reached enemy
                    if (distanceFromEnemy <= 0) {
                        player.setState(Player.State.STATE_FIGHTING);
                        //Generate enemy
                        enemyKilled = false;
                        enemy = Factory.FACTORY.generateEnemy((worldLevel/3) + 1);
                        distanceFromEnemy = 10;
                    } else {
                        distanceFromEnemy -= player.getSpeed();
                    }
                    break;
                //Player is fighting an enemy
                case STATE_FIGHTING:
                    //Check if enemy is still alive
                    if (!enemyKilled) {
                        applyDPS();
                        if (enemy.getHealth() <= 0) {
                            enemyKilled = true;
                            worldLevel++;
                            player.setState(Player.State.STATE_WON);
                            applyXP();
                        }
                    }
                    break;
                case STATE_WON:
                    if(player.getBag().size() < 20)
                    {
                        player.getLoot(Factory.FACTORY.generateLoot(player.getEquip()));
                    }
                    player.setState(Player.State.STATE_MOVING);
                    break;
                case STATE_DEAD:
                    break;
            }
        }
    }

    public void render(SpriteBatch batch, BitmapFont font)
    {
        switch(this.getState())
        {
            case STATE_STATS:
                //Player Stats
                font.draw(batch, "Level: " + player.getLevel(), 20, 290);
                font.draw(batch, "Health: " + player.getHealth(), 20, 270);
                font.draw(batch, "Attack: " + player.getAttack(), 20, 250);
                font.draw(batch, "Defense: " + player.getDefense(), 20, 230);
                font.draw(batch, "Speed: " + player.getSpeed(), 20, 210);
                font.draw(batch, "Xp: " + player.getXpCurrent(), 20, 190);
                font.draw(batch, "Next Level: " + player.getXpNeeded(), 20, 170);
                font.draw(batch, "DPS: " + getDPS(), 20, 150);
                font.draw(batch, "Click DMG: " + player.getClickDMG(), 20, 130);

                switch (player.getState()) {
                    case STATE_MOVING:
                        font.draw(batch, "Distance until next enemy: " + distanceFromEnemy, 300, 290);
                        break;
                    case STATE_FIGHTING:
                        font.draw(batch, "Name: " + enemy.getName(), 350, 290);
                        font.draw(batch, "Level: " + enemy.getLevel(), 350, 260);
                        font.draw(batch, "Health: " + enemy.getHealth(), 350, 230);
                        font.draw(batch, "Attack: " + enemy.getAttack(), 350, 200);
                        font.draw(batch, "Defense: " + enemy.getDefense(), 350, 170);

                        break;
                    case STATE_WON:
                        font.draw(batch, "Enemy Defeated", 350, 290);
                        break;
                    case STATE_DEAD:
                        break;
                }
                break;
            case STATE_BAG:
                ArrayList<Loot> playerBag = player.getBag();
                for(int i = 0; (i < 9 && i + bagPos < playerBag.size()) && playerBag.size() != 0; i++)
                {
                    font.draw(batch, playerBag.get(bagPos + i).getName(), 20, 290 - (20*i));
                }

                //Check that something in the bag is selected
                if(playerBag.size() > bagPos + selectedPos )
                {
                    Loot selected = playerBag.get(bagPos + selectedPos);
                    font.draw(batch, selected.getName(), 160, 290);
                    font.draw(batch, "Health Bonus: "+selected.getHealth(), 160, 270);
                    font.draw(batch, "Attack Bonus: "+selected.getAttack(), 160, 250);
                    font.draw(batch, "Defense Bonus: "+selected.getDefense(), 160, 230);
                    font.draw(batch, "Speed Bonus: "+selected.getSpeed(), 160, 210);
                    font.draw(batch, "XP Gain Bonus: "+selected.getXpGain(), 160, 190);
                    font.draw(batch, "Click DMG Bonus: "+selected.getClickDMG(), 160, 170);
                    font.draw(batch, "DPS Bonus: "+selected.getDPS(), 160, 150);
                }
                break;
            case STATE_GEAR:
                break;
        }

    }

    //Calculates the damage per second.
    //Minimum damage is player level
    public int getDPS()
    {
        return Math.max(player.getAttack() * (player.getAttack()/enemy.getDefense()), player.getLevel());
    }

    //Actually does damage to the enemy
    public void applyDPS()
    {
        enemy.setHealth(enemy.getHealth()-getDPS());
    }

    //Calculates and applies the xp gained
    public void applyXP()
    {
        player.gainXP(enemy.getXpDrop() * enemy.getLevel());
    }

    public void applyClickDMG()
    {
        enemy.setHealth(enemy.getHealth() - player.getClickDMG());
    }

    public void clickedEnemy()
    {
        if (!enemyKilled) {
            applyClickDMG();
            if (enemy.getHealth() <= 0) {
                enemyKilled = true;
                worldLevel++;
                player.setState(Player.State.STATE_WON);
                applyXP();
            }
        }
    }

    //getters
    public State getState() {
        return state;
    }

    public int getBagPos() { return bagPos; }

    public int getSelectedPos() { return selectedPos; }

    //setters
    public void setState(State state)
    {
        this.state = state;
    }

    public void setBagPos(int newPos)
    {
        if(newPos >= player.getBag().size() - 10) //if bagPos is at last 10 items, make sure to display 10 always
        {
            newPos = player.getBag().size() - 11;
        }
        if(newPos < 0) //If bagPos is negative, set to 0
        {
            newPos = 0;
        }
        this.bagPos = newPos;
    }

    public void setSelectedPos(int selectedPos)
    {
        this.selectedPos = selectedPos;
        if(this.selectedPos < 0)
        {
            this.selectedPos = 0;
        }
        if(this.selectedPos > 9)
        {
            this.selectedPos = 9;
        }
    }

}
