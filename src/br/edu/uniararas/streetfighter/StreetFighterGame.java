package br.edu.uniararas.streetfighter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import br.edu.uniararas.streetfighter.screen.AbstractScreen;
import br.edu.uniararas.streetfighter.screen.GameScreen;
import br.edu.uniararas.streetfighter.screen.WelcomeScreen;

public class StreetFighterGame extends Game {

	private AbstractScreen telaAtual;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		telaAtual = new WelcomeScreen();
		telaAtual.setId("WELCOME");
		
	}
	
	public void render(){
		float delta = Gdx.graphics.getDeltaTime();
		telaAtual.render(delta);
		if (telaAtual.isDone()){ // terminei meu trabalho?
			
			if (telaAtual.getId().equals("WELCOME")){
				telaAtual =  new GameScreen();
				telaAtual.setId("GAME");
			}
			else{
				telaAtual = new WelcomeScreen();
				telaAtual.setId("WELCOME");
			}
		}
		
	}
}
