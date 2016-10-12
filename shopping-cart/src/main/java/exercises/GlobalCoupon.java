package exercises;

import java.util.List;

public class GlobalCoupon extends Item {
	private static final double PERCENTAGE = 30.0;

	public double calcule(List<Item> items) {
		double total = 0;
		for (Item item : items) {
			if (!item.isCoupon()) {
				// TODO it is possible to improve?
				total += item.calcule(items);
			}
		}
		double discount = (total / 100.0) * PERCENTAGE;
		return -discount;
	}

	public boolean isCoupon() {
		return true;
	}
}
