package ui;

import model.Country;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SummaryPanel extends JPanel {

    private Map<String, JLabel> countryMap;

    public SummaryPanel(Country... countries) {
        initUI();
        countryMap = new HashMap<>(countries.length);
        for (Country c : countries) {
            JLabel j = new JLabel(getLabelText(c.getName(), c.getPeople().size(), c.getInfectedNumber()));
            j.setFont(new Font("times new roman", Font.PLAIN, 24));
            countryMap.put(c.getName(), j);

            add(j);
        }
    }

    public void updateSummary(String countryName, int countrySize, int infectedSize) {
        synchronized (this) {
            this.countryMap.get(countryName).setText(getLabelText(countryName, countrySize, infectedSize));
        }
    }

    private String getLabelText(String countryName, int countrySize, int infectedSize) {
        return "Country: " + countryName + ", population: " + countrySize + ", infected: " + infectedSize;
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.yellow);
        JLabel title = new JLabel("Infection summary");
        title.setFont(new Font("times new roman", Font.PLAIN, 36));
        add(title);
    }
}
