

import java.util.HashMap;

public class PricingRules {
	private HashMap<String,Item> products;
	
	PricingRules() {
		products = new HashMap<String,Item>();
	}
	
	public void addProduct(String productCode, String productName, double price) {
		addProduct(productCode, productName, price, false);
	}
	
	public void addProduct(String productCode, String productName, double price, boolean isFreebie) {
		Item product = new Item(productCode, productName, price, isFreebie);
		products.put(productCode, product);
	}
	
	public double getPrice(String productCode) {
		Item product = products.get(productCode);
		return product.getPrice();
	}
}