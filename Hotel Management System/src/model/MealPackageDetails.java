package model;

public class MealPackageDetails {
    private String mealPackageId;
    private String mealId;
    private String mealType;
    private int qty;

    public MealPackageDetails() {
    }

    public MealPackageDetails(String mealPackageId, String mealId, String mealType, int qty) {
        this.setMealPackageId(mealPackageId);
        this.setMealId(mealId);
        this.setMealType(mealType);
        this.setQty(qty);
    }

    public String getMealPackageId() {
        return mealPackageId;
    }

    public void setMealPackageId(String mealPackageId) {
        this.mealPackageId = mealPackageId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "MealPackageDetails{" +
                "mealPackageId='" + mealPackageId + '\'' +
                ", mealId='" + mealId + '\'' +
                ", mealType='" + mealType + '\'' +
                ", qty=" + qty +
                '}';
    }
}
