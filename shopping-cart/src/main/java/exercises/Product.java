package exercises;

import java.util.List;

public class Product extends Item {

	private final double price;

	public Product(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public double calcule(List<Item> items) {
		return price;
	}

	public boolean isCoupon() {
		return false;
	}

}
