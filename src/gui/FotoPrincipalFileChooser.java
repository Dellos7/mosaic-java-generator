package gui;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FotoPrincipalFileChooser extends JFileChooser {
	
	private static final long serialVersionUID = 3414644237859106731L;

	public FotoPrincipalFileChooser() {
		super();
		this.setFileFilter( new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Archivos de imagen (.jpg, .jpeg, .png)";
			}
			
			@Override
			public boolean accept(File f) {
				if( f.isDirectory() ) {
					return true;
				}
				String nombreFich = f.getName().toLowerCase();
				return nombreFich.endsWith( ".jpg" )
						|| nombreFich.endsWith( ".jpeg" )
						|| nombreFich.endsWith( ".png" );
			}
		} );
	}
	
	public String obtenerRuta() {
		return this.getSelectedFile().getPath();
	}
	
	public File obtenerFicheroElegido() {
		return this.getSelectedFile();
	}

}
