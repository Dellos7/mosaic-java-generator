package gui;

import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import utils.Logger;

public class ModalMostrarProceso extends JDialog implements Logger {
	
	private static final long serialVersionUID = -139933092961606696L;
	private JTextArea textoProcesoTa;
	private StringBuilder sb;
	public List<String> buffer;
	
	public ModalMostrarProceso() {
		super();
		this.setTitle( "Procesando..." );
		this.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		this.sb = new StringBuilder();
		this.textoProcesoTa = new JTextArea();
		this.textoProcesoTa.setEnabled(false);
		this.add( this.textoProcesoTa );
		this.setVisible(false);
		this.buffer = new LinkedList<>();
	}
	
	public void eliminarDeBuffer(int idx) {
		this.buffer.remove(idx);
	}
	
	public void registrarInformacion( String info ) {
		this.sb.append( info );
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {				
				textoProcesoTa.setText( sb.toString() );
				pack();
				mostrar(true);
			}
		});
	}
	
	public void limpiar() {
		this.sb = new StringBuilder();
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {				
				textoProcesoTa.setText("");
			}
		});
	}
	
	private void mostrar( boolean mostrar ) {
		this.setVisible( mostrar );
	}

	@Override
	public void log(String msg) {
		this.registrarInformacion(msg);
	}
	
	@Override
	public void clear() {
		this.limpiar();
	}

}
