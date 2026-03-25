import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
public abstract class Plansza extends JPanel {
    protected Statek[][] statki;
    protected JButton[][] plansza;
    protected static final int ROZMIAR_PLANSZY = 10;
    protected JPanel nazwaPlanszy = new JPanel(new FlowLayout(FlowLayout.CENTER));
    protected JPanel komunikaty = new JPanel(new FlowLayout(FlowLayout.CENTER));
    protected JLabel nazwaTekstu = new JLabel();
    protected JLabel komunikat = new JLabel("Tutaj będą pojawiać się komunikaty");

    public Plansza(String nazwa) {
        nazwaTekstu.setText(nazwa);
        nazwaTekstu.setForeground(Color.GRAY);
        nazwaTekstu.setFont(new Font("Monospaced", Font.PLAIN, 20));
        komunikat.setForeground(Color.GRAY);
        komunikat.setBorder(new EmptyBorder(0, 0, 10, 0));
        nazwaPlanszy.add(nazwaTekstu);
        komunikaty.add(komunikat, BorderLayout.CENTER);

        setLayout(new BorderLayout());

        JPanel planszaPanel = new JPanel();
        int rozmiarPrzycisku = 50;
        planszaPanel.setBackground(Color.GRAY);
        planszaPanel.setPreferredSize(new Dimension(ROZMIAR_PLANSZY * rozmiarPrzycisku, ROZMIAR_PLANSZY * rozmiarPrzycisku));
        planszaPanel.setBorder(new LineBorder(Color.GRAY, 5));
        utworzPlansze(planszaPanel);

        add(nazwaPlanszy, BorderLayout.NORTH);
        add(komunikaty, BorderLayout.CENTER);
        add(planszaPanel, BorderLayout.SOUTH);
    }

    protected abstract void utworzPlansze(JPanel pole);
    protected abstract void aktualizujPole(int x, int y);

    protected void resetujPlansze(Color color) {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                plansza[i][j].setBackground(color);
            }
        }
    }

    protected void zablokujPlansze() {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                plansza[i][j].setEnabled(false);
            }
        }
    }

    protected void odblokujPlansze() {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                plansza[i][j].setEnabled(true);
            }
        }
    }

    protected boolean czyMoznaUmiescicStatek(int x, int y, int dlugosc, boolean poziomo) {
        int koncowaWspolrzedna = poziomo ? x + dlugosc : y + dlugosc;

        if ((poziomo && koncowaWspolrzedna <= ROZMIAR_PLANSZY) || (!poziomo && koncowaWspolrzedna <= ROZMIAR_PLANSZY)) {
            for (int i = -1; i <= dlugosc; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nowyX = poziomo ? x + i : x + j;
                    int nowyY = poziomo ? y + j : y + i;

                    if (czyNaPlanszy(nowyX, nowyY)) {
                        if (statki[nowyX][nowyY] != null) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean czyNaPlanszy(int x, int y) {
        return x >= 0 && x < ROZMIAR_PLANSZY && y >= 0 && y < ROZMIAR_PLANSZY;
    }

    protected void umiescStatekNaPlanszy(int x, int y, int dlugosc, boolean pionowo, Statek statek) {
        if (pionowo) {
            for (int i = 0; i < dlugosc; i++) {
                statki[x + i][y] = statek;
            }
        } else {
            for (int i = 0; i < dlugosc; i++) {
                statki[x][y + i] = statek;
            }
        }
    }

    protected void usunStatekZPlanszy(Statek statek) {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                if (statki[i][j] == statek) {
                    statki[i][j] = null;
                }
            }
        }
    }

    protected void usunWszystkieStatki() {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                statki[i][j] = null;
            }
        }
    }

    public boolean czyPoleZawieraPancernik(int x, int y) {
        return !(statki[x][y] instanceof Pancernik);
    }

    protected void ustawKomunikat(String tekst) {
        komunikat.setText(tekst);
    }

    protected void wyswietlEkranKoncowy(boolean wygrana) {
        JFrame okno = new JFrame();
        EkranKoncowy ekran = new EkranKoncowy(okno, wygrana);
        ekran.setVisible(true);
    }
}