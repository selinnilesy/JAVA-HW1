import javax.swing.*;

import static java.lang.Thread.sleep;


public class SimulationRunner {
    private JFrame window = new JFrame();
    private Display display = new Display();

    private static void createAndShowGUI() {
        SimulationRunner runner = new SimulationRunner();
        runner.window.add(runner.display);
        runner.window.setTitle(Common.getTitle());
        runner.window.setSize(Common.getWindowWidth(), Common.getWindowHeight());
        //runner.window.setSize(1800, 1300);
        runner.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runner.window.setResizable(false);
        runner.window.setLocationRelativeTo(null);
        runner.window.setVisible(true);


        new Timer(5, actionEvent -> {
            Common.stepAllEntities();
            runner.display.repaint();
            runner.display.revalidate();

        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimulationRunner::createAndShowGUI);
    }
}