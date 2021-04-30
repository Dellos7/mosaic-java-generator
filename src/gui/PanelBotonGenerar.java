package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import generator.Generator;
import generator.PixelArtGeneratorCircles;
import generator.PixelArtGeneratorSquares;
import generator.PixelArtImageGeneratorAvgCircles;
import generator.PixelArtImageGeneratorAvgSquares;
import generator.PixelArtImageGeneratorColorPixel;
import main.AplicacionGUI;
import utils.ConsoleLogger;

public class PanelBotonGenerar extends JPanel {
	
	private PanelElegirTipo panelElegirTipo;
	private PanelOpcionesGenerador panelOpcionesGenerador;
	private PanelCargaFotos panelCargaFotos;
	private JButton botonGenerar;
	
	public PanelBotonGenerar(
			PanelElegirTipo panelElegirTipo,
			PanelOpcionesGenerador panelOpcionesGenerador,
			PanelCargaFotos panelCargaFotos ) {
		super();
		this.panelElegirTipo = panelElegirTipo;
		this.panelOpcionesGenerador = panelOpcionesGenerador;
		this.panelCargaFotos = panelCargaFotos;
		setLayout( new FlowLayout( FlowLayout.CENTER ) );
		this.botonGenerar = new JButton( "Generar" );
		this.botonGenerar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				generarImagen();
			}
		});
		this.botonGenerar.setEnabled(false);
		add( this.botonGenerar );
		setVisible(true);
	}
	
	public void generarImagen() {
		int celdaWidth = this.panelOpcionesGenerador.obtenerWidthCelda();
		int celdaHeight = this.panelOpcionesGenerador.obtenerHeightCelda();
		float factorEscalado = this.panelOpcionesGenerador.obtenerFactorEscalado();
		// Lanzamos en otro hilo el procesamiento, y utilizaremos 
		// SwingUtilities.invokeLater para actualizar la GUI
		new Thread( new Runnable() {
			@Override
			public void run() {
				Generator generador = null;
				try {	
					switch( panelElegirTipo.tipoGenerador ) {
					case PIXELART_CUADRADO:
						generador = new PixelArtGeneratorSquares(
								panelCargaFotos.getFotoPrincipal(),
								null, celdaWidth, celdaHeight, factorEscalado,
								new ConsoleLogger(), AplicacionGUI.modalMostrarProceso );
						break;
					case PIXELART_REDONDO:
						generador = new PixelArtGeneratorCircles(
								panelCargaFotos.getFotoPrincipal(),
								null, celdaWidth, celdaHeight, factorEscalado,
								new ConsoleLogger(), AplicacionGUI.modalMostrarProceso );
						break;
					case PIXELART_IMAGEN_AVG_CIRCULOS:
						generador = new PixelArtImageGeneratorAvgCircles(
								panelCargaFotos.getFotoPrincipal(),
								panelCargaFotos.getFotosMiniaturas(),
								celdaWidth, celdaHeight, factorEscalado,
								new ConsoleLogger(), AplicacionGUI.modalMostrarProceso );
						break;
					case PIXELART_IMAGEN_AVG_CUADRADOS:
						generador = new PixelArtImageGeneratorAvgSquares(
								panelCargaFotos.getFotoPrincipal(),
								panelCargaFotos.getFotosMiniaturas(),
								celdaWidth, celdaHeight, factorEscalado,
								new ConsoleLogger(), AplicacionGUI.modalMostrarProceso );
						break;
					case PIXELART_IMAGEN_COLOR_PIXEL_EXACTO:
						generador = new PixelArtImageGeneratorColorPixel(
								panelCargaFotos.getFotoPrincipal(),
								panelCargaFotos.getFotosMiniaturas(),
								celdaWidth, celdaHeight, factorEscalado,
								new ConsoleLogger(), AplicacionGUI.modalMostrarProceso );
						break;
					default:
						break;	
					}
					generador.clearLogs();
					generador.log( "Generando imagen final..." );
					generador.generate();								
					new ModalMostrarFotoOutput( generador );
					generador.log( "¡Imagen generada!" );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}
		}).start();
	}
	
	public void activarBotonGenerar( boolean activar ) {
		this.botonGenerar.setEnabled( activar );
	}

}
