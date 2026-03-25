import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
/*
Opóźnienie przeciwnika
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
*/

public class PlanszaGracza extends Plansza {
    public static int trafioneStatkiGracza;
    public static int wynik;
    private final Nawigator nawigator;
    private final Bombowiec bombowiec;
    private Niszczyciel niszczyciel;
    protected Przeciwnik przeciwnik;
    private boolean wybuchBombowca;

    //private final ScheduledExecutorService wykonawca = Executors.newSingleThreadScheduledExecutor();

    public PlanszaGracza(String nazwa, Przeciwnik przeciwnik) {
        super(nazwa);
        this.przeciwnik = przeciwnik;
        statki = new Statek[ROZMIAR_PLANSZY][ROZMIAR_PLANSZY];
        trafioneStatkiGracza = 0;
        nawigator = new Nawigator();
        bombowiec = new Bombowiec();
        niszczyciel = new Niszczyciel();
        inicjalizujPlansze();
        zablokujPlansze();
    }

    @Override
    protected void utworzPlansze(JPanel pole) {
        plansza = new Pole[ROZMIAR_PLANSZY][ROZMIAR_PLANSZY];
        pole.setLayout(new GridLayout(ROZMIAR_PLANSZY, ROZMIAR_PLANSZY));

        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                plansza[i][j] = new Pole(Color.LIGHT_GRAY);
                plansza[i][j].addActionListener(new PlanszaActionListener(i, j));
                pole.add(plansza[i][j]);
            }
        }
    }

    public void inicjalizujPlansze() {
        umiescStatek(new Pieciomasztowiec());
        umiescStatek(new Czteromasztowiec());
        umiescStatek(new Trojmasztowiec());
        umiescStatek(new Niszczyciel());
        umiescStatek(new Dwumasztowiec());
        umiescStatek(new Bombowiec());
        umiescStatek(new Jednomasztowiec());
        umiescStatek(new Nawigator());
        umiescStatek(new Pancernik());
        przeciwnik.wyczyscWybranePola();
        wynik = 100;
        niszczyciel = new Niszczyciel();
        PlanszaPrzeciwnika.niszczyciel = new Niszczyciel();
    }

    private void umiescStatek(Statek statek) {
        int x, y;
        boolean umieszczony = false;

        while (!umieszczony) {
            x = (int) (Math.random() * ROZMIAR_PLANSZY);
            y = (int) (Math.random() * ROZMIAR_PLANSZY);

            boolean pionowo = (Math.random() < 0.5);

            if (czyMoznaUmiescicStatek(x, y, statek.getDlugosc(), pionowo)) {
                umieszczony = true;
                umiescStatekNaPlanszy(x, y, statek.getDlugosc(), pionowo, statek);
            }
        }
    }
    private class PlanszaActionListener implements ActionListener {
        private final int x;
        private final int y;

        public PlanszaActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            aktualizujPole(x, y);
        }
    }

    protected void aktualizujPole(int x, int y) {
        char litera = (char) ('A' + y);
        if (statki[x][y] != null) {
            statki[x][y].trafiony();
            plansza[x][y].setBackground(statki[x][y].getKolor());
            if (Objects.equals(statki[x][y].getRodzaj(), "Niszczyciel")) {
                niszczyciel = (Niszczyciel) statki[x][y];
            }
            trafioneStatkiGracza++;
            wynik++;

            if (statki[x][y].isZatopiony()) {
                reakcjaPoZatopieniu(statki[x][y], x, y);
            } else {
                plansza[x][y].setBackground(statki[x][y].getKolor());
                if (!Objects.equals(statki[x][y].getRodzaj(), "Pancernik")) plansza[x][y].setEnabled(false);
                ustawKomunikat("(" + (x + 1) + " - " + litera + ") Trafiony " + statki[x][y].getRodzaj() + "!");
            }

            if (trafioneStatkiGracza == 23) {
                zakonczGre();
                return;
            }
        } else {
            plansza[x][y].setBackground(Color.BLUE);
            plansza[x][y].setEnabled(false);
            ustawKomunikat("(" + (x + 1) + " - " + litera + ") Pudło!");
            wynik--;
        }
        wykonajRuchPrzeciwnika();
    }

    private void reakcjaPoZatopieniu(Statek statek, int x, int y) {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                if (statki[i][j] == statek) {
                    plansza[i][j].setBackground(statek.getKolor());
                    plansza[i][j].setEnabled(false);

                    if (Objects.equals(statek.getRodzaj(), "Bombowiec")) {
                        reakcjaPoZatopieniuBombowca(i, j);
                    }
                }
            }
        }

        ustawKomunikat("(" + (x + 1) + " - " + (char) ('A' + y) + ") Zatopiony " + statek.getRodzaj() + "!");
        usunStatekZPlanszy(statek);
        if (Objects.equals(statek.getRodzaj(), "Nawigator")) {
            reakcjaPoZatopieniuNawigatora();
        }
    }

    private void zakonczGre() {
        ustawKomunikat("Zatopiłeś wszystkie statki!");
        trafioneStatkiGracza = 0;
        PlanszaPrzeciwnika.trafioneStatkiPrzeciwnika = 0;
        wyswietlEkranKoncowy(true);
        zablokujPlansze();
    }

    private void wykonajRuchPrzeciwnika() {
        if (!wybuchBombowca) przeciwnik.wykonajRuch();
        //if (!wybuchBombowca) wykonawca.schedule(() -> przeciwnik.wykonajRuch(), 500, TimeUnit.MILLISECONDS);
        if (niszczyciel.isTrafiony() && !niszczyciel.isZatopiony() && PlanszaPrzeciwnika.trafioneStatkiPrzeciwnika != 0) przeciwnik.wykonajRuch();
        //if (niszczyciel.isTrafiony() && !niszczyciel.isZatopiony() && PlanszaPrzeciwnika.trafioneStatkiPrzeciwnika != 0) wykonawca.schedule(() -> przeciwnik.wykonajRuch(), 500, TimeUnit.MILLISECONDS);
    }

    private void reakcjaPoZatopieniuNawigatora() {
        Point nietrafionePole = nawigator.znajdzNietrafionePole(statki);
        if (nietrafionePole != null) {
            int x = (int) nietrafionePole.getX();
            int y = (int) nietrafionePole.getY();
            plansza[x][y].setBackground(Color.GREEN);
        }
    }

    protected void reakcjaPoZatopieniuBombowca(int i, int j) {
        ArrayList<Point> nietrafionePola = bombowiec.znajdzNietrafionePola(statki);
        wybuchBombowca = true;
        if (!nietrafionePola.isEmpty()) {
            for (Point pole : nietrafionePola) {
                int x = (int) pole.getX();
                int y = (int) pole.getY();
                if ((x == i || y == j) && plansza[x][y].isEnabled()) {
                    aktualizujPole(x, y);
                }
            }
        }
        for (int k = 0; k < ROZMIAR_PLANSZY; k++) {
            if (plansza[i][k].isEnabled() && czyPoleZawieraPancernik(i, k)) {
                plansza[i][k].setBackground(Color.BLUE);
                plansza[i][k].setEnabled(false);
            }
            if (plansza[k][j].isEnabled() && czyPoleZawieraPancernik(k, j)) {
                plansza[k][j].setBackground(Color.BLUE);
                plansza[k][j].setEnabled(false);
            }
        }
        wybuchBombowca = false;
    }
}