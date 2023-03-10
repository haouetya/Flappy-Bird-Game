package JUnitTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Scene;

class SceneTest {

	private Scene scene;

	@BeforeEach
	void setUp() {

		scene = new Scene();

	}

	@AfterEach
	void tearDown() {
		scene = null;

	}

	// Test case 1: When collision is detected

	@Test
	public void detectcollideWhenCollideTest() {
		scene.flappyBird.setX(400);
		scene.flappyBird.setY(10);
		assertTrue(scene.collideWith(scene.tubes.get(0), scene.flappyBird));

	}

	// Test case 2: When The bird doesn't collide

	@Test
	public void detectcollideWhenNotCollideTest() {
		scene.flappyBird.setX(150);
		scene.flappyBird.setY(150);
		assertFalse(scene.collideWith(scene.tubes.get(0), scene.flappyBird));

	}

}
