package exercises;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

public class TestItem {

	private final static LinkedList<Item> ITEMS = new LinkedList<Item>() {
		private static final long serialVersionUID = 1L;

		{
			add(new Product(1.0));
			add(new Product(2.0));
		}
	};

	@Test
	public void createProductOk() {
		Product product = new Product(1.0);
		assertTrue(product.getPrice() == 1.0);
	}

	@Test
	public void productCalculeOk() {
		Product product = new Product(1.0);
		assertTrue(product.calcule(new LinkedList<Item>()) == 1.0);
	}

	@Test
	public void productCheckIsCouponOk() {
		Product product = new Product(1.0);
		assertTrue(!product.isCoupon());
	}

	// TODO creation factory pattern
	@Test
	public void createGlobalCouponOk() {
		GlobalCoupon coupon = new GlobalCoupon();
		assertTrue(coupon.isCoupon());
	}

	@Test
	public void calculeGlobalCoupon() {
		GlobalCoupon coupon = new GlobalCoupon();
		double discount = coupon.calcule(ITEMS);
		assertEquals(discount, -0.9, .1);
	}

	@Test
	public void createNullItemOk() {
		Item item = new NullItem();
		assertTrue(item.isNull());
	}

}
