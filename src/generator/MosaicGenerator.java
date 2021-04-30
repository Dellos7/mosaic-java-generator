package generator;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class MosaicGenerator extends Generator {

	public MosaicGenerator(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight) throws IOException {
		this(imgFile, tilesImgsFiles, cellWidth, cellHeight, 1.0f);
	}
	
	public MosaicGenerator(File imgFile, File[] tilesImgsFiles, int cellWidth, int cellHeight, float factor) throws IOException {
		super(imgFile, tilesImgsFiles, cellWidth, cellHeight, factor);
	}
	
	@Override
	public void generate() throws IOException {
		this.outputImage = this.createOutputImage();
		for( int i = 0; i < this.outputImage.getNumRows(); i++ ) {
			for( int j = 0; j < this.outputImage.getNumCols(); j++ ) {
				RGB avgAreaColor = this.outputImage.averageColor(
						j*this.outputImage.getCellWidth(),
						i*this.outputImage.getCellHeight(),
						this.outputImage.getCellWidth(),
						this.outputImage.getCellHeight() );
				
				// TODO: procesar tiles y obtener su color medio, calcular la mejor...
				// Pintar porción de la imagen con la nueva
				this.outputImage.getImageGraphics().drawImage(
						this.tilesImages[0].getImage(),
						i*this.outputImage.getCellWidth(),
						j*this.outputImage.getCellHeight(),
						this.outputImage.getCellWidth(),
						this.outputImage.getCellHeight(),
						null
				);
				
			}
		}
		this.outputImage.getImageGraphics().dispose();
	}

}
