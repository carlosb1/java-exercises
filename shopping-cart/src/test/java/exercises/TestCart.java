package exercises;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestCart {
	public Cart cart;

	@Before
	public void setUp() {
		cart = new Cart();
	}

	@Test
	public void addItemOk() {
		cart.addItem(new Item(1.0));
		assertTrue(cart.getItem(0).getPrice() == 1.0);
	}

	@Test
	public void getItemIncorrect() {
		assertTrue(cart.getItem(0).getPrice() == -1.0);
	}

}
