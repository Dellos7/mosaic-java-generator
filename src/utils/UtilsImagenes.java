package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class UtilsImagenes {
	
	public static Image scaleImage( Image image, int w, int h ) {
		 int nw = image.getWidth(null);
	        int nh = image.getHeight(null);

	        // Regla de 3
	        if(image.getWidth(null) > w) {
	          nw = w;
	          nh = (nw * image.getHeight(null)) / image.getWidth(null);
	        }
	        
	        // Regla de 3
	        if(nh > h) {
	          nh = h;
	          nw = (image.getWidth(null) * nh) / image.getHeight(null);
	        }
	        return image.getScaledInstance(nw,  nh, Image.SCALE_DEFAULT );
	}
	
	public static ImageIcon scaleImage(ImageIcon icon, int w, int h) {
		return new ImageIcon( scaleImage( icon.getImage() , w, h) );
	}
	
	public static BufferedImage castImageToBufferedImage( Image image ) {
		if( image instanceof BufferedImage ) {
			return (BufferedImage) image;
		}
		BufferedImage buffImg = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR );
		Graphics2D g2 = buffImg.createGraphics();
		g2.drawImage( image, 0, 0, null );
		g2.dispose();
		return buffImg;
	}

//	public static ImageIcon scaleImage(ImageIcon icon, int w, int h) {
//        int nw = icon.getIconWidth();
//        int nh = icon.getIconHeight();
//
//        // Regla de 3
//        if(icon.getIconWidth() > w) {
//          nw = w;
//          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
//        }
//        
//        // Regla de 3
//        if(nh > h) {
//          nh = h;
//          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
//        }
//
//        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
//    }

	
}
