package views.tm;

public class AddNewMealPackageTm {
    private String mealPackageId;
    private String mealPackageName;
    private String mealPackageType;
    private String description;
    private Double price;

    public AddNewMealPackageTm() {
    }

    public AddNewMealPackageTm(String mealPackageId, String mealPackageName, String mealPackageType, String description, Double price) {
        this.setMealPackageId(mealPackageId);
        this.setMealPackageName(mealPackageName);
        this.setMealPackageType(mealPackageType);
        this.setDescription(description);
        this.setPrice(price);
    }

    public String getMealPackageId() {
        return mealPackageId;
    }

    public void setMealPackageId(String mealPackageId) {
        this.mealPackageId = mealPackageId;
    }

    public String getMealPackageName() {
        return mealPackageName;
    }

    public void setMealPackageName(String mealPackageName) {
        this.mealPackageName = mealPackageName;
    }

    public String getMealPackageType() {
        return mealPackageType;
    }

    public void setMealPackageType(String mealPackageType) {
        this.mealPackageType = mealPackageType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AddNewMealPackageTm{" +
                "mealPackageId='" + mealPackageId + '\'' +
                ", mealPackageName='" + mealPackageName + '\'' +
                ", mealPackageType='" + mealPackageType + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
