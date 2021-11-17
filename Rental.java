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

		switch ( video.getVideoType() ) {
			case Video.VHS: limit = 5 ; break ;
			case Video.CD: limit = 3 ; break ;
			case Video.DVD: limit = 2 ; break ;
		}
		return limit ;
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

	enum RentalStatus {
		RENTED,
		RETURNED
	}
}
