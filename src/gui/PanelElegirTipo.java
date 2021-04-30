package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelElegirTipo extends JPanel {

	private static final long serialVersionUID = 828898625841846094L;
	private JRadioButton pixelartCuadradoRadio;
	private JRadioButton pixelartRedondoRadio;
	private JRadioButton pixelartImagenAvgCuadrados;
	private JRadioButton pixelartImagenAvgCirculos;
	private JRadioButton pixelartImagenAvgColorPixelExacto;
	private PanelCargaFotos panelCargaFotos;
	public TipoGenerador tipoGenerador;
	
	public PanelElegirTipo( PanelCargaFotos panelCargaFotos ) {
		super();
		this.panelCargaFotos = panelCargaFotos;
		final PanelCargaFotos pcf = this.panelCargaFotos;
		setLayout( new BoxLayout( this , BoxLayout.Y_AXIS) );
		setAlignmentX( JPanel.CENTER_ALIGNMENT );
		ButtonGroup bg = new ButtonGroup();
		this.pixelartCuadradoRadio = new JRadioButton( "Pixelart colores cuadrados" );
		this.pixelartCuadradoRadio.setSelected( true );
		this.tipoGenerador = TipoGenerador.PIXELART_CUADRADO;
		this.pixelartCuadradoRadio.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pcf.mostrarBotonCargarMiniaturas( false );
				tipoGenerador = TipoGenerador.PIXELART_CUADRADO;
			}
		});
		this.pixelartRedondoRadio = new JRadioButton( "Pixelart colores redondos" );
		this.pixelartRedondoRadio.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pcf.mostrarBotonCargarMiniaturas( false );
				tipoGenerador = TipoGenerador.PIXELART_REDONDO;
			}
		});
		this.pixelartImagenAvgCuadrados = new JRadioButton( "Mosaico con imágenes cuadradas (media de colores)" );
		this.pixelartImagenAvgCuadrados.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pcf.mostrarBotonCargarMiniaturas( true );
				tipoGenerador = TipoGenerador.PIXELART_IMAGEN_AVG_CUADRADOS;
			}
		});
		this.pixelartImagenAvgCirculos = new JRadioButton( "Mosaico con imágenes circulares (media de colores)" );
		this.pixelartImagenAvgCirculos.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pcf.mostrarBotonCargarMiniaturas( true );
				tipoGenerador = TipoGenerador.PIXELART_IMAGEN_AVG_CIRCULOS;
			}
		});
		this.pixelartImagenAvgColorPixelExacto = new JRadioButton( "Mosaico con imágenes cuadradas (color exacto del píxel)" );
		this.pixelartImagenAvgColorPixelExacto.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pcf.mostrarBotonCargarMiniaturas( true );
				tipoGenerador = TipoGenerador.PIXELART_IMAGEN_COLOR_PIXEL_EXACTO;
			}
		});
		bg.add( this.pixelartCuadradoRadio );
		bg.add( this.pixelartRedondoRadio );
		bg.add( this.pixelartImagenAvgCuadrados );
		bg.add( this.pixelartImagenAvgCirculos );
		bg.add( this.pixelartImagenAvgColorPixelExacto );
		
		add( this.pixelartCuadradoRadio );
		add( this.pixelartRedondoRadio );
		add( this.pixelartImagenAvgCuadrados );
		add( this.pixelartImagenAvgCirculos );
		add( this.pixelartImagenAvgColorPixelExacto );
	}

}
