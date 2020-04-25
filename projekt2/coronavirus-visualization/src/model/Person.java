package model;

public class Person {

    private String name;
    private HealthStatus status;
    private int positionX;
    private int positionY;

    public Person(String name, int initialPositionX, int initialPositionY) {
        this.name = name;
        this.status = HealthStatus.HEALTHY;
        this.positionX = initialPositionX;
        this.positionY = initialPositionY;
    }

    public String getName() {
        return name;
    }

    public HealthStatus getStatus() {
        return status;
    }

    public void setStatus(HealthStatus status) {
        this.status = status;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
