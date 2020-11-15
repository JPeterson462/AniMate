package net.digiturtle.animate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.digiturtle.animate.AnimationDefinition.FrameDefinition;

public class Animator {
	
	private class Frame {

		public int x, y, width, height, dx, dy;
		
		public TextureRegion textureRegion;
		
		private Frame (int x, int y, int width, int height, int dx, int dy) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.dx = dx;
			this.dy = dy;
		}
		
	}
	
	private Frame[] frames;
	private float length, timePerFrame;
	private String spritesheetPath;
	private Texture spritesheet;
	private float t;
	
	public Animator (AnimationDefinition definition, String definitionPath) {
		spritesheetPath = definitionPath + "/" + definition.spritesheet;
		timePerFrame = definition.timePerFrame;
		if (definition.pingPong) {
			frames = new Frame[definition.frames.length * 2 - 2];
			length = frames.length * definition.timePerFrame;
			int dx = 0, dy = 0, j = 0;
			for (int i = 0; i < definition.frames.length; i++) {
				FrameDefinition frame = definition.frames[i];
				dx += frame.dx;
				dy += frame.dy;
				frames[j] = new Frame(frame.x, frame.y, frame.width, frame.height, dx, dy);
				j++;
			}
			for (int i = definition.frames.length - 1 - 1; i >= 1; i--) {
				FrameDefinition frame = definition.frames[i];
				dx += frame.dx;
				dy += frame.dy;
				frames[j] = new Frame(frame.x, frame.y, frame.width, frame.height, dx, dy);
				j++;
			}
		} else {
			frames = new Frame[definition.frames.length];
			length = frames.length * definition.timePerFrame;
			int dx = 0, dy = 0, j = 0;
			for (int i = 0; i < frames.length; i++) {
				FrameDefinition frame = definition.frames[i];
				dx += frame.dx;
				dy += frame.dy;
				frames[j] = new Frame(frame.x, frame.y, frame.width, frame.height, dx, dy);
				j++;	
			}
		}
	}
	
	public void create () {
		spritesheet = new Texture(Gdx.files.absolute(spritesheetPath));
		for (int i = 0; i < frames.length; i++) {
			Frame frame = frames[i];
			frame.textureRegion = new TextureRegion(spritesheet, frame.x, frame.y, frame.width, frame.height);
		}
		t = 0;
	}
	
	public void update (float dt) {
		t += dt;
	}
	
	public void render (SpriteBatch spriteBatch, int x, int y) {
		float offset = t % length;
		int index = (int) (offset / timePerFrame);
		index = Math.min(frames.length - 1, index);
		Frame frame = frames[index];
		spriteBatch.draw(frame.textureRegion, x + frame.dx, y + frame.dy);
	}

}
