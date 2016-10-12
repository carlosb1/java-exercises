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

	// TODO create test for product and coupon
	/*
	 * @Test public void addItemOk() { cart.addItem(new Product(1.0));
	 * assertTrue(cart.getItem(0).getPrice() == 1.0); }
	 */

	@Test
	public void getItemIncorrect() {
		assertTrue(cart.getItem(0).isNull());
	}

	@Test
	public void addItemCheckoutOk() {
		cart.addItem(new Product(1.0));
		double totalPrice = cart.checkout();
		assertTrue(1.0 == totalPrice);
	}

	@Test
	public void addMultipleItemsCheckoutOk() {
		cart.addItem(new Product(1.0));
		cart.addItem(new Product(2.0));
		double totalPrice = cart.checkout();
		assertTrue(3.0 == totalPrice);
	}

	@Test
	public void addItemsAndTotalDiscountOk() {
		cart.addItem(new Product(1.0));
		cart.addItem(new Product(2.0));
		cart.addItem(new GlobalCoupon());
		double totalPrice = cart.checkout();
		assertTrue(2.1 == totalPrice);

	}

}
