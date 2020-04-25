package model;

import java.util.Random;

public class Person extends Thread {

    private static final int[] MOVE_DIRECTION = {-1, 1};

    private String personName;
    private HealthStatus status;
    private int positionX;
    private int positionY;
    private Country country;
    private int sleepTime;
    private Random random;

    public Person(String name, int initialPositionX, int initialPositionY, Country country) {
        super(name);
        this.random = new Random();
        this.personName = name;
        this.status = HealthStatus.HEALTHY;
        this.positionX = initialPositionX;
        this.positionY = initialPositionY;
        this.country = country;
        this.sleepTime = 1000 + random.nextInt(2000);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.sleepTime);
                tryToMove();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tryToMove() {
        int moveX = MOVE_DIRECTION[random.nextInt(2)];
        int moveY = MOVE_DIRECTION[random.nextInt(2)];

        int newX = (positionX + moveX) % country.getCountryWidth();
        if (newX < 0)
            newX = 0;

        int newY = (positionY + moveY) % country.getCountryHeight();
        if (newY < 0)
            newY = 0;

        this.country.movePerson(this, newX, newY);
    }

    public String getPersonName() {
        return personName;
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
