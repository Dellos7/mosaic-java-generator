package generator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	private BufferedImage image;
	private Graphics imageGraphics;
	private int width;
	private int height;
	private int numRows;
	private int numCols;
	private int cellWidth;
	private int cellHeight;
	
	public Image( BufferedImage image, int width, int height, int numRows, int numCols, int cellWidth, int cellHeight) throws IOException {
		super();
		this.image = image;
		this.setImageGraphics(image.createGraphics());
		this.width = width;
		this.height = height;
		this.numRows = numRows;
		this.numCols = numCols;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getNumRows() {
		return numRows;
	}
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	public int getNumCols() {
		return numCols;
	}
	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}
	public int getCellWidth() {
		return cellWidth;
	}
	public void setCellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
	}
	public int getCellHeight() {
		return cellHeight;
	}
	public void setCellHeight(int cellHeight) {
		this.cellHeight = cellHeight;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Graphics getImageGraphics() {
		return imageGraphics;
	}

	public void setImageGraphics(Graphics imageGraphics) {
		this.imageGraphics = imageGraphics;
	}
	
	public RGB getPixelColor( int x, int y ) {
		int bitColor = this.image.getRGB( x, y );
		return new RGB( bitColor );
	}
	
	public RGB averageColor( int x, int y, int width, int height ) {
		int r = 0, g = 0, b = 0;
		int num = 0;
		for( int i = x; i < x + width; i++ ) {
			for( int j = y; j < y + height; j++ ) {
				RGB pixelColor = getPixelColor(i, j);
				r += Math.pow( pixelColor.r, 2 );
				g += Math.pow( pixelColor.g, 2 );
				b += Math.pow( pixelColor.b, 2 );
				num++;
			}
		}
		return new RGB( (int) Math.sqrt( r/num ), (int) Math.sqrt( g/num ), (int) Math.sqrt( b/num ) );
	}

}
