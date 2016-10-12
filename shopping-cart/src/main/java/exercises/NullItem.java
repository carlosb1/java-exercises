package exercises;

import java.util.List;

public class NullItem extends Item {

	public boolean isNull() {
		return true;
	}

	// TODO add test for null item
	public double calcule(List<Item> items) {
		return 0;
	}

	public boolean isCoupon() {
		return false;
	}
}
