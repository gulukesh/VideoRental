import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRentalsSize() {
		return rentals.size();
	}

	public Rental getRental(int i) {
		if(i < rentals.size()) {
			return rentals.get(i);
		} else {
			return null;
		}
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public void clearRentals() {
		rentals.clear();
	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		double totalCharge = 0;
		int totalPoint = 0;

		for (int i = 0; i < getRentalsSize(); ++i) {
			Rental each = getRental(i);
			double eachCharge = 0;
			int eachPoint = 0 ;
			int daysRented = 0;
			daysRented = each.getDaysRented();

			eachCharge = each.calculateCharge(eachCharge, daysRented);

			eachPoint = each.calculatePoint(eachPoint, daysRented);

			result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			totalCharge += eachCharge;

			totalPoint += eachPoint ;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";


		notifyFreeCoupon(totalPoint);
		return result ;
	}

	private void notifyFreeCoupon(int totalPoint) {
		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
	}

}
