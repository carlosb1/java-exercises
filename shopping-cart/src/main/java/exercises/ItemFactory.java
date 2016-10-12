package exercises;

import coupons.GlobalCoupon;
import coupons.NextItemCoupon;
import coupons.NumberItemsCoupon;

public class ItemFactory {
	public static enum TypeCoupon {
		GLOBAL, NEXTITEM, NUMBERITEMS
	};

	public Item makeCoupon(TypeCoupon type) throws Exception {
		if (type == TypeCoupon.GLOBAL) {
			return new GlobalCoupon();
		} else if (type == TypeCoupon.NEXTITEM) {
			return new NextItemCoupon();
		} else if (type == TypeCoupon.NUMBERITEMS) {
			return new NumberItemsCoupon();
		} else {
			throw new Exception("It doesn't exist this coupon");
		}
	}

	private final static NullItem nullItem = new NullItem();

	public Item makeNullItem() {
		return nullItem;
	}
}
