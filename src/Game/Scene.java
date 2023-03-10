package Game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Objects.Bird;
import Objects.Tube;

@SuppressWarnings("serial")
public class Scene extends JPanel implements Serializable {

	// VARIABLES
	private ImageIcon icoBackground;
	private Image imgBackground;
	public ArrayList<Tube> tubes;
	private int bestScore;
	private int score;
	private Font font;
	public Bird flappyBird;
	private final int BACKGROUND_WIDTH = 253;
	private final int DISTANCE_BETWEEN_TUBES = 300;
	private final int GAP_BETWEEN_UP_DOWN_TUBES = 120;

	public int xBackground;
	public boolean endOfGame;

	private Random random;

	// CONSTRUCTOR
	public Scene() {

		super();
		this.icoBackground = new ImageIcon(getClass().getResource("/Images/background.png"));
		this.imgBackground = this.icoBackground.getImage();
		this.score = 0;
		Path path = Paths.get("C:\\Users\\HAOUETYA\\eclipse-workspace\\FlappyBird_Final/bestScore.txt");

		if (Files.exists(path)) {
			try {
				this.bestScore = loadBestScore();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} else {
			bestScore = 0;
			try {
				this.saveBestScore();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}

		this.xBackground = 0;
		this.endOfGame = false;

		ArrayList<Tube> tubes = new ArrayList<Tube>();

		Tube TubeUp1 = new Tube(400, -150, "/images/TubeUp.png");
		Tube TubeDown1 = new Tube(400, 250, "/images/TubeDown.png");
		Tube TubeUp2 = new Tube(400 + this.DISTANCE_BETWEEN_TUBES, -100, "/images/TubeUp.png");
		Tube TubeDown2 = new Tube(400 + this.DISTANCE_BETWEEN_TUBES, 300, "/images/TubeDown.png");
		Tube TubeUp3 = new Tube(400 + this.DISTANCE_BETWEEN_TUBES * 2, -120, "/images/TubeUp.png");
		Tube TubeDown3 = new Tube(400 + this.DISTANCE_BETWEEN_TUBES * 2, 280, "/images/TubeDown.png");
		tubes.add(TubeUp1);
		tubes.add(TubeDown1);
		tubes.add(TubeUp2);
		tubes.add(TubeDown2);
		tubes.add(TubeUp3);
		tubes.add(TubeDown3);
		this.tubes = tubes;

		this.flappyBird = new Bird(100, 150, "/Images/bird.gif");

		random = new Random();

		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Keyboard());

		this.score = 0;
		this.font = new Font("TimesNewRoman", Font.PLAIN, 30);

		Thread chronoScreen = new Thread(new Chrono());
		chronoScreen.start();

	}

	// METHODS

	private void tubeShifting(Graphics g) {
		// Tube1
		this.tubes.get(0).setX(this.tubes.get(0).getX() - 1);
		this.tubes.get(1).setX(this.tubes.get(0).getX());

		if (this.tubes.get(0).getX() == -100) {
			this.tubes.get(0).setX(this.tubes.get(4).getX() + this.DISTANCE_BETWEEN_TUBES);
			this.tubes.get(0).setY(-100 - 10 * this.random.nextInt(18));
			this.tubes.get(1)
					.setY(this.tubes.get(0).getY() + this.tubes.get(0).getHeight() + this.GAP_BETWEEN_UP_DOWN_TUBES);
		}
		g.drawImage(this.tubes.get(0).getImgTube(), this.tubes.get(0).getX(), this.tubes.get(0).getY(), null);
		g.drawImage(this.tubes.get(1).getImgTube(), this.tubes.get(1).getX(), this.tubes.get(1).getY(), null);

		// Tube2
		this.tubes.get(2).setX(this.tubes.get(2).getX() - 1);
		this.tubes.get(3).setX(this.tubes.get(2).getX());

		if (this.tubes.get(2).getX() == -100) {
			this.tubes.get(2).setX(this.tubes.get(0).getX() + this.DISTANCE_BETWEEN_TUBES);
			this.tubes.get(2).setY(-100 - 10 * this.random.nextInt(18));
			this.tubes.get(3)
					.setY(this.tubes.get(2).getY() + this.tubes.get(2).getHeight() + this.GAP_BETWEEN_UP_DOWN_TUBES);
		}
		g.drawImage(this.tubes.get(2).getImgTube(), this.tubes.get(2).getX(), this.tubes.get(2).getY(), null);
		g.drawImage(this.tubes.get(3).getImgTube(), this.tubes.get(3).getX(), this.tubes.get(3).getY(), null);

		// Tube3
		this.tubes.get(4).setX(this.tubes.get(4).getX() - 1);
		this.tubes.get(5).setX(this.tubes.get(4).getX());

		if (this.tubes.get(4).getX() == -100) {
			this.tubes.get(4).setX(this.tubes.get(2).getX() + this.DISTANCE_BETWEEN_TUBES);
			this.tubes.get(4).setY(-100 - 10 * this.random.nextInt(18));
			this.tubes.get(5)
					.setY(this.tubes.get(4).getY() + this.tubes.get(4).getHeight() + this.GAP_BETWEEN_UP_DOWN_TUBES);
		}
		g.drawImage(this.tubes.get(4).getImgTube(), this.tubes.get(4).getX(), this.tubes.get(4).getY(), null);
		g.drawImage(this.tubes.get(5).getImgTube(), this.tubes.get(5).getX(), this.tubes.get(5).getY(), null);
	}

	public boolean collideWith(Tube tube, Bird bird) {
		if (tube.getY() < 50) {
			if (bird.getY() <= tube.getY() + tube.getHeight() - 4 && bird.getX() + bird.getWidth() >= tube.getX()
					&& bird.getX() <= tube.getX() + tube.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else if (bird.getY() + bird.getHeight() >= tube.getY() + 4 && bird.getX() + bird.getWidth() >= tube.getX()
				&& bird.getX() <= tube.getX() + tube.getWidth()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean gameOver() { // game ends when the bird hits one of the 6 pipes or the ground
		boolean endOfGame = false;
		endOfGame = collideWith(this.tubes.get(0), this.flappyBird) || collideWith(this.tubes.get(1), this.flappyBird)
				|| collideWith(this.tubes.get(2), this.flappyBird) || collideWith(this.tubes.get(3), this.flappyBird)
				|| collideWith(this.tubes.get(4), this.flappyBird) || collideWith(this.tubes.get(5), this.flappyBird)
				|| this.flappyBird.getY() + this.flappyBird.getHeight() > 330;
		return endOfGame;
	}

	private void score() throws IOException, ClassNotFoundException {
		if (this.tubes.get(1).getX() + this.tubes.get(1).getWidth() == 90
				|| this.tubes.get(3).getX() + this.tubes.get(3).getWidth() == 90
				|| this.tubes.get(5).getX() + this.tubes.get(5).getWidth() == 90) {
			this.score++;
			if (bestScore <= score) {
				bestScore = score;
				this.saveBestScore();
			}

		}
	}

	private void saveBestScore() throws IOException, ClassNotFoundException {

		FileOutputStream fo = new FileOutputStream(new File("bestScore.txt"));
		ObjectOutputStream o = new ObjectOutputStream(fo);
		o.writeObject(bestScore);
		o.close();
		fo.close();

	}

	private int loadBestScore() throws IOException, ClassNotFoundException {

		int bestScore;
		FileInputStream fi = new FileInputStream(new File("bestScore.txt"));
		ObjectInputStream oi = new ObjectInputStream(fi);
		bestScore = (int) oi.readObject();
		oi.close();
		fi.close();
		return bestScore;
	}

	public void paintComponent(Graphics g) {
		if (this.xBackground == -this.BACKGROUND_WIDTH) {
			this.xBackground = 0;
		}
		g.drawImage(this.imgBackground, this.xBackground, 0, null);
		g.drawImage(this.imgBackground, this.xBackground + this.BACKGROUND_WIDTH, 0, null);
		g.drawImage(this.imgBackground, this.xBackground + this.BACKGROUND_WIDTH * 2, 0, null);
		this.tubeShifting(g);
		try {
			this.score();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}

		g.setFont(font);
		g.drawString("" + score, 140, 50);
		this.endOfGame = this.gameOver();
		this.flappyBird.setY(this.flappyBird.getY() + 1);
		g.drawImage(this.flappyBird.getImgBird(), this.flappyBird.getX(), this.flappyBird.getY(), null);
		if (this.endOfGame == true) {
			g.drawString("Game Over", 65, 200);
			try {
				g.drawString("Best Score : " + Integer.toString(loadBestScore()), 50, 370);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}

		}
	}
}
