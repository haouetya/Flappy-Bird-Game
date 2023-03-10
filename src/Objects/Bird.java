package Objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bird implements Runnable {

	// VARIABLES
	private int width;
	private int height;
	private int x;
	private int y;
	private int dy;
	private String strImage;
	private ImageIcon icoBird;
	private Image imgBird;

	private final int PAUSE = 10;

	// CONSTRUCTEUR
	public Bird(int x, int y, String strImage) {

		this.width = 34;
		this.height = 24;
		this.x = x;
		this.y = y;
		this.strImage = strImage;
		this.icoBird = new ImageIcon(getClass().getResource(this.strImage));
		this.imgBird = this.icoBird.getImage();

		Thread fly = new Thread(this);
		fly.start();
	}

	// GETTERS
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Image getImgBird() {
		return imgBird;
	}

	// SETTERS
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	// METHODS
	public void ascend() {
		this.dy = 50;
	}

	private void descend(int dy) {
		this.dy--;
		if (dy > 20) {
			this.icoBird = new ImageIcon(getClass().getResource("/Images/bird.gif"));
			this.imgBird = this.icoBird.getImage();
			this.y = this.y - 3;
		} else if (dy > 10) {
			this.y = this.y - 2;
		} else if (dy > 2) {
			this.y = this.y - 1;
		}
	}

	@Override
	public void run() {
		while (true) {
			this.descend(dy);
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
			}
		}

	}
}
