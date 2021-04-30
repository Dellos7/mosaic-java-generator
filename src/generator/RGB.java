package generator;

public class RGB {

	public int r;
	public int g;
	public int b;
	
	public RGB( int bitColor ) {
		this.b = ( bitColor & 0xff ) >> 0;
		this.g = (bitColor & 0xff00) >> 8;
		this.r = ( bitColor & 0xff0000 ) >> 16;
	}
	
	public RGB( int r, int g, int b ) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	@Override
	public String toString() {
		return "RGB("+ this.r + ", " + this.g + ", " + this.b + ")";
	}
	
}
