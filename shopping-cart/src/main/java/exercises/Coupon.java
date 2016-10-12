package exercises;

import java.util.LinkedList;

public interface Coupon {
	public void apply(int currentIndex, LinkedList<Item> items);

}
