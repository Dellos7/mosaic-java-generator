package generator;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import utils.Logger;

/**
 * Se compone la imagen final con imágenes aleatorias del directorio de "tiles"
 * Cada imagen se colorea con el color medio del área de la imagen original
 * donde se va a pegar la imagen pequeña
 * La imagen original no se ve demasiado  nítida
 * @author david
 *
 */
public class PixelArtImageGeneratorAvgSquares extends Generator {

	public PixelArtImageGeneratorAvgSquares(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight)
			throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, 1.0f);
	}
	
	public PixelArtImageGeneratorAvgSquares(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight, float factor)
			throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, factor);
	}
	
	public PixelArtImageGeneratorAvgSquares(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight,
			float factor, Logger... loggers) throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, factor, loggers);
	}

	public PixelArtImageGeneratorAvgSquares(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight,
			Logger... loggers) throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, loggers);
	}

	@Override
	public void generate() throws IOException {
		this.outputImage = this.createOutputImage();
		this.inputImageCopy = this.createInputImageCopy();
		for( int i = 0; i < this.outputImage.getNumRows(); i++ ) {
			for( int j = 0; j < this.outputImage.getNumCols(); j++ ) {
				RGB avgAreaColor = this.inputImageCopy.averageColor(
						j*this.inputImageCopy.getCellWidth(),
						i*this.inputImageCopy.getCellHeight(),
						this.inputImageCopy.getCellWidth(),
						this.inputImageCopy.getCellHeight() );

				// Imagen circular
//				BufferedImage tileCopy = new BufferedImage( this.tilesImages[0].getImage().getWidth(), this.tilesImages[0].getImage().getHeight(), BufferedImage.TYPE_4BYTE_ABGR );
//				Graphics2D g2 = tileCopy.createGraphics();
//				g2.setClip( new Ellipse2D.Float(0, 0, tileCopy.getWidth(), tileCopy.getHeight()) );
//				g2.drawImage(this.tilesImages[0].getImage(), 0, 0, tileCopy.getWidth(), tileCopy.getHeight(), null );
//				g2.dispose();
//				this.outputImage.getImageGraphics().drawImage(
//						tileCopy,
//						j*this.outputImage.getCellWidth(),
//						i*this.outputImage.getCellHeight(),
//						this.outputImage.getCellWidth(),
//						this.outputImage.getCellHeight(),
//						null
//				);
				
				// Pintar porción de la imagen con la nueva
				this.outputImage.getImageGraphics().drawImage(
						this.getRandomTile().getImage(),
						j*this.outputImage.getCellWidth(),
						i*this.outputImage.getCellHeight(),
						this.outputImage.getCellWidth(),
						this.outputImage.getCellHeight(),
						null
				);
				
				// Copiar el color medio de la imagen original en esa sección en forma de rectángulo
				this.outputImage.getImageGraphics().setColor(
						new Color(
								avgAreaColor.r/255.0f,
								avgAreaColor.g/255.0f,
								avgAreaColor.b/255.0f,
								.6f)
				);
				// Dibujar rectángulos
				this.outputImage.getImageGraphics().fillRect(
						j*this.outputImage.getCellWidth(),
						i*this.outputImage.getCellHeight(),
						this.outputImage.getCellWidth(),
						this.outputImage.getCellHeight());
//				this.outputImage.getImageGraphics().fillRoundRect(
//						j*this.outputImage.getCellWidth(),
//						i*this.outputImage.getCellHeight(),
//						this.outputImage.getCellWidth(),
//						this.outputImage.getCellHeight(), 50, 50);
				
				
			}
		}
		this.outputImage.getImageGraphics().dispose();
	}
	
	

}
