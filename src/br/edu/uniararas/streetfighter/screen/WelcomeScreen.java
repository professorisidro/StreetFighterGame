package br.edu.uniararas.streetfighter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.uniararas.streetfighter.util.Parameters;

public class WelcomeScreen extends AbstractScreen {

	private Texture     fundo;
	private SpriteBatch spriteBatch;
	private Matrix4     viewMatrix;
	private Matrix4     tranMatrix;
	
	
	public WelcomeScreen(){
		fundo       = new Texture(Gdx.files.internal("bg/telaWelcome.jpg"));
		spriteBatch = new SpriteBatch();
		viewMatrix  = new Matrix4();
		tranMatrix  = new Matrix4();
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if (Gdx.input.justTouched()){
			setDone(true);
		}
		
	}

	@Override
	public void draw(float delta) {
		// TODO Auto-generated method stub
		viewMatrix.setToOrtho2D(0, 0, Parameters.WIDTH, Parameters.HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		
		spriteBatch.begin();
		spriteBatch.draw(fundo, 0, 0, Parameters.WIDTH, Parameters.HEIGHT, 
                                0, 0, fundo.getWidth(), fundo.getHeight(),
                                false, false);  
		spriteBatch.end();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		fundo.dispose();
		spriteBatch.dispose();
	}


}
