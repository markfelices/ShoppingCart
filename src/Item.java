

public class Item {
	private String productCode;
	private String productName;
	private boolean isFreebie;
	private double price;
	private int quantity;
	
	Item(String productCode, String productName) {
		setDefaults(productCode, productName, 0, false);
	}
	
	Item(String productCode, String productName, double price) {
		setDefaults(productCode, productName, price, false);
	}
	
	Item(String productCode, String productName, double price, boolean isFreebie) {
		setDefaults(productCode, productName, price, isFreebie);
	}
	
	private void setDefaults(String productCode, String productName, double price, boolean isFreebie) {
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.isFreebie = isFreebie;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public boolean isFreebie() {
		return isFreebie;
	}
	public void setFreebie(boolean isFreebie) {
		this.isFreebie = isFreebie;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}