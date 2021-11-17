import java.util.Date;

public class DVDVideo extends Video {
    public DVDVideo(String title, PriceCode priceCode, Date registeredDate) {
        super(title, priceCode, registeredDate);
    }

    @Override
    public int getLateReturnPointPenalty() {
        return 3;
    }

    @Override
    public int getLimitDays() {
        return 2;
    }
}
