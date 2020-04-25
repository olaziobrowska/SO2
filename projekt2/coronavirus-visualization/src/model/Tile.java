package model;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class Tile extends JPanel {

    private static final Color HEALTHY_COLOR = Color.GREEN;
    private static final Color INFECTED_COLOR = Color.RED;
    private static final Color NO_PERSON_COLOR = Color.WHITE;

    private Person visitor;
    private JLabel text;

    private Semaphore semaphore;

    public Tile(Person visitor) {
        this.semaphore = new Semaphore(1, true);
        this.visitor = visitor;
        initUI();
    }

    public Tile() {
        this.semaphore = new Semaphore(1, true);
        this.visitor = null;
        initUI();
    }

    private void initUI() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(NO_PERSON_COLOR);

        this.text = new JLabel("");
        if (this.visitor != null) {
            this.text.setText(this.visitor.getPersonName());
        }

        this.add(this.text);
    }

    public boolean isFree() {
        return visitor == null;
    }

    public void free() {
        this.visitor = null;
        this.text.setText("");
        this.setBackground(NO_PERSON_COLOR);
        this.semaphore.release();
    }

    public Person getVisitor() {
        return visitor;
    }

    public void setVisitor(Person visitor) {
        try {
            this.semaphore.acquire();
            this.visitor = visitor;
            this.text.setText(visitor.getPersonName());
            if (visitor.getStatus().equals(HealthStatus.HEALTHY)) {
                this.setBackground(HEALTHY_COLOR);
            } else if (visitor.getStatus().equals(HealthStatus.INFECTED)) {
                this.setBackground(INFECTED_COLOR);
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
