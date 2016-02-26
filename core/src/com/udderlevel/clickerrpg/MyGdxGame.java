package com.udderlevel.clickerrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Texture img;
	Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();


		//Check for save data

			//Create new player based off of saved data

		//else Create a blank player
		player = new Player();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		font.draw(batch, "Level: " + player.getLevel(), 0, 450);
		font.draw(batch, "Health: " + player.getHealth(), 0, 400);
		font.draw(batch, "Attack: " + player.getAttack(), 0, 350);
		font.draw(batch, "Defense: " + player.getDefense(), 0, 300);
		font.draw(batch, "Speed: " + player.getSpeed(), 0, 250);
		font.draw(batch, "Xp: " + player.getXpCurrent(), 0, 200);
		font.draw(batch, "Next Level: " + player.getXpNeeded(), 0, 150);


		batch.end();
	}
}
