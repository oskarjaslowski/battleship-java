import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Gra w Statki");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon icon = new ImageIcon("ikona.png");
        setIconImage(icon.getImage());

        JLabel tytul = new JLabel("GRA W STATKI");
        tytul.setForeground(Color.GRAY);
        tytul.setFont(new Font("Monospaced", Font.PLAIN, 70));

        JPanel panelTytulu = new JPanel();
        panelTytulu.add(tytul);

        PlanszaPrzeciwnika planszaPrzeciwnika = new PlanszaPrzeciwnika("PRZECIWNIK");
        PlanszaGracza planszaGracza = new PlanszaGracza("GRACZ", new Przeciwnik(planszaPrzeciwnika));
        planszaGracza.setBorder(new EmptyBorder(0, 20, 20, 20));
        planszaPrzeciwnika.setBorder(new EmptyBorder(0, 20, 20, 20));

        JPanel panelMenu = new Menu(planszaGracza, planszaPrzeciwnika);
        panelMenu.setBorder(new EmptyBorder(74, 0, 0, 0));

        JPanel glownyPanel = new JPanel(new BorderLayout());
        glownyPanel.add(panelMenu, BorderLayout.CENTER);
        glownyPanel.add(planszaGracza, BorderLayout.WEST);
        glownyPanel.add(planszaPrzeciwnika, BorderLayout.EAST);

        Container glownyKontener = getContentPane();
        glownyKontener.setLayout(new BorderLayout());
        glownyKontener.add(panelTytulu);
        glownyKontener.add(glownyPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}