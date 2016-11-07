package br.edu.uniararas.streetfighter.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationDesc;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationListener;

public class GameObject extends ModelInstance {

	private AnimationController animationController;
	private boolean animationFinished;
	
	public GameObject(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
		animationFinished = false;
		
		animationController = new AnimationController(this);
		animationController.setAnimation(this.animations.get(0).id, -1, 
				new AnimationListener() {
					
					@Override
					public void onLoop(AnimationDesc animation) {
						// TODO Auto-generated method stub
						animationFinished = true;
					}
					
					@Override
					public void onEnd(AnimationDesc animation) {
						// TODO Auto-generated method stub
						animationFinished = true;
					}
				});	
	}
	
	public void update(float delta){
		animationController.update(delta);
	}
	
	public void resetAnimation(){
		animationFinished = false;
	}
	public boolean isFinished(){
		return this.animationFinished;
	}

}
