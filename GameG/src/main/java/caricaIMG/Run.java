package caricaIMG;

import javax.swing.*;
import java.awt.*;

public class Run extends JFrame{

    public Run() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Moving sprite");
        setSize(1920, 1202);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Run ex = new Run();
            ex.setVisible(true);
        });
    }
}
