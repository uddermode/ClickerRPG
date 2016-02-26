package com.udderlevel.clickerrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.udderlevel.clickerrpg.enemy.Enemy;
import com.udderlevel.clickerrpg.enemy.EnemyFactory;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Texture img;
	World world;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();


		//Check for save data

			//Create new player based off of saved data

		//else Create a blank player
		world = new World();
	}

	@Override
	public void render ()
	{
		world.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		world.render(batch, font);

		batch.end();
	}
}
