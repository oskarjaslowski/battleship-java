import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
public class Bombowiec extends Dwumasztowiec {
    public Bombowiec() {
        super();
        setKolor(Color.MAGENTA);
    }
    public ArrayList<Point> znajdzNietrafionePola(Statek[][] statki) {
        ArrayList<Point> nietrafionePola = new ArrayList<>();
        for (int i = 0; i < statki.length; i++) {
            for (int j = 0; j < statki[i].length; j++) {
                if (statki[i][j] != null && !statki[i][j].isZatopiony()) {
                    nietrafionePola.add(new Point(i, j));
                }
            }
        }
        return nietrafionePola;
    }
    @Override
    public String getRodzaj() {
        return "Bombowiec";
    }
}