import java.util.Date;

public class Rental {
	private Video video ;
	private RentalStatus status ;
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video;
		status = RentalStatus.RENTED;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public RentalStatus getStatus() {
		return status;
	}

	public void returnVideo() {
		if (isRented()) {
			changeToReturnedStatus();
			returnDate = new Date() ;
		}
	}

	private boolean isRented() {
		return status == RentalStatus.RENTED;
	}

	public void changeToReturnedStatus() {
		this.status = RentalStatus.RETURNED;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented ;

		if (isReturned()) {
			daysRented = getDaysRented(rentDate.getTime(), returnDate.getTime());
		} else {
			daysRented = getDaysRented(rentDate.getTime(), new Date().getTime());

		}
		if ( daysRented <= 2) return limit ;

		return video.getLimitDays();
	}

	private int getDaysRented(long from, long to) {
		int daysRented;
		long diff = to - from;
		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		return daysRented;
	}

	private boolean isReturned() {
		return this.status == RentalStatus.RETURNED;
	}

	int calculatePoint(int eachPoint, int daysRented) {
		eachPoint++;

		if ((getVideo().getPriceCode() == PriceCode.NEW_RELEASE) )
			eachPoint++;

		if ( daysRented > getDaysRentedLimit() )
			eachPoint -= Math.min(eachPoint, getVideo().getLateReturnPointPenalty()) ;
		return eachPoint;
	}

	double calculateCharge(double eachCharge, int daysRented) {
		switch (getVideo().getPriceCode()) {
		case REGULAR:
			eachCharge += 2;
			if (daysRented > 2)
				eachCharge += (daysRented - 2) * 1.5;
			break;
		case NEW_RELEASE:
			eachCharge = daysRented * 3;
			break;
		}
		return eachCharge;
	}

	int getDaysRented() {
		int daysRented;
		long diff;
		if (getStatus() == RentalStatus.RETURNED) { // returned Video
			diff = getReturnDate().getTime() - getRentDate().getTime();
		} else { // not yet returned
			diff = new Date().getTime() - getRentDate().getTime();
		}
		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		return daysRented;
	}

	enum RentalStatus {
		RENTED,
		RETURNED
	}
}
