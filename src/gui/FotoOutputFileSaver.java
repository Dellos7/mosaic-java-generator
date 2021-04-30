package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FotoOutputFileSaver extends JFileChooser {
	
	private static final long serialVersionUID = 1419767845975435011L;

	public FotoOutputFileSaver() {
		super();
		this.setFileFilter( new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Imagen PNG (*.png)";
			}
			
			@Override
			public boolean accept(File f) {
				if( f.isDirectory() ) return true;
				return f.getName().toLowerCase().endsWith( ".png" );
			}
		} );
	}
	
	public String obtenerPath() {
		return this.getSelectedFile().getPath();
	}

}
