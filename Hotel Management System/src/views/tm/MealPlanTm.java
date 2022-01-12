package views.tm;

public class MealPlanTm {
    private String mealPlanId;
    private String mealPackageId;
    private String bookingId;
    private String guestId;
    private String mealPlanType;
    private String remarks;
    private Double mealPlanPrice;
    private Double extraPrice;
    private Double total;

    public MealPlanTm() {
    }

    public MealPlanTm(String mealPlanId, String mealPackageId, String bookingId, String guestId, String mealPlanType, String remarks, Double mealPlanPrice, Double extraPrice, Double total) {
        this.setMealPlanId(mealPlanId);
        this.setMealPackageId(mealPackageId);
        this.setBookingId(bookingId);
        this.setGuestId(guestId);
        this.setMealPlanType(mealPlanType);
        this.setRemarks(remarks);
        this.setMealPlanPrice(mealPlanPrice);
        this.setExtraPrice(extraPrice);
        this.setTotal(total);
    }

    public String getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(String mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public String getMealPackageId() {
        return mealPackageId;
    }

    public void setMealPackageId(String mealPackageId) {
        this.mealPackageId = mealPackageId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getMealPlanType() {
        return mealPlanType;
    }

    public void setMealPlanType(String mealPlanType) {
        this.mealPlanType = mealPlanType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Double getMealPlanPrice() {
        return mealPlanPrice;
    }

    public void setMealPlanPrice(Double mealPlanPrice) {
        this.mealPlanPrice = mealPlanPrice;
    }

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "MealPlanTm{" +
                "mealPlanId='" + mealPlanId + '\'' +
                ", mealPackageId='" + mealPackageId + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", guestId='" + guestId + '\'' +
                ", mealPlanType='" + mealPlanType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", mealPlanPrice=" + mealPlanPrice +
                ", extraPrice=" + extraPrice +
                ", total=" + total +
                '}';
    }
}
