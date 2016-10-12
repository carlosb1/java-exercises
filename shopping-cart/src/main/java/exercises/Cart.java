package exercises;

import java.util.LinkedList;

public class Cart {
	private final LinkedList<Item> items;

	public Cart() {
		this.items = new LinkedList<Item>();
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public Item getItem(int i) {
		Item item = new NullItem();
		if (i < 0 || i >= this.items.size()) {
			return item;
		}
		return this.items.get(i);
	}

	public double checkout() {
		double totalPrice = 0;
		for (Item item : items) {
			totalPrice += item.calcule(items);
		}

		return totalPrice;
	}

}
