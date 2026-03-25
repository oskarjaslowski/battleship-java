import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class EkranKoncowy extends JDialog {
    private final JTextField poleNaPseudonim;
    public String nazwa;
    public EkranKoncowy(JFrame okno, boolean wygrana) {
        super(okno, true);

        setTitle(wygrana ? "Gratulacje! Wygrałeś!" : "Niestety przegrałeś!");
        poleNaPseudonim = new JTextField(12);
        JLabel podaj = new JLabel("Podaj swój pseudonim: ");
        podaj.setForeground(Color.GRAY);
        JButton przycisk = new JButton("Potwierdź");
        przycisk.setBackground(Color.GRAY);
        przycisk.setForeground(Color.WHITE);
        przycisk.setFocusPainted(false);
        przycisk.setOpaque(true);
        JLabel komunikat = new JLabel(wygrana ? "Wygrałeś! Twój wynik to: " + PlanszaGracza.wynik : "Niestety przegrałeś... Szkoda!");
        komunikat.setForeground(Color.GRAY);
        komunikat.setFont(new Font("Monospaced", Font.PLAIN, 30));
        JPanel glownyPanel = new JPanel();
        JPanel gornyPanel = new JPanel();
        JPanel panel = new JPanel();

        gornyPanel.add(komunikat);
        gornyPanel.setBorder(new EmptyBorder(60, 0, 60, 0));
        panel.add(wygrana ? podaj : new JLabel(), BorderLayout.WEST);
        panel.add(wygrana ? poleNaPseudonim : new JLabel(), wygrana ? BorderLayout.CENTER : BorderLayout.EAST);
        panel.add(przycisk, wygrana ? BorderLayout.EAST : BorderLayout.CENTER);
        glownyPanel.add(gornyPanel, BorderLayout.NORTH);
        glownyPanel.add(panel, BorderLayout.CENTER);
        glownyPanel.setPreferredSize(new Dimension(640, 360));

        przycisk.addActionListener(e -> {
            try {
                if (wygrana) {
                    nazwa = poleNaPseudonim.getText().trim();
                    if (nazwa.isEmpty()) {
                        throw new IllegalArgumentException("Wprowadź swój pseudonim!");
                    } else if (nazwa.length() < 3) {
                        throw new IllegalArgumentException("Pseudonim musi mieć co najmniej 3 znaki!");
                    } else if (nazwa.length() > 12) {
                        throw new IllegalArgumentException("Pseudonim nie może mieć więcej niż 12 znaków!");
                    } else {
                        Gracz gracz = new Gracz(nazwa, PlanszaGracza.wynik);
                        gracz.zapiszDoPliku();
                        JOptionPane.showMessageDialog(this, "Udało się zapisać twój wynik!", "Powodzenie", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } else {
                    dispose();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });

        setContentPane(glownyPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(okno);
        setResizable(false);
    }
}