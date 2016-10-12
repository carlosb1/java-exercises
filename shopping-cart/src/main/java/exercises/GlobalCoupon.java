package exercises;

import java.util.List;

public class GlobalCoupon extends Item {
	public double calcule(List<Item> items) {
		double total = 0;
		for (Item item : items) {
			if (!item.isCoupon()) {
				// TODO it is possible to improve?
				total += item.calcule(items);
			}
		}
		// TODO create constants
		double discount = (total / 100.0) * 30.0;
		return -discount;
	}

	public boolean isCoupon() {
		return true;
	}
}
