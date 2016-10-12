package exercises;

import java.util.List;

public class Item {
	private final double price;

	public Item(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public double calcule(List<Item> items) {
		return 0.0;
	}

	public boolean isCoupon() {
		return false;
	}

}
