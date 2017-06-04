import static org.junit.Assert.*;
import java.text.DecimalFormat;
import org.junit.*;

public class ShoppingCartJUnitTest {
	
	private PricingRules pricingRules;
	private ShoppingCart cart;
	DecimalFormat df;
	Item item1;
	Item item2;
	Item item3;
	Item item4;
	
	@Before
	public void init() {
		pricingRules = new PricingRules();
		cart = new ShoppingCart(pricingRules);
		df = new DecimalFormat("0.00");
		pricingRules.addProduct("ult_small", "Unlimited 1GB", 24.90);
		pricingRules.addProduct("ult_medium", "Unlimited 2GB", 29.90);
		pricingRules.addProduct("ult_large", "Unlimited 5GB", 44.90);
		pricingRules.addProduct("1gb", "1GB Data-pack", 9.90);
		item1 = new Item("ult_small","Unlimited 1GB");
		item2 = new Item("ult_medium", "Unlimited 2GB");
		item3 = new Item("ult_large", "Unlimited 5GB");
		item4 = new Item("1gb", "1GB Data-pack");
	}
	
	@Test
	public void scenario1() {
		cart.add(item1);
		cart.add(item1);
		cart.add(item1);
		cart.add(item3);
		cart.items();
		assertEquals(df.format(cart.total()), "94.70");
	}
	
	@Test
	public void scenario2() {
		cart.add(item1);
		cart.add(item1);
		cart.add(item3);
		cart.add(item3);
		cart.add(item3);
		cart.add(item3);
		cart.items();
		assertEquals(df.format(cart.total()), "209.40");
	}
	
	@Test
	public void scenario3() {
		cart.add(item1);
		cart.add(item2);
		cart.add(item2);
		cart.items();
		assertEquals(df.format(cart.total()), "84.70");
	}
	
	@Test
	public void scenario4() {
		cart.add(item1);
		cart.add(item4, "I<3AMAYSIM");
		cart.items();
		assertEquals(df.format(cart.total()), "31.32");
	}
}