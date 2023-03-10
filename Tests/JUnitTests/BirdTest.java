package JUnitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Objects.Bird;

public class BirdTest {

	Bird flappy = new Bird(100, 150, "/Images/bird.gif");

	@Test
	public void testSetX() {
		flappy.setX(200);
		Assertions.assertEquals(200, flappy.getX());
	}

	@Test
	public void testSetY() {
		flappy.setY(200);
		Assertions.assertEquals(200, flappy.getY());
	}

}
