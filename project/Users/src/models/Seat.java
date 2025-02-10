package models;

public class Seat {
    private int id;
    private int seatNumber;
    private boolean reserved;
    private String reservedByName;

    public Seat() {}

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    public boolean isReserved() {
        return reserved;
    }
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
    public String getReservedByName() {
        return reservedByName;
    }
    public void setReservedByName(String reservedByName) {
        this.reservedByName = reservedByName;
    }
}
