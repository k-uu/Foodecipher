package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a closable Tab component for a JTabbedPane
public class ClosableTab extends JPanel {

    private final JTabbedPane panes;

    // EFFECTS: constructs a tab component with tabName and a button to close it.
    // Throws a NullPointerException if the JTabbedPane to add this to is null
    public ClosableTab(JTabbedPane panes, String tabName) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (panes == null) {
            throw new NullPointerException("panes was never initialized");
        }
        this.panes = panes;
        setOpaque(false);

        JLabel label = new JLabel(tabName);
        add(label);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        JButton deleteButton = new TabButton();
        add(deleteButton);
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    // Represents a button to remove an active tab
    private class TabButton extends JButton implements ActionListener {
        public TabButton() {
            int size = 15;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("close this tab");
            setUI(new BasicButtonUI());
            setContentAreaFilled(false);
            setFocusable(false);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = panes.indexOfTabComponent(ClosableTab.this);
            if (i != -1) {
                panes.remove(i);

            }
        }

        //EFFECTS: draw button graphic
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.BLACK);
            g2.drawOval(3, 3, 10, 10);
            g2.setColor(Color.RED);
            g2.fillOval(3, 3, 10, 10);
            g2.dispose();
        }
    }
}