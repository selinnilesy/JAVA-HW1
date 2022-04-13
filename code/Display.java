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

        Common.Mexico.draw( (Graphics2D) g );
        Common.Chile.draw( (Graphics2D) g );
        Common.Nigeria.draw( (Graphics2D) g );
        Common.Poland.draw( (Graphics2D) g );
        Common.Malaysia.draw( (Graphics2D) g );

        Common.boeing.draw( (Graphics2D) g );
        Common.general_dynamics.draw( (Graphics2D) g );
        Common.lockheed_martin.draw( (Graphics2D) g );
        Common.northrop_grumman.draw( (Graphics2D) g );
        Common.raytheon.draw( (Graphics2D) g );


        // TODO: draw other entities
    }
}