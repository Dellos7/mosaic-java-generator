package gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {
	
	private static final long serialVersionUID = -6535985200778052240L;
	private PanelElegirTipo panelElegirTipo;
	private PanelCargaFotos panelCargaFotos;
	private PanelMostrarFotoPrincipal panelMostrarFotoPrincipal;
	private PanelOpcionesGenerador panelOpcionesGenerador;
	private PanelBotonGenerar panelBotonGenerar;
	
	public VentanaPrincipal() {
		super( "PixelArt & Mosaic image Generator" );
		BoxLayout bl = new BoxLayout( getContentPane(), BoxLayout.Y_AXIS ); 
		setLayout( bl );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.panelMostrarFotoPrincipal = new PanelMostrarFotoPrincipal();
		this.panelCargaFotos = new PanelCargaFotos( this.panelMostrarFotoPrincipal );
		this.panelElegirTipo = new PanelElegirTipo( this.panelCargaFotos );
		this.panelCargaFotos.setPanelElegirTipo( this.panelElegirTipo );
		this.panelOpcionesGenerador = new PanelOpcionesGenerador();
		this.panelBotonGenerar = new PanelBotonGenerar( this.panelElegirTipo, this.panelOpcionesGenerador, this.panelCargaFotos );
		this.panelCargaFotos.setPanelBotonGenerar( this.panelBotonGenerar );
		add( this.panelElegirTipo );
		add( this.panelOpcionesGenerador );
		add( this.panelCargaFotos );
		add( this.panelMostrarFotoPrincipal );
		add( this.panelBotonGenerar );
	}

}
