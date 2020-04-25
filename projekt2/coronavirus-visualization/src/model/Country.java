package model;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Country extends JPanel {

    private String name;
    private Tile[][] tiles;
    private List<Person> people;

    public Country(String name, int width, int height, int populationNumber) {
        initUI(name, width, height);
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
        this.setBorder(BorderFactory.createTitledBorder(null, name, TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,12), Color.yellow));
    }

    private List<Person> preparePeople(int populationNumber) {
        Random r = new Random();
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < populationNumber; i++) {
            int initialX = r.nextInt(this.tiles.length);
            int initialY = r.nextInt(this.tiles[0].length);

            while (!tiles[initialX][initialY].isFree()) {
                initialX = r.nextInt(this.tiles.length);
                initialY = r.nextInt(this.tiles[0].length);
            }

            Person p = new Person(this.name + " " + (i + 1), initialX, initialY);
            tiles[initialX][initialY].setVisitor(p);
            people.add(p);
        }
        return people;
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
