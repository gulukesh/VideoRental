public enum PriceCode {
    REGULAR,
    NEW_RELEASE;

    public static PriceCode fromInteger(int i) {
        switch(i) {
            case 0:
                return REGULAR;
            case 1:
                return NEW_RELEASE;
            default:
                return null;
        }
    }
}
