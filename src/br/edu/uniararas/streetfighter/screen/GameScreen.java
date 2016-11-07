package br.edu.uniararas.streetfighter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	
	private Vector3           posPlayer1;
	private Vector3           posPlayer2;
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
		
		posPlayer1  = new Vector3();
		posPlayer2  = new Vector3();
		
	}
	
	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.Q))
			player1.nextSkin();
		if (Gdx.input.isKeyJustPressed(Input.Keys.P))
			player2.nextSkin();
		if (Gdx.input.isKeyJustPressed(Input.Keys.S))
			player1.chutar();
		if (Gdx.input.isKeyJustPressed(Input.Keys.K))
			player2.chutar();
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)){
			player1.andarPraFrente();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.J)){
			player2.andarPraFrente();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)){
			player1.andarPraTras();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.L)){
			player2.andarPraTras();
		}
		
		
		player1.update(delta);
		player2.update(delta);
		
		player1.personagem[player1.estado].transform.getTranslation(posPlayer1);
		player2.personagem[player2.estado].transform.getTranslation(posPlayer2);
		
		float dist = Math.abs(posPlayer1.x - posPlayer2.x);
		
		if (dist < 3.50f){
			if (player1.estado == Player.ATTACK && player2.estado == Player.ATTACK){
				player1.tomouPorradaFraca();
				player2.tomouPorradaFraca();
			}
			else if (player1.estado == Player.ATTACK && player2.estado == Player.IDLE){
				player2.tomouPorradaForte();
			}
			else if (player1.estado == Player.IDLE && player2.estado == Player.ATTACK){
				player1.tomouPorradaForte();
			}
			System.out.println("P1 = "+ player1.getVida()+"    P2 = "+player2.getVida());
		}
		
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
