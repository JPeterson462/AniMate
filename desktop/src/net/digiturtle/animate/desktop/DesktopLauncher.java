package net.digiturtle.animate.desktop;

import java.io.File;
import java.nio.file.Files;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.digiturtle.animate.AniMate;

public class DesktopLauncher {
	public static void main (String[] arg) throws Exception {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		File animationJsonFile = FileChooser.selectFile("Select an Animation Definition", "Definition (JSON)", new String[] { "json" });
		
		if (animationJsonFile == null) {
			throw new Exception("No animation JSON file selected!");
		}
		
		String animationJson = String.join("\n", Files.readAllLines(animationJsonFile.toPath()));
		
		new LwjglApplication(new AniMate(animationJson, animationJsonFile.getAbsolutePath().substring(0, animationJsonFile.getAbsolutePath().lastIndexOf('/'))), config);
	}
}
