package model;

import ui.SummaryPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Country extends JPanel {

    private static final int INFECTION_PROBABILITY_CLOSE = 40;

    private String name;
    private Tile[][] tiles;
    private List<Person> people;
    private int countryWidth;
    private int countryHeight;
    private Random r;
    private SummaryPanel summaryPanel;

    public Country(String name, int width, int height, int populationNumber) {
        initUI(name, width, height);
        this.r = new Random();
        this.countryWidth = width;
        this.countryHeight = height;
        this.name = name;
        System.out.println("Creating country of width = " + width + " and height = " + height);
        this.tiles = prepareTiles(width, height);
        if (populationNumber >= (width * height) / 3)
            populationNumber = (width * height) / 3;

        this.people = preparePeople(populationNumber);
    }

    private void initUI(String name, int width, int height) {
        this.setLayout(new GridLayout(width, height));
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(500, 500));
        this.setBorder(BorderFactory.createTitledBorder(null, name, TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,24), Color.yellow));
    }

    private List<Person> preparePeople(int populationNumber) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < populationNumber; i++) {
            int initialX = r.nextInt(this.tiles.length);
            int initialY = r.nextInt(this.tiles[0].length);

            while (!tiles[initialX][initialY].isFree()) {
                initialX = r.nextInt(this.tiles.length);
                initialY = r.nextInt(this.tiles[0].length);
            }

            Person p = new Person(this.name + " " + (i + 1), initialX, initialY, this);
            if (i == 0)
                p.setStatus(HealthStatus.INFECTED);
            tiles[initialX][initialY].setVisitor(p);
            people.add(p);
        }
        return people;
    }

    public int getInfectedNumber() {
        int i = 0;
        for (Person p : this.people) {
            if (p.getStatus().equals(HealthStatus.INFECTED))
                i++;
        }
        return i;
    }

    public void updateSummary() {
        this.summaryPanel.updateSummary(this.name, this.people.size(), getInfectedNumber());
    }

    public void movePerson(Person p, int destinationX, int destinationY) {
        this.tiles[p.getPositionX()][p.getPositionY()].free();

        if (isInfectedPersonNearby(destinationX, destinationY)) {
            int infectionProbability = r.nextInt(100);
            if (infectionProbability > INFECTION_PROBABILITY_CLOSE) {
                p.setStatus(HealthStatus.INFECTED);
            }
        }

        this.tiles[destinationX][destinationY].setVisitor(p);
    }

    private boolean isInfectedPersonNearby(int x, int y) {
        int right = (x + 1) % countryWidth;
        int left = x - 1 >= 0 ? x - 1 : 0;
        int up = (y + 1) % countryHeight;
        int down = y - 1 >= 0 ? y - 1 : 0;

        if (isInfectedTile(right, y) || isInfectedTile(left, y) || isInfectedTile(x, up) || isInfectedTile(x , down)) {
            return true;
        }
        return false;
    }

    private boolean isInfectedTile(int x, int y) {
        if (! tiles[x][y].isFree()) {
            return tiles[x][y].getVisitor().getStatus() == HealthStatus.INFECTED;
        }
        return false;
    }

    private Tile[][] prepareTiles(int width, int height) {
        Tile[][] tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile();
                this.add(tiles[i][j]);
            }
        }

        return tiles;
    }

    public SummaryPanel getSummaryPanel() {
        return summaryPanel;
    }

    public void setSummaryPanel(SummaryPanel summaryPanel) {
        this.summaryPanel = summaryPanel;
    }

    public int getCountryWidth() {
        return countryWidth;
    }

    public void setCountryWidth(int countryWidth) {
        this.countryWidth = countryWidth;
    }

    public int getCountryHeight() {
        return countryHeight;
    }

    public void setCountryHeight(int countryHeight) {
        this.countryHeight = countryHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
