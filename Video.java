import java.util.Date;

public abstract class Video {
	private String title ;

	private PriceCode priceCode ;

	private Date registeredDate ;
	private boolean rented ;

	protected Video(String title, PriceCode priceCode, Date registeredDate) {
		this.setTitle(title) ;
		this.setPriceCode(priceCode) ;
		this.registeredDate = registeredDate ;
	}

	public abstract int getLateReturnPointPenalty();
	public abstract int getLimitDays();

	public PriceCode getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRented() {
		return rented;
	}

	public boolean isRentedVideo(String videoTitle) {
		if (videoTitle == title && isRented()) {
			return true;
		}
		return false;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}
}
