package coupons;

import java.util.LinkedList;

import exercises.Item;

public interface Coupon {
	public void apply(int currentIndex, LinkedList<Item> items);

}
