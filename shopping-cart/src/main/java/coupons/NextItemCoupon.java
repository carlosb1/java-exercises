package coupons;

import java.util.LinkedList;
import java.util.List;

import exercises.Item;
import exercises.Product;

public class NextItemCoupon extends Item implements Coupon {

	private static final double PERCENTAGE = 50;

	public void apply(int currentIndex, LinkedList<Item> items) {
		List<Item> subItems = items.subList(currentIndex, items.size());
		for (Item item : subItems) {
			if (!item.isCoupon()) {
				// TODO it is possible to improve?
				Product product = (Product) item;
				product.setPrice(product.getPrice() - (product.getPrice() / 100.0) * PERCENTAGE);
				return;
			}
		}
	}

	@Override
	public boolean isCoupon() {
		return true;
	}

}
