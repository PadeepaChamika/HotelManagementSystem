package model;

import javafx.scene.control.SingleSelectionModel;

public class Room {
    private String roomId;
    private String roomName;
    private String roomType;
    private String floor;
    private String description;
    private Double price;

    public Room() {
    }
/* public Room(String text, String selectedRadioButtonText, SingleSelectionModel selectionModel, SingleSelectionModel model, String txtDescriptionText, String txtPriceText) {
    }*/

    public Room(String roomId, String roomName, String roomType, String floor, String description, Double price) {
        this.setRoomId(roomId);
        this.setRoomName(roomName);
        this.setRoomType(roomType);
        this.setFloor(floor);
        this.setDescription(description);
        this.setPrice(price);
    }

    /*public Room(String text, String text1, Object selectedItem, Object selectedItem1, String text2, double price) {
    }
*/
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", floor='" + floor + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
