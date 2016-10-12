package coupons;

import java.util.LinkedList;

import exercises.Item;
import exercises.Product;

public class GlobalCoupon extends Item implements Coupon {
	private static final double PERCENTAGE = 30.0;

	public boolean isCoupon() {
		return true;
	}

	public void apply(int currentIndex, LinkedList<Item> items) {
		for (Item item : items) {
			if (!item.isCoupon()) {
				// TODO it is possible to improve?
				Product product = (Product) item;
				product.setPrice(product.getPrice() - (product.getPrice() / 100.0) * PERCENTAGE);
			}
		}
	}
}
