import javax.swing.*;
import java.awt.*;
import java.util.List;
public class Wyniki extends JDialog {
    private static Wyniki przypadekWynikow = null;

    protected Wyniki(JFrame okno) {
        super(okno);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel tytul = new JLabel("TABELA WYNIKÓW", SwingConstants.CENTER);
        tytul.setForeground(Color.GRAY);
        tytul.setFont(new Font("Monospaced", Font.PLAIN, 30));
        add(tytul, BorderLayout.NORTH);

        List<Gracz> gracze = Gracz.odczytajWszystkichGraczy();

        if (gracze.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Brak wyników do wyświetlenia.", "Brak wyników", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        gracze.sort((g1, g2) -> Integer.compare(g2.wynik(), g1.wynik()));

        String[][] data = new String[gracze.size()][3];
        for (int i = 0; i < gracze.size(); i++) {
            Gracz gracz = gracze.get(i);
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = gracz.pseudonim();
            data[i][2] = String.valueOf(gracz.wynik());
        }

        String[] columnNames = {"Pozycja", "Pseudonim", "Wynik"};

        NiemodyfikowalnaTabela tabela = new NiemodyfikowalnaTabela(data, columnNames);
        JScrollPane suwak = new JScrollPane(tabela);
        add(suwak, BorderLayout.CENTER);

        JButton przycisk = new JButton("Zamknij");
        przycisk.setBackground(Color.GRAY);
        przycisk.setForeground(Color.WHITE);
        przycisk.setFocusPainted(false);
        przycisk.setOpaque(true);
        przycisk.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(przycisk);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(okno);
        setResizable(false);
    }

    public static void WyswietlWyniki(JFrame okno) {
        if (przypadekWynikow != null) {
            przypadekWynikow.dispose();
        }
        przypadekWynikow = new Wyniki(okno);
        przypadekWynikow.setVisible(true);
    }

    private static class NiemodyfikowalnaTabela extends JTable {
        public NiemodyfikowalnaTabela(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}