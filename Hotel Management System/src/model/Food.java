package model;

public class Food {
    private String foodId;
    private String foodType;
    private int qty;

    public Food() {
    }

    public Food(String foodId, String foodType, int qty) {
        this.setFoodId(foodId);
        this.setFoodType(foodType);
        this.setQty(qty);
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId='" + foodId + '\'' +
                ", foodType='" + foodType + '\'' +
                ", qty=" + qty +
                '}';
    }
    //private Button btn;

   /* public Food() {
    }

    public Food(String foodId, String foodType, int qty, Button btn) {
        this.setFoodId(foodId);
        this.setFoodType(foodType);
        this.setQty(qty);
        this.setBtn(btn);
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId='" + foodId + '\'' +
                ", foodType='" + foodType + '\'' +
                ", qty=" + qty +
                ", btn=" + btn +
                '}';
    }*/
}
