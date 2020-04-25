package model;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    private static final Color HEALTHY_COLOR = Color.GREEN;
    private static final Color INFECTED_COLOR = Color.RED;
    private static final Color NO_PERSON_COLOR = Color.WHITE;

    private Person visitor;

    public Tile(Person visitor) {
        this.visitor = visitor;
        initUI();
    }

    public Tile() {
        this.visitor = null;
        initUI();
    }

    private void initUI() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(NO_PERSON_COLOR);
    }

    public boolean isFree() {
        return visitor == null;
    }

    public void free() {
        this.visitor = null;
        this.setBackground(NO_PERSON_COLOR);
    }

    public Person getVisitor() {
        return visitor;
    }

    public void setVisitor(Person visitor) {
        this.visitor = visitor;
        if (visitor.getStatus().equals(HealthStatus.HEALTHY)) {
            this.setBackground(HEALTHY_COLOR);
        }
        else if (visitor.getStatus().equals(HealthStatus.INFECTED)) {
            this.setBackground(INFECTED_COLOR);
        }
    }
}
