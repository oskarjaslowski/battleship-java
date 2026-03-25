import javax.swing.*;
import java.awt.*;
public class Menu extends JPanel {
    private final PlanszaGracza planszaGracza;
    private final PlanszaPrzeciwnika planszaPrzeciwnika;
    private JButton[] przyciski;
    private JLabel[] teksty;
    private final String[] nazwyPrzyciskow = {"Rozpocznij Grę", "Restartuj Grę",
            "Wyniki", "Instrukcja", "Wyjdź", "Łatwy", "Normalny", "Trudny",
            "Jednomasztowiec", "Nawigator", "Pancernik", "Dwumasztowiec",
            "Bombowiec", "Trójmasztowiec", "Niszczyciel", "Czteromasztowiec",
            "Pięciomasztowiec", "Akceptuj", "Tak", "Nie"};
    protected JButton akceptujPrzycisk;

    public Menu(PlanszaGracza planszaGracza, PlanszaPrzeciwnika planszaPrzeciwnika) {
        this.planszaGracza = planszaGracza;
        this.planszaPrzeciwnika = planszaPrzeciwnika;
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 9));
        inicjalizujPrzyciski();
        inicjalizujTeksty();
        utworzMenu();
    }

    private void inicjalizujPrzyciski() {
        przyciski = new JButton[nazwyPrzyciskow.length];
        for (int i = 0; i < nazwyPrzyciskow.length; i++) {
            przyciski[i] = new Przycisk(nazwyPrzyciskow[i], this, planszaGracza, planszaPrzeciwnika);
            if (nazwyPrzyciskow[i].equals("Akceptuj")) {
                akceptujPrzycisk = przyciski[i];
            }
        }
    }

    private void inicjalizujTeksty() {
        teksty = new JLabel[3];
        teksty[0] = new JLabel("Wybierz poziom trudności:");
        teksty[1] = new JLabel("Rozmieść statki:");
        teksty[2] = new JLabel("Chcesz zresetować grę?");
    }

    private void utworzMenu() {
        removeAll();
        add(przyciski[0]);
        add(przyciski[2]);
        add(przyciski[3]);
        add(przyciski[4]);
        revalidate();
        repaint();
    }

    public void utworzMenuGry() {
        removeAll();
        add(przyciski[1]);
        add(przyciski[2]);
        add(przyciski[3]);
        add(przyciski[4]);
        revalidate();
        repaint();
    }

    public void utworzMenuAkceptacji() {
        removeAll();
        teksty[2].setForeground(Color.GRAY);
        add(teksty[2]);
        add(przyciski[18]);
        add(przyciski[19]);
        revalidate();
        repaint();
    }

    public void utworzMenuTrudnosci() {
        removeAll();
        teksty[0].setForeground(Color.GRAY);
        add(teksty[0]);
        add(przyciski[5]);
        add(przyciski[6]);
        add(przyciski[7]);
        revalidate();
        repaint();
    }

    public void utworzMenuStatkow() {
        removeAll();
        teksty[1].setForeground(Color.GRAY);
        add(teksty[1]);
        for (int i = 8; i < przyciski.length - 3; i++) {
            add(przyciski[i]);
        }
        revalidate();
        repaint();
    }
}