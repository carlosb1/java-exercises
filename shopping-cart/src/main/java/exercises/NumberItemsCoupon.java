package exercises;

import java.util.LinkedList;

public class NumberItemsCoupon extends Item implements Coupon {

	private static final int PERCENTAGE = 5;

	public void apply(int currentIndex, LinkedList<Item> items) {
		int numItems = 0;
		for (Item item : items) {
			if (!item.isCoupon()) {
				numItems++;
			}
		}
		float percent = numItems * PERCENTAGE;
		if (percent > 50) {
			percent = 50;
		}

		for (Item item : items) {
			if (!item.isCoupon()) {
				Product product = (Product) item;
				product.setPrice(product.getPrice() - (product.getPrice() / 100.0) * percent);
			}
		}

	}

	@Override
	public boolean isCoupon() {
		return true;
	}

}
