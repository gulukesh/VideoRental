import java.util.Date;

public class Rental {
	private Video video ;
	private RentalStatus status ;
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = RentalStatus.Rented;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public RentalStatus getStatus() {
		return status;
	}

	public void returnVideo() {
		if (status == RentalStatus.Rented) {
			changeToReturnedStatus();
			returnDate = new Date() ;
		}
	}

	public void changeToReturnedStatus() {
		this.status = RentalStatus.Returned;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented ;
		long diff;
		if (getStatus() == RentalStatus.Returned) { // returned Video
			diff = returnDate.getTime() - rentDate.getTime();
		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();
		}
		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		if ( daysRented <= 2) return limit ;

		return video.getLimitDays();
	}

	enum RentalStatus {
		Rented,
		Returned
	}
}
