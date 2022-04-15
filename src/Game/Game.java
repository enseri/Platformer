package Game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame {
    public Game() {
        initUI();
    }

    private long lastFrame;
    private double timePerFrame = 1000000000.0 / 60.0;

    private void initUI() {
        add(new Map());
        setResizable(false);
        pack();
        setTitle("Platformer");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loopGame() {
        while (true) {
            System.out.print("");
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                repaint();
            } else {

            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }
}
