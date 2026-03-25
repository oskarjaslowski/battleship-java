import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Przycisk extends JButton {
    private final Menu menu;
    private final PlanszaGracza planszaGracza;
    private final PlanszaPrzeciwnika planszaPrzeciwnika;

    public Przycisk(String tekst, Menu menu, PlanszaGracza planszaGracza, PlanszaPrzeciwnika planszaPrzeciwnika) {
        super(tekst);
        this.menu = menu;
        this.planszaGracza = planszaGracza;
        this.planszaPrzeciwnika = planszaPrzeciwnika;

        setPreferredSize(new Dimension(200, 40));
        setBackground(Color.GRAY);
        setForeground(Color.WHITE);
        setBorder(new LineBorder(Color.GRAY, 5));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        addActionListener(e -> operowaniePrzyciskow(tekst));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(Color.DARK_GRAY);
                setBorder(new LineBorder(Color.DARK_GRAY, 5));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(Color.GRAY);
                setBorder(new LineBorder(Color.GRAY, 5));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.LIGHT_GRAY);
                setBorder(new LineBorder(Color.LIGHT_GRAY, 5));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.GRAY);
                setBorder(new LineBorder(Color.GRAY, 5));
            }
        });
    }

    private void operowaniePrzyciskow(String tekst) {
        switch (tekst) {
            case "Rozpocznij Grę":
                menu.utworzMenuTrudnosci();
                planszaPrzeciwnika.odblokujPlansze();
                break;
            case "Restartuj Grę":
                menu.utworzMenuAkceptacji();
                break;
            case "Wyniki":
                Wyniki.WyswietlWyniki(new JFrame());
                break;
            case "Instrukcja":
                Instrukcja.WyswietlInstrukcje(new JFrame());
                break;
            case "Wyjdź":
                System.exit(0);
                break;
            case "Łatwy":
                menu.utworzMenuStatkow();
                planszaGracza.ustawKomunikat("Umieść statki na planszy przeciwnika");
                planszaPrzeciwnika.ustawKomunikat("Umieść statki na planszy przeciwnika");
                planszaGracza.przeciwnik = new PrzeciwnikLatwy(planszaPrzeciwnika);
                break;
            case "Normalny":
                menu.utworzMenuStatkow();
                planszaGracza.ustawKomunikat("Umieść statki na planszy przeciwnika");
                planszaPrzeciwnika.ustawKomunikat("Umieść statki na planszy przeciwnika");
                PlanszaGracza.wynik += 50;
                break;
            case "Trudny":
                menu.utworzMenuStatkow();
                planszaGracza.ustawKomunikat("Umieść statki na planszy przeciwnika");
                planszaPrzeciwnika.ustawKomunikat("Umieść statki na planszy przeciwnika");
                planszaGracza.przeciwnik = new PrzeciwnikTrudny(planszaPrzeciwnika);
                PlanszaGracza.wynik += 100;
                break;
            case "Jednomasztowiec":
                if (planszaPrzeciwnika.umiescStatekNaPoczatek(new Jednomasztowiec())) {
                    usunPrzycisk();
                    planszaPrzeciwnika.ustawKomunikat("Jednomasztowiec na planszy");
                }
                break;
            case "Nawigator":
                if (planszaPrzeciwnika.umiescStatekNaPoczatek(new Nawigator())) {
                    usunPrzycisk();
                    planszaPrzeciwnika.ustawKomunikat("Nawigator na planszy");
                }
                break;
            case "Pancernik":
                if (planszaPrzeciwnika.umiescStatekNaPoczatek(new Pancernik())) {
                    usunPrzycisk();
                    planszaPrzeciwnika.ustawKomunikat("Pancernik na planszy");
                }
                break;
            case "Dwumasztowiec":
                if (planszaPrzeciwnika.umiescStatekNaPoczatek(new Dwumasztowiec())) {
                    usunPrzycisk();
                    planszaPrzeciwnika.ustawKomunikat("Dwumasztowiec na planszy");
                }
                break;
            case "Bombowiec":
                if (planszaPrzeciwnika.umiescStatekNaPoczatek(new Bombowiec())) {
                    usunPrzycisk();
                    planszaPrzeciwnika.ustawKomunikat("Bombowiec na planszy");
                }
                break;
            case "Trójmasztowiec":
                if (planszaPrzeciwnika.umiescStatekNaPoczatek(new Trojmasztowiec())) {
                    usunPrzycisk();
                    planszaPrzeciwnika.ustawKomunikat("Trójmasztowiec na planszy");
                }
                break;
            case "Niszczyciel":
                if (planszaPrzeciwnika.umiescStatekNaPoczatek(new Niszczyciel())) {
                    usunPrzycisk();
                    planszaPrzeciwnika.ustawKomunikat("Niszczyciel na planszy");
                }
                break;
            case "Czteromasztowiec":
                if (planszaPrzeciwnika.umiescStatekNaPoczatek(new Czteromasztowiec())) {
                    usunPrzycisk();
                    planszaPrzeciwnika.ustawKomunikat("Czteromasztowiec na planszy");
                }
                break;
            case "Pięciomasztowiec":
                if (planszaPrzeciwnika.umiescStatekNaPoczatek(new Pieciomasztowiec())) {
                    usunPrzycisk();
                    planszaPrzeciwnika.ustawKomunikat("Pięciomasztowiec na planszy");
                }
                break;
            case "Akceptuj":
                if (!planszaPrzeciwnika.wybieraniePola) {
                    planszaGracza.odblokujPlansze();
                    planszaPrzeciwnika.zablokujPlansze();
                    menu.utworzMenuGry();
                    planszaGracza.ustawKomunikat("Odblokowano planszę gracza");
                    planszaPrzeciwnika.ustawKomunikat("Zablokowano planszę przeciwnika");
                } else {
                    JOptionPane.showMessageDialog(this, "Wszystkie statki muszą znajdować się na planszy!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Tak":
                planszaGracza.usunWszystkieStatki();
                planszaPrzeciwnika.usunWszystkieStatki();
                planszaGracza.zablokujPlansze();
                planszaPrzeciwnika.odblokujPlansze();
                planszaGracza.resetujPlansze(Color.LIGHT_GRAY);
                planszaPrzeciwnika.resetujPlansze(Color.BLUE);
                planszaGracza.inicjalizujPlansze();
                menu.utworzMenuTrudnosci();
                planszaGracza.ustawKomunikat("Tutaj będą pojawiać się komunikaty");
                planszaPrzeciwnika.ustawKomunikat("Tutaj będą pojawiać się komunikaty");
                PlanszaPrzeciwnika.trafioneStatkiPrzeciwnika = 1;
                PlanszaGracza.trafioneStatkiGracza = 0;
                break;
            case "Nie":
                menu.utworzMenuGry();
                break;
        }
    }

    private void usunPrzycisk() {
        Container rodzic = getParent();
        if (rodzic != null) {
            rodzic.remove(this);
            Component[] iloscPrzyciskow = rodzic.getComponents();
            if (iloscPrzyciskow.length == 1) {
                menu.add(menu.akceptujPrzycisk);
            }
            rodzic.revalidate();
            rodzic.repaint();
        }
    }
}