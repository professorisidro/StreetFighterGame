package br.edu.uniararas.streetfighter.screen;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen{

	private String  id;
	private boolean done;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
	public abstract void update(float delta);
	public abstract void draw(float delta);

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		update(delta);
		draw(delta);
	}

	@Override
	public void show() {
		// nao faz nada..
	}
	
	@Override
	public void resize(int width, int height) {
		//nao faz nada		
	}

	@Override
	public void pause() {
		// nao faz nada		
	}

	@Override
	public void resume() {
		// nao faz nada
	}

	@Override
	public void hide() {
		// nao faz nada
	}	
}
