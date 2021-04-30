package generator;

import java.awt.Color;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import utils.Logger;
import utils.UtilsImagenes;

public abstract class Generator {
	
	protected File imgFile;
	protected File[] tilesImgsFiles;
	protected Image inputImage;
	protected Image inputImageCopy;
	protected Image outputImage;
	protected Image[] tilesImages;
	protected int cellWidth;
	protected int cellHeight;
	private float factor;
	private Logger[] loggers;
	
	public Generator( File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight ) throws IOException {
		this( imgFile, tilesImgsFiles, cellWidth, cellHeight, 1.0f );
	}
	
	public Generator( File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight, Logger...loggers ) throws IOException {
		this( imgFile, tilesImgsFiles, cellWidth, cellHeight, 1.0f );
	}
	
	public Generator( File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight, float factor ) throws IOException {
		this( imgFile, tilesImgsFiles, cellWidth, cellHeight, 1.0f, new Logger[0] );
	}
	
	public Generator( File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight, float factor, Logger ...loggers ) throws IOException {
		this.imgFile = imgFile;
		this.tilesImgsFiles = tilesImgsFiles;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.factor = factor;
		this.loggers = loggers;
		BufferedImage inputBuffImg = ImageIO.read( this.imgFile ); 
		this.inputImage = new Image(
				inputBuffImg,
				inputBuffImg.getWidth(),
				inputBuffImg.getHeight(),
				1, 1,
				inputBuffImg.getWidth(),
				inputBuffImg.getHeight()
		);
		if( tilesImgsFiles != null ) {			
			this.tilesImages = getTilesImages( tilesImgsFiles );
		}
	}
	
	protected Image createOutputImage() throws IOException {
		int inputImageWidth = this.inputImage.getWidth();
		int inputImageHeight = this.inputImage.getHeight();
		inputImageWidth = (int) Math.ceil( inputImageWidth*this.factor );
		inputImageHeight = (int) Math.ceil( inputImageHeight*this.factor );
		int numCols = (int) Math.ceil( inputImageWidth/(double) this.cellWidth );
		int outputImageWidth = this.cellWidth*numCols;
		int numRows = (int) Math.ceil( inputImageHeight/(double) this.cellHeight );
		int outputImageHeight = this.cellHeight*numRows;
		this.log("Input image width: " + inputImageWidth,
				"Input image height: " + inputImageHeight,
				"Num rows: " + numRows,
				"Num cols: " + numCols,
				"Output image width: "  + outputImageWidth,
				"Output image height: "  + outputImageHeight );
		BufferedImage outputImgBuff = new BufferedImage( outputImageWidth, outputImageHeight, BufferedImage.TYPE_4BYTE_ABGR );
		return new Image( outputImgBuff, outputImageWidth, outputImageHeight, numRows, numCols, this.cellWidth, this.cellHeight);
	}
	
	protected Image createInputImageCopy() throws IOException{
		Image inputImageCopy = this.createOutputImage();
		// Copiamos la imagen original en la de salida para poder obtener después el color de los píxeles
		inputImageCopy.getImage().getGraphics().drawImage(
				this.inputImage.getImage(),
				0,
				0,
				inputImageCopy.getWidth(),
				inputImageCopy.getHeight(),
				null
		);
		return inputImageCopy;
	}
	
	private Image[] getTilesImages( File[] tilesFiles ) throws IOException {
		if( tilesFiles.length > 0 ) this.log( "Procesando miniaturas..." );
		Image[] tilesImages = new Image[ tilesFiles.length ];
		BufferedImage buffImg = null;
		for( int i = 0; i < tilesFiles.length; i++ ) {
			buffImg = ImageIO.read( tilesFiles[i] );
			buffImg = UtilsImagenes.castImageToBufferedImage( UtilsImagenes.scaleImage( buffImg , this.cellWidth, this.cellHeight) );
			tilesImages[i] = new Image( buffImg, buffImg.getWidth(), buffImg.getHeight(), 1, 1, buffImg.getWidth(), buffImg.getHeight() );
			this.log( "-> Miniatura " + i + " de " + tilesFiles.length + "cargada" );
		}
		if( tilesFiles.length > 0 ) this.log( "Miniaturas procesadas" );
		return tilesImages;
	}
	
	public abstract void generate() throws IOException;
	
	public void save( String outputName ) throws IOException {
		if( outputName == null ) {
			outputName = "output.png";
		}
		ImageIO.write( this.outputImage.getImage() , "png", new File( outputName ) );
	}
	
	protected Image getRandomTile() {
		Random r = new Random();
		int idx = r.nextInt( this.tilesImages.length );
		return this.tilesImages[idx];
	}
	
	public java.awt.Image getOutputBufferedImage() {
		return this.outputImage.getImage();
	}
	
	public void log( String...msgs ) {
		StringBuilder sb = new StringBuilder();
		for( String str: msgs ) {
			sb.append(str + "\n");
		}
		for( Logger logger: this.loggers ) {
			logger.log(sb.toString());
		}
	}
	
	public void clearLogs() {
		for( Logger logger: this.loggers ) {
			logger.clear();
		}
	}
	

}
