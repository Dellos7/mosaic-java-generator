package gui;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelOpcionesGenerador extends JPanel {
	
	private static final int NUM_COLUMNAS_TEXTFIELDS = 3;
	private JTextField tamCeldaWidthTf;
	private JTextField tamCeldaHeightTf;
	private JTextField factorEscaladoTf;
	
	public PanelOpcionesGenerador() {
		super();
		setLayout( new BoxLayout( this , BoxLayout.Y_AXIS) );
		JPanel panelTamCeldas = new JPanel();
		panelTamCeldas.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		JLabel tamCeldaWidthLabel = new JLabel( "Celda width (px): " );
		this.tamCeldaWidthTf = new JTextField( "50", NUM_COLUMNAS_TEXTFIELDS );
		JLabel tamCeldaHeightLabel = new JLabel( "Celda height (px): " );
		this.tamCeldaHeightTf = new JTextField( "50", NUM_COLUMNAS_TEXTFIELDS );
		panelTamCeldas.add( tamCeldaWidthLabel );
		panelTamCeldas.add( this.tamCeldaWidthTf );
		panelTamCeldas.add( tamCeldaHeightLabel );
		panelTamCeldas.add( this.tamCeldaHeightTf );
		add( panelTamCeldas );
		JPanel panelFactorEscalado = new JPanel();
		panelFactorEscalado.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		JLabel factorEscaladoLabel = new JLabel( "Factor de escalado: " );
		this.factorEscaladoTf = new JTextField( "1.0", NUM_COLUMNAS_TEXTFIELDS ); 
		panelFactorEscalado.add( factorEscaladoLabel );
		panelFactorEscalado.add( this.factorEscaladoTf );
		add( panelFactorEscalado );
		setVisible( true );
	}
	
	public int obtenerWidthCelda() {
		return Integer.parseInt( this.tamCeldaWidthTf.getText() );
	}
	
	public int obtenerHeightCelda() {
		return Integer.parseInt( this.tamCeldaHeightTf.getText() );
	}
	
	public float obtenerFactorEscalado() {
		return Float.parseFloat( this.factorEscaladoTf.getText() );
	}

}
