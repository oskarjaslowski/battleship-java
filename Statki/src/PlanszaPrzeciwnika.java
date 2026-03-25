import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class PlanszaPrzeciwnika extends Plansza {
    public static int trafioneStatkiPrzeciwnika;
    private Statek zapisanyStatek;
    protected boolean wybieraniePola = false;
    protected Bombowiec bombowiec;
    protected static Niszczyciel niszczyciel;

    public PlanszaPrzeciwnika(String nazwa) {
        super(nazwa);
        statki = new Statek[ROZMIAR_PLANSZY][ROZMIAR_PLANSZY];
        trafioneStatkiPrzeciwnika = 1;
        niszczyciel = new Niszczyciel();
        bombowiec = new Bombowiec();
    }

    @Override
    protected void utworzPlansze(JPanel pole) {
        plansza = new Pole[ROZMIAR_PLANSZY][ROZMIAR_PLANSZY];
        pole.setLayout(new GridLayout(ROZMIAR_PLANSZY, ROZMIAR_PLANSZY));

        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                plansza[i][j] = new Pole(Color.BLUE);
                pole.add(plansza[i][j]);
            }
        }
    }

    private void aktualizujPlansze() {
        for (int i = 0; i < statki.length; i++) {
            for (int j = 0; j < statki[i].length; j++) {
                if (statki[i][j] != null) {
                    plansza[i][j].setBackground(statki[i][j].getKolor());
                } else {
                    plansza[i][j].setBackground(Color.BLUE);
                }
            }
        }
    }

    @Override
    protected void zablokujPlansze() {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                for (MouseListener ml : plansza[i][j].getMouseListeners()) {
                    plansza[i][j].removeMouseListener(ml);
                }
            }
        }
    }

    @Override
    protected void odblokujPlansze() {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                int finalI = i;
                int finalJ = j;
                plansza[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (e.getSource() instanceof JButton clickedButton) {
                                zmienMiejsceStatku(clickedButton);
                            }
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            zapisanyStatek.pionowo = !zapisanyStatek.pionowo;
                            aktualizujPlansze();
                            JButton clickedButton = (JButton) e.getSource();
                            zmienKolorNaStatku(clickedButton);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        if (e.getSource() instanceof JButton enteredButton) {
                            if (wybieraniePola && czyMoznaUmiescicStatek(finalI, finalJ, zapisanyStatek.getDlugosc(), zapisanyStatek.pionowo)) {
                                aktualizujPlansze();
                                zmienKolorNaStatku(enteredButton);
                            }
                        }
                    }
                });
                plansza[i][j].setEnabled(true);
            }
        }
    }

    protected void aktualizujPole(int x, int y) {
        char litera = (char) ('A' + y);
        if (statki[x][y] != null) {
            trafioneStatkiPrzeciwnika++;
            plansza[x][y].setBackground(Color.BLACK);
            if (Objects.equals(statki[x][y].getRodzaj(), "Bombowiec")) bombowiec = (Bombowiec) statki[x][y];
            if (Objects.equals(statki[x][y].getRodzaj(), "Bombowiec") && statki[x][y].isTrafiony()) {
                reakcjaPoZatopieniuBombowca(x, y, statki[x][y].pionowo, bombowiec);
            }
            statki[x][y].trafiony();
            if (Objects.equals(statki[x][y].getRodzaj(), "Niszczyciel")) {
                niszczyciel = (Niszczyciel) statki[x][y];
            }
            if (Objects.equals(statki[x][y].getRodzaj(), "Pancernik") && !statki[x][y].isZatopiony()) {
                Przeciwnik.wyczyscWybranePole(new Point (x,y));
                plansza[x][y].setBackground(Color.ORANGE);
            }

            if (!Objects.equals(statki[x][y].getRodzaj(), "Pancernik")) plansza[x][y].setEnabled(false);
            else if (Objects.equals(statki[x][y].getRodzaj(), "Pancernik") && statki[x][y].isZatopiony()) plansza[x][y].setEnabled(false);

            ustawKomunikat("(" + (x + 1) + " - " + litera + (statki[x][y].isZatopiony() ? ") Zatopiony " : ") Trafiony ") + statki[x][y].getRodzaj() + "!");

            if (trafioneStatkiPrzeciwnika == 24){
                ustawKomunikat("Przeciwnik zatopił wszystkie statki!");
                trafioneStatkiPrzeciwnika = 0;
                PlanszaGracza.trafioneStatkiGracza = 0;
                wyswietlEkranKoncowy(false);
            }
        } else {
            plansza[x][y].setBackground(Color.DARK_GRAY);
            plansza[x][y].setEnabled(false);
            ustawKomunikat("(" + (x + 1) + " - " + litera + ") Pudło!");
        }
    }

    private void zmienKolorNaStatku(JButton button) {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                if (plansza[i][j] == button && zapisanyStatek != null && czyMoznaUmiescicStatek(i, j, zapisanyStatek.getDlugosc(), zapisanyStatek.pionowo)) {
                    for (int k = 0; k < zapisanyStatek.getDlugosc(); k++) {
                        if (zapisanyStatek.pionowo) plansza[i+k][j].setBackground(zapisanyStatek.getKolor());
                        else plansza[i][j+k].setBackground(zapisanyStatek.getKolor());
                    }
                    return;
                }
            }
        }
    }

    private void zmienMiejsceStatku(JButton button) {
        for (int i = 0; i < ROZMIAR_PLANSZY; i++) {
            for (int j = 0; j < ROZMIAR_PLANSZY; j++) {
                if (plansza[i][j] == button) {
                    if (!wybieraniePola && statki[i][j] != null) {
                        zapisanyStatek = statki[i][j];
                        ustawKomunikat("Zabrano " + statki[i][j].getRodzaj() + ".");
                        usunStatekZPlanszy(statki[i][j]);
                        wybieraniePola = true;
                    } else if (wybieraniePola && statki[i][j] == null){
                        umiescStatek(zapisanyStatek, i , j);
                    }
                    return;
                }
            }
        }
    }

    private void umiescStatek(Statek statek, int x, int y) {
        boolean umieszczony = false;
        if (czyMoznaUmiescicStatek(x, y, statek.getDlugosc(), statek.pionowo)) {
            umieszczony = true;
            umiescStatekNaPlanszy(x, y, statek.getDlugosc(), statek.pionowo, statek);
            ustawKomunikat("Umieszczono " + statek.getRodzaj() + ".");
            wybieraniePola = false;
        }

        if (!umieszczony) {
            ustawKomunikat(statek.getRodzaj() + " nie może być tutaj umieszczony.");
        } else aktualizujPlansze();
    }

    public boolean umiescStatekNaPoczatek(Statek statek) {
        int x, y;
        boolean umieszczony = false;

        for (x = 0; x < ROZMIAR_PLANSZY && !umieszczony; x++) {
            for (y = 0; y < ROZMIAR_PLANSZY && !umieszczony; y++) {
                if (czyMoznaUmiescicStatek(x, y, statek.getDlugosc(), true)) {
                    umieszczony = true;
                    umiescStatekNaPlanszy(x, y, statek.getDlugosc(), true, statek);
                }
                else if (czyMoznaUmiescicStatek(x, y, statek.getDlugosc(), false)) {
                    umieszczony = true;
                    statek.pionowo = false;
                    umiescStatekNaPlanszy(x, y, statek.getDlugosc(), false, statek);
                }
            }
        }
        if (!umieszczony) {
            JOptionPane.showMessageDialog(null, "Nie można umieścić statku na planszy.", "Zmień lokalizacje statków!", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            aktualizujPlansze();
            return true;
        }
    }

    protected void reakcjaPoZatopieniuBombowca(int i, int j, boolean pionowo, Bombowiec bombowiec) {
        for (int k = 0; k < Plansza.ROZMIAR_PLANSZY; k++) {
            wybuchBombowca(k, j, k != i);
            wybuchBombowca(i, k, k != j);
            if (!pionowo){
                if (j > 0 && statki[i][j-1] != bombowiec) wybuchBombowca(k, j+1, k != i);
                if (j < 9 && statki[i][j+1] != bombowiec) wybuchBombowca(k, j-1, k != i);

                if (j==0) wybuchBombowca(k, j+1, k != i);
                if (j==9) wybuchBombowca(k, j-1, k != i);
            } else {
                if (i > 0 && statki[i-1][j] != bombowiec) wybuchBombowca(i+1, k, k != j);
                if (i < 9 && statki[i+1][j] != bombowiec) wybuchBombowca(i-1, k, k != j);

                if (i==0) wybuchBombowca(i+1, k, k != j);
                if (i==9) wybuchBombowca(i-1, k, k != j);
            }
        }
    }

    private void wybuchBombowca(int x, int y, boolean warunek) {
        if (warunek && plansza[x][y].isEnabled()) {
            aktualizujPole(x, y);
            if (statki[x][y] == null) Przeciwnik.wybranePola.add(new Point(x, y));
            else if (!Objects.equals(statki[x][y].getRodzaj(), "Pancernik")) Przeciwnik.wybranePola.add(new Point(x, y));
            else if (Objects.equals(statki[x][y].getRodzaj(), "Pancernik") && statki[x][y].isTrafiony()) Przeciwnik.wybranePola.add(new Point(x, y));
        }
    }
}