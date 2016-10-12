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
		// TODO refactor to null object
		Item item = new Item(-1.0);
		if (i < 0 || i >= this.items.size()) {
			return item;
		}
		return this.items.get(i);
	}

}
