package Game;

public class Chrono implements Runnable {

	private final int PAUSE = 5;

	@Override
	public void run() {
		while (Main.scene.endOfGame == false) {
			Main.scene.xBackground--;
			Main.scene.repaint();
			try {
				Thread.sleep(this.PAUSE);
			} catch (InterruptedException e) {
			}
		}
	}
}
