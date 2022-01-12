package views.tm;

public class AvailableRoomTm {
    private String roomId;
    private String roomName;
    private String roomType;
    private String floor;
    private Double price;

    public AvailableRoomTm() {
    }

    public AvailableRoomTm(String roomId, String roomName, String roomType, String floor, Double price) {
        this.setRoomId(roomId);
        this.setRoomName(roomName);
        this.setRoomType(roomType);
        this.setFloor(floor);
        this.setPrice(price);
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AvailableRoomTm{" +
                "roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", floor='" + floor + '\'' +
                ", price=" + price +
                '}';
    }
}
