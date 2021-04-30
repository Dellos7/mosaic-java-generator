package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import generator.Generator;
import utils.UtilsImagenes;

public class ModalMostrarFotoOutput extends JDialog {
	
	private static final long serialVersionUID = -8087872367580303809L;
	private JLabel imagen;
	private JButton botonGuardar;
	
	public ModalMostrarFotoOutput( Generator generador ) {
		super();
		final ModalMostrarFotoOutput self = this;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle( "Resultado" );
		setLayout( new BoxLayout( getContentPane() , BoxLayout.Y_AXIS) );
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		Image outputImage = generador.getOutputBufferedImage();
		ImageIcon imagenEscalada = UtilsImagenes.scaleImage(
				new ImageIcon( outputImage ),
				Math.min( outputImage.getWidth(null), screenWidth-100 ),
				Math.min( outputImage.getHeight(null) , screenHeight-200 )
		);
		this.imagen = new JLabel( imagenEscalada );
		this.botonGuardar = new JButton( "Guardar" );
		this.botonGuardar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FotoOutputFileSaver fofs = new FotoOutputFileSaver();
				int res = fofs.showSaveDialog( self );
				switch(res) {
				case FotoOutputFileSaver.APPROVE_OPTION:
					try {
						generador.save( fofs.obtenerPath() );
						self.dispose();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
					break;
				default:
					break;
				}
			}
		});
		this.add( this.imagen );
		this.add( this.botonGuardar );
		this.setVisible(true);
		this.pack();
	}

}
