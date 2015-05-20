package services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import entities.Customer;
import entities.Product;

public class CustomerService implements CustomerServiceInterface {

	private List<Customer> customers;

	public CustomerService(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public List<Customer> findByName(String name) {
		return customers.stream().filter(c->c.getName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public List<Customer> findByField(String fieldName, Object value) {
		return customers.stream().filter(c->{
			try{
				Field f = c.getClass().getDeclaredField(fieldName);
				f.setAccessible(true);
				if(value!=null)
					return f.get(c).equals(value);
				else
					return f.get(c) == null;
			}catch(Exception e){
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());
	}

	@Override
	public List<Customer> customersWhoBoughtMoreThan(int number) {
		return customers.stream().filter(c->c.getBoughtProducts().size()>number).collect(Collectors.toList());
	}

	@Override
	public List<Customer> customersWhoSpentMoreThan(double price) {
		return customers.stream().filter(c->c.getBoughtProducts().stream().mapToDouble(b->b.getPrice()).sum()>price).collect(Collectors.toList());
	}

	@Override
	public List<Customer> customersWithNoOrders() {
		return customers.stream().filter(c->c.getBoughtProducts().size()==0).collect(Collectors.toList());
	}

	@Override
	public void addProductToAllCustomers(Product p) {
		customers.stream().forEach(c->c.addProduct(p));
	}

	@Override
	public double avgOrders(boolean includeEmpty) {
		return customers.stream().filter(c->{
				if(includeEmpty)
					return c.getBoughtProducts().size()>=0;
				else
					return c.getBoughtProducts().size()>0;
			}).mapToInt(c->c.getBoughtProducts().size()).average().getAsDouble();
	}

	@Override
	public boolean wasProductBought(Product p) {
		return customers.stream().anyMatch(c-> {  return c.getBoughtProducts().contains(p);});
	}

	@Override
	public List<Product> mostPopularProduct() {
		//return customers.stream().map(c->c.getBoughtProducts().stream().distinct()).flatMap()
		return null;
	}

	@Override
	public int countBuys(Product p) {
		return (int) customers.stream().filter(c->c.getBoughtProducts().contains(p)).count();
	}

	@Override
	public int countCustomersWhoBought(Product p) {
		return (int) customers.stream().filter(c->c.getBoughtProducts().contains(p)).count();
	}

}