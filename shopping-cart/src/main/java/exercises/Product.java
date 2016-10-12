package exercises;

public class Product extends Item {

	private double price;

	public Product(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isCoupon() {
		return false;
	}

}
