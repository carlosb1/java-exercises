package exercises;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

public class TestItem {

	@Test
	public void createProductOk() {
		Product product = new Product(1.0);
		assertTrue(product.getPrice() == 1.0);
	}

	@Test
	public void productsetItemOk() {
		Product product = new Product(1.0);
		product.setPrice(1.1);

		assertTrue(product.getPrice() == 1.1);
	}

	@Test
	public void productIsNotNullOk() {
		Product product = new Product(1.0);
		assertTrue(!product.isNull());
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
	public void globalCouponIsNotNullOk() {
		GlobalCoupon coupon = new GlobalCoupon();
		assertTrue(!coupon.isNull());
	}

	@Test
	public void nextItemCouponIsNotNullOk() {
		NextItemCoupon coupon = new NextItemCoupon();
		assertTrue(!coupon.isNull());
	}

	@Test
	public void calculeGlobalCoupon() {
		LinkedList<Item> items = new LinkedList<Item>() {
			private static final long serialVersionUID = 1L;

			{
				add(new Product(1.0));
				add(new Product(2.0));
			}
		};

		GlobalCoupon coupon = new GlobalCoupon();
		coupon.apply(0, items);
		assertEquals(((Product) items.get(0)).getPrice(), 0.7, .01);
		assertEquals(((Product) items.get(1)).getPrice(), 1.4, .01);
	}

	@Test
	public void createNullItemOk() {
		Item item = new NullItem();
		assertTrue(item.isNull());
	}

	@Test
	public void nullItemIsNotCouponOk() {
		Item item = new NullItem();
		assertTrue(!item.isCoupon());
	}

}
