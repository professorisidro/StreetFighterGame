package br.edu.uniararas.streetfighter.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;

public class GameObject extends ModelInstance {

	private AnimationController animationController;
	public GameObject(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
		
		animationController = new AnimationController(this);
		animationController.setAnimation(this.animations.get(0).id, -1);		
	}
	
	public void update(float delta){
		animationController.update(delta);
	}
	

}
