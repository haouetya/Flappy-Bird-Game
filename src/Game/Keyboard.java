package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && Main.scene.endOfGame == false) {
			Main.scene.flappyBird.ascend();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && Main.scene.endOfGame == true) {
			Main.main(null);
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && Main.scene.endOfGame == true) {
			System.exit(0);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
