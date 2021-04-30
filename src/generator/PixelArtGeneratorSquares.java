package generator;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import utils.Logger;

public class PixelArtGeneratorSquares extends Generator {

	public PixelArtGeneratorSquares(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight) throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight);
	}
	
	public PixelArtGeneratorSquares(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight, float factor,
			Logger... loggers) throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, factor, loggers);
		// TODO Auto-generated constructor stub
	}

	public PixelArtGeneratorSquares(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight,
			Logger... loggers) throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, loggers);
		// TODO Auto-generated constructor stub
	}

	public PixelArtGeneratorSquares(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight, float factor) throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, factor);
	}
	
	@Override
	public void generate() throws IOException {
		this.outputImage = this.createOutputImage();
		this.outputImage.getImageGraphics().setColor(
				new Color(0, 0, 0));
		this.outputImage.getImageGraphics().fillRect( 0, 0, this.outputImage.getWidth(), this.outputImage.getHeight());
		this.inputImageCopy = this.createInputImageCopy();
		for( int i = 0; i < this.outputImage.getNumRows(); i++ ) {
			for( int j = 0; j < this.outputImage.getNumCols(); j++ ) {
				RGB avgAreaColor = this.inputImageCopy.averageColor(
						j*this.inputImageCopy.getCellWidth(),
						i*this.inputImageCopy.getCellHeight(),
						this.inputImageCopy.getCellWidth(),
						this.inputImageCopy.getCellHeight() );
				
				// Copiar el color medio de la imagen original en esa sección en forma de rectángulo
				this.outputImage.getImageGraphics().setColor(
						new Color(avgAreaColor.r, avgAreaColor.g, avgAreaColor.b)
				);
				// Dibujar rectángulos
				this.outputImage.getImageGraphics().drawRect(
						j*this.outputImage.getCellWidth(),
						i*this.outputImage.getCellHeight(),
						this.outputImage.getCellWidth(),
						this.outputImage.getCellHeight());
				this.outputImage.getImageGraphics().fillRect(
						j*this.outputImage.getCellWidth(),
						i*this.outputImage.getCellHeight(),
						this.outputImage.getCellWidth(),
						this.outputImage.getCellHeight());
				
			}
		}
		this.outputImage.getImageGraphics().dispose();
	}

}
