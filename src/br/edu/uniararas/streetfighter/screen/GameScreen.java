package br.edu.uniararas.streetfighter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import br.edu.uniararas.streetfighter.model.Player;
import br.edu.uniararas.streetfighter.util.Parameters;

public class GameScreen extends AbstractScreen {
	
	private Texture           fundo;
	private SpriteBatch       spriteBatch;
	private Matrix4           viewMatrix;
	private Matrix4           tranMatrix;
	private PerspectiveCamera camera;
	private ModelBatch        modelBatch;
	private Environment       environment;
	
	private Player            player1;
	private Player            player2;
	
	public GameScreen(){
		fundo       = new Texture(Gdx.files.internal("bg/telaLuta.jpg"));
		spriteBatch = new SpriteBatch();
		viewMatrix  = new Matrix4();
		tranMatrix  = new Matrix4();
		modelBatch  = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight,1,1,1,1));
		
		camera = new PerspectiveCamera(67.0f, Parameters.WIDTH, Parameters.HEIGHT);
		camera.near = 0.1f;
		camera.far  = 1000f;
		camera.position.set(0, 5, 10);
		camera.lookAt(0, 5, 0);
		camera.update();
		
		player1 = new Player(1);
		player2 = new Player(2);
		
	}
	
	@Override
	public void update(float delta) {
		
		player1.update(delta);
		player2.update(delta);
		
		
	}

	@Override
	public void draw(float delta) {
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0,0,0,0); // pintar o fundo de preto
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		viewMatrix.setToOrtho2D(0, 0, Parameters.WIDTH, Parameters.HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		
		spriteBatch.begin();
		spriteBatch.draw(fundo, 0, 0, Parameters.WIDTH, Parameters.HEIGHT, 
                                0, 0, fundo.getWidth(), fundo.getHeight(),
                                false, false);  
		spriteBatch.end();
		
		modelBatch.begin(camera);
		modelBatch.render(player1.personagem[player1.estado], environment);
		modelBatch.render(player2.personagem[player2.estado], environment);
		modelBatch.end();
		
		camera.update();
	}
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		fundo.dispose();
		spriteBatch.dispose();
	}

}
