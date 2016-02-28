package com.udderlevel.clickerrpg;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame implements ApplicationListener, GestureListener, InputProcessor {
	SpriteBatch batch;
	BitmapFont font;
	OrthographicCamera camera;
	Viewport vp;
	World world;
	AssetManager manager;

	//Rectangles for menus and other clickable stuff
	Rectangle fightArea;
	Rectangle statsMenu;
	Rectangle bagMenu;
	Rectangle gearMenu;
	
	@Override
	public void create () {
		//set up rendering
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		//load the assets
		manager = new AssetManager();
		manager.load("stats.png", Texture.class);
		manager.load("bag.png", Texture.class);
		manager.load("gear.png", Texture.class);
		manager.finishLoading();

		//set up camera
		camera = new OrthographicCamera();
		vp = new StretchViewport(500, 300, camera);
		vp.apply();
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		//load player and world or set new
		//Check for save data
			//Create new player based off of saved data
		//else Create a blank player
		world = new World();

		//set up the HUD
		fightArea = new Rectangle(200, 100, 200, 300);
		statsMenu = new Rectangle(100, 0, 100, 100);
		bagMenu = new Rectangle(200, 0, 100, 100);
		gearMenu = new Rectangle(300, 0, 100, 100);

		//set up input handling
		InputMultiplexer im = new InputMultiplexer();
		GestureDetector gd = new GestureDetector(this);
		im.addProcessor(gd);
		im.addProcessor(this);

		Gdx.input.setInputProcessor(im);
	}

	@Override
	public void resize(int width, int height)
	{
		vp.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}

	@Override
	public void render ()
	{
		Texture background = null;
		//clear the screen
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		switch(world.getState())
		{
			case STATE_STATS:
				background = manager.get("stats.png");
				break;
			case STATE_BAG:
				background = manager.get("bag.png");
				break;
			case STATE_GEAR:
				background = manager.get("gear.png");
				break;
		}

		batch.draw(background, 0, 0);
		//update
		world.update(Gdx.graphics.getDeltaTime());
		world.render(batch, font);

		batch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	//mobile inputs
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	//Desktop inputs
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		//convert touch to match screen coords
		Vector3 touchCoords = camera.unproject(new Vector3(screenX,screenY,0));

		if(fightArea.contains(touchCoords.x, touchCoords.y))
		{
			world.clickedEnemy();
		}
		else if(statsMenu.contains(touchCoords.x, touchCoords.y))
		{
			world.setState(World.State.STATE_STATS);
		}
		else if(bagMenu.contains(touchCoords.x, touchCoords.y))
		{
			world.setState(World.State.STATE_BAG);
		}
		else if(gearMenu.contains(touchCoords.x, touchCoords.y))
		{
			world.setState(World.State.STATE_GEAR);
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
