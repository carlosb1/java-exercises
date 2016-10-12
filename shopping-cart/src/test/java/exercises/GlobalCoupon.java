package exercises;

import java.util.List;

public class GlobalCoupon extends Item {

	public GlobalCoupon(double price) {
		super(price);
		// TODO Auto-generated constructor stub
	}

	public GlobalCoupon() {
		// TODO change this type of constructor
		super(-1.0);
	}

	// TODO refactor
	public double calcule(List<Item> items) {
		double total = 0;
		for (Item item : items) {
			if (!item.isCoupon()) {
				total += item.getPrice();
			}
		}
		// TODO create constants
		double discount = (total / 100.0) * 30.0;
		return -discount;
	}

	// TODO move this method
	public boolean isCoupon() {
		return true;
	}
}
