package br.edu.uniararas.streetfighter.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.assets.loaders.ModelLoader.ModelParameters;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;

public class Player {
	
	public static final int IDLE   = 0;
	public static final int ATTACK = 1;
	
	public int        estado;
	public GameObject personagem[];
	
	private Texture   skins[];
	private int       currentSkin = 0;
	
	public Player(int mode){
		Model model;	
		ModelLoader<ModelParameters> loader = new G3dModelLoader(new UBJsonReader());
		
		personagem = new GameObject[2];
		
		model = loader.loadModel(Gdx.files.internal("player/idle_a.g3db"));
		personagem[IDLE] = new GameObject(model);
		model = loader.loadModel(Gdx.files.internal("player/kick_a.g3db"));
		personagem[ATTACK] = new GameObject(model);
		
		estado = IDLE;
		
		if (mode == 1){ // player 1
			for (GameObject g: personagem){
				g.transform.rotate(Vector3.Y, 90);
				g.transform.translate(0,0,-5);
			}
		}
		else{
			for (GameObject g: personagem){
				g.transform.rotate(Vector3.Y, -90);
				g.transform.translate(0, 0, -5);
			}
		}
		
		// carregando toda as possiveis texturas;
		skins = new Texture[5];
		skins[0] = new Texture(Gdx.files.internal("player/texture2.jpg"));
		skins[1] = new Texture(Gdx.files.internal("player/texture_antman.jpg"));
		skins[2] = new Texture(Gdx.files.internal("player/texture_ironman.jpg"));
		skins[3] = new Texture(Gdx.files.internal("player/texture_kratos.jpg"));
		skins[4] = new Texture(Gdx.files.internal("player/texture_chapolin.jpg"));
		
	}
	
	public void update(float delta){
		personagem[estado].update(delta);
	}
	
	public void chutar(){
		estado = ATTACK;
	}
	public void idle(){
		estado = IDLE;
	}
	
	public void nextSkin(){
		
	}
	
}
