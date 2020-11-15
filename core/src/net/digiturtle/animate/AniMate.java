package net.digiturtle.animate;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;

public class AniMate extends ApplicationAdapter {
	
	private SpriteBatch batch;
	
	private String animationDefinitionJson;
	private String animationDefinitionPath;
	
	private AnimationDefinition animationDefinition;
	
	private Animator animator;
	
	private OrthographicCamera camera;
	
	public AniMate (String animationDefinitionJson, String pathOfDefinition) {
		this.animationDefinitionJson = animationDefinitionJson;
		animationDefinitionPath = pathOfDefinition;
	}
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3);
		batch = new SpriteBatch();
		Json json = new Json();
		animationDefinition = json.fromJson(AnimationDefinition.class, animationDefinitionJson);
		animator = new Animator(animationDefinition, animationDefinitionPath);
		animator.create();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.7f, .7f, .75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		animator.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		animator.render(batch, (int)(camera.viewportWidth - animationDefinition.frames[0].width) / 2,
				(int)(camera.viewportHeight - animationDefinition.frames[0].height) / 2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
