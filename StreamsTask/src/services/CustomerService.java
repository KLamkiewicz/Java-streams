package services;

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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Product> mostPopularProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countBuys(Product p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countCustomersWhoBought(Product p) {
		return (int) customers.stream().filter(c->c.getBoughtProducts().contains(p)).count();
	}

}