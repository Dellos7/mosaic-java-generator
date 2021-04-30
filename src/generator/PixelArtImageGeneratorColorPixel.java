package generator;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import utils.Logger;

/**
 * Se compone la imagen final con imágenes aleatorias del directorio de "tiles"
 * Se colorea cada píxel con el color de la imagen original, de tal forma que
 * la imagen original se ve mucho más nítida
 * TODO: ¿probar a redondear imágenes?
 * @author david
 *
 */
public class PixelArtImageGeneratorColorPixel extends Generator {
	
	public PixelArtImageGeneratorColorPixel(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight )
			throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight);
	}
	
	public PixelArtImageGeneratorColorPixel(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight, float factor)
			throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, factor);
	}

	public PixelArtImageGeneratorColorPixel(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight,
			float factor, Logger... loggers) throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, factor, loggers);
	}

	public PixelArtImageGeneratorColorPixel(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight,
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
				
				// Pintar porción de la imagen con la nueva
				this.outputImage.getImageGraphics().drawImage(
//						this.tilesImages[0].getImage(),
						this.getRandomTile().getImage(),
						j*this.outputImage.getCellWidth(),
						i*this.outputImage.getCellHeight(),
						this.outputImage.getCellWidth(),
						this.outputImage.getCellHeight(),
						null
				);
				
				for( int x = j*this.inputImageCopy.getCellWidth(); x < j*this.inputImageCopy.getCellWidth() + this.inputImageCopy.getCellWidth(); x++ ) {
					for( int y = i*this.inputImageCopy.getCellHeight(); y < i*this.inputImageCopy.getCellHeight() + this.inputImageCopy.getCellHeight(); y++ ) {
						RGB rgb = this.inputImageCopy.getPixelColor(x, y);
						// El color del píxel de la imagen original
						this.outputImage.getImageGraphics().setColor(
								new Color(
										rgb.r/255.0f,
										rgb.g/255.0f,
										rgb.b/255.0f,
										.6f)
						);
						// Dibujar rectángulo de tamaño 1 pixel con el color especificado
						this.outputImage.getImageGraphics().fillRect(
								x,
								y,
								1,
								1);
					}
				}
				
			}
		}
		this.outputImage.getImageGraphics().dispose();
	}
	
	

}
