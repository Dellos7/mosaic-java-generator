package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.AplicacionGUI;

public class PanelCargaFotos extends JPanel {
	
	private static final long serialVersionUID = -8214092563124410171L;
	private JPanel panelBotonesCarga;
	private JButton botonCargaFotoPrincipal;
	private JButton botonCargarMiniaturas;
	private JPanel panelMiniaturasCargadas;
	private JLabel labelMiniaturasCargadas;
	private PanelMostrarFotoPrincipal panelMostrarFotoPrincipal;
	private PanelElegirTipo panelElegirTipo;
	private PanelBotonGenerar panelBotonGenerar;
	private File fotoPrincipal;
	private File[] fotosMiniaturas;
	
	public PanelCargaFotos( PanelMostrarFotoPrincipal panelMostrarFotoPrincipal ) {
		super();
		this.panelMostrarFotoPrincipal = panelMostrarFotoPrincipal;
		setLayout( new BoxLayout( this , BoxLayout.Y_AXIS) );
		this.panelBotonesCarga = new JPanel();
		this.panelBotonesCarga.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		this.botonCargaFotoPrincipal = new JButton( "Cargar foto principal" );
		this.botonCargaFotoPrincipal.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				cargarFotoPrincipal();
			}
		});
		this.botonCargarMiniaturas = new JButton( "Cargar miniaturas" );
		this.botonCargarMiniaturas.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarMiniaturas();
			}
		} );
		this.botonCargarMiniaturas.setVisible(false);
		this.panelBotonesCarga.add( this.botonCargaFotoPrincipal );
		this.panelBotonesCarga.add( this.botonCargarMiniaturas );
		add( this.panelBotonesCarga );
		this.labelMiniaturasCargadas = new JLabel( "Miniaturas no cargadas" );
		this.panelMiniaturasCargadas = new JPanel();
		this.panelMiniaturasCargadas.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		this.panelMiniaturasCargadas.add( this.labelMiniaturasCargadas );
		this.panelMiniaturasCargadas.setVisible( false );
		add( this.panelMiniaturasCargadas );
		setVisible( true );
	}
	
	public void mostrarBotonCargarMiniaturas( boolean mostrar ) {
		this.botonCargarMiniaturas.setVisible( mostrar );
		this.panelMiniaturasCargadas.setVisible( mostrar );
		AplicacionGUI.ventanaPrincipal.pack();
	}
	
	public void cargarFotoPrincipal() {
		try {
			FotoPrincipalFileChooser fpfc = new FotoPrincipalFileChooser();
			int accion = fpfc.showOpenDialog( this );
			switch( accion ) {
				// Foto principal elegida
				case JFileChooser.APPROVE_OPTION:
					this.fotoPrincipal = fpfc.obtenerFicheroElegido();
					this.panelMostrarFotoPrincipal.mostrarFotoPrincipal( ImageIO.read( this.fotoPrincipal ) );
					break;
				default:
					break;
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}
		if( this.fotoPrincipal != null &&
				( this.panelElegirTipo.tipoGenerador == TipoGenerador.PIXELART_CUADRADO ||
					this.panelElegirTipo.tipoGenerador == TipoGenerador.PIXELART_REDONDO ) ){
			this.panelBotonGenerar.activarBotonGenerar(true);
		} else if( this.fotoPrincipal != null &&
				this.fotosMiniaturas != null &&
				this.fotosMiniaturas.length > 0 &&
					( this.panelElegirTipo.tipoGenerador == TipoGenerador.PIXELART_IMAGEN_AVG_CIRCULOS ||
						this.panelElegirTipo.tipoGenerador == TipoGenerador.PIXELART_IMAGEN_AVG_CUADRADOS ||
						this.panelElegirTipo.tipoGenerador == TipoGenerador.PIXELART_IMAGEN_COLOR_PIXEL_EXACTO
					)) {
				this.panelBotonGenerar.activarBotonGenerar(true);
			}
	}
	
	public void cargarMiniaturas() {
		FotoPrincipalFileChooser fpfc = new FotoPrincipalFileChooser();
		fpfc.setMultiSelectionEnabled(true);
		int accion = fpfc.showOpenDialog( this );
		switch( accion ) {
			case JFileChooser.APPROVE_OPTION:
				this.fotosMiniaturas = fpfc.getSelectedFiles();
				this.labelMiniaturasCargadas.setText( "Miniaturas cargadas" );
				break;
			default:
				break;
		}
		if( this.fotoPrincipal != null &&
			this.fotosMiniaturas != null &&
			this.fotosMiniaturas.length > 0 &&
				( this.panelElegirTipo.tipoGenerador == TipoGenerador.PIXELART_IMAGEN_AVG_CIRCULOS ||
					this.panelElegirTipo.tipoGenerador == TipoGenerador.PIXELART_IMAGEN_AVG_CUADRADOS ||
					this.panelElegirTipo.tipoGenerador == TipoGenerador.PIXELART_IMAGEN_COLOR_PIXEL_EXACTO
				)) {
			this.panelBotonGenerar.activarBotonGenerar(true);
		}
	}

	public File getFotoPrincipal() {
		return fotoPrincipal;
	}

	public void setFotoPrincipal(File fotoPrincipal) {
		this.fotoPrincipal = fotoPrincipal;
	}

	public File[] getFotosMiniaturas() {
		return fotosMiniaturas;
	}

	public void setFotosMiniaturas(File[] fotosMiniaturas) {
		this.fotosMiniaturas = fotosMiniaturas;
	}

	public PanelElegirTipo getPanelElegirTipo() {
		return panelElegirTipo;
	}

	public void setPanelElegirTipo(PanelElegirTipo panelElegirTipo) {
		this.panelElegirTipo = panelElegirTipo;
	}

	public PanelBotonGenerar getPanelBotonGenerar() {
		return panelBotonGenerar;
	}

	public void setPanelBotonGenerar(PanelBotonGenerar panelBotonGenerar) {
		this.panelBotonGenerar = panelBotonGenerar;
	}

}
