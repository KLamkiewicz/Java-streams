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
	public void testFndByField(){
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
		
	}
	
	@Test
	public void testAvgOrders(){
		double avgOrderWithEmpties = cs.avgOrders(true);
	}
	
	@Test
	public void testWasProductBought(){
	
	}
	
	@Test
	public void testMostPopularProduct(){
		List<Product> products = cs.mostPopularProduct();
		
	}
	
	@Test
	public void testCountBuys(){
		
	}
	
	@Test
	public void testCountCustomersWhoBought(){
		
	}
	
}