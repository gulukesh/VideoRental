import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers = new ArrayList<Customer>() ;

    public void add(Customer customer){

    }
    public void add(String customerName){

    }
    public void printCustomer() {
        for ( Customer customer: customers ) {
            System.out.println("Name: " + customer.getName() +
                    "\tRentals: " + customer.getRentals().size()) ;
            for ( Rental rental: customer.getRentals() ) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
            }
        }
    }

    public Customer getCustomer(String customerName) {
        Customer foundCustomer = null;
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }
        return foundCustomer;
    }
}
