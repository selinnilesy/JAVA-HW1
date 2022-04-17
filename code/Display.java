import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    public Display() { this.setBackground(new Color(180, 180, 180)); }

    @Override
    public Dimension getPreferredSize() { return super.getPreferredSize(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Common.getFoodPrice().draw((Graphics2D) g);
        Common.getElectronicsPrice().draw((Graphics2D) g);
        Common.getGoldPrice().draw((Graphics2D) g);

        g.drawLine(Common.getFirstVerticalLineX(), 0, Common.getFirstVerticalLineX(), Common.getHorizontalLineY());
        g.drawLine(Common.getSecondVerticalLineX(), 0, Common.getSecondVerticalLineX(), Common.getHorizontalLineY());
        g.drawLine(0, Common.getHorizontalLineY(), Common.getWindowWidth(), Common.getHorizontalLineY());

        // TODO: draw other entities

        for(Country x : Common.getCountries())
            x.draw( (Graphics2D) g  );

        for(Corporation x : Common.getCorporations())
            x.draw( (Graphics2D) g  );

    }
}