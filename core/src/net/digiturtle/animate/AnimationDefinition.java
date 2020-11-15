package net.digiturtle.animate;

public class AnimationDefinition {
	
	public String spritesheet;
	
	public float timePerFrame;
	
	public boolean pingPong;
	
	public FrameDefinition[] frames;
	
	public static class FrameDefinition {
		
		public int x, y, width, height;
		
		public int dx, dy;
		
	}

}
