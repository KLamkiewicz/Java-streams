package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import services.CustomerService;
import services.CustomerServiceInterface;
import entities.Customer;
import entities.Product;

public class CustomerServiceTests {

	private CustomerService cs;
	private List<Customer> res;
	
	@Before
	public void testBefore() {
		cs = new CustomerService(DataProducer.getTestData(10));
	}
	
	@Test
	public void testFindByName() {
		res = cs.findByName("Customer: 1");
		assertNotNull("Result can't be null", res);
		assertEquals(1, res.size());
	}

	@Test
	public void testFindByField(){
		//CustomerServiceInterface cs = new CustomerService(DataProducer.getTestData(10));
		//List<Customer> res = cs.findByField("", value);
	}

	@Test
	public void testCustomersWhoBoughtMoreThan(){
		res = cs.customersWhoBoughtMoreThan(5);
		assertEquals(2, res.size());
	}
	
	@Test
	public void testCustomersWhoSpentMoreThan(){
		res = cs.customersWhoSpentMoreThan(4.9);
		assertEquals(0, res.size());
		
		res = cs.customersWhoSpentMoreThan(4.8);
		assertEquals(1, res.size());
		
		res = cs.customersWhoSpentMoreThan(3.9);
		assertEquals(2, res.size());
	}
	
	@Test
	public void testCustomersWithNoOrders(){
		res = cs.customersWithNoOrders();
		assertEquals(3, res.size());
	}
	
	@Test
	public void testAddProductToAllCustomers(){
		Product p = new Product(999, "Promotional product", 4.2);
		
		assertEquals(3, cs.customersWithNoOrders().size());
		
		cs.addProductToAllCustomers(p);
		assertEquals(0, cs.customersWithNoOrders().size());
		
		assertEquals(10, cs.countCustomersWhoBought(p));
		
	}
	
	@Test
	public void testAvgOrders(){
		double avgOrderWithEmpties = cs.avgOrders(false);
		assertEquals(4.0, avgOrderWithEmpties, 0.01);
		
		double avgOrderWithoutEmpties = cs.avgOrders(true);
		assertEquals(2.8, avgOrderWithoutEmpties, 0.01);
	}
	
	@Test
	public void testWasProductBought(){
		Product p = new Product(3, "Product: 3", 0.3);
		assertEquals(false, cs.wasProductBought(p));
		
		p = new Product(4, "Product: 4", 0.4);
		assertEquals(true, cs.wasProductBought(p));
		
		p = new Product(10, "Product: 10", 1.0);
		assertEquals(true, cs.wasProductBought(p));
	}
	
	@Test
	public void testMostPopularProduct(){
		//List<Product> products = cs.mostPopularProduct();
		//assertEquals(new Product(10, "Product: 10", 10*0.1), products.get(0));
		
	}
	
	@Test
	public void testCountBuys(){
		Product p = new Product(4, "Product: 4", 4*0.1);
		assertEquals(7, cs.countBuys(p));
		
		p = new Product(10, "Product: 10", 10*0.1);
		assertEquals(1,  cs.countBuys(p));
		
		p = new Product(3, "Product: 3", 3*0.1);
		assertEquals(0,  cs.countBuys(p));
	}
	
	@Test
	public void testCountCustomersWhoBought(){
		Product p = new Product(10, "Product: 10", 10*0.1);
		assertEquals(1, cs.countCustomersWhoBought(p));
		
		p = new Product(8, "Product: 8", 8*0.1);
		assertEquals(3, cs.countCustomersWhoBought(p));
	}
	
}