package net.digiturtle.animate.desktop;

import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileChooser {
	
	public static File selectFile (String title, final String description, final String[] extensions) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		fileChooser.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return Arrays.binarySearch(extensions, f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf('.') + 1).toLowerCase()) >= 0;
			}

			@Override
			public String getDescription() {
				return description;
			}
			
		});
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file;
		}
		return null;
	}

}
