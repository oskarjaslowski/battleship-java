import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Przeciwnik {
    protected final PlanszaPrzeciwnika planszaPrzeciwnika;
    protected static ArrayList<Point> wybranePola;
    protected Bombowiec bombowiec;
    public static boolean czyNiszczyciel;
    protected static Point ostatnieTrafionePole = new Point(-2,-2);
    protected static int szczescie;
    protected static int maxSzczescie;

    public Przeciwnik(PlanszaPrzeciwnika planszaPrzeciwnika) {
        this.planszaPrzeciwnika = planszaPrzeciwnika;
        wybranePola = new ArrayList<>();
        bombowiec = new Bombowiec();
        czyNiszczyciel = false;
        szczescie = 0;
        maxSzczescie = 5;
    }

    public void wykonajRuch() {
        if (PlanszaPrzeciwnika.niszczyciel.isTrafiony() && !PlanszaPrzeciwnika.niszczyciel.isZatopiony()) czyNiszczyciel = !czyNiszczyciel;
        if (PlanszaPrzeciwnika.niszczyciel.isZatopiony() || !PlanszaPrzeciwnika.niszczyciel.isTrafiony()) czyNiszczyciel = false;
        if (PlanszaPrzeciwnika.trafioneStatkiPrzeciwnika != 0 && !czyNiszczyciel) {
            Point wybranePole = wybierzPole();
            planszaPrzeciwnika.aktualizujPole(wybranePole.x, wybranePole.y);
        }
    }

    protected Point wybierzPole() {
        ArrayList<Point> nietrafionePola = bombowiec.znajdzNietrafionePola(planszaPrzeciwnika.statki);
        ArrayList<Point> potencjalnePola = new ArrayList<>();
        Random random = new Random();
        int x, y;
        int index;
        Point wybranePole;
        potencjalnePola.add(new Point(ostatnieTrafionePole.x - 1, ostatnieTrafionePole.y));
        potencjalnePola.add(new Point(ostatnieTrafionePole.x + 1, ostatnieTrafionePole.y));
        potencjalnePola.add(new Point(ostatnieTrafionePole.x, ostatnieTrafionePole.y - 1));
        potencjalnePola.add(new Point(ostatnieTrafionePole.x, ostatnieTrafionePole.y + 1));

        if (!wybranePola.contains(ostatnieTrafionePole) && ostatnieTrafionePole.x != -2) wybranePole = ostatnieTrafionePole;
        else if ((nietrafionePola.contains(potencjalnePola.get(0)) && !wybranePola.contains(potencjalnePola.get(0))) ||
                (nietrafionePola.contains(potencjalnePola.get(1)) && !wybranePola.contains(potencjalnePola.get(1))) ||
                (nietrafionePola.contains(potencjalnePola.get(2)) && !wybranePola.contains(potencjalnePola.get(2))) ||
                (nietrafionePola.contains(potencjalnePola.get(3)) && !wybranePola.contains(potencjalnePola.get(3)))) {
            if (potencjalnePola.get(3).y == 10) potencjalnePola.remove(3);
            if (potencjalnePola.get(2).y == -1) potencjalnePola.remove(2);
            if (potencjalnePola.get(1).x == 10) potencjalnePola.remove(1);
            if (potencjalnePola.get(0).x == -1) potencjalnePola.remove(0);
            do {
                index = random.nextInt(potencjalnePola.size());
                wybranePole = potencjalnePola.get(index);
            } while (czyPoleWybrane(wybranePole));
        }
        else if ((nietrafionePola.contains(potencjalnePola.get(0)) && wybranePola.contains(potencjalnePola.get(0)))) {
            do {
                potencjalnePola.get(0).x--;
                wybranePole = new Point(potencjalnePola.get(0).x, potencjalnePola.get(0).y);
            } while (czyPoleWybrane(wybranePole));
        }
        else if ((nietrafionePola.contains(potencjalnePola.get(1)) && wybranePola.contains(potencjalnePola.get(1)))) {
            do {
                potencjalnePola.get(1).x++;
                wybranePole = new Point(potencjalnePola.get(1).x, potencjalnePola.get(1).y);
            } while (czyPoleWybrane(wybranePole));
        }
        else if ((nietrafionePola.contains(potencjalnePola.get(2)) && wybranePola.contains(potencjalnePola.get(2)))) {
            do {
                potencjalnePola.get(2).y--;
                wybranePole = new Point(potencjalnePola.get(2).x, potencjalnePola.get(2).y);
            } while (czyPoleWybrane(wybranePole));
        }
        else if ((nietrafionePola.contains(potencjalnePola.get(3)) && wybranePola.contains(potencjalnePola.get(3)))) {
            do {
                potencjalnePola.get(3).y++;
                wybranePole = new Point(potencjalnePola.get(3).x, potencjalnePola.get(3).y);
            } while (czyPoleWybrane(wybranePole));
        }
        else if (!nietrafionePola.isEmpty() && szczescie == maxSzczescie){
            szczescie = 0;
            do {
                index = random.nextInt(nietrafionePola.size());
                wybranePole = nietrafionePola.get(index);
            } while (czyPoleWybrane(wybranePole));
        }
        else {
            szczescie++;
            do {
                x = random.nextInt(Plansza.ROZMIAR_PLANSZY);
                y = random.nextInt(Plansza.ROZMIAR_PLANSZY);
                wybranePole = new Point(x, y);

            } while (czyPoleWybrane(wybranePole));
        }

        if (nietrafionePola.contains(wybranePole)) {
            uzupelnijWybranePola(wybranePole);
        }

        wybranePola.add(wybranePole);
        return wybranePole;
    }

    protected void uzupelnijWybranePola(Point wybranePole) {
        ostatnieTrafionePole = wybranePole;
    }

    protected boolean czyPoleWybrane(Point point) {
        for (Point pole : wybranePola) {
            if (pole.equals(point)) {
                return true;
            }
        }
        return false;
    }

    public void wyczyscWybranePola() {
        wybranePola.clear();
    }
    public static void wyczyscWybranePole(Point poleDoWyczyszczenia) {
        wybranePola.remove(poleDoWyczyszczenia);
    }
}