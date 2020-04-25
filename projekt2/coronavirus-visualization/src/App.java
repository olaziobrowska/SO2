import model.Country;
import model.Person;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class App extends JFrame {

    public App() {
        initUI();
    }

    private void initUI() {
        setTitle("Coronavirus visualization");
        setSize(1280, 1024);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel pane = new JPanel();
        getContentPane().add(pane, BorderLayout.CENTER);

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Country poland = new Country("Poland", 4, 4, 3);
        Country italy = new Country("Italy", 4, 4, 6);
        Country romania = new Country("Romania", 4, 4, 2);

        JPanel countryPanel = new JPanel();
        countryPanel.setLayout(new GridLayout(1, 3));

        countryPanel.add(poland);
        countryPanel.add(italy);
        countryPanel.add(romania);

        pane.add(countryPanel);

        JPanel summary = new JPanel();
        summary.setBackground(Color.yellow);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 200;      //make this component tall
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(summary, c);
        pack();
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            App ex = new App();
            ex.setVisible(true);
        });
    }
}
