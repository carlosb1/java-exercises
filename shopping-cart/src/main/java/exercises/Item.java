package exercises;

import java.util.List;

public abstract class Item {

	public abstract double calcule(List<Item> items);

	public abstract boolean isCoupon();

	public boolean isNull() {
		return false;
	}

}
