package exercises;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import coupons.NextItemCoupon;
import exercises.ItemFactory.TypeCoupon;

/**
 * Unit test for simple App.
 */
public class TestCart {
	public Cart cart;
	private static ItemFactory factoryItem;

	@BeforeClass
	public static void allSetUp() {
		factoryItem = new ItemFactory();
	}

	@Before
	public void setUp() {
		cart = new Cart();
	}

	@Test
	public void addItemOk() {
		cart.addItem(new Product(1.0));
		assertTrue(!cart.getItem(0).isNull());
	}

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
	public void addItemsAndTotalDiscountOk() throws Exception {
		cart.addItem(new Product(1.0));
		cart.addItem(new Product(2.0));
		cart.addItem(factoryItem.makeCoupon(TypeCoupon.GLOBAL));
		double totalPrice = cart.checkout();
		assertEquals(2.1, totalPrice, 0.1);
	}

	@Test
	public void addItemsAndApplyTwoTotalDiscountOk() throws Exception {
		cart.addItem(new Product(1.0));
		cart.addItem(new Product(2.0));
		cart.addItem(factoryItem.makeCoupon(TypeCoupon.GLOBAL));
		cart.addItem(factoryItem.makeCoupon(TypeCoupon.GLOBAL));
		double totalPrice = cart.checkout();
		assertEquals(1.47, totalPrice, 0.1);
	}

	@Test
	public void addItemsAndApplyNextItemDiscountOk() {
		cart.addItem(new Product(1.0));
		cart.addItem(new NextItemCoupon());
		cart.addItem(new Product(2.0));
		double totalPrice = cart.checkout();
		assertEquals(2.0, totalPrice, 0.1);
	}

	@Test
	public void addItemsAndApplyTwoDifsDiscountsOk() throws Exception {
		cart.addItem(new Product(1.0));
		cart.addItem(factoryItem.makeCoupon(TypeCoupon.GLOBAL));
		cart.addItem(factoryItem.makeCoupon(TypeCoupon.NEXTITEM));
		cart.addItem(new Product(2.0));
		double totalPrice = cart.checkout();
		assertEquals(1.4, totalPrice, 0.1);
	}

	@Test
	public void addItemsNumberItemDiscountOk() throws Exception {
		cart.addItem(new Product(1.0));
		cart.addItem(factoryItem.makeCoupon(TypeCoupon.NUMBERITEMS));
		cart.addItem(new Product(2.0));
		double totalPrice = cart.checkout();
		assertEquals(2.7, totalPrice, 0.1);
	}

	@Test
	public void addItemsAndApplyNumberItemDiscountAndMoreOk() throws Exception {
		cart.addItem(new Product(1.0));
		cart.addItem(factoryItem.makeCoupon(TypeCoupon.NUMBERITEMS));
		cart.addItem(factoryItem.makeCoupon(TypeCoupon.NEXTITEM));
		cart.addItem(factoryItem.makeCoupon(TypeCoupon.GLOBAL));
		cart.addItem(new Product(2.0));
		double totalPrice = cart.checkout();
		assertEquals((1.8 - 0.54), totalPrice, 0.1);
	}

}
