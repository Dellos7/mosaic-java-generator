package main;

import java.io.File;
import java.io.IOException;

import generator.Generator;
import generator.PixelArtGeneratorSquares;
import generator.PixelArtImageGeneratorAvgSquares;
import generator.PixelArtImageGeneratorColorPixel;
import generator.PixelArtImageGeneratorAvgCircles;

public class Main {
	
	public static void main( String[] args ) {
		AplicacionGUI.mostrar();
	}
	
	private static void doIt() {
		File img = new File( "villarreal.jpg" );
		if( img != null ) {
			try {
				Generator mg = new PixelArtImageGeneratorAvgCircles( img , getTiles(), 80, 80, 4 );
				mg.generate();
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}
	}
	
	private static File[] getTiles() {
		File[] tiles = new File( "villarreal_tiles" ).listFiles();
		return tiles;
	}

}
