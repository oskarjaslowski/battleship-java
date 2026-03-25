import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class Instrukcja extends JDialog {
    private static Instrukcja przypadekInstrukcji = null;
    public Instrukcja(JFrame okno) {
        super(okno);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel tytul = new JLabel("INSTRUKCJA GRY", SwingConstants.CENTER);
        tytul.setForeground(Color.GRAY);
        tytul.setFont(new Font("Monospaced", Font.PLAIN, 30));
        tytul.setBorder(new EmptyBorder(30, 0, 20, 0));
        add(tytul, BorderLayout.NORTH);

        JPanel panelTekstu = new JPanel();
        panelTekstu.setLayout(new BoxLayout(panelTekstu, BoxLayout.Y_AXIS));
        panelTekstu.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel[] teksty = {
                new JLabel(" "),
                new JLabel("Celem gry jest zatopienie wszystkich 9 statków przeciwnika."),
                new JLabel(" "),
                new JLabel("Są 3 poziomy trudności:"),
                new JLabel("Łatwy - przeciwnik pechowy, którego ruchy przypadkowo trafiają w planszę."),
                new JLabel("Normalny - przeciwnik domyślny, który czasami ustrzeli jakiś statek."),
                new JLabel("Trudny - przeciwnik fartowny, którego ruchy wyglądają jakby oszukiwał."),
                new JLabel(" "),
                new JLabel("W pierwszej kolejności należy ustawić swoje statki na polu przeciwnika."),
                new JLabel("Lewym przyciskiem myszy chwytamy statek i umieszczamy go drugim kliknięciem."),
                new JLabel("Prawym przyciskiem myszy można zmienić jego orientacje."),
                new JLabel(" "),
                new JLabel("Jest 5 masztowców, które różnią się jedynie długością."),
                new JLabel("Są 4 specjalne statki, które mają dodatkowe funkcjonalności:"),
                new JLabel("Nawigator po zatopieniu pokazuje pole, na którym znajduje się inny statek."),
                new JLabel("Pancernik musi zostać trafiony 2 razy, żeby zostać zatopionym."),
                new JLabel("Bombowiec wysadza pola wzdłuż linii, na których się znajdował."),
                new JLabel("Niszczyciel po trafieniu zadaje podwójny atak do czasu zatopienia.")
        };

        for (JLabel tekst : teksty) {
            tekst.setForeground(Color.GRAY);
            tekst.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelTekstu.add(tekst);
        }
        add(panelTekstu, BorderLayout.CENTER);

        JButton przycisk = new JButton("Zamknij");
        przycisk.setBackground(Color.GRAY);
        przycisk.setForeground(Color.WHITE);
        przycisk.setFocusPainted(false);
        przycisk.setOpaque(true);
        przycisk.addActionListener(e -> dispose());
        JPanel panelPrzycisku = new JPanel();
        panelPrzycisku.add(przycisk);
        add(panelPrzycisku, BorderLayout.SOUTH);

        setVisible(true);
    }
    public static void WyswietlInstrukcje(JFrame okno) {
        if (przypadekInstrukcji != null) {
            przypadekInstrukcji.dispose();
        }
        przypadekInstrukcji = new Instrukcja(okno);
        przypadekInstrukcji.setVisible(true);
    }
}