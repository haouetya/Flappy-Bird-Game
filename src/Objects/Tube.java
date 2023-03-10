package Objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tube {

	// VARIABLES
	private int width;
	private int height;
	private int x;
	private int y;
	private String strImage;
	private ImageIcon icoTube;
	private Image imgTube;
	
	
	// CONSTRUCTOR
	public Tube(int x, int y, String strImage){
		this.width = 50;
		this.height = 300;
		this.x = x;
		this.y = y;
		this.strImage = strImage;
		
		this.icoTube = new ImageIcon(getClass().getResource(this.strImage));
		this.imgTube = this.icoTube.getImage();
	}


	// GETTERS
	public int getX() {return x;}

	public int getY() {return y;}

	public int getWidth() {return width;}
	
	public int getHeight() {return height;}
	
	public Image getImgTube() {return imgTube;}
	
	
	// SETTERS
	public void setX(int x) {this.x = x;}

	public void setY(int y) {this.y = y;}
	
}
