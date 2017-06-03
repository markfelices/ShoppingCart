

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShoppingCart {
	private PricingRules pricingRules;
	private ArrayList<Item> cartItems;
	private String promoCode;
	private double promoCodeDiscount;
	
	ShoppingCart(PricingRules pricingRules) {
		this.pricingRules = pricingRules;
		this.cartItems = new ArrayList<Item>();
		this.promoCode = "I<3AMAYSIM";
		this.promoCodeDiscount = 0;
	}
	
	public void add(Item item) {
		boolean isAdded = false;
		for(int x = 0; x < cartItems.size(); x++) {
			Item temp = cartItems.get(x);
			if(temp.getProductCode().equals(item.getProductCode())
					&& temp.isFreebie() == item.isFreebie()) {
				int quantity = temp.getQuantity() + 1;
				temp.setQuantity(quantity);
				cartItems.set(x, temp);
				isAdded = true;
			}
		}
		if(!isAdded) {
			item.setQuantity(1);
			cartItems.add(item);
		}
		this.applyFreebie(item);
	}
	
	public void add(Item item, String promoCode) {
		add(item);
		applyPromoCode(promoCode);
	}
	
	public void applyPromoCode(String promoCode) {
		if(this.promoCode.equals(promoCode)) {
			this.promoCodeDiscount = 0.1;
		}
	}
	
	private void applyFreebie(Item item) {
		if(item.getProductCode().equals("ult_medium")) {
			Item freebie = new Item("1gb", "1GB Data-pack", 0, true);
			freebie.setQuantity(1);
			this.add(freebie);
		}
	}
	
	public double total() {
		double total = 0;
		int _1GB = 0;
		int _5GB = 0;
		double discount_1GB = 0;
		double discount_5GB = 0;
		
		for(Item item : cartItems) {
			switch(item.getProductCode()) {
				case "ult_small":
					_1GB = item.getQuantity();
				break;
				case "ult_large":
					_5GB = item.getQuantity();
				break;
			}
			if(!item.isFreebie()) {
				total += pricingRules.getPrice(item.getProductCode()) * item.getQuantity();
			}
		}
		
		if(_1GB >= 3) {
			_1GB = _1GB / 3;
			discount_1GB = _1GB * pricingRules.getPrice("ult_small");
		}
		
		if(_5GB > 3) {
			discount_5GB = _5GB * 5;
		}
		
		//Apply Discount
		total -= (total * promoCodeDiscount) + discount_1GB + discount_5GB;
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("Total: $" + df.format(total));
		
		return total;
	}
	
	public String items() {
		String strCartItems = "\nCart Items: \n";
		for(Item item : cartItems) {
			strCartItems += Integer.toString(item.getQuantity()) + " x " + item.getProductName() + " \n";
		}
		System.out.println(strCartItems);
		return strCartItems;
	}
	
	public static void main(String [] args) {
		PricingRules pricingRules = new PricingRules();
		pricingRules.addProduct("ult_small", "Unlimited 1GB", 24.90);
		pricingRules.addProduct("ult_medium", "Unlimited 2GB", 29.90);
		pricingRules.addProduct("ult_large", "Unlimited 5GB", 44.90);
		pricingRules.addProduct("1gb", "1GB Data-pack", 9.90);
		
		Item item1 = new Item("ult_small","Unlimited 1GB");
		Item item2 = new Item("ult_medium", "Unlimited 2GB");
		Item item3 = new Item("ult_large", "Unlimited 5GB");
		Item item4 = new Item("1gb", "1GB Data-pack");
		
		ShoppingCart cart = new ShoppingCart(pricingRules);
		cart.add(item1);
		cart.add(item1);
		cart.add(item1);
		cart.add(item3);
		cart.items();
		cart.total();
		
		cart = new ShoppingCart(pricingRules);
		cart.add(item1);
		cart.add(item1);
		cart.add(item3);
		cart.add(item3);
		cart.add(item3);
		cart.add(item3);
		cart.items();
		cart.total();
		
		cart = new ShoppingCart(pricingRules);
		cart.add(item1);
		cart.add(item2);
		cart.add(item2);
		cart.items();
		cart.total();
		
		cart = new ShoppingCart(pricingRules);
		cart.add(item1);
		cart.add(item4, "I<3AMAYSIM");
		cart.items();
		cart.total();
	}
}