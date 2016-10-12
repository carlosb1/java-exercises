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
		/* apply coupon */
		for (int index = 0; index < items.size(); index++) {
			Item item = items.get(index);
			if (item.isCoupon()) {
				// TODO best way
				((Coupon) item).apply(index, items);
			}
		}

		double totalPrice = 0;
		for (Item item : items) {
			if (!item.isCoupon()) {
				totalPrice += ((Product) item).getPrice();
			}
		}

		return totalPrice;
	}

}
