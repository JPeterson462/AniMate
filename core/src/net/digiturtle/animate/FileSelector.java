package net.digiturtle.animate;

import java.io.InputStream;

@FunctionalInterface
public interface FileSelector {

	public InputStream select(String path);
	
}
