package gui;

import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.AplicacionGUI;
import utils.UtilsImagenes;

public class PanelMostrarFotoPrincipal extends JPanel {
	
	private static final long serialVersionUID = -186612773763135368L;
	private JLabel labelImagen;
	
	public PanelMostrarFotoPrincipal() {
		super();
		this.setLayout( new BoxLayout( this, BoxLayout.X_AXIS) );
		this.labelImagen = new JLabel( "Foto principal no cargada" );
		add( this.labelImagen );
		setVisible( true );
	}
	
	public void mostrarFotoPrincipal( BufferedImage imagen ) {
		this.remove( this.labelImagen );
		this.labelImagen = new JLabel( UtilsImagenes.scaleImage( new ImageIcon(imagen), 800, 500) );
		this.labelImagen.setVisible(true);
		this.add( this.labelImagen );
		AplicacionGUI.ventanaPrincipal.pack();
	}

}
