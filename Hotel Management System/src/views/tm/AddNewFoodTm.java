package views.tm;


import javafx.scene.control.Button;

public class AddNewFoodTm {
    private String foodId;
    private String foodType;
    private int qty;
    private Button btn;



    public AddNewFoodTm() {
    }

    public AddNewFoodTm(String foodId, String foodType, int qty, Button btn) {
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
        return "AddNewFoodTm{" +
                "foodId='" + foodId + '\'' +
                ", foodType='" + foodType + '\'' +
                ", qty=" + qty +
                ", btn=" + btn +
                '}';
    }
}
